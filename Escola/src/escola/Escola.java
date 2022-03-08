/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escola;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import myinputs.Ler;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Escola implements Serializable {

    private static final long serialVersionUID = 1L;
    public static String nome = "Universidade Interior dos Bananas";
    public static String localizacao = "Covilha";
    private ArrayList<Professor> professores;
    private ArrayList<Aluno> alunos;
    private ArrayList<Curso> cursos;

    public Escola() {
        professores = new ArrayList<>();
        alunos = new ArrayList<>();
        cursos = new ArrayList<>();
    }

    public Escola(ArrayList<Professor> professores, ArrayList<Aluno> alunos, ArrayList<Curso> cursos) {
        this.professores = professores;
        this.alunos = alunos;
        this.cursos = cursos;
    }

    public static String getLocalizacao() {
        return localizacao;
    }

    public static void setLocalizacao(String localizacao) {
        Escola.localizacao = localizacao;
    }

    public ArrayList<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(ArrayList<Professor> professores) {
        this.professores = professores;
    }

    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(ArrayList<Aluno> alunos) {
        this.alunos = alunos;
    }

    public ArrayList<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(ArrayList<Curso> cursos) {
        this.cursos = cursos;
    }

    @Override
    public String toString() {
        return "Escola{" + "professores=" + professores + ", alunos=" + alunos + ", cursos=" + cursos + '}';
    }

    public boolean equals(Object obj) {
        //Se a classe do objecto for igual à classe a que me refiro
        if (obj != null && this.getClass() == obj.getClass()) {
            Escola a = (Escola) obj;
            return (this.professores == a.professores && this.alunos == a.alunos && this.cursos == a.cursos && super.equals(a));

        } else {
            return false;
        }

    }

    public Object clone() {
        Escola copia = new Escola(professores, alunos, cursos);
        return copia;
    }

    //  FUNÇÕES ALUNOS 
    public static void inserirAluno(ArrayList<Aluno> alunos, ArrayList<Curso> cursos) {
        //obter dados do livro; instanciar o objeto Livro;

        System.out.println("Numero dos alunos que já existem registados: \n");
        for (int i = 0; i < alunos.size(); i++) {
            System.out.println("Numero: " + alunos.get(i).getNumero());
        }
        System.out.println("Qual o número do aluno? ");
        int num = Ler.umInt();
        // validar número de livro
        for (int i = 0; i < alunos.size(); i++) {
            if (alunos.get(i).getNumero() == num) {
                System.out.println("Já existe um aluno com esse número");
                return;
            }
        }

        System.out.println("Qual o nome do aluno? ");
        String nome = Ler.umaString();
        System.out.println("Insira o morada do aluno: ");
        String morada = Ler.umaString();
        System.out.println("Insira a idade do aluno: ");
        int idade = Ler.umInt();
        listarCursos(cursos);
        System.out.println("Insira o codigo do curso: ");
        int cod_curso = Ler.umInt();

        Pessoa p = new Pessoa(nome, morada, idade);

        for (int i = 0; i < cursos.size(); i++) {
            if (cursos.get(i).getCodigo() == cod_curso) {
                Curso c = cursos.get(i);
                Aluno a = new Aluno(p, c, num);
                // adicionar o novo aluno à lista    
                alunos.add(a);

                //adicionar aluno á lista de alunos do curso
                //c.getAlunos().add(a);
                c.adicionarAluno(a);
                System.out.println("Aluno adicionado com sucesso!");
                break; // para sair do for porque ja encontrei o curso
            }
        }
        // atualizar ficheiro
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("C:\\Users\\PCRVa\\OneDrive\\Ambiente de Trabalho\\Me\\Mestrado\\2Ano\\ProjetoFinal\\Escola\\cursos.dat"));
            ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream("C:\\Users\\PCRVa\\OneDrive\\Ambiente de Trabalho\\Me\\Mestrado\\2Ano\\ProjetoFinal\\Escola\\alunos.dat"));
            // escrever o objeto livros no ficheiro
            os.writeObject(cursos);
            os.flush(); // os dados são copiados de memória para o disco
            oss.writeObject(alunos);
            oss.flush(); // os dados são copiados de memória para o disco
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void removerAluno(ArrayList<Aluno> alunos, ArrayList<Curso> cursos) {

        System.out.println("Alunos que já estão registados: \n");

        listarAlunos(alunos);

        System.out.println("Insira o numero do aluno a remover:");
        int numero = Ler.umInt();

        for (int i = 0; i < alunos.size(); i++) {
            if (alunos.get(i).getNumero() == numero) {
                Aluno aa = alunos.get(i);
              
                for (int j = 0; j < cursos.size(); j++) {
                    for (int k = 0; k < cursos.get(j).getAlunos().size(); k++) {
                        if (cursos.get(j).getAlunos().get(k)==alunos.get(i)) {
                            cursos.get(j).getAlunos().remove(aa);
                            break;
                        }

                    }

                }
                // Curso c = alunos.get(i).getCurso();
                // c.getAlunos().remove(aa);
                alunos.remove(aa);

                System.out.println("Removido com sucesso");
                break;
            }
        }

        // atualizar ficheiro
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("C:\\Users\\PCRVa\\OneDrive\\Ambiente de Trabalho\\Me\\Mestrado\\2Ano\\ProjetoFinal\\Escola\\alunos.dat"));
            ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream("C:\\Users\\PCRVa\\OneDrive\\Ambiente de Trabalho\\Me\\Mestrado\\2Ano\\ProjetoFinal\\Escola\\cursos.dat"));
            // escrever o objeto livros no ficheiro
            os.writeObject(alunos);
            os.flush(); // os dados são copiados de memória para o disco
            oss.writeObject(cursos);
            oss.flush(); // os dados são copiados de memória para o disco
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void listarAlunos(ArrayList<Aluno> alunos) {

        System.out.println("__________________ALUNOS____________________\n");
        for (int i = 0; i < alunos.size(); i++) {
            System.out.println("Nome: " + alunos.get(i).getNome() + "\n"
                    + "Morada: " + alunos.get(i).getMorada() + "\n"
                    + "Idade: " + alunos.get(i).getIdade() + "\n"
                    + "Curso: " + alunos.get(i).getCurso().getNome() + "\n"
                    + "Nota: " + alunos.get(i).getNota() + "\n"
                    + "Numero: " + alunos.get(i).getNumero() + "\n"
                    + "___________________________________________\n");
        }
    }

    public static void pesuisaNumeroAluno(ArrayList<Aluno> alunos) {

        System.out.println("Insira o código do curso a pesquisar:");
        int numero = Ler.umInt();

        int f = 0;

        for (int i = 0; i < alunos.size(); i++) {
            if (alunos.get(i).getNumero() == numero) {
                System.out.println("Nome: " + alunos.get(i).getNome() + "\n"
                        + "Morada: " + alunos.get(i).getMorada() + "\n"
                        + "Idade: " + alunos.get(i).getIdade() + "\n"
                        + "Curso: " + alunos.get(i).getCurso().getNome() + "\n"
                        + "Nota: " + alunos.get(i).getNota() + "\n"
                        + "Numero: " + alunos.get(i).getNumero() + "\n"
                        + "___________________________________________\n");
                System.out.println("Encontrado com sucesso");
                f = 1;
                break;
            }
        }
        if (f == 0) {
            System.out.println("Número inválido!");
        }
    }

    public static void alterarCursoDeAluno(ArrayList<Aluno> alunos, ArrayList<Curso> cursos) {

        listarAlunos(alunos);
        System.out.println("Introduza o numero do aluno que pretende alterar o curso:\n");
        int numero = Ler.umInt();

        listarCursos(cursos);
        System.out.println("Introduza o codigo do curso:\n");
        int cod_curso = Ler.umInt();

        for (int i = 0; i < alunos.size(); i++) {
            if (alunos.get(i).getNumero() == numero) {
                Aluno aa = alunos.get(i);
                Curso c = alunos.get(i).getCurso();

                c.getAlunos().remove(aa);

                //c.removerAluno(alunos.get(i));
                for (int j = 0; j < cursos.size(); j++) {

                    if (cursos.get(j).getCodigo() == cod_curso) {

                        //cursos.get(j).adicionarAluno(alunos.get(i));
                        aa.setCurso(cursos.get(j));
                        cursos.get(j).getAlunos().add(aa);
                        System.out.println("Curso alterado com sucesso!\n");
                        break;
                    }

                }

                break; // para sair do for porque ja encontrei o curso

            }
        }

        // atualizar ficheiro
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("C:\\Users\\PCRVa\\OneDrive\\Ambiente de Trabalho\\Me\\Mestrado\\2Ano\\ProjetoFinal\\Escola\\alunos.dat"));
            ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream("C:\\Users\\PCRVa\\OneDrive\\Ambiente de Trabalho\\Me\\Mestrado\\2Ano\\ProjetoFinal\\Escola\\cursos.dat"));
            // escrever o objeto livros no ficheiro
            os.writeObject(alunos);
            os.flush(); // os dados são copiados de memória para o disco
            oss.writeObject(cursos);
            oss.flush(); // os dados são copiados de memória para o disco
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void consultarNota(ArrayList<Aluno> alunos) {

        listarAlunos(alunos);
        System.out.println("Insira o número do aluno:\n");
        int n = Ler.umInt();
        int flag = 0;

        for (int i = 0; i < alunos.size(); i++) {
            if (alunos.get(i).getNumero() == n) {
                System.out.println("Curso: " + alunos.get(i).getCurso().getNome() + "  Nota: " + alunos.get(i).getNota() + "\n");
                if (alunos.get(i).getNota() < 9.5) {
                    System.out.println("Reprovado.");
                } else {
                    System.out.println("Aprovado.");
                }
                flag = 1;
                return;
            }
        }

        if (flag == 0) {
            System.out.println("Aluno não encontrado.");
        }

    }

    public static void alterarNomeAluno(ArrayList<Aluno> alunos, ArrayList<Curso> cursos) {

        System.out.println("Numero dos alunos que já existem registados: \n");
        for (int i = 0; i < alunos.size(); i++) {
            System.out.println("Numero: " + alunos.get(i).getNumero() + "Nome : " + alunos.get(i).getNome());
        }

        System.out.println("Qual o número do aluno? ");
        int num = Ler.umInt();

        for (int i = 0; i < alunos.size(); i++) {
            if (alunos.get(i).getNumero() == num) {

                System.out.println("Introduza um novo nome para o aluno: \n");
                String nome = Ler.umaString();

                //alterar nome na lista de alunos do curso
                for (int j = 0; j < cursos.size(); j++) {

                    for (int k = 0; k < cursos.get(j).getAlunos().size(); k++) {
                        if (num == cursos.get(j).getAlunos().get(k).getNumero()) {

                            cursos.get(j).getAlunos().get(k).setNome(nome);
                        }
                    }

                }

                alunos.get(i).setNome(nome);

            }
        }

        // atualizar ficheiro
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("C:\\Users\\PCRVa\\OneDrive\\Ambiente de Trabalho\\Me\\Mestrado\\2Ano\\ProjetoFinal\\Escola\\cursos.dat"));
            ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream("C:\\Users\\PCRVa\\OneDrive\\Ambiente de Trabalho\\Me\\Mestrado\\2Ano\\ProjetoFinal\\Escola\\alunos.dat"));
            // escrever o objeto livros no ficheiro
            os.writeObject(cursos);
            os.flush(); // os dados são copiados de memória para o disco
            oss.writeObject(alunos);
            oss.flush(); // os dados são copiados de memória para o disco
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void alterarIdadeAluno(ArrayList<Aluno> alunos, ArrayList<Curso> cursos) {

        System.out.println("Numero dos alunos que já existem registados: \n");
        for (int i = 0; i < alunos.size(); i++) {
            System.out.println("Numero: " + alunos.get(i).getNumero() + "Nome : " + alunos.get(i).getNome());
        }

        System.out.println("Qual o número do aluno? ");
        int num = Ler.umInt();

        for (int i = 0; i < alunos.size(); i++) {
            if (alunos.get(i).getNumero() == num) {

                System.out.println("Introduza uma nova idade para o aluno: \n");
                int idade = Ler.umInt();

                //alterar nome na lista de alunos do curso
                for (int j = 0; j < cursos.size(); j++) {

                    for (int k = 0; k < cursos.get(j).getAlunos().size(); k++) {
                        if (num == cursos.get(j).getAlunos().get(k).getNumero()) {

                            cursos.get(j).getAlunos().get(k).setIdade(idade);
                        }
                    }

                }

                alunos.get(i).setIdade(idade);

            }
        }

        // atualizar ficheiro
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("C:\\Users\\PCRVa\\OneDrive\\Ambiente de Trabalho\\Me\\Mestrado\\2Ano\\ProjetoFinal\\Escola\\cursos.dat"));
            ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream("C:\\Users\\PCRVa\\OneDrive\\Ambiente de Trabalho\\Me\\Mestrado\\2Ano\\ProjetoFinal\\Escola\\alunos.dat"));
            // escrever o objeto livros no ficheiro
            os.writeObject(cursos);
            os.flush(); // os dados são copiados de memória para o disco
            oss.writeObject(alunos);
            oss.flush(); // os dados são copiados de memória para o disco
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void alterarMoradaAluno(ArrayList<Aluno> alunos, ArrayList<Curso> cursos) {

        System.out.println("Numero dos alunos que já existem registados: \n");
        for (int i = 0; i < alunos.size(); i++) {
            System.out.println("Numero: " + alunos.get(i).getNumero() + "Nome : " + alunos.get(i).getNome());
        }

        System.out.println("Qual o número do aluno? ");
        int num = Ler.umInt();

        for (int i = 0; i < alunos.size(); i++) {
            if (alunos.get(i).getNumero() == num) {

                System.out.println("Introduza uma nova morada para o aluno: \n");
                String morada = Ler.umaString();

                //alterar nome na lista de alunos do curso
                for (int j = 0; j < cursos.size(); j++) {

                    for (int k = 0; k < cursos.get(j).getAlunos().size(); k++) {
                        if (num == cursos.get(j).getAlunos().get(k).getNumero()) {

                            cursos.get(j).getAlunos().get(k).setMorada(morada);
                        }
                    }

                }

                alunos.get(i).setMorada(morada);

            }
        }

        // atualizar ficheiro
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("C:\\Users\\PCRVa\\OneDrive\\Ambiente de Trabalho\\Me\\Mestrado\\2Ano\\ProjetoFinal\\Escola\\cursos.dat"));
            ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream("C:\\Users\\PCRVa\\OneDrive\\Ambiente de Trabalho\\Me\\Mestrado\\2Ano\\ProjetoFinal\\Escola\\alunos.dat"));
            // escrever o objeto livros no ficheiro
            os.writeObject(cursos);
            os.flush(); // os dados são copiados de memória para o disco
            oss.writeObject(alunos);
            oss.flush(); // os dados são copiados de memória para o disco
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    ///////////////////
    //FUNÇÕES PROFESSORES
    public static void inserirProfessor(ArrayList<Professor> professores) {
        //obter dados do livro; instanciar o objeto Livro;

        System.out.println("Numero dos professores que já existem registados: \n");
        for (int i = 0; i < professores.size(); i++) {
            System.out.println("Numero: " + professores.get(i).getNumero());
        }
        System.out.println("Qual o número do professor? ");
        int num = Ler.umInt();

        for (int i = 0; i < professores.size(); i++) {
            if (professores.get(i).getNumero() == num) {
                System.out.println("Já existe um professor com esse número");
                return;
            }
        }

        System.out.println("Insira o nome do professor: ");
        String nome = Ler.umaString();
        System.out.println("Insira o morada do professor: ");
        String morada = Ler.umaString();
        System.out.println("Insira a idade do professor: ");
        int idade = Ler.umInt();
        Pessoa p1 = new Pessoa(nome, morada, idade);
        Professor p = new Professor(p1, num);
        professores.add(p);
        // atualizar ficheiro
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("C:\\Users\\PCRVa\\OneDrive\\Ambiente de Trabalho\\Me\\Mestrado\\2Ano\\ProjetoFinal\\Escola\\professores.dat"));
            // escrever o objeto livros no ficheiro
            os.writeObject(professores);
            os.flush(); // os dados são copiados de memória para o disco
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void removerProfessor(ArrayList<Professor> professores, ArrayList<Curso> cursos) {

        System.out.println("Professores que já estão registados: \n");

        listarProfessores(professores);

        System.out.println("Insira o numero do professor a remover:");
        int numero = Ler.umInt();

        for (int i = 0; i < professores.size(); i++) {
            if (professores.get(i).getNumero() == numero) {
                Professor aa = professores.get(i);
                //alterar os cursos que ele dava para pessoa desconhecida 0
                for (int j = 0; j < cursos.size(); j++) {
                    if (cursos.get(j).getProfessor() == aa) {
                        for (int k = 0; k < professores.size(); k++) {
                            if (professores.get(k).getNumero() == 0) {
                                Professor p = professores.get(k);
                                p.adicionarCurso(cursos.get(j));
                                cursos.get(j).setProfessor(p); // pessoa desconhecia 

                                break;
                            }
                        }

                    }
                }
                professores.remove(aa);
                System.out.println("Removido com sucesso");
                break;
            }
        }

        // atualizar ficheiro
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("C:\\Users\\PCRVa\\OneDrive\\Ambiente de Trabalho\\Me\\Mestrado\\2Ano\\ProjetoFinal\\Escola\\cursos.dat"));
            ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream("C:\\Users\\PCRVa\\OneDrive\\Ambiente de Trabalho\\Me\\Mestrado\\2Ano\\ProjetoFinal\\Escola\\professores.dat"));
            // escrever o objeto livros no ficheiro
            os.writeObject(cursos);
            os.flush(); // os dados são copiados de memória para o disco
            oss.writeObject(professores);
            oss.flush(); // os dados são copiados de memória para o disco
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void trocarProfdoCurso(ArrayList<Professor> professores, ArrayList<Curso> cursos) {
        //obter dados do livro; instanciar o objeto Livro;

        listarCursos(cursos);

        System.out.println("Introduza o código do curso que pretende editar:");
        int numero = Ler.umInt();

        listarProfessores(professores);
        System.out.println("Introduza o número do professor que vai lecionar o curso:");
        int nump = Ler.umInt();

        for (int i = 0; i < cursos.size(); i++) {
            if (cursos.get(i).getCodigo() == numero) {
                if (cursos.get(i).getProfessor().getNumero() != nump) {
                    //remover o curso da lista de cursos do professor antigo
                    Curso c = cursos.get(i);
                    c.getProfessor().getCurso().remove(c);

                    for (int j = 0; j < professores.size(); j++) {

                        if (professores.get(j).getNumero() == nump) {
                            Professor p = professores.get(j);
                            //adicionar á lista de professores do professor selecionado o curso
                            p.adicionarCurso(c);
                            //alterar ao curso o novo professor
                            c.setProfessor(p);
                            break;
                        }
                    }
                    break;
                } else {
                    System.out.println("Esse professor já leciona o curso em questao!");
                }

            }
        }

        // atualizar ficheiro
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("C:\\Users\\PCRVa\\OneDrive\\Ambiente de Trabalho\\Me\\Mestrado\\2Ano\\ProjetoFinal\\Escola\\cursos.dat"));
            ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream("C:\\Users\\PCRVa\\OneDrive\\Ambiente de Trabalho\\Me\\Mestrado\\2Ano\\ProjetoFinal\\Escola\\professores.dat"));
            // escrever o objeto livros no ficheiro
            os.writeObject(cursos);
            os.flush(); // os dados são copiados de memória para o disco
            oss.writeObject(professores);
            oss.flush(); // os dados são copiados de memória para o disco
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void listarProfessores(ArrayList<Professor> professores) {

        System.out.println("__________________PROFESSORES____________________\n");
        for (int i = 0; i < professores.size(); i++) {
            System.out.println("Nome: " + professores.get(i).getNome() + "\n"
                    + "Morada: " + professores.get(i).getMorada() + "\n"
                    + "Idade: " + professores.get(i).getIdade() + "\n"
                    + "Numero: " + professores.get(i).getNumero() + "\n");
            System.out.println("Cursos que leciona: ");
            for (int j = 0; j < professores.get(i).getCurso().size(); j++) {
                System.out.println("Curso " + professores.get(i).getCurso().get(j).getNome() + " Codigo: " + professores.get(i).getCurso().get(j).getCodigo() + "\n");
            }
            System.out.println("____________________________________________");
        }
    }

    public static void pesuisaNumeroProfessor(ArrayList<Professor> professores) {

        System.out.println("Insira o código do curso a pesquisar:");
        int numero = Ler.umInt();

        int f = 0;

        for (int i = 0; i < professores.size(); i++) {
            if (professores.get(i).getNumero() == numero) {
                System.out.println("Nome: " + professores.get(i).getNome() + "\n"
                        + "Morada: " + professores.get(i).getMorada() + "\n"
                        + "Idade: " + professores.get(i).getIdade() + "\n"
                        + "Numero: " + professores.get(i).getNumero() + "\n");
                System.out.println("Cursos que leciona: ");
                for (int j = 0; j < professores.get(i).getCurso().size(); j++) {
                    System.out.println("Curso " + professores.get(i).getCurso().get(j).getNome() + " Codigo: " + professores.get(i).getCurso().get(j).getCodigo() + "\n");
                }
                System.out.println("____________________________________________");
                System.out.println("Encontrado com sucesso");
                f = 1;
                break;
            }
        }
        if (f == 0) {
            System.out.println("Número inválido!");
        }
    }

    public static void listarCursosdoProf(ArrayList<Professor> professores, int n, ArrayList<Curso> cursos) {
        //professor só pode inserir nota dos cursos que leciona 
        for (int i = 0; i < professores.size(); i++) {
            if (professores.get(i).getNumero() == n) {
                Professor p = professores.get(i);
                System.out.println("Cursos que leciona: ");
                for (int j = 0; j < p.getCurso().size(); j++) {
                    Curso c = p.getCurso().get(j);
                    System.out.println("Curso " + p.getCurso().get(j).getNome() + " Codigo: " + p.getCurso().get(j).getCodigo() + "\n");

                    /*System.out.println("Alunos Inscritos: \n");
                    for(int k = 0; k< c.getAlunos().size(); k++){
                        System.out.println("ALUNOS");
                        System.out.println("Nome : " + c.getAlunos().get(k).getNome() + "Numero: " + c.getAlunos().get(k).getNumero());
                    System.out.println("___________________________________________\n");
                     */
                }

                break;

            }

        }

    }

    public static void inserirNotaAoAluno(ArrayList<Professor> professores, ArrayList<Aluno> alunos, ArrayList<Curso> cursos) {
        //professor só pode inserir nota dos cursos que leciona 
        int f = 0;
        ArrayList aux = new ArrayList();
        listarProfessores(professores);
        System.out.println("Insira o número do professor: ");
        int num = Ler.umInt();

        listarCursosdoProf(professores, num, cursos);

        System.out.println("Insira codigo do curso que quer alterar pauta");
        int n_curso = Ler.umInt();

        for (int i = 0; i < cursos.size(); i++) {
            if (cursos.get(i).getCodigo() == n_curso) {
                System.out.println("Alunos inscritos: ");
                for (int j = 0; j < cursos.get(i).getAlunos().size(); j++) {
                    System.out.println("Nome : " + cursos.get(i).getAlunos().get(j).getNome() + "Numero: " + cursos.get(i).getAlunos().get(j).getNumero());
                    System.out.println("___________________________________________\n");
                    aux.add(cursos.get(i).getAlunos().get(j).getNumero());
                }

                System.out.println("Insira numero do aluno quem quer alterar nota:\n");
                int aluno = Ler.umInt();

                for (int k = 0; k < alunos.size(); k++) {

                    if (alunos.get(k).getNumero() == aluno) {
                        if (aux.contains(aluno)) {
                            System.out.println("Insira nota :\n");
                            float nota = Ler.umFloat();
                            alunos.get(k).setNota(nota);
                            System.out.println("Nota inserida com sucesso");
                            f = 1;
                        }
                        break;
                    }
                }

                break;
            }
        }

        if (f == 0) {
            System.out.println("Numero de aluno incorreto");
        }

        // atualizar ficheiro
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("C:\\Users\\PCRVa\\OneDrive\\Ambiente de Trabalho\\Me\\Mestrado\\2Ano\\ProjetoFinal\\Escola\\cursos.dat"));
            ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream("C:\\Users\\PCRVa\\OneDrive\\Ambiente de Trabalho\\Me\\Mestrado\\2Ano\\ProjetoFinal\\Escola\\professores.dat"));
            ObjectOutputStream osss = new ObjectOutputStream(new FileOutputStream("C:\\Users\\PCRVa\\OneDrive\\Ambiente de Trabalho\\Me\\Mestrado\\2Ano\\ProjetoFinal\\Escola\\alunos.dat"));
            // escrever o objeto livros no ficheiro
            os.writeObject(cursos);
            os.flush(); // os dados são copiados de memória para o disco
            oss.writeObject(professores);
            oss.flush(); // os dados são copiados de memória para o disco
            osss.writeObject(alunos);
            osss.flush(); // os dados são copiados de memória para o disco
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    ///////////////
    //FUNÇÕES CURSOS
    public static void inserirCurso(ArrayList<Curso> cursos, ArrayList<Professor> professores) {
        //obter dados do livro; instanciar o objeto Livro;

        System.out.println("Código dos cursos que já existem registados: \n");
        for (int i = 0; i < cursos.size(); i++) {
            System.out.println("Código: " + cursos.get(i).getCodigo());
        }
        System.out.println("Qual o Código do Curso? ");
        int num = Ler.umInt();

        for (int i = 0; i < cursos.size(); i++) {
            if (cursos.get(i).getCodigo() == num) {
                System.out.println("Já existe um curso com esse código");
                return;
            }
        }

        System.out.println("Insira o nome do curso: ");
        String nome = Ler.umaString();
        //listar professores existentes 
        listarProfessores(professores);
        System.out.println("Deseja adicionar um novo professor ou associar o curso a um professor já existente: \n");
        System.out.println("1 - Professor já existente \n" + "2 - Professor Novo \n");
        int escolha = Ler.umInt();

        if (escolha == 2) {
            inserirProfessor(professores);
            listarProfessores(professores);
        }

        System.out.println("Insira o número do professor que leciona este curso: ");
        int prof = Ler.umInt();

        for (int i = 0; i < professores.size(); i++) {
            if (professores.get(i).getNumero() == prof) {
                Professor p = professores.get(i);
                System.out.println("Insira o numero total de horas do curso:");
                int horas = Ler.umInt();
                System.out.println("Insira a data de inicio, " + "dd/mm/yyyy :");
                String data_i = Ler.umaString();
                System.out.println("Insira a data de fim, " + "dd/mm/yyyy :");
                String data_f = Ler.umaString();
                Curso c = new Curso(num, nome, p, horas, data_i, data_f);
                cursos.add(c);

                //adicionar á lista de cursos dos professores
                professores.get(i).getCurso().add(c);
            }
        }

        // atualizar ficheiro
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("C:\\Users\\PCRVa\\OneDrive\\Ambiente de Trabalho\\Me\\Mestrado\\2Ano\\ProjetoFinal\\Escola\\cursos.dat"));
            ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream("C:\\Users\\PCRVa\\OneDrive\\Ambiente de Trabalho\\Me\\Mestrado\\2Ano\\ProjetoFinal\\Escola\\professores.dat"));

// escrever o objeto livros no ficheiro
            os.writeObject(cursos);
            os.flush(); // os dados são copiados de memória para o disco
            oss.writeObject(professores);
            oss.flush(); // os dados são copiados de memória para o disco
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void removerCurso(ArrayList<Curso> cursos, ArrayList<Professor> professores, ArrayList<Aluno> alunos) {

        System.out.println("Cursos que já estão registados: \n");

        listarCursos(cursos);
        System.out.println("Insira o código do curso a remover:");
        int numero = Ler.umInt();

        for (int i = 0; i < cursos.size(); i++) {
            if (cursos.get(i).getCodigo() == numero) {
                Curso c = cursos.get(i);

                int tamanho = 0;
                System.out.println("Alunos inscritos: ");
                for (int j = 0; j < cursos.get(i).getAlunos().size(); j++) {
                    System.out.println("Nome : " + cursos.get(i).getAlunos().get(j).getNome() + "Numero: " + cursos.get(i).getAlunos().get(j).getNumero());
                    System.out.println("___________________________________________\n");
                    tamanho++;
                }

                // criar um array com os alunos do curso
                ArrayList<Aluno> alunosCurso = c.getAlunos();
                //remover curso da lista de cursos do professores
                c.getProfessor().removerCurso(c);
                cursos.remove(c);
                System.out.println("Removido com sucesso");
                listarCursos(cursos);

                while (tamanho > 0) {  //tem de ser um while e nao um do while para que o ciclo apenas corra se tamanho >0
                    //ADICONAR NOVO CURSO AOS ALUNOS QUE TINHAM O CURSO A ASER ELIMINADO 
                    System.out.println("Introduza o numero do aluno que pretende alterar o curso:\n");
                    int nu = Ler.umInt();
                    System.out.println("Introduza o codigo do curso:\n");
                    int cod_curso = Ler.umInt();

                    for (int l = 0; l < alunos.size(); l++) {
                        if (alunos.get(l).getNumero() == nu) {

                            for (int k = 0; k < cursos.size(); k++) {
                                if (cursos.get(k).getCodigo() == cod_curso) {

                                    //cursos.get(k).getAlunos().add(alunos.get(l));
                                    cursos.get(k).adicionarAluno(alunos.get(l));

                                    alunos.get(l).setCurso(cursos.get(k));

                                }

                            }

                            System.out.println("Curso alterado com sucesso!\n");
                            tamanho--;
                            break; // para sair do for porque ja encontrei o curso
                        }

                    }

                }

                break;
            }
        }

        // atualizar ficheiro
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("C:\\Users\\PCRVa\\OneDrive\\Ambiente de Trabalho\\Me\\Mestrado\\2Ano\\ProjetoFinal\\Escola\\cursos.dat"));
            ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream("C:\\Users\\PCRVa\\OneDrive\\Ambiente de Trabalho\\Me\\Mestrado\\2Ano\\ProjetoFinal\\Escola\\professores.dat"));
            ObjectOutputStream osss = new ObjectOutputStream(new FileOutputStream("C:\\Users\\PCRVa\\OneDrive\\Ambiente de Trabalho\\Me\\Mestrado\\2Ano\\ProjetoFinal\\Escola\\alunos.dat"));
            // escrever o objeto livros no ficheiro
            os.writeObject(cursos);
            os.flush(); // os dados são copiados de memória para o disco
            oss.writeObject(professores);
            oss.flush(); // os dados são copiados de memória para o disco
            osss.writeObject(alunos);
            osss.flush(); // os dados são copiados de memória para o disco
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void pesuisaCodigoCurso(ArrayList<Curso> cursos) {

        System.out.println("Insira o código do curso a pesquisar:");
        int numero = Ler.umInt();

        int f = 0;

        for (int i = 0; i < cursos.size(); i++) {
            if (cursos.get(i).getCodigo() == numero) {
                System.out.println("Nome: " + cursos.get(i).getNome() + "\n"
                        + "Professor: " + cursos.get(i).getProfessor().getNome() + "\n"
                        + "Número total de horas:" + cursos.get(i).getN_horas() + "\n"
                        + "Data de Inicio: " + cursos.get(i).getData_inicio() + "\n"
                        + "Data de Fim: " + cursos.get(i).getData_fim() + "\n"
                        + "Código: " + cursos.get(i).getCodigo() + "\n"
                        + "___________________________________________\n");
                System.out.println("Encontrado com sucesso");
                f = 1;
                break;
            }
        }
        if (f == 0) {
            System.out.println("Código inválido!");
        }
    }

    public static void listarCursos(ArrayList<Curso> cursos) {

        System.out.println("__________________CURSOS____________________\n");
        for (int i = 0; i < cursos.size(); i++) {
            System.out.println("Nome: " + cursos.get(i).getNome() + "\n"
                    + "Professor: " + cursos.get(i).getProfessor().getNome() + "\n"
                    + "Número total de horas:" + cursos.get(i).getN_horas() + "\n"
                    + "Data de Inicio: " + cursos.get(i).getData_inicio() + "\n"
                    + "Data de Fim: " + cursos.get(i).getData_fim() + "\n"
                    + "Código: " + cursos.get(i).getCodigo() + "\n"
                    + "___________________________________________\n");
        }
    }

    public static void listarAlunosCurso(ArrayList<Curso> cursos, ArrayList<Aluno> alunos) {

        listarCursos(cursos);
        System.out.println("Insira o código do curso a pesquisar:");
        int numero = Ler.umInt();

        System.out.println("__________________CURSO____________________\n");
        for (int i = 0; i < cursos.size(); i++) {
            if (cursos.get(i).getCodigo() == numero) {
                System.out.println("Nome: " + cursos.get(i).getNome() + "\n"
                        + "Código: " + cursos.get(i).getCodigo() + "\n");
                System.out.println("Alunos inscritos: ");
                for (int j = 0; j < cursos.get(i).getAlunos().size(); j++) {
                    System.out.println("Nome : " + cursos.get(i).getAlunos().get(j).getNome() + "Numero: " + cursos.get(i).getAlunos().get(j).getNumero());
                    System.out.println("___________________________________________\n");

                }
                break;
            }
        }
    }

    
    //Estatisticas
    public static void alunosComMoradaEscola(ArrayList<Aluno> alunos, String localizacao) {

        System.out.println("Localização da escola: " + Escola.getLocalizacao());

        //ArrayList aux = new ArrayList();
        System.out.println("Os alunos listados abaixo estão na mesma localização da Escola");
        int n_alunos = 0;
        for (int i = 0; i < alunos.size(); i++) {
            if (alunos.get(i).getMorada().contains(localizacao)) {
                System.out.println("Nome: " + alunos.get(i).getNome() + "\n"
                        + "Morada: " + alunos.get(i).getMorada() + "\n");

                n_alunos++;
            }
        }

        System.out.println("TOTAL DE ALUNOS COM A MESMA LOCALIZAO DA ESCOLA: " + n_alunos + "\n");

    }

    public static void professoresComMoradaEscola(ArrayList<Professor> professores, String localizacao) {

        System.out.println("Localização da escola: " + Escola.getLocalizacao());

        //ArrayList aux = new ArrayList();
        System.out.println("Os professores listados abaixo estão na mesma localização da Escola");
        int n_professores = 0;
        for (int i = 0; i < professores.size(); i++) {
            if (professores.get(i).getMorada().contains(localizacao)) {
                System.out.println("Nome: " + professores.get(i).getNome() + "\n"
                        + "Morada: " + professores.get(i).getMorada() + "\n");

                n_professores++;
            }
        }

        System.out.println("TOTAL DE PROFESSORES COM A MESMA LOCALIZAO DA ESCOLA: " + n_professores + "\n");

    }

    public static void pesquisaAlunoMorada(ArrayList<Aluno> alunos) {

        System.out.println("Insira a morada");
        String morada = Ler.umaString();

        int flag = 0;
        int j = 0;

        for (int i = 0; i < alunos.size(); i++) {
            if (alunos.get(i).getMorada().equals(morada)) {
                System.out.println("Nome: " + alunos.get(i).getNome() + "\n"
                        + "Morada: " + alunos.get(i).getMorada() + "\n");
                flag = 1;
                j++;
            }

        }

        if (flag == 0) {
            System.out.println("Localizacao não existente");
        }

        System.out.println("A lOCALIZACAO INTRODUZIDA TEM NO TOTAL: " + j + " ALUNOS \n");

    }

    public static void cursosMaisFrequentado(ArrayList<Aluno> alunos, ArrayList<Curso> cursos) {

        int tamanho = 0;
        int maior = 0;

        //percorrer a lista dos cursos e dos alunos do mesmo e ver qual a lista maior
        for (int i = 0; i < cursos.size(); i++) {
            tamanho = cursos.get(i).getAlunos().size();
            if (tamanho > maior) {
                maior = tamanho;

            }

        }

        for (int j = 0; j < cursos.size(); j++) {
            if (cursos.get(j).getAlunos().size() == maior) {
                System.out.println("Curso mais frequentado : " + cursos.get(j).getNome());
            }
        }
        System.out.println("Número de alunos: " + maior);
    }

    public static void professorQueLecionaMaisCursos(ArrayList<Professor> professores, ArrayList<Curso> cursos) {

        int tamanho = 0;
        int maior = 0;

        //percorrer a lista dos professores  qual a lista maior de cursos que leciona
        for (int i = 0; i < professores.size(); i++) {
            tamanho = professores.get(i).getCurso().size();
            if (tamanho > maior) {
                maior = tamanho;
            }

        }

        for (int j = 0; j < professores.size(); j++) {
            if (professores.get(j).getCurso().size() == maior) {
                System.out.println("Professor que leciona mais cursos : " + professores.get(j).getNome());
            }
        }
        System.out.println("Número de cursos que leciona: " + maior);
    }

    public static void pesquisaProfessorMorada(ArrayList<Professor> professores) {

        System.out.println("Insira a morada");
        String morada = Ler.umaString();

        int flag = 0;
        int j = 0;

        for (int i = 0; i < professores.size(); i++) {
            if (professores.get(i).getMorada().equals(morada)) {
                System.out.println("Nome: " + professores.get(i).getNome() + "\n"
                        + "Morada: " + professores.get(i).getMorada() + "\n");
                flag = 1;
                j++;
            }

        }

        if (flag == 0) {
            System.out.println("Localizacao não existente");
        }

        System.out.println("A lOCALIZACAO INTRODUZIDA TEM NO TOTAL: " + j + " PROFESSORES \n");

    }

    public static void alterarNomeProfessor(ArrayList<Professor> professores, ArrayList<Curso> cursos) {

        System.out.println("Professores que já existem registados: \n");
        for (int i = 0; i < professores.size(); i++) {
            System.out.println("Numero: " + professores.get(i).getNumero() + '\n' + "Nome : " + professores.get(i).getNome());
        }

        System.out.println("Qual o número do Professor? ");
        int num = Ler.umInt();

        System.out.println("Introduza um novo nome para o professor: \n");
        String nomen = Ler.umaString();

        for (int i = 0; i < professores.size(); i++) {

            if (professores.get(i).getNumero() == num) {

                //alterar nome na lista de alunos do curso
                for (int j = 0; j < cursos.size(); j++) {
                    if (cursos.get(j).getProfessor().getNumero() == num) {
                        cursos.get(j).getProfessor().setNome(nomen);
                    }
                }
                professores.get(i).setNome(nomen);

            }

        }

        // atualizar ficheiro
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("C:\\Users\\PCRVa\\OneDrive\\Ambiente de Trabalho\\Me\\Mestrado\\2Ano\\ProjetoFinal\\Escola\\cursos.dat"));
            ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream("C:\\Users\\PCRVa\\OneDrive\\Ambiente de Trabalho\\Me\\Mestrado\\2Ano\\ProjetoFinal\\Escola\\professores.dat"));

// escrever o objeto livros no ficheiro
            os.writeObject(cursos);
            os.flush(); // os dados são copiados de memória para o disco
            oss.writeObject(professores);
            oss.flush(); // os dados são copiados de memória para o disco
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void alterarIdadeProfessor(ArrayList<Professor> professores, ArrayList<Curso> cursos) {

        System.out.println("Professores que já existem registados: \n");
        for (int i = 0; i < professores.size(); i++) {
            System.out.println("Numero: " + professores.get(i).getNumero() + '\n' + "Nome : " + professores.get(i).getNome());
        }

        System.out.println("Qual o número do Professor? ");
        int num = Ler.umInt();

        System.out.println("Introduza uma nova idade para o professor: \n");
        int idade = Ler.umInt();

        for (int i = 0; i < professores.size(); i++) {

            if (professores.get(i).getNumero() == num) {

                //alterar nome na lista de alunos do curso
                for (int j = 0; j < cursos.size(); j++) {
                    if (cursos.get(j).getProfessor().getNumero() == num) {
                        cursos.get(j).getProfessor().setIdade(idade);
                    }
                }
                professores.get(i).setIdade(idade);

            }

        }

        // atualizar ficheiro
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("C:\\Users\\PCRVa\\OneDrive\\Ambiente de Trabalho\\Me\\Mestrado\\2Ano\\ProjetoFinal\\Escola\\cursos.dat"));
            ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream("C:\\Users\\PCRVa\\OneDrive\\Ambiente de Trabalho\\Me\\Mestrado\\2Ano\\ProjetoFinal\\Escola\\professores.dat"));

// escrever o objeto livros no ficheiro
            os.writeObject(cursos);
            os.flush(); // os dados são copiados de memória para o disco
            oss.writeObject(professores);
            oss.flush(); // os dados são copiados de memória para o disco
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void alterarMoradaProfessor(ArrayList<Professor> professores, ArrayList<Curso> cursos) {

        System.out.println("Professores que já existem registados: \n");
        for (int i = 0; i < professores.size(); i++) {
            System.out.println("Numero: " + professores.get(i).getNumero() + '\n' + "Nome : " + professores.get(i).getNome());
        }

        System.out.println("Qual o número do Professor? ");
        int num = Ler.umInt();

        System.out.println("Introduza uma nova moarada para o professor: \n");
        String morada = Ler.umaString();

        for (int i = 0; i < professores.size(); i++) {

            if (professores.get(i).getNumero() == num) {

                //alterar nome na lista de alunos do curso
                for (int j = 0; j < cursos.size(); j++) {
                    if (cursos.get(j).getProfessor().getNumero() == num) {
                        cursos.get(j).getProfessor().setMorada(morada);
                    }
                }
                professores.get(i).setMorada(morada);

            }

        }

        // atualizar ficheiro
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("C:\\Users\\PCRVa\\OneDrive\\Ambiente de Trabalho\\Me\\Mestrado\\2Ano\\ProjetoFinal\\Escola\\cursos.dat"));
            ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream("C:\\Users\\PCRVa\\OneDrive\\Ambiente de Trabalho\\Me\\Mestrado\\2Ano\\ProjetoFinal\\Escola\\professores.dat"));

// escrever o objeto livros no ficheiro
            os.writeObject(cursos);
            os.flush(); // os dados são copiados de memória para o disco
            oss.writeObject(professores);
            oss.flush(); // os dados são copiados de memória para o disco
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
