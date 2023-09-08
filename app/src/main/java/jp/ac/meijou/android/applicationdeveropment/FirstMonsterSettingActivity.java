package jp.ac.meijou.android.applicationdeveropment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import jp.ac.meijou.android.applicationdeveropment.databinding.ActivityFirstMonsterSettingBinding;

public class FirstMonsterSettingActivity extends AppCompatActivity {
    private ActivityFirstMonsterSettingBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFirstMonsterSettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.okButton.setOnClickListener(view -> {
            var intent = new Intent(this, TopActivity.class);
            intent.putExtra("text", binding.monsterNameEdit.getText().toString());
            startActivity(intent);
            finish();
        });
    }
}