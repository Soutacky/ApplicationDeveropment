package jp.ac.meijou.android.applicationdeveropment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


import java.util.Optional;

import jp.ac.meijou.android.applicationdeveropment.databinding.ActivityTopBinding;


public class TopActivity extends AppCompatActivity {

    private ActivityTopBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTopBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        Optional.ofNullable(getIntent().getStringExtra("text"))
//                .ifPresent(text -> binding.playerNameView.setText(text));

        binding.imageButton3.setOnClickListener(view -> {
            var intent = new Intent(this, SettingActivity.class);
            startActivity(intent);
        });

        binding.mealButton.setOnClickListener(view -> {
            var intent = new Intent(this, CalenderActivity.class);
            startActivity(intent);
        });

        binding.sleepButton.setOnClickListener(view -> {
            var intent = new Intent(this, CalenderActivity.class);
            startActivity(intent);
        });

        binding.exerciseButton.setOnClickListener(view -> {
            var intent = new Intent(this, CalenderActivity.class);
            startActivity(intent);
        });

        //データの引き出し
        SharedPreferences preferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String name = preferences.getString("name", "error");
        String monsterName = preferences.getString("monsterName", "error");
        binding.playerNameView.setText(name);
        binding.monsterNameView.setText(monsterName);
    }
}