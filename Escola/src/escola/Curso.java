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
public class Curso implements Serializable  {
    
    private static final long serialVersionUID = 1L;
    private int ultimo = 0;
    private int codigo;
    private String nome;
    private Professor professor;
    private ArrayList<Aluno> alunos;
    private int n_horas;
    private String data_inicio;
    private String data_fim;
    //horario    

    public Curso(int cod, String nome, Professor professor, int n_horas, String data_inicio, String data_fim) {
        this.nome=nome;
        this.professor = professor;
        this.n_horas = n_horas;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
        //ultimo++;
        this.codigo=cod;
          alunos = new ArrayList<>();
    }
    
    
    
    public Curso(int codigo,String nome, Professor professor, ArrayList<Aluno> alunos, int n_horas, String data_inicio, String data_fim) {
        this.nome=nome;
        this.codigo = codigo;
        this.professor = professor;
        this.alunos = alunos;
        this.n_horas = n_horas;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
                  alunos = new ArrayList<>();

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
    
    public int getUltimo() {
        return ultimo;
    }

    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(ArrayList<Aluno> alunos) {
        this.alunos = alunos;
    }

    public int getN_horas() {
        return n_horas;
    }

    public void setN_horas(int n_horas) {
        this.n_horas = n_horas;
    }

    public String getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(String data_inicio) {
        this.data_inicio = data_inicio;
    }

    public String getData_fim() {
        return data_fim;
    }

    public void setData_fim(String data_fim) {
        this.data_fim = data_fim;
    }
    
    public void adicionarAluno(Aluno a){
        alunos.add(a);
    }
    
    public void removerAluno(Aluno a){
        alunos.remove(a);
    }

    @Override
    public String toString() {
        return "Curso{" + "ultimo=" + ultimo + ", codigo=" + codigo + ", nome=" + nome + ", professor=" + professor + ", alunos=" + alunos + ", n_horas=" + n_horas + ", data_inicio=" + data_inicio + ", data_fim=" + data_fim + '}';
    }

 

    public boolean equals(Object obj) {
        //Se a classe do objecto for igual à classe a que me refiro
        if (obj != null && this.getClass() == obj.getClass()) {
            Curso c = (Curso) obj;
            return (this.codigo == c.codigo && this.professor == c.professor && this.alunos == c.alunos && this.n_horas == c.n_horas && this.data_inicio == c.data_inicio && this.data_fim == c.data_fim);

        } else {
            return false;
        }

    }

    public Object clone() {
        Curso copia = new Curso(codigo, nome,professor, alunos, n_horas, data_inicio, data_fim);
        return copia;
    }
    
    
    
    //TENTATIVA
    

}
