package com.tokioschool.flujologin;

import static com.tokioschool.flujologin.Registro.KEY_PASSWORD;
import static com.tokioschool.flujologin.Registro.KEY_USER;
import static com.tokioschool.flujologin.Registro.KEY_USERNAME;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tokioschool.flujologin.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private String registeredUsername = "";
    private String registeredPassword = "";

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  Inicializa ViewBinding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.loginCreateText.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Registro.class);
            //Abrimos Registro esperando un resultado con código 666
            startActivityForResult(intent, 666);
        });

        //
        binding.loginBtnLogin.setOnClickListener(view -> {
            //Obtener valores de los EditTexts
            String username = binding.loginInputLayoutUsernameText.getText().toString();
            String password = binding.loginPasswordInputText.getText().toString();

            //Validar campos de entrada
            if (TextUtils.isEmpty(registeredUsername) || TextUtils.isEmpty(registeredPassword)) {
                Toast.makeText(MainActivity.this, getString(R.string.error3), Toast.LENGTH_SHORT).show();
                return;
            }


            //Validar credenciales del usuario
            if (username.equals(registeredUsername) && password.equals(registeredPassword)) {

                Intent home = new Intent(MainActivity.this, Home.class);
                home.putExtra(KEY_USER, username);
                startActivity(home);

            } else {
                Toast.makeText(MainActivity.this, getString(R.string.error5), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 666 && null != data) {
            //Si el resultado con código 666 ha ido bien nos guardamos username y password y habilitamos el botón de login
            registeredUsername = data.getStringExtra(KEY_USERNAME);
            registeredPassword = data.getStringExtra(KEY_PASSWORD);
            binding.loginBtnLogin.setEnabled(true);
        }
    }
}
