package jp.ac.meijou.android.applicationdeveropment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import jp.ac.meijou.android.applicationdeveropment.databinding.ActivityCalenderBinding;

public class CalenderActivity extends AppCompatActivity {
    private ActivityCalenderBinding binding;
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

        binding.Sunday1.setOnClickListener(view -> {
            var intent = new Intent(this, DayCalenderActivity.class);
            startActivity(intent);
        });
    }
}