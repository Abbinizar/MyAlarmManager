package com.example.abbinizar.myalarmmanager;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.SimpleTimeZone;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvOneTimeDate, tvOneTimeTime, tvRepeatingTime;
    private EditText edOnetimeMessage, edRepeatingMessage;
    private Button btnOneTimeDate, btnOneTimeTime, btnOneTime ,btnRepeatingTime ,btnRepeating,  btnCancelRepeatingAlarm;

    private Calendar calOnetimeDate, calOnetimeTime, calRepeatTime;
    private AlarmReceiver alarmReceiver;
    private AlarmPreference alarmPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvOneTimeDate = (TextView)findViewById(R.id.tv_one_time_alarm_date);
        tvOneTimeTime = (TextView)findViewById(R.id.tv_one_time_alarm_time);
        edOnetimeMessage = (EditText)findViewById(R.id.edt_one_time_alarm_message);
        btnOneTimeDate = (Button)findViewById(R.id.btn_one_time_alarm_date);
        btnOneTimeTime = (Button)findViewById(R.id.btn_one_time_alarm_time);
        btnOneTime = (Button)findViewById(R.id.btn_set_one_time_alarm);
        tvRepeatingTime = (TextView)findViewById(R.id.tv_repeating_alarm_time);
        edRepeatingMessage = (EditText)findViewById(R.id.ed_repeating_alarm_message);
        btnRepeatingTime = (Button)findViewById(R.id.btn_repeating_time_alarm_time);
        btnRepeating = (Button)findViewById(R.id.btn_repeating_time_alarm);
        btnCancelRepeatingAlarm = (Button)findViewById(R.id.btn_cancel_repeating_alarm);
        btnOneTimeDate.setOnClickListener((View.OnClickListener) this);
        btnOneTimeTime.setOnClickListener((View.OnClickListener) this);
        btnOneTime.setOnClickListener(this);
        btnRepeatingTime.setOnClickListener(this);
        btnRepeating.setOnClickListener(this);
        btnCancelRepeatingAlarm.setOnClickListener(this);
        calOnetimeDate = Calendar.getInstance();
        calOnetimeTime = Calendar.getInstance();
        calRepeatTime = Calendar.getInstance();
        alarmPreference = new AlarmPreference(this);
        alarmReceiver = new AlarmReceiver();
        if (!TextUtils.isEmpty(alarmPreference.getOntimeDate())){
            setOneTimeText();
        }

    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_one_time_alarm_date){
            final Calendar currentDate = Calendar.getInstance();
            new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    calOnetimeDate.set(year, dayOfMonth, dayOfMonth);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd");
                    tvOneTimeDate.setText(dateFormat.format(calOnetimeDate.getTime()));
                }
            }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
        }
        else if (v.getId() == R.id.btn_one_time_alarm_time){
            final Calendar currentTime = Calendar.getInstance();
            new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    calOnetimeTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    calOnetimeTime.set(Calendar.MINUTE, minute);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
                    tvOneTimeTime.setText(dateFormat.format(calOnetimeTime.getTime()));
                    //Log.v(TAG, "The choosen one " + date.getTime());
                }
            },currentTime.get(Calendar.HOUR_OF_DAY), currentTime.get(Calendar.MINUTE), true).show();
        }
        else if (v.getId()== R.id.btn_set_one_time_alarm){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd");
            String oneTimeDate = dateFormat.format(calOnetimeDate.getTime());
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            String oneTimeTime = dateFormat.format(calOnetimeTime.getTime());
            String oneTimeMessage = edOnetimeMessage.getText().toString();

            alarmPreference.setOntimeDate(oneTimeDate);
            alarmPreference.setOntimeTime(oneTimeTime);
            alarmPreference.setOntimeMessage(oneTimeMessage);

            alarmReceiver.setOneTimeAlarm(this, AlarmReceiver.TYPE_ONE_TIME,
                    alarmPreference.getOnetimeTime(),
                    alarmPreference.getOntimeDate(),
                    alarmPreference.getOntimeMessage());
        }
    }
    private void setOneTimeText() {
        tvOneTimeDate.setText(alarmPreference.getOntimeDate());
        tvOneTimeTime.setText(alarmPreference.getOnetimeTime());
        tvRepeatingTime.setText(alarmPreference.getRepeatingTime());
    }
}
