package jp.ac.meijou.android.applicationdeveropment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import jp.ac.meijou.android.applicationdeveropment.databinding.ActivitySettingBinding;

public class SettingActivity extends AppCompatActivity {
    private ActivitySettingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.okButton.setOnClickListener(view -> {
            //データの保存
            SharedPreferences preferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("name", binding.nameEdit.getText().toString());
            editor.putString("age", binding.ageEdit.getText().toString());
            editor.apply();

            var intent = new Intent(this, TopActivity.class);
            startActivity(intent);
            finish();
        });

        binding.cancelButton.setOnClickListener(view -> {
            var intent = new Intent(this, TopActivity.class);
            startActivity(intent);
            finish();
        });

        //データの引き出し
        SharedPreferences preferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String name = preferences.getString("name", "error");
        String age = preferences.getString("age", "error");

        binding.nameEdit.setText(name);
        binding.ageEdit.setText(age);
    }
}