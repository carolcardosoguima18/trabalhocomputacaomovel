package com.example.appfacu;

import android.app.AlertDialog;
import android.content.Context;

//Classe utilizada para enviar mensagens as telas
public class Mensagem {
    private Context _context;

    // Construtor
    public Mensagem(Context context) {
        this._context = context;
    }

    // Mostra a mensagem
    public void show(String titulo, final String texto) {
        AlertDialog.Builder msg = new AlertDialog.Builder(_context);
        msg.setTitle(titulo);
        msg.setMessage(texto);
        msg.setPositiveButton("OK", null);

        msg.show();
    }
}
