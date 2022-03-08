package escola;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import myinputs.*;
import java.io.*;

public class Main {

    private static ArrayList<Aluno> alunos;
    private static ArrayList<Professor> professores;
    private static ArrayList<Curso> cursos;

    private static Escola escola;
    private static Curso curso;
    private static Pessoa pessoa_desconhecida;
    private static Professor professor;

    /**
     * @param args the command line arguments1
     * @throws java.text.ParseException
     *
     */
    public static void main(String[] args) throws ParseException, FileNotFoundException, IOException {

        alunos = new ArrayList<Aluno>();
        professores = new ArrayList<Professor>();
        cursos = new ArrayList<Curso>();

        escola = new Escola(professores, alunos, cursos);
        pessoa_desconhecida = new Pessoa("", "", 100);
        professor = new Professor(pessoa_desconhecida, 0);
        professores.add(professor);

        //curso= new Curso("Eletro",professor,200,data_i,data_f);
        //escola.adicionarCurso(curso);
        //LER DO FICHEIRO
        // Ler ficheiro dos alunos
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("C:\\Users\\PCRVa\\OneDrive\\Ambiente de Trabalho\\Me\\Mestrado\\2Ano\\ProjetoFinal\\Escola\\alunos.dat"));

            alunos = (ArrayList<Aluno>) is.readObject();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        // Ler ficheiro dos professores
        try {
            ObjectInputStream iss = new ObjectInputStream(new FileInputStream("C:\\Users\\PCRVa\\OneDrive\\Ambiente de Trabalho\\Me\\Mestrado\\2Ano\\ProjetoFinal\\Escola\\professores.dat"));
            professores = (ArrayList<Professor>) iss.readObject();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        // Ler ficheiro dos cursos
        try {
            ObjectInputStream o = new ObjectInputStream(new FileInputStream("C:\\Users\\PCRVa\\OneDrive\\Ambiente de Trabalho\\Me\\Mestrado\\2Ano\\ProjetoFinal\\Escola\\cursos.dat"));
            cursos = (ArrayList<Curso>) o.readObject();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        ////////////////

        ////////////////
        int opcao = 0;
        int opcao1 = 0;
        int opcao2 = 0;
        int opcao3 = 0;
        int opcao4 = 0;
        int opcao5 = 0;
        int opcao6 = 0;

        do {

            Menu();
            opcao = Ler.umInt();

            switch (opcao) {
                case 1:
                    //GERIR ALUNOS 

                    do {
                        MenuAlunos();
                        opcao1 = Ler.umInt();

                        switch (opcao1) {
                            case 1: //Adicionar aluno
                                Escola.inserirAluno(alunos, cursos);
                                break;
                            case 2:
                                Escola.removerAluno(alunos, cursos);
                                break;

                            case 3:
                                Escola.pesuisaNumeroAluno(alunos);
                                break;

                            case 4: //Listar alunos 
                                Escola.listarAlunos(alunos);
                                break;

                            case 5: // Consultar Nota
                                Escola.consultarNota(alunos);
                                break;

                            case 6: // Trocar curso do aluno
                                Escola.alterarCursoDeAluno(alunos, cursos);
                                break;

                            case 7: //Alterar paramtros
                                do {
                                    AlterarOpcoesAlunos();
                                    opcao5 = Ler.umInt();

                                    switch (opcao5) {
                                        case 1: //Alterar Nome
                                            Escola.alterarNomeAluno(alunos, cursos);
                                            break;
                                        case 2: //Alterar Idade
                                            Escola.alterarIdadeAluno(alunos, cursos);
                                            break;

                                        case 3: //Alterar Morada
                                            Escola.alterarMoradaAluno(alunos, cursos);
                                            break;

                                        case 4: 
                                            
                                            break;

                                        
                                       
                                    }
                                } while (opcao5 != 4);

                                break;
                              

                            case 8:
                                break;
                        }
                    } while (opcao1 != 8);

                    break;

                case 2:
                    //GERIR PROFESSORES 
                    do {
                        MenuProfessores();
                        opcao2 = Ler.umInt();

                        switch (opcao2) {
                            case 1: //Adicionar professor
                                Escola.inserirProfessor(professores);

                                break;

                            case 2: //Remover professor
                                Escola.removerProfessor(professores, cursos);
                                break;

                            case 3:
                                Escola.pesuisaNumeroProfessor(professores);
                                break;

                            case 4: //Listar professores 
                                Escola.listarProfessores(professores);

                                break;

                            case 5: //Alterar o Professor do Curso
                                Escola.trocarProfdoCurso(professores, cursos);

                                break;

                            case 6://Inserir nota no aluno
                                Escola.inserirNotaAoAluno(professores, alunos, cursos);
                                break;
                                
                            case 7://Alterar Parametros
                                 do {
                                    AlterarOpcoesProfessores();
                                    opcao6 = Ler.umInt();

                                    switch (opcao6) {
                                        case 1: //Alterar Nome
                                            Escola.alterarNomeProfessor(professores, cursos);
                                            break;
                                        case 2: //Alterar Idade
                                            Escola.alterarIdadeProfessor(professores, cursos);
                                            break;

                                        case 3: //Alterar Morada
                                            Escola.alterarMoradaProfessor(professores, cursos);
                                            break;

                                        case 4: 
                                            
                                            break;

                                       

                                       
                                    }
                                } while (opcao6 != 4);

                                break;
                                
                                                                
                            case 8:
                                break;
                        }
                    } while (opcao2 != 8);

                    break;

                case 3:

                    //GERIR  CURSOS 
                    do {
                        MenuCursos();
                        opcao3 = Ler.umInt();

                        switch (opcao3) {
                            case 1: //Adicionar curso
                                Escola.inserirCurso(cursos, professores);
                                break;

                            case 2:
                                Escola.removerCurso(cursos, professores, alunos);
                                break;

                            case 3:
                                Escola.pesuisaCodigoCurso(cursos);

                                break;

                            case 4:
                                Escola.listarCursos(cursos);
                                break;

                            case 5:
                                Escola.listarAlunosCurso(cursos, alunos);
                                break;

                            case 6:
                                break;
                        }
                    } while (opcao3 != 6);

                    break;

                case 4:

                    //ESTATISTICA
                    do {
                        MenuEstatistica();
                        opcao4 = Ler.umInt();

                        switch (opcao4) {
                            case 1:
                                Escola.cursosMaisFrequentado(alunos, cursos);
                                break;

                            case 2:
                                Escola.professorQueLecionaMaisCursos(professores, cursos);
                                break;

                            case 3: //Alunos que moram na mesma cidade da escola
                                Escola.alunosComMoradaEscola(alunos, Escola.getLocalizacao());
                                break;

                            case 4://Professores que moram na mesma cidade da escola
                                Escola.professoresComMoradaEscola(professores, Escola.getLocalizacao());
                                break;

                            case 5://Pesquisar alunos por cidade
                                Escola.pesquisaAlunoMorada(alunos);
                                break;

                            case 6://Pesquisar professores por cidade
                                Escola.pesquisaProfessorMorada(professores);
                                break;

                            case 7:
                                break;
                        }
                    } while (opcao4 != 7);

                    break;

                case 5:
                    break;
            }
        } while (opcao < 5);

    }

    private static void Menu() {
        System.out.println("********ESCOLA*******");
        System.out.println(
                "1 – Gerir Alunos;\n"
                + "2 – Gerir Professores;\n"
                + "3 – Gerir Cursos;\n"
                + "4 – Estatisticas;\n"
                + "5 – Terminar.");
    }

    private static void MenuAlunos() {
        System.out.println("********Gerir Alunos*******");
        System.out.println(
                "1 – Adicionar Aluno;\n"
                + "2 – Remover Aluno;\n"
                + "3 – Pesquisar por número;\n"
                + "4 – Listar alunos;\n"
                + "5 - Consultar Nota;\n"
                + "6 - Alterar Curso;\n"
                + "7 - Alterar Parametros;\n"
                + "8 – Retroceder.");
    }

    private static void MenuProfessores() {
        System.out.println("********Gerir Professores*******");
        System.out.println(
                "1 – Adicionar Professor;\n"
                + "2 – Remover Professor;\n"
                + "3 – Pesquisar por número;\n"
                + "4 – Listar Professores;\n"
                + "5 - Alterar o Professor do Curso;\n"
                + "6 - Inserir Notas;\n"
                + "7 - Alterar Parametros;\n"
                + "8 – Retroceder.");
    }

    private static void MenuCursos() {
        System.out.println("********Gerir Cursos*******");
        System.out.println(
                "1 – Adicionar Curso;\n"
                + "2 – Remover Curso;\n"
                + "3 – Pesquisar por código;\n"
                + "4 – Listar cursos;\n"
                + "5 – Listar Alunos do Curso;\n"
                + "6 – Retroceder.");
    }

    private static void MenuEstatistica() {
        System.out.println("********Estatistica*******");
        System.out.println(
                "1 – Curso mais frequentado;\n"
                + "2 – Professor que leciona mais cursos;\n"
                + "3 – Alunos que moram na mesma cidade da escola;\n"
                + "4 – Professores que moram na mesma cidade da escola;\n"
                + "5 – Pesquisar Alunos por cidade;\n"
                + "6 – Pesquisar Professores por cidade;\n"
                + "7 – Retroceder.");
    }

    private static void AlterarOpcoesAlunos() {
        System.out.println("********OPCÕES*******");
        System.out.println(
                "1 – Alterar Nome;\n"
                + "2 – Alterar Idade;\n"
                + "3 – Alterar Morada;\n"
                + "4 – Retroceder;\n");
    }

    private static void AlterarOpcoesProfessores() {
        System.out.println("********OPCÕES*******");
        System.out.println(
                "1 – Alterar Nome;\n"
                + "2 – Alterar Idade;\n"
                + "3 – Alterar Morada;\n"
                + "4 – Retroceder;\n");

    }

}
