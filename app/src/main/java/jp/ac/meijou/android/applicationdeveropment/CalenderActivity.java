package jp.ac.meijou.android.applicationdeveropment;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import jp.ac.meijou.android.applicationdeveropment.databinding.ActivityCalenderBinding;

public class CalenderActivity extends AppCompatActivity {
    private ActivityCalenderBinding binding;
    private ScheduleAdapter scheduleAdapter;
    private static final int REQUEST_CODE_EDIT_SCHEDULE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCalenderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.homeButton.setOnClickListener(view -> {
            var intent = new Intent(this, TopActivity.class);
            startActivity(intent);
            finish();
        });

        binding.mealButton.setOnClickListener(view -> {
            var intent = new Intent(this, CalenderActivity.class);
            startActivity(intent);
            finish();
        });

        binding.sleepButton.setOnClickListener(view -> {
            var intent = new Intent(this, CalenderActivity.class);
            startActivity(intent);
            finish();
        });

        binding.exerciseButton.setOnClickListener(view -> {
            var intent = new Intent(this, CalenderActivity.class);
            startActivity(intent);
            finish();
        });

        List<String> scheduleList = new ArrayList<>();
        scheduleAdapter = new ScheduleAdapter(scheduleList, new ScheduleAdapter.OnScheduleClickListener() {
            @Override
            public void onScheduleClick(String schedule) {
                editSchedule(schedule);
            }
        });
        binding.scheduleRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.scheduleRecyclerView.setAdapter(scheduleAdapter);

        binding.calendarView.setOnDateChangeListener((calendarView, year, month, day) -> {
            //保存
            SharedPreferences preferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("selectedYear", year);
            editor.putInt("selectedMonth", month);
            editor.putInt("selectedDay", day);
            editor.apply();

            List<String> schedulesForSelectedDay = getSelectedSchedules();
            updateScheduleList(schedulesForSelectedDay);
        });
    }

    private List<String> getSelectedSchedules() {
        List<String> schedules = new ArrayList<>();
        //読み込み
        SharedPreferences preferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
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
        String stringStartHour = Integer.valueOf(startHour).toString();
        String stringStartMinute = Integer.valueOf(startMinute).toString();
        String stringEndHour = Integer.valueOf(endHour).toString();
        String stringEndMinute = Integer.valueOf(endMinute).toString();
        String other = preferences.getString("other"+ stringYear +stringMonth + stringDay,"");

        schedules.add("朝食\n"+
                stringStartHour+"時"+stringStartMinute+"分"+
                "～"+
                stringEndHour+"時"+stringEndMinute+"分\n\n"+
                "備考\n"+
                other);
        schedules.add("昼食"+
                stringStartHour+"時"+stringStartMinute+"分"+
                "～"+
                stringEndHour+"時"+stringEndMinute+"分\n\n"+
                "備考\n"+
                other);
        schedules.add("夕食"+
                stringStartHour+"時"+stringStartMinute+"分"+
                "～"+
                stringEndHour+"時"+stringEndMinute+"分\n\n"+
                "備考\n"+
                other);
        return schedules;
    }

    private void updateScheduleList(List<String> schedules) {
        scheduleAdapter.updateData(schedules);
    }

    private ActivityResultLauncher<Intent> editScheduleLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        String editedSchedule = data.getStringExtra(DayCalenderActivity.EXTRA_SCHEDULE);
                        List<String> updatedSchedules = getSelectedSchedules();
                        updateScheduleList(updatedSchedules);
                    }
                }
            }
    );

    // editSchedule メソッドの呼び出し
    private void editSchedule(String schedule) {
        Intent editIntent = new Intent(this, DayCalenderActivity.class);
        editIntent.putExtra(DayCalenderActivity.EXTRA_SCHEDULE, schedule);
        editScheduleLauncher.launch(editIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_EDIT_SCHEDULE && resultCode == RESULT_OK && data != null) {
            String editedSchedule = data.getStringExtra(DayCalenderActivity.EXTRA_SCHEDULE);
            // 保存が完了したら、ここでデータを更新などの処理を行う
            // 仮のデータ更新
            List<String> updatedSchedules = getSelectedSchedules();
            updateScheduleList(updatedSchedules);
        }
    }
}