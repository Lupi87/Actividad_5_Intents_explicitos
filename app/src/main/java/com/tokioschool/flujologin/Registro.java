package com.tokioschool.flujologin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.tokioschool.flujologin.databinding.RegistroBinding;

import java.util.ArrayList;
import java.util.List;

public class Registro extends AppCompatActivity {
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";

    private RegistroBinding binding;
    private TextInputLayout exposedDropdownLayout;
    private ImageView imagenCamara;
    private TextView linkTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  Inicializa ViewBinding
        binding = RegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        exposedDropdownLayout = binding.registroExposedDropdownLayout;
        imagenCamara = binding.registroImagenCamara;
        linkTextView = binding.registroCondicionesPrivacidad;

        //Llamada a la función de la camara al pulsar la imagenCamara
        imagenCamara.setOnClickListener(view -> openCamara());

        //aqui si el usuario escribe "@" o "!" le mostrara un texto de error
        binding.registroInputLayoutTextName.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {
                if (s.toString().contains("@") || s.toString().contains("!")) {
                    binding.registroInputLayoutTextName.setError(getString(R.string.error4));
                } else {
                    binding.registroInputLayoutTextName.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable Editable) {

            }

        });

        binding.registroInputLayoutLastName.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {
                if (s.toString().contains("@") || s.toString().contains("!")) {
                    binding.registroInputLayoutLastName.setError(getString(R.string.error4));
                } else {
                    binding.registroInputLayoutLastName.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //exposed Dropdown
        List<String> edad = new ArrayList<>();
        edad.add("0-5");
        edad.add("6-11");
        edad.add("12-17");
        edad.add("18-99");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, edad);

        binding.exposedDropdown.setAdapter(adapter);

        //error si selecciona menor 18

        binding.exposedDropdown.setOnItemClickListener((adapterView, view, position, id) -> {
            String selectedItem = adapterView.getItemAtPosition(position).toString();
            if (selectedItem.equals("0-5") || selectedItem.equals("6-11") || selectedItem.equals("12-17")) {
                exposedDropdownLayout.setError(getString(R.string.error2));
            } else {
                exposedDropdownLayout.setError(null);
            }
        });

        //politica de privacidad

        linkTextView.setOnClickListener(view -> openUrl("https://developers.google.com/ml-kit/terms"));

        //btn registro y pasar datos a activity de logg-in (MainActivity)
        binding.registroBtn.setOnClickListener(v -> {
            //Obtener valores de los EditTexts
            String username = binding.registroInputTextName.getText().toString();
            String password = binding.registroInputTextLastName.getText().toString();

            //Validar campos de entrada
            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                Toast.makeText(Registro.this, getString(R.string.error3), Toast.LENGTH_SHORT).show();
                return;
            }

            Intent pasarDatosRegristro = new Intent();
            pasarDatosRegristro.putExtra(KEY_USERNAME, username);
            pasarDatosRegristro.putExtra(KEY_PASSWORD, password);
            //Guardamos el resultado de la activity, con username y password para que se devuelvan al hacer finish
            setResult(RESULT_OK, pasarDatosRegristro);
            finish();
        });

    }

    //función politica privacidad
    private void openUrl(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    //función camara
    private static final int REQUEST_iMAGE_CAPTURE = 1;

    private void openCamara() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) ;
        startActivityForResult(takePictureIntent, REQUEST_iMAGE_CAPTURE);
    }


    public static final String KEY_USER = "usuario";


}
