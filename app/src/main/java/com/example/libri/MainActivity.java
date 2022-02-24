package com.example.libri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import database.SQLHelper;
import helpers.Login;

public class MainActivity extends AppCompatActivity {

    private EditText txtLogin;
    private EditText txtSenha;
    private Button btnEntrar;
    private Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLogin = findViewById(R.id.txtLogin);
        txtSenha = findViewById(R.id.txtSenha);
        btnEntrar = findViewById(R.id.btnLogar);
        btnCadastrar = findViewById(R.id.btnCadastrarUsuario);

        btnCadastrar.setOnClickListener(view -> {
            Intent telaCadastro = new Intent(MainActivity.this, CadastroUsuario.class);
            startActivity(telaCadastro);
        });

        btnEntrar.setOnClickListener(view -> {

//            String login = "Gabriel";
            String login = txtLogin.getText().toString();
            String senha = txtSenha.getText().toString();
            //            String senha = "Lucena";

            int cod_usuario = SQLHelper.getINSTANCE(this).login(login, senha);

            if (cod_usuario > 0) {

                Login.setIdUsuario(cod_usuario);

                startActivity(new Intent(MainActivity.this, FeedActivity.class));

            } else {
                Toast.makeText(this, "Dados de login incorretos", Toast.LENGTH_LONG).show();
            }

        });

    }
}