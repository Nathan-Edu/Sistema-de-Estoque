package Aplicacoes.Dao;

import Aplicacoes.BD.DatabaseConnection;
import Aplicacoes.Modelos.Estoque;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstoqueDAO {
    public void adicionaEstoque(Estoque estoque) {
        String sql = "INSERT INTO Estoque (id_material, quantidade, unidade_medida, data_entrada) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, estoque.getCodMaterial());
            stmt.setBigDecimal(2, estoque.getQuantidade());
            stmt.setString(3, estoque.getUnidadeMedida());
            stmt.setDate(4, new Date(estoque.getDataEntrada().getTime()));

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Estoque buscarEstoquePorId(int id) {
        String sql = "SELECT * FROM Estoque WHERE id_estoque = ?";
        Estoque estoque = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    estoque = new Estoque(
                            rs.getInt("id_estoque"),
                            rs.getInt("id_material"),
                            rs.getBigDecimal("quantidade"),
                            rs.getString("unidade_medida"),
                            rs.getDate("data_entrada")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return estoque;
    }

    public List<Estoque> listarEstoques() {
        List<Estoque> estoques = new ArrayList<>();
        String sql = "SELECT id_estoque, id_material, quantidade, unidade_medida, data_entrada FROM Estoque";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Estoque estoque = new Estoque(
                        rs.getInt("id_estoque"),
                        rs.getInt("id_material"),
                        rs.getBigDecimal("quantidade"),
                        rs.getString("unidade_medida"),
                        rs.getDate("data_entrada")
                );
                estoques.add(estoque);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return estoques;
    }

    public void atualizarEstoque(Estoque estoque) {
        String sql = "UPDATE Estoque SET id_material = ?, quantidade = ?, unidade_medida = ?, data_entrada = ? WHERE id_estoque = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, estoque.getCodMaterial());
            stmt.setBigDecimal(2, estoque.getQuantidade());
            stmt.setString(3, estoque.getUnidadeMedida());
            stmt.setDate(4, new Date(estoque.getDataEntrada().getTime()));
            stmt.setInt(5, estoque.getIdEstoque());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarEstoque(int id) {
        String sql = "DELETE FROM Estoque WHERE id_estoque = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
