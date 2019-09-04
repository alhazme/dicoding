package me.alhaz.moviecatalog.reminder

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.core.widget.CompoundButtonCompat
import androidx.fragment.app.FragmentActivity
import kotlinx.android.synthetic.main.fragment_setting.*
import me.alhaz.moviecatalog.R

class SettingFragment : Fragment() {

    private var alarmReceiver = AlarmReceiver()

    private val DAILY_REMINDER = 100
    private val DAILY_REMINDER_TIME = "07:00"
    private val RELEASE_REMINDER = 101
    private val RELEASE_REMINDER_TIME = "08:00"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let { fragmentActivity ->
            setupLayout(fragmentActivity)
        }
    }

    fun setupLayout(fragmentActivity: FragmentActivity) {

        // Daily Reminder

        if (alarmReceiver.isAlarmSet(fragmentActivity, DAILY_REMINDER)) {
            sw_daily_reminder.setChecked(true)
        }
        else {
            sw_daily_reminder.setChecked(false)
        }
        sw_daily_reminder.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                if (alarmReceiver.isAlarmSet(fragmentActivity, DAILY_REMINDER)) {
                    alarmReceiver.cancelAlarm(fragmentActivity, DAILY_REMINDER)
                }
                alarmReceiver.setRepeatingAlarm(
                    fragmentActivity,
                    DAILY_REMINDER,
                    fragmentActivity.resources.getString(R.string.reminder_daily_message),
                    DAILY_REMINDER_TIME
                )
            }
            else {
                if (alarmReceiver.isAlarmSet(fragmentActivity, DAILY_REMINDER)) {
                    alarmReceiver.cancelAlarm(fragmentActivity, DAILY_REMINDER)
                }
            }
        }

        // Release Reminder

        if (alarmReceiver.isAlarmSet(fragmentActivity, RELEASE_REMINDER)) {
            sw_release_reminder.setChecked(true)
        }
        else {
            sw_release_reminder.setChecked(false)
        }
        sw_release_reminder.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                if (alarmReceiver.isAlarmSet(fragmentActivity, RELEASE_REMINDER)) {
                    alarmReceiver.cancelAlarm(fragmentActivity, RELEASE_REMINDER)
                }
                alarmReceiver.setRepeatingAlarm(
                    fragmentActivity,
                    RELEASE_REMINDER,
                    "New movie " + fragmentActivity.resources.getString(R.string.reminder_release_message),
                    RELEASE_REMINDER_TIME
                )
            }
            else {
                if (alarmReceiver.isAlarmSet(fragmentActivity, RELEASE_REMINDER)) {
                    alarmReceiver.cancelAlarm(fragmentActivity, RELEASE_REMINDER)
                }
            }
        }

    }


}
