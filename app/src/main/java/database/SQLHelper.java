package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class SQLHelper extends SQLiteOpenHelper {

    /* Atributos da classe de connection */

    private static final String DB_NAME = "libri";
    private static final int DB_VERSION = 2;
    private static SQLHelper INSTANCE;

    /*Método de verificar se a conexão está aberta*/

    public SQLHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /*
     * Método construtor - Recebe os valores iniciais de abertura da conexão
     * */

    public static SQLHelper getINSTANCE(Context context) {

        if (INSTANCE == null) {
            INSTANCE = new SQLHelper(context);
        }

        return INSTANCE;

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE tblUsuario" +
                "(idUsuario INTEGER PRIMARY KEY," +
                "nome TEXT," +
                "sobrenome TEXT," +
                "email TEXT," +
                "login TEXT," +
                "senha TEXT," +
                "created_date DATETIME)");

        Log.d("SQLITE-", "BANCO DE DADOS CRIADO! - " + DB_VERSION);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("CREATE TABLE tblLivro" +
                "(idLivro INTEGER PRIMARY KEY," +
                "IdUsuario INTEGER," +
                "titulo TEXT," +
                "descricao TEXT," +
                "foto TEXT," +
                "created_date DATETIME," +
                "FOREIGN KEY (idUsuario) REFERENCES tblUsuario (idUsuario))");

        Log.d("SQLITE-", "BANCO DE DADOS CRIADO! - " + DB_VERSION);
    }

    /*
     * Inserção de usuário
     */

    public boolean addUser(String nome, String sobrenome, String email, String login, String senha, String created_date) {

        /*
         * Configura o SQLITE para a escrita:
         */

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        try {

            sqLiteDatabase.beginTransaction();

            ContentValues values = new ContentValues();

            values.put("nome", nome);
            values.put("sobrenome", sobrenome);
            values.put("email", email);
            values.put("login", login);
            values.put("senha", senha);
            values.put("created_date", created_date);

            sqLiteDatabase.insertOrThrow("tblUsuario", null, values);
            sqLiteDatabase.setTransactionSuccessful();

            return true;

        } catch (Exception e) {

            Log.d("SQLITE-", e.getMessage());
            return false;

        } finally {

            if (sqLiteDatabase.isOpen()) {

                sqLiteDatabase.endTransaction();

            }

        }

    }

    //Inserção de livros


    public boolean addBook(int idUsuario, String titulo, String descricao, String foto, String created_date) {

        //Configura o SQLITE para escrita:

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        int idLivro = 0;

        try {

            sqLiteDatabase.beginTransaction();

            ContentValues values = new ContentValues();

            values.put("idUsuario", idUsuario);
            values.put("titulo", titulo);
            values.put("descricao", descricao);
            values.put("foto", foto);
            values.put("created_date", created_date);

            sqLiteDatabase.insertOrThrow("tblLivro", null, values);
            sqLiteDatabase.setTransactionSuccessful();

            return true;

        } catch (Exception e) {

            Log.d("SQLITE-", e.getMessage());
            return false;

        } finally {

            if (sqLiteDatabase.isOpen()) {

                sqLiteDatabase.endTransaction();

            }

        }

    }

    public int login(String login, String senha) {

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM tblUsuario WHERE login = ? AND senha = ?", new String[]{login, senha});

        int idUsuario = 0;

        try {

            if (cursor.moveToFirst()) {

                idUsuario = cursor.getInt(cursor.getColumnIndex("idUsuario"));
                return idUsuario;

            }

            return 0;

        } catch (Exception e) {

            Log.d("SQLITE-", e.getMessage());

        } finally {

            if (cursor != null && !cursor.isClosed()) {

                cursor.close();

            }

        }

        return 0;

    }


}