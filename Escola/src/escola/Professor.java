/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escola;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author PCRVa
 */
class Professor extends Pessoa implements Serializable{

    private static final long serialVersionUID = 1L;
    private static int ultimo = -1;
    private int numero;
    private ArrayList<Curso> cursos;

    public Professor(Pessoa professor , int n) {
        super(professor.getNome(), professor.getMorada(), professor.getIdade());
        cursos = new ArrayList<>();
         //ultimo++;
        this.numero = n;
       
    }

    public Professor(Pessoa professor, int numero, ArrayList<Curso> cursos) {
        super(professor.getNome(), professor.getMorada(), professor.getIdade());   
        //ultimo++;
        this.numero = numero;
        
        cursos = new ArrayList<>();
    }

    
     public Professor(Pessoa professor, int numero, ArrayList<Curso> cursos, int ultimo) {
        super(professor.getNome(), professor.getMorada(), professor.getIdade());
        this.numero = numero;
         this.ultimo=ultimo;
       cursos = new ArrayList<>();
    }
     
     
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getUltimo() {
        return ultimo;
    }

    public ArrayList<Curso> getCurso() {
        return cursos;
    }

    public void setCurso(ArrayList<Curso> cursos) {
        this.cursos = cursos;
    }
    
    public String toString(){
        String s= super.toString() + "Numero: " + numero + "Cursos" ;
           for (int i = 0; i < cursos.size(); i++) {
            s = s + (" " + cursos.get(i).getNome());
        }
        return s;
    }
    
    
    
    public boolean equals(Object obj){
        if(obj != null && this.getClass() == obj.getClass()){
            Professor prof = (Professor) obj;
            return (this.numero == prof.numero);
        }
        return false;
    }
    
    public void adicionarCurso(Curso c) {
        cursos.add(c);
    }
    
    public void removerCurso(Curso c){
        cursos.remove(c);
    }
}
