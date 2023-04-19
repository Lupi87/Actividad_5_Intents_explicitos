package com.tokioschool.flujologin;

import static com.tokioschool.flujologin.Registro.KEY_USER;

import android.os.Bundle;
import android.util.Log;

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

        //Recibimos el username del intent recibido
        String username = getIntent().getStringExtra(KEY_USER);

        Log.d("Home", "Username recibido: " + username);
    }

}