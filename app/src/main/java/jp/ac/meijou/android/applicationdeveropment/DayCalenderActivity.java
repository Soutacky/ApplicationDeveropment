package jp.ac.meijou.android.applicationdeveropment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import jp.ac.meijou.android.applicationdeveropment.databinding.ActivityDayCalenderBinding;

public class DayCalenderActivity extends AppCompatActivity {

    public static final String EXTRA_SCHEDULE = "extra_schedule";
    private ActivityDayCalenderBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDayCalenderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String existingSchedule = getIntent().getStringExtra(EXTRA_SCHEDULE);
        if (existingSchedule != null) {
            binding.editTextSchedule.setText(existingSchedule);
        }

        //読み込み
        SharedPreferences preferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        int selectedYear = preferences.getInt("selectedYear", 0);
        int selectedMonth = preferences.getInt("selectedMonth", 0);
        int selectedDay = preferences.getInt("selectedDay", 0);
        String stringYear = Integer.valueOf(selectedYear).toString();
        String stringMonth = Integer.valueOf(selectedMonth).toString();
        String stringDay = Integer.valueOf(selectedDay).toString();

        int startHour = preferences.getInt("StartHour"+ stringYear + stringMonth + stringDay,0);
        int startMinute = preferences.getInt("StartMinute"+ stringYear +stringMonth + stringDay,0);
        int endHour = preferences.getInt("EndHour"+ stringYear + stringMonth + stringDay,0);
        int endMinute = preferences.getInt("EndMinute"+ stringYear +stringMonth + stringDay,0);
        String other = preferences.getString("other"+ stringYear +stringMonth + stringDay,"");

        binding.editNumberStartHour.setText(String.valueOf(startHour));
        binding.editNumberStartMinute.setText(String.valueOf(startMinute));
        binding.editNumberEndHour.setText(String.valueOf(endHour));
        binding.editNumberEndMinute.setText(String.valueOf(endMinute));
        binding.editTextOther.setText(other);

        binding.buttonSave.setOnClickListener(view -> saveSchedule());
    }

    private void saveSchedule() {
        String editedSchedule = binding.editTextSchedule.getText().toString();

        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCHEDULE, editedSchedule);
        setResult(RESULT_OK, resultIntent);
        //保存
        SharedPreferences preferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        int year = preferences.getInt("selectedYear", 0);
        int month = preferences.getInt("selectedMonth", 0);
        int day = preferences.getInt("selectedDay", 0);
        int startHour = Integer.parseInt(binding.editNumberStartHour.getText().toString());
        int startMinute = Integer.parseInt(binding.editNumberStartMinute.getText().toString());
        int endHour = Integer.parseInt(binding.editNumberEndHour.getText().toString());
        int endMinute = Integer.parseInt(binding.editNumberEndMinute.getText().toString());
        String other = binding.editTextOther.getText().toString();
        editor.putInt("StartHour" + Integer.valueOf(year).toString() + Integer.valueOf(month).toString() + Integer.valueOf(day).toString(), startHour);
        editor.putInt("StartMinute" + Integer.valueOf(year).toString() + Integer.valueOf(month).toString() + Integer.valueOf(day).toString(), startMinute);
        editor.putInt("EndHour" + Integer.valueOf(year).toString() + Integer.valueOf(month).toString() + Integer.valueOf(day).toString(), endHour);
        editor.putInt("EndMinute" + Integer.valueOf(year).toString() + Integer.valueOf(month).toString() + Integer.valueOf(day).toString(), endMinute);
        editor.putString("other" + Integer.valueOf(year).toString() + Integer.valueOf(month).toString() + Integer.valueOf(day).toString(), other);
        editor.apply();
        finish();
    }
}