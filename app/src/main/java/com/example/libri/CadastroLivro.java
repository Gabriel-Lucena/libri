package com.example.libri;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import database.SQLHelper;
import helpers.DateFormat;

public class CadastroLivro extends AppCompatActivity {

    private EditText txtNome;
    private EditText txtFoto;
    private EditText txtDescricao;
    private EditText txtTitulo;
    private Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_livro);

        txtTitulo = findViewById(R.id.txtNome);
        txtFoto = findViewById(R.id.txtFoto);
        txtDescricao = findViewById(R.id.txtDescricao);
        btnCadastrar = findViewById(R.id.btnCadastrarLivro);

        btnCadastrar.setOnClickListener(view -> {
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.titulo_cadastro_livro))
                    .setMessage(getString(R.string.mensagem_cadastro_livro))
                    .setPositiveButton(R.string.salvar, (dialog1, which) -> {

                        /**
                         * Ação do poisitive button
                         */

                        String nome = txtNome.getText().toString();
                        String descricao = txtDescricao.getText().toString();
                        String foto = txtFoto.getText().toString();

                        /** Data de incremento de usuário **/
                        DateFormat df = new DateFormat();
                        String create_date = df.getDateFormat();

                        boolean cadastrarLivro = SQLHelper.getINSTANCE(CadastroLivro.this)
                                .addBook(1, nome, descricao, foto, create_date);

                        if (cadastrarLivro) {

                            Toast.makeText(this,
                                    "Cadastro realizado com sucesso!",
                                    Toast.LENGTH_LONG).show();

                        } else {

                            Toast.makeText(this,
                                    "Houve um erro ao realizar o cadastro de livro!",
                                    Toast.LENGTH_LONG).show();

                        }
                    })
                    .setNegativeButton(R.string.cancelar, (dialog1, which) -> {

                        /**
                         * Ação do negative button
                         */


                    }).create();

            dialog.show();

            /**
             * O corpo do lambda trata a ação que será executado após o clique
             */

        });

    }
    /**
     *  Fim do método onCreate
     */

    /**
     * Método de validação
     */

    private boolean validate() {

        return (
                !txtNome.getText().toString().isEmpty() &&
                        !txtNome.getText().toString().isEmpty() &&
                        !txtFoto.getText().toString().isEmpty() &&
                        !txtDescricao.getText().toString().isEmpty()

        );

    }


    /**
     *  Fim da classe
     * **/
}