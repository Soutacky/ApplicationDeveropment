package jp.ac.meijou.android.applicationdeveropment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import jp.ac.meijou.android.applicationdeveropment.databinding.ActivityFirstMonsterSettingBinding;

public class FirstMonsterSettingActivity extends AppCompatActivity {
    private ActivityFirstMonsterSettingBinding binding;
    private int currentImage = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFirstMonsterSettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.okButton.setOnClickListener(view -> {
            //データの保存
            SharedPreferences preferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("monsterName", binding.monsterNameEdit.getText().toString());
            editor.putInt("currentImage", currentImage);
            editor.apply();

            var intent = new Intent(this, TopActivity.class);
            intent.putExtra("text", binding.monsterNameEdit.getText().toString());
            startActivity(intent);
            finish();
        });

        binding.buttonLeft.setOnClickListener(view -> {
            if (currentImage == 1) {
                binding.monsterView.setImageResource(R.drawable.ic_launcher_foreground);
                currentImage = 3;
            } else if (currentImage == 2) {
                binding.monsterView.setImageResource(R.drawable.ic_android_green);
                currentImage = 1;
            } else {
                binding.monsterView.setImageResource(R.drawable.ic_android);
                currentImage = 2;
            }
        });

        binding.buttonRight.setOnClickListener(view -> {
            if (currentImage == 1) {
                binding.monsterView.setImageResource(R.drawable.ic_android);
                currentImage = 2;
            } else if (currentImage == 2) {
                binding.monsterView.setImageResource(R.drawable.ic_launcher_foreground);
                currentImage = 3;
            } else {
                binding.monsterView.setImageResource(R.drawable.ic_android_green);
                currentImage = 1;
            }
        });
    }
}