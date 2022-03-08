/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escola;

import java.io.Serializable;

/**
 *
 */
public class Pessoa implements Serializable{

    private static final long serialVersionUID = 1L;
    private String nome;
    private String morada;
    private int idade;

    public Pessoa() {
        nome = "";
        morada = "";
        idade = 0;
    }

    public Pessoa(String nome, String morada, int idade) {
        this.nome = nome;
        this.morada = morada;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public String getMorada() {
        return morada;
    }

    public int getIdade() {
        return idade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String toString() {
        String p = "Nome: " + nome + '\n' + " Morada: " + morada + '\n' + " Idade: " + idade;
        return p;
    }

    public boolean equals(Object obj) {
        //Se a classe do objecto for igual à classe a que me refiro
        if (obj != null && this.getClass() == obj.getClass()) {
            Pessoa p = (Pessoa) obj;
            return (this.nome == p.nome && this.morada == p.morada && this.idade == p.idade);

        } else {
            return false;
        }

    }

    public Object clone() {
        Pessoa p = new Pessoa();
        p.nome = this.nome;
        p.morada = this.morada;
        p.idade=this.idade;

        return p;
    }
}
