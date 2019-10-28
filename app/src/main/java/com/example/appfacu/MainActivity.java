package com.example.appfacu;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // Declaração dos botões
    Button btInsert, btList, btSearch, btExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Associa o botão de inserção e configura o evento do clique para abrir a tela de inclusão
        btInsert = findViewById(R.id.btInserir);
        btInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent insert = new Intent(getApplicationContext(), Insert.class);
                startActivity(insert);
            }
        });

        // Associa o botão e configura a ação para abrr a tela de buscas
        btList = findViewById(R.id.btConsultar);
        btList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cria uma caixa de mensagem e mostra os dados incluídos
                //Message message = new Message(MainActivity.this);
                //message.show("Dados incluídos com sucesso!","Dados", R.drawable.ic_add);
                Intent insert = new Intent(getApplicationContext(), ListAll.class);
                startActivity(insert);
            }
        });


    }
}
