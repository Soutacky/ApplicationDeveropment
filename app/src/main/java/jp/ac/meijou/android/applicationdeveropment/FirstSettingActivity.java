package jp.ac.meijou.android.applicationdeveropment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
            //データの保存
            SharedPreferences preferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("name", binding.firstNameEdit.getText().toString());
            editor.putString("age", binding.firstAgeEdit.getText().toString());
            editor.apply();

            var intent = new Intent(this, FirstMonsterSettingActivity.class);
            startActivity(intent);
            finish();
        });

        //データの引き出し
//        SharedPreferences preferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
//        String name = preferences.getString("name", "error");
//        String age = preferences.getString("age", "error");
//        binding.firstNameEdit.setText(name);
//        binding.firstAgeEdit.setText(age);

    }
}