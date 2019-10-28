package com.example.appfacu;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

//Backend da Tela de lista de contatos
public class ListAll extends AppCompatActivity {

    ListView listViewContatos;
    ArrayList<Contato> contatos = new ArrayList<>();
    ArrayAdapter<Contato> adaptador;
    SQLiteDatabase db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //Mostra um botão na Barra Superior para voltar
        getSupportActionBar().setTitle("Lista de Contatos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Abreo banco de dados existente
        db = openOrCreateDatabase("db_contato", Context.MODE_PRIVATE, null);

        listViewContatos = findViewById(R.id.listContatos);

        // Carrega os registros em ordem alfabética no ArrayList para anexar ao adaptador
        contatos.clear();
        Cursor c = db.rawQuery("SELECT * FROM contato ORDER BY nome ASC", null);
        while (c.moveToNext()) {
            contatos.add(new Contato(
                    c.getInt(0),
                    c.getString(1),
                    c.getString(2),
                    c.getString(3))
                );
             }
        // Configura o adaptador
        adaptador = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                contatos);

    // Atribui o adaptador à ListView
    listViewContatos.setAdapter(adaptador);

    listViewContatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contato Contato = (Contato) listViewContatos.getItemAtPosition(position);
                Intent itContato = new Intent(getApplicationContext(), Details.class);
                itContato.putExtra("objContato", Contato);
                startActivity(itContato);
            }
        });
    }
}
