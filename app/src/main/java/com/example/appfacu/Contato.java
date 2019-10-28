package com.example.appfacu;

import java.io.Serializable;


//Classe utilizada para o CRUD

public class Contato implements Serializable{

    private int id;
    private String nome;
    private String telefone;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Contato(){}

    public Contato(String nome, String telefone, String email){
     this.nome = nome;
     this.telefone = telefone;
     this.email = email;
}

    public Contato(int id,String nome, String telefone, String email){
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public String getDados(){
        return "ID: " + id + "\n" +
                "NOME: " + nome + "\n" +
                "TELEFONE: " + telefone + "\n" +
                "EMAIL: " + email;
    }

    public String toString() {
        return nome;
    }
}

