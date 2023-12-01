package jp.ac.meijou.android.applicationdeveropment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import jp.ac.meijou.android.applicationdeveropment.databinding.ActivityTopBinding;


public class TopActivity extends AppCompatActivity {

    private ActivityTopBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTopBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.dateView.setText(getCurrentDate());


        binding.imageButton3.setOnClickListener(view -> {
            var intent = new Intent(this, SettingActivity.class);
            startActivity(intent);
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

        //データの引き出し
        SharedPreferences preferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String name = preferences.getString("name", "error");
        String monsterName = preferences.getString("monsterName", "error");
        int currentImage = preferences.getInt("currentImage", 1);
        binding.playerNameView.setText(name);
        binding.monsterNameView.setText(monsterName);

        if (currentImage == 1) {
            binding.monsterImageView.setImageResource(R.drawable.ic_android_green);
        } else if (currentImage == 2) {
            binding.monsterImageView.setImageResource(R.drawable.monster1);
        } else {
            binding.monsterImageView.setImageResource(R.drawable.ic_launcher_foreground);
        }
    }
    public static String getCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd");
        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
        String[] japaneseDayOfWeek = {
                "日", "月", "火", "水", "木", "金", "土"
        };

        String yobi = japaneseDayOfWeek[dayOfWeek.getValue() - 1];
        return currentDate.format(formatter) + "(" + yobi + ")";
    }
}