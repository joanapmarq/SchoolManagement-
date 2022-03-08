/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escola;

import java.util.ArrayList;
import java.io.Serializable;


class Aluno extends Pessoa implements Serializable {

   private static final long serialVersionUID = 1L;
    private int numero;
    private Curso curso;
    private float nota;
    private static int ultimo = 0;
    
    

    public Aluno(Pessoa aluno,  Curso curso , int num) {
        super(aluno.getNome(), aluno.getMorada(), aluno.getIdade());
        this.curso = curso;
        //ultimo++;
        this.numero=num;
    }

    public Aluno(Pessoa aluno, int numero, float nota, Curso curso, int ultimo) {
        super(aluno.getNome(), aluno.getMorada(), aluno.getIdade());
        this.numero = numero;
        this.ultimo = ultimo;
        this.curso=curso;
        this.nota = nota;
    }

    public int getNumero() {
        return numero;
    }

    public Curso getCurso() {
        return curso;
    }

    public float getNota() {
        return nota;
    }

    public int getUltimo() {
        return ultimo;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setCurso(Curso cursos) {
        this.curso = cursos;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }


    public String toString() {
        return super.toString() + "numero=" + numero + ", nota=" + nota + '}';
    }

    public boolean equals(Object obj) {
        //Se a classe do objecto for igual à classe a que me refiro
        if (obj != null && this.getClass() == obj.getClass()) {
            Aluno a = (Aluno) obj;
            return (this.numero == a.numero && this.nota == a.nota && this.curso == a.curso && super.equals(a));

        } else {
            return false;
        }

    }
  
    public Object clone() {
        Pessoa p = new Pessoa (super.getNome(), super.getMorada(),super.getIdade());
        Aluno copia = new Aluno(p,numero, nota, curso, ultimo);
        return copia;
    }
    

    
}
