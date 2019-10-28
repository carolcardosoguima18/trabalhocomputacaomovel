package com.example.appfacu;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;

//Backend da Tela de Incluir Contato
public class Insert extends AppCompatActivity {

    EditText nome, telefone, email;
    Button btSalvar;
    SQLiteDatabase db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        //Criar banco de dados
        db = openOrCreateDatabase("db_contato", Context.MODE_PRIVATE, null);
        //db.execSQL("Delete from contato");
        db.execSQL("CREATE TABLE IF NOT EXISTS contato(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome VARCHAR NOT NULL," +
                "telefone VARCHAR NOT NULL," +
                "email VARCHAR NOT NULL);");


        nome = findViewById(R.id.editNome);
        telefone = findViewById(R.id.editTelefone);
        email = findViewById(R.id.editEmail);
        btSalvar = findViewById(R.id.btSalvar);

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Instancia o objeto Contato
                Contato objcontato = new Contato();
                objcontato.setNome(nome.getText().toString());
                objcontato.setTelefone(telefone.getText().toString());
                objcontato.setEmail(email.getText().toString());
                //
                ContentValues values = new ContentValues();
                values.put("NOME",objcontato.getNome());
                values.put("TELEFONE", objcontato.getTelefone());
                values.put("EMAIL",objcontato.getEmail());

                // Insere os dados na tabela
                db.insert("contato", null, values);

                // Cria uma caixa de mensagem e mostra os dados incluídos
                Mensagem mensagem = new Mensagem(Insert.this);
                mensagem.show(
                "Dados incluídos com sucesso!", objcontato.getDados());

                clearText();
            }
        });
    }
    public void clearText(){
        nome.setText("");
        telefone.setText("");
        email.setText("");
        nome.requestFocus();

        // fecha o teclado virtual
        ((InputMethodManager) Insert.this.getSystemService(
                Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                getCurrentFocus().getWindowToken(), 0);
    }
}