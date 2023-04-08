package com.tokioschool.flujologin;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.tokioschool.flujologin.databinding.HomeBinding;

public class Home extends AppCompatActivity {


    private HomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  Inicializa ViewBinding
        binding = HomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }

}