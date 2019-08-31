package me.alhaz.moviecatalog.reminder

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import android.app.AlarmManager
import android.app.PendingIntent
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import android.app.NotificationManager
import android.app.NotificationChannel
import android.os.Build
import androidx.core.content.ContextCompat
import android.media.RingtoneManager
import androidx.core.app.NotificationCompat
import me.alhaz.moviecatalog.R


class AlarmReceiver: BroadcastReceiver() {

    val repeatingTime = "07:00"
    val requestCode = 101

    override fun onReceive(context: Context?, intent: Intent?) {

        context?.let { context ->
            intent?.let { intent ->
                val message = intent.getStringExtra("message")
                showAlarmNotification(context, context.resources.getString(R.string.reminder_daily_title), message, requestCode)
            }
        }
    }

    fun cancelAlarm(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)

        val pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0)
        pendingIntent.cancel()

        if (alarmManager != null) {
            alarmManager.cancel(pendingIntent)
        }

        Toast.makeText(context, context.resources.getString(R.string.reminder_deactived), Toast.LENGTH_SHORT).show();
    }

    fun isAlarmSet(context: Context) : Boolean {
        val intent = Intent(context, AlarmReceiver::class.java)
        return (PendingIntent.getBroadcast(context, requestCode, intent ,PendingIntent.FLAG_NO_CREATE) != null)
    }

    fun setRepeatingAlarm(context: Context, message: String) {

        // Validasi inputan waktu terlebih dahulu
        if (isDateInvalid(repeatingTime, "HH:mm")) return

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        intent.putExtra("message", message)
        intent.putExtra("type", "RepeatingAlarm")

        val timeArray = repeatingTime.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]))
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]))
        calendar.set(Calendar.SECOND, 0)

        val pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0)
        alarmManager?.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.getTimeInMillis(),
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )

        Toast.makeText(context, context.resources.getString(R.string.reminder_daily_actived), Toast.LENGTH_SHORT).show()
    }

    fun isDateInvalid(date: String, format: String): Boolean {
        try {
            val df = SimpleDateFormat(format, Locale.getDefault())
            df.setLenient(false)
            df.parse(date)
            return false
        } catch (e: ParseException) {
            return true
        }

    }

    private fun showAlarmNotification(context: Context, title: String, message: String, notifId: Int) {

        val CHANNEL_ID = "Channel_1"
        val CHANNEL_NAME = "AlarmManager channel"

        val notificationManagerCompat = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_movies)
            .setContentTitle(title)
            .setContentText(message)
            .setColor(ContextCompat.getColor(context, android.R.color.transparent))
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setSound(alarmSound)

        /*
        Untuk android Oreo ke atas perlu menambahkan notification channel
        Materi ini akan dibahas lebih lanjut di modul extended
         */

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            /* Create or update. */
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )

            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(1000, 1000, 1000, 1000, 1000)

            builder.setChannelId(CHANNEL_ID)

            notificationManagerCompat?.createNotificationChannel(channel)
        }

        val notification = builder.build()

        notificationManagerCompat?.notify(notifId, notification)

    }


}