package com.example.libri;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import model.Item;
import model.Livro;

public class FeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
    }


    /**
     * фInflate do menu
     */

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /*
     * Ações do menu
     */

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.d("MENUITEM-", String.valueOf(item.getItemId()));

        switch (item.getItemId()) {

            case R.id.menu_cadastrar_livro:
                startActivity(new Intent(this, CadastroLivro.class));
                break;

            case R.id.menu_feed_livro:
                startActivity(new Intent(this, FeedActivity.class));
                break;

            case R.id.menu_sair:
                startActivity(new Intent(this, MainActivity.class));
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    /*
     *  Adapter do recycler
     */

    class LivroAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        /*
         * Atributo que recebe os ojetos de "items"
         */

        public List<Item> item;

        /**
         * Construtor da classe LivroAdapter
         */

        public LivroAdapter(List<Item> item) {

            this.item = item;
        }


        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            /*
             * Inicializa a classe ViewHolder dentro da adapter
             */

            if (viewType == 0) {

                return new LivroAdapter.LivroViewHolder();
            }

            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            /*
             * Associa os objetos da ViewHolder aos dados
             */


        }

        @Override
        public int getItemCount() {

            /*
             * Quantidade de itens na contagem
             */

            return 0;
        }

        /*
         * Início da Viewholder - Define as estruturas de interface. "Envelopa"as estruturas .xmls
         */

        class LivroViewHolder extends RecyclerView.ViewHolder {

            private TextView textLivroTitulo, textLivroDescricao;
            private int Idlivro;

            public LivroViewHolder(@NonNull View itemView) {
                super(itemView);

                textLivroTitulo = itemView.findViewById(R.id.textLivroTitulo);
                textLivroDescricao = itemView.findViewById(R.id.textDescricao);

            }

            public void setLivroData(Livro livro) {

                textLivroTitulo.setText(livro.getTitulo());
                textLivroDescricao.setText(livro.getDescricao());

            }

        }

        /*
         * Final da Viewholder
         */
    }

    /*
     * Final da adapter
     */
}