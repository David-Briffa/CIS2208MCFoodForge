package com.example.cis2208mcfoodforge.ui.timer;

import static android.content.Context.ALARM_SERVICE;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cis2208mcfoodforge.R;
import com.example.cis2208mcfoodforge.databinding.FragmentTimerBinding;

import java.util.Calendar;
import java.util.Objects;

public class TimerFragment extends Fragment {
    private TimePicker alarmTimePicker;
    private AlarmManager alarmManager;
    private FragmentTimerBinding binding;
    private PendingIntent pendingIntent = null;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentTimerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //Binding timepicker with our fragment
        alarmTimePicker = root.findViewById(R.id.timer);
        alarmManager = (AlarmManager) requireContext().getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(getContext(), TimerReceiver.class);

        //Pending intent is used since the alarm will be set for the future
        //the intent will hence be triggered when the alarm reaches its destination
        pendingIntent = PendingIntent.getBroadcast(getContext(), 1001, intent, PendingIntent.FLAG_IMMUTABLE);

        //Changes alarm state
        ToggleButton toggleButton = root.findViewById(R.id.toggle);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pendingIntent != null) {
                    setAlarm(v);
                }
            }
        });
        return root;
    }

    public void setAlarm(View view) {
        boolean checked = ((ToggleButton) view).isChecked();
        if (checked) {
            Toast.makeText(getContext(), "Alarm is on, relax.", Toast.LENGTH_SHORT).show();

            //getting the values for the timer from the picker
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getHour());
            calendar.set(Calendar.MINUTE, alarmTimePicker.getMinute()-1); //10 seconds before actual time for some reason

            //checking if the timer is set for morning of evening, boilerplate code.
            long time = (calendar.getTimeInMillis() - (calendar.getTimeInMillis() % 60000));
            if (System.currentTimeMillis() > time) {
                if (Calendar.AM_PM == 0)
                    time = time + (1000 * 60 * 60 * 12);
                else
                    time = time + (1000 * 60 * 60 * 24);
            }
            //annoying beeping every few seconds
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, 60000, pendingIntent);
        } else {
            //cancel timer on off toggle
            if (pendingIntent != null) {
                alarmManager.cancel(pendingIntent);
                Toast.makeText(getContext(), "Alarm is now off", Toast.LENGTH_SHORT).show();
                pendingIntent = null;
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}