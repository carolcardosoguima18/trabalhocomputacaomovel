package com.example.appfacu;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

//Backend da tela de Editar Contato
public class Edit extends AppCompatActivity {
    TextView id;
    EditText nome, telefone, email;
    Button btSalvar;
    SQLiteDatabase db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        // Mostra um botão na Barra Superior para voltar
        getSupportActionBar().setTitle("Contatos - Editar");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        id = findViewById(R.id.editObterID);
        nome = findViewById(R.id.editObterNome);
        telefone = findViewById(R.id.editObterTelefone);
        email = findViewById(R.id.editObterEmail);
        btSalvar = findViewById(R.id.btEditSalvar);

        final Intent itContato = getIntent();
        final Contato contato = (Contato)itContato.getExtras().getSerializable("objContato");

        id.setText(String.valueOf(contato.getId()));
        nome.setText(contato.getNome());
        telefone.setText(contato.getTelefone());
        email.setText(contato.getEmail());


        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                // Coleta os dados digitados nos campos
                ContentValues values = new ContentValues();
                //values.put("id", id.getText().toString());
                values.put("nome", nome.getText().toString());
                values.put("telefone", telefone.getText().toString());
                values.put("email", email.getText().toString());


                Contato novosDados = new Contato();
                novosDados.setNome(nome.getText().toString());
                novosDados.setTelefone(telefone.getText().toString());
                novosDados.setEmail(email.getText().toString());

                // Atualiza os dados na tabela
                db = openOrCreateDatabase("db_contato", Context.MODE_PRIVATE, null);
                db.execSQL("UPDATE contato SET " +
                        "nome='" + novosDados.getNome() + "'," +
                        "telefone='" + novosDados.getTelefone() + "'," +
                        "email='" + novosDados.getEmail() + "' " +
                        "WHERE id=" + contato.getId()
                );

                // Cria uma caixa de mensagem e mostra os dados incluídos
                Mensagem mensagem = new Mensagem(Edit.this);
                mensagem.show(
                        "Dados atualizados com sucesso!", novosDados.getDados());
                ;
                //Intent main = new Intent(getApplicationContext(), MainActivity.class);
                //startActivity(main);
            }
        });
    }
}

