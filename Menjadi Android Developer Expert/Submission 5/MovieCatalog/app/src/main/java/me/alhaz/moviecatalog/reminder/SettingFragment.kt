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
        if (alarmReceiver.isAlarmSet(fragmentActivity)) {
            sw_daily_reminder.setChecked(true)
        }
        else {
            sw_daily_reminder.setChecked(false)
        }
        sw_daily_reminder.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                if (alarmReceiver.isAlarmSet(fragmentActivity)) {
                    alarmReceiver.cancelAlarm(fragmentActivity)
                }
                alarmReceiver.setRepeatingAlarm(fragmentActivity, "Hai, udah cek aplikasi Movie Catalogue belum?")
            }
            else {
                if (alarmReceiver.isAlarmSet(fragmentActivity)) {
                    alarmReceiver.cancelAlarm(fragmentActivity)
                }
            }
        }

    }


}
