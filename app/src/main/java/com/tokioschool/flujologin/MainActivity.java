package com.tokioschool.flujologin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tokioschool.flujologin.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private TextView CreateText;
    private Bundle datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  Inicializa ViewBinding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Referencia al TextView
        CreateText =binding.loginCreateText;

        // Agrego un OnClickListener al TextView
        CreateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creo un Intent para iniciar la nueva actividad
                Intent NewAccount = new Intent(MainActivity.this,Registro.class);
                startActivity(NewAccount);

                Bundle bundle = getIntent().getExtras();



            }
        });


    }








}
