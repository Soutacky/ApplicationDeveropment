package jp.ac.meijou.android.applicationdeveropment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import jp.ac.meijou.android.applicationdeveropment.databinding.ActivityTopBinding;


public class TopActivity extends AppCompatActivity {

    private ActivityTopBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTopBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}