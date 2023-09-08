package jp.ac.meijou.android.applicationdeveropment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import jp.ac.meijou.android.applicationdeveropment.databinding.ActivityFirstSettingBinding;

public class FirstSettingActivity extends AppCompatActivity {
    private ActivityFirstSettingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFirstSettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.okButton.setOnClickListener(view -> {
            var intent = new Intent(this, FirstMonsterSettingActivity.class);
            intent.putExtra("text", binding.firstNameEdit.getText().toString());
            startActivity(intent);
            finish();
        });
    }
}