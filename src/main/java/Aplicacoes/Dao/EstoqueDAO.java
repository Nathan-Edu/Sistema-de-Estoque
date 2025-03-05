package Aplicacoes.Dao;

import Aplicacoes.BD.DatabaseConnection;
import Aplicacoes.Modelos.Estoque;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstoqueDAO {
    public void adicionaEstoque(Estoque estoque) {
        String sql = "INSERT INTO Estoque (id_estoque, id_material, quantidade, unidade_medida, data_entrada) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, estoque.getIdEstoque());
            stmt.setInt(2, estoque.getCodMaterial());
            stmt.setBigDecimal(3, estoque.getQuantidade());
            stmt.setString(4, estoque.getUnidadeMedida());
            stmt.setDate(5, new Date(estoque.getDataEntrada().getTime()));

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Estoque buscarEstoquePorId(int idEstoque) {
        String sql = "SELECT * FROM Estoque WHERE id_estoque = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEstoque);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Estoque(
                        rs.getInt("id_estoque"),
                        rs.getInt("id_material"),
                        rs.getBigDecimal("quantidade"),
                        rs.getString("unidade_medida"),
                        rs.getDate("data_entrada")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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

    public void deletarEstoque(int idEstoque) {
        String sql = "DELETE FROM Estoque WHERE id_estoque = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEstoque);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Estoque> buscarEstoquePorNome(String nomeMaterial) {
        List<Estoque> estoques = new ArrayList<>();
        String sql = "SELECT e.id_estoque, e.id_material, e.quantidade, e.unidade_medida, e.data_entrada " +
                "FROM Estoque e JOIN Material m ON e.id_material = m.id_material WHERE m.descricao_curta = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nomeMaterial);
            try (ResultSet rs = stmt.executeQuery()) {
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return estoques;
    }
}
