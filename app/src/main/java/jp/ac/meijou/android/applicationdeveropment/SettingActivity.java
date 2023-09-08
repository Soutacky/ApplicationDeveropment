package jp.ac.meijou.android.applicationdeveropment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
            var intent = new Intent(this, TopActivity.class);
            startActivity(intent);
            finish();
        });

        binding.cancelButton.setOnClickListener(view -> {
            var intent = new Intent(this, TopActivity.class);
            startActivity(intent);
            finish();
        });
    }
}