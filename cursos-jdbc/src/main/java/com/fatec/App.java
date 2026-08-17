package com.fatec;
import com.fatec.dao.CursoDAO;
import com.fatec.modelo.Curso;
import java.util.List;
import java.util.Scanner;

public class App {

    private static Scanner scanner = new Scanner(System.in);
    private static CursoDAO cursoDAO = new CursoDAO();

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenu();
            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    inserirCurso();
                    break;
                case 2:
                    listarCursos();
                    break;
                case 3:
                    atualizarCurso();
                    break;
                case 4:
                    deletarCurso();
                    break;
                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }

        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n---- MENU DE CURSOS ----");
        System.out.println("1. Inserir curso");
        System.out.println("2. Listar cursos");
        System.out.println("3. Atualizar curso");
        System.out.println("4. Deletar curso");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static int lerOpcao() {
        try {
            int opcao = Integer.parseInt(scanner.nextLine());
            return opcao;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void inserirCurso() {
        System.out.println("\n--- Inserir Curso ---");

        System.out.print("Nome do curso: ");
        String nome = scanner.nextLine();

        System.out.print("Carga horária (em horas): ");
        int cargaHoraria = lerInteiro();

        System.out.print("Professor: ");
        String professor = scanner.nextLine();

        Curso curso = new Curso(nome, cargaHoraria, professor);
        cursoDAO.inserir(curso);
    }

    private static void listarCursos() {
        System.out.println("\n--- Lista de Cursos ---");
        List<Curso> cursos = cursoDAO.listar();

        if (cursos.isEmpty()) {
            System.out.println("Nenhum curso cadastrado.");
        } else {
            for (Curso curso : cursos) {
                System.out.println(curso);
            }
        }
    }

    private static void atualizarCurso() {
        System.out.println("\n--- Atualizar Curso ---");

        System.out.print("Digite o ID do curso a ser atualizado: ");
        int id = lerInteiro();

        System.out.print("Novo nome do curso: ");
        String nome = scanner.nextLine();

        System.out.print("Nova carga horária: ");
        int cargaHoraria = lerInteiro();

        System.out.print("Novo professor: ");
        String professor = scanner.nextLine();

        Curso curso = new Curso(id, nome, cargaHoraria, professor);
        cursoDAO.atualizar(curso);
    }

    private static void deletarCurso() {
        System.out.println("\n--- Deletar Curso ---");

        System.out.print("Digite o ID do curso a ser deletado: ");
        int id = lerInteiro();

        cursoDAO.deletar(id);
    }

    private static int lerInteiro() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Valor inválido, digite um número: ");
            }
        }
    }
}