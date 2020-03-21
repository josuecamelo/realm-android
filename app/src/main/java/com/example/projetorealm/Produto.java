package com.example.projetorealm;

import io.realm.RealmObject;

public class Produto extends RealmObject {

    private String nome;
    private double peso;
    private double preco;

    public Produto(){

    }

    public Produto(String nome, double peso, double preco){
        this.nome = nome;
        this.peso = peso;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
