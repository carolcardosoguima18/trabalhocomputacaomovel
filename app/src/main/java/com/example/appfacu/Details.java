package com.example.appfacu;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

//Backend da tela de Detalhes do contato
public class Details extends AppCompatActivity {
    Button btEditar, btExcluir;
    TextView id, nome, telefone, email;
    SQLiteDatabase db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Mostra um botão na Barra Superior para voltar
        getSupportActionBar().setTitle("Contato - Detalhes");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        id = findViewById(R.id.textLerId);
        nome = findViewById(R.id.textLerNome);
        telefone = findViewById(R.id.textLerTelefone);
        email = findViewById(R.id.textLerEmail);
        btEditar = findViewById(R.id.btEditar);
        btExcluir = findViewById(R.id.btExcluirContato);

        Intent itContato = getIntent();
        final Contato contato = (Contato)itContato.getExtras().getSerializable("objContato");

        id.setText(String.valueOf(contato.getId()));
        nome.setText(contato.getNome());
        telefone.setText(contato.getTelefone());
        email.setText(contato.getEmail());

        //BtEditar - Chama a tela de edicao
        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent editar = new Intent(getApplicationContext(),Edit.class);
                editar.putExtra("objContato",contato);
                startActivity(editar);
            }
        });

        //BtExcluir - Exclui diretamente da tela de detalhes e volta para a home
        btExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                // Atualiza os dados na tabela
                db = openOrCreateDatabase("db_contato", Context.MODE_PRIVATE, null);
                db.execSQL("Delete from contato" +
                        " WHERE id=" + contato.getId()
                );

                // Cria uma caixa de mensagem e mostra os dados incluídos
                Mensagem mensagem = new Mensagem(Details.this);
                mensagem.show(
                        "Dados excluidos com sucesso!", "");
                Intent main = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(main);
            }
        });
    }
}
