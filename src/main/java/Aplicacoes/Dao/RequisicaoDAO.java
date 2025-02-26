package Aplicacoes.Dao;

import Aplicacoes.BD.DatabaseConnection;
import Aplicacoes.Modelos.Requisicao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequisicaoDAO {
    public void adicionaRequisicao(Requisicao requisicao) {
        String sql = "INSERT INTO Requisicoes (id_usuario, solicitante, id_material, quantidade, data) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, requisicao.getIdUsuario());
            stmt.setString(2, requisicao.getSolicitante());
            stmt.setInt(3, requisicao.getIdMaterial());
            stmt.setDouble(4, requisicao.getQuantidade());
            stmt.setDate(5, Date.valueOf(requisicao.getData()));

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    requisicao.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Requisicao buscarRequisicaoPorId(int id) {
        String sql = "SELECT * FROM Requisicoes WHERE id_requisicao = ?";
        Requisicao requisicao = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    requisicao = new Requisicao(
                            rs.getInt("id_requisicao"),
                            rs.getInt("id_usuario"),
                            rs.getString("solicitante"),
                            rs.getInt("id_material"),
                            rs.getDouble("quantidade"),
                            rs.getDate("data").toString()
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requisicao;
    }

    public List<Requisicao> listarRequisicoes() {
        List<Requisicao> requisicoes = new ArrayList<>();
        String sql = "SELECT * FROM Requisicoes";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Requisicao requisicao = new Requisicao(
                        rs.getInt("id_requisicao"),
                        rs.getInt("id_usuario"),
                        rs.getString("solicitante"),
                        rs.getInt("id_material"),
                        rs.getDouble("quantidade"),
                        rs.getDate("data").toString()
                );
                requisicoes.add(requisicao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requisicoes;
    }

    public void atualizarRequisicao(Requisicao requisicao) {
        String sql = "UPDATE Requisicoes SET id_usuario = ?, solicitante = ?, id_material = ?, quantidade = ?, data = ? WHERE id_requisicao = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, requisicao.getIdUsuario());
            stmt.setString(2, requisicao.getSolicitante());
            stmt.setInt(3, requisicao.getIdMaterial());
            stmt.setDouble(4, requisicao.getQuantidade());
            stmt.setDate(5, Date.valueOf(requisicao.getData()));
            stmt.setInt(6, requisicao.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarRequisicao(int id) {
        String sql = "DELETE FROM Requisicoes WHERE id_requisicao = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
