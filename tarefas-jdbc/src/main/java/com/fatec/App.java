package com.fatec;
import com.fatec.dao.TarefaDAO;
import com.fatec.modelo.Tarefa;
import java.util.List;
import java.util.Scanner;

public class App {

    private static Scanner scanner = new Scanner(System.in);
    private static TarefaDAO tarefaDAO = new TarefaDAO();

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenu();
            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    criarTarefa();
                    break;
                case 2:
                    listarTarefas();
                    break;
                case 3:
                    editarTarefa();
                    break;
                case 4:
                    marcarComoConcluida();
                    break;
                case 5:
                    excluirTarefa();
                    break;
                case 6:
                    filtrarPorCategoria();
                    break;
                case 7:
                    filtrarPorStatus();
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
        System.out.println("\n---- GESTÃO DE TAREFAS ----");
        System.out.println("1. Criar tarefa");
        System.out.println("2. Listar todas as tarefas");
        System.out.println("3. Editar tarefa");
        System.out.println("4. Marcar tarefa como concluída");
        System.out.println("5. Excluir tarefa");
        System.out.println("6. Filtrar por categoria");
        System.out.println("7. Filtrar por status");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void criarTarefa() {
        System.out.println("\n--- Criar Tarefa ---");

        System.out.print("Título da tarefa: ");
        String titulo = scanner.nextLine();

        System.out.print("Categoria (ex: Trabalho, Estudos, Pessoal): ");
        String categoria = scanner.nextLine();

        Tarefa tarefa = new Tarefa(titulo, categoria);
        tarefaDAO.inserir(tarefa);
    }

    private static void listarTarefas() {
        System.out.println("\n--- Todas as Tarefas ---");
        exibirLista(tarefaDAO.listar());
    }

    private static void editarTarefa() {
        System.out.println("\n--- Editar Tarefa ---");

        System.out.print("Digite o ID da tarefa a ser editada: ");
        int id = lerInteiro();

        System.out.print("Novo título: ");
        String titulo = scanner.nextLine();

        System.out.print("Nova categoria: ");
        String categoria = scanner.nextLine();

        System.out.print("Está concluída? (S/N): ");
        String resposta = scanner.nextLine();
        boolean concluida = resposta.equalsIgnoreCase("S");

        Tarefa tarefa = new Tarefa(id, titulo, categoria, concluida);
        tarefaDAO.atualizar(tarefa);
    }

    private static void marcarComoConcluida() {
        System.out.println("\n--- Marcar como Concluída ---");

        System.out.print("Digite o ID da tarefa: ");
        int id = lerInteiro();

        tarefaDAO.marcarComoConcluida(id);
    }

    private static void excluirTarefa() {
        System.out.println("\n--- Excluir Tarefa ---");

        System.out.print("Digite o ID da tarefa a ser excluída: ");
        int id = lerInteiro();

        tarefaDAO.deletar(id);
    }

    private static void filtrarPorCategoria() {
        System.out.println("\n--- Filtrar por Categoria ---");

        System.out.print("Digite a categoria: ");
        String categoria = scanner.nextLine();

        List<Tarefa> tarefas = tarefaDAO.listarPorCategoria(categoria);
        exibirLista(tarefas);
    }

    private static void filtrarPorStatus() {
        System.out.println("\n--- Filtrar por Status ---");
        System.out.println("1. Concluídas");
        System.out.println("2. Pendentes");
        System.out.print("Escolha: ");

        int opcao = lerInteiro();
        boolean concluida = (opcao == 1);

        List<Tarefa> tarefas = tarefaDAO.listarPorStatus(concluida);
        exibirLista(tarefas);
    }

    private static void exibirLista(List<Tarefa> tarefas) {
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa encontrada.");
        } else {
            for (Tarefa tarefa : tarefas) {
                System.out.println(tarefa);
            }
        }
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