package com.fatec.dao;

import com.fatec.conexao.ConnectionFactory;
import com.fatec.modelo.Tarefa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TarefaDAO {

    public void inserir(Tarefa tarefa) {
        String sql = "INSERT INTO tarefa (titulo, categoria, concluida) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tarefa.getTitulo());
            stmt.setString(2, tarefa.getCategoria());
            stmt.setBoolean(3, tarefa.isConcluida());

            stmt.executeUpdate();
            System.out.println("Tarefa criada com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir tarefa: " + e.getMessage());
        }
    }

    public List<Tarefa> listar() {
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM tarefa ORDER BY id";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                tarefas.add(mapearTarefa(rs));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar tarefas: " + e.getMessage());
        }

        return tarefas;
    }

    public List<Tarefa> listarPorCategoria(String categoria) {
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM tarefa WHERE categoria ILIKE ? ORDER BY id";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, categoria);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    tarefas.add(mapearTarefa(rs));
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao filtrar por categoria: " + e.getMessage());
        }

        return tarefas;
    }

    public List<Tarefa> listarPorStatus(boolean concluida) {
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM tarefa WHERE concluida = ? ORDER BY id";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBoolean(1, concluida);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    tarefas.add(mapearTarefa(rs));
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao filtrar por status: " + e.getMessage());
        }

        return tarefas;
    }

    public void atualizar(Tarefa tarefa) {
        String sql = "UPDATE tarefa SET titulo = ?, categoria = ?, concluida = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tarefa.getTitulo());
            stmt.setString(2, tarefa.getCategoria());
            stmt.setBoolean(3, tarefa.isConcluida());
            stmt.setInt(4, tarefa.getId());

            int linhas = stmt.executeUpdate();
            if (linhas > 0) {
                System.out.println("Tarefa atualizada com sucesso!");
            } else {
                System.out.println("Nenhuma tarefa encontrada com esse ID.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar tarefa: " + e.getMessage());
        }
    }

    public void marcarComoConcluida(int id) {
        String sql = "UPDATE tarefa SET concluida = TRUE WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int linhas = stmt.executeUpdate();
            if (linhas > 0) {
                System.out.println("Tarefa marcada como concluída!");
            } else {
                System.out.println("Nenhuma tarefa encontrada com esse ID.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao concluir tarefa: " + e.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM tarefa WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int linhas = stmt.executeUpdate();
            if (linhas > 0) {
                System.out.println("Tarefa excluída com sucesso!");
            } else {
                System.out.println("Nenhuma tarefa encontrada com esse ID.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao excluir tarefa: " + e.getMessage());
        }
    }

    private Tarefa mapearTarefa(ResultSet rs) throws SQLException {
        return new Tarefa(
                rs.getInt("id"),
                rs.getString("titulo"),
                rs.getString("categoria"),
                rs.getBoolean("concluida")
        );
    }
}