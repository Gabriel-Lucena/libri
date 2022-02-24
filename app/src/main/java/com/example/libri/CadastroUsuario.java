package com.example.libri;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import database.SQLHelper;
import helpers.DateFormat;

public class CadastroUsuario extends AppCompatActivity {

    /**
     * Representação dos campos da activity
     */

    private EditText txtNome;
    private EditText txtSobrenome;
    private EditText txtEmail;
    private EditText txtLogin;
    private EditText txtSenha;
    private Button btnCadastrarUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        /**
         * Captura dos componentes gráficos da activity
         * **/

        txtNome = findViewById(R.id.txtNome);
        txtSobrenome = findViewById(R.id.txtSobrenome);
        txtEmail = findViewById(R.id.txtEmail);
        txtLogin = findViewById(R.id.txtLogin);
        txtSenha = findViewById(R.id.txtSenha);
        btnCadastrarUsuario = findViewById(R.id.btnCadastrarUsuario);

        /**
         * Tratamento do evento de clique do botão
         */

        btnCadastrarUsuario.setOnClickListener(view -> {

            if (!validate()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                return;
            } else {
                Toast.makeText(this, "Deu bom", Toast.LENGTH_SHORT).show();
            }

            /**
             * Processo de gravaçào de usuário
             * **/

            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.cadastroUsuarioTitulo))
                    .setMessage(getString(R.string.mensagem_cadastro_usuario))
                    .setPositiveButton(R.string.salvar, (dialog1, which) -> {

                        /**
                         * Ação do poisitive button
                         */

                        String nome = txtNome.getText().toString();
                        String sobrenome = txtSobrenome.getText().toString();
                        String email = txtEmail.getText().toString();
                        String senha = txtSenha.getText().toString();
                        String login = txtLogin.getText().toString();

                        /** Data de incremento de usuário **/
                        DateFormat df = new DateFormat();
                        String create_date = df.getDateFormat();

                        boolean cadastarUsuario = SQLHelper.getINSTANCE(this)
                                .addUser(nome, sobrenome, email, login, senha, create_date);

                        if (cadastarUsuario) {

                            Toast.makeText(this,
                                    "Cadastro realizadocom sucesso!",
                                    Toast.LENGTH_LONG).show();

                        } else {

                            Toast.makeText(this,
                                    "Houve um erro ao realizar o cadastro de usuário!",
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

    } // Final do método onCreate

    /**
     * Método de validação
     */

    private boolean validate() {

        return (
                !txtNome.getText().toString().isEmpty() &&
                        !txtSobrenome.getText().toString().isEmpty() &&
                        !txtEmail.getText().toString().isEmpty() &&
                        !txtLogin.getText().toString().isEmpty() &&
                        !txtSenha.getText().toString().isEmpty()

        );

    }

} // Final da classe