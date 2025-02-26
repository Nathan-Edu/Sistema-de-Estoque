package Aplicacoes.Dao;

import Aplicacoes.BD.DatabaseConnection;
import Aplicacoes.Modelos.Material;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaterialDAO {

    public boolean idMaterialExists(int id) {
        String sql = "SELECT COUNT(*) FROM materiais WHERE id_material = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void adicionaMaterial(Material material) {
        String sql = "INSERT INTO materiais (descricao_curta, descricao_longa, quantidade, unidade_medida, deposito) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, material.getDescricao_curta());
            stmt.setString(2, material.getDescricao_Longa());
            stmt.setBigDecimal(3, material.getQuantidade());  // Usando setBigDecimal para quantidade
            stmt.setString(4, material.getUnidade_Medida());
            stmt.setString(5, material.getDeposito());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Material buscarMaterialPorId(int id) {
        String sql = "SELECT * FROM materiais WHERE id_material = ?";
        Material material = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    material = new Material(
                            rs.getInt("id_material"),
                            rs.getString("descricao_curta"),
                            rs.getString("descricao_longa"),
                            rs.getBigDecimal("quantidade"), // Ajustado para BigDecimal
                            rs.getString("unidade_medida"),
                            rs.getString("deposito")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return material;
    }

    public List<Material> listarMateriais() {
        List<Material> materiais = new ArrayList<>();
        String sql = "SELECT * FROM materiais";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Material material = new Material(
                        rs.getInt("id_material"),
                        rs.getString("descricao_curta"),
                        rs.getString("descricao_longa"),
                        rs.getBigDecimal("quantidade"), // Ajustado para BigDecimal
                        rs.getString("unidade_medida"),
                        rs.getString("deposito")
                );
                materiais.add(material);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materiais;
    }

    public void atualizarMaterial(Material material) {
        String sql = "UPDATE materiais SET descricao_curta = ?, descricao_longa = ?, quantidade = ?, unidade_medida = ?, deposito = ? WHERE id_material = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, material.getDescricao_curta());
            stmt.setString(2, material.getDescricao_Longa());
            stmt.setBigDecimal(3, material.getQuantidade());  // Usando setBigDecimal para quantidade
            stmt.setString(4, material.getUnidade_Medida());
            stmt.setString(5, material.getDeposito());
            stmt.setInt(6, material.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarMaterial(int id) {

        String deleteEstoqueSQL = "DELETE FROM estoque WHERE id_material = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement deleteEstoqueStmt = conn.prepareStatement(deleteEstoqueSQL)) {
            deleteEstoqueStmt.setInt(1, id);
            deleteEstoqueStmt.executeUpdate();

            String deleteMaterialSQL = "DELETE FROM materiais WHERE id_material = ?";
            try (PreparedStatement deleteMaterialStmt = conn.prepareStatement(deleteMaterialSQL)) {
                deleteMaterialStmt.setInt(1, id);
                deleteMaterialStmt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
