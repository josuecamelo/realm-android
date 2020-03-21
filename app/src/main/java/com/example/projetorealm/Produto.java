package com.example.projetorealm;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Produto extends RealmObject {

    @PrimaryKey
    @Required
    private String id = UUID.randomUUID().toString();
    @Required
    private String nome;
    private double peso;
    private double preco;

    @Ignore //não quero persistir essa campo
    private int beminutil;

    public Produto(){

    }

    public Produto(String nome, double peso, double preco){
        this.nome = nome;
        this.peso = peso;
        this.preco = preco;
    }

    public Produto(String id,String nome, double peso, double preco){
        this.id = id;
        this.nome = nome;
        this.peso = peso;
        this.preco = preco;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
