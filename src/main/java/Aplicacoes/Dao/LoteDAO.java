package Aplicacoes.Dao;

import Aplicacoes.BD.DatabaseConnection;
import Aplicacoes.Modelos.Lote;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoteDAO {
    public void adicionaLote(Lote lote) {
        String sql = "INSERT INTO Lotes (id_material, quantidade, tipo_acao, data) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, lote.getCodMaterial());
            stmt.setBigDecimal(2, lote.getQuantidade());
            stmt.setString(3, lote.getTipoAcao());
            stmt.setDate(4, new Date(lote.getDataEntrada().getTime()));

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    lote.setIdLote(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Lote buscarLotePorId(int id) {
        String sql = "SELECT * FROM Lotes WHERE id_lote = ?";
        Lote lote = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    lote = new Lote(
                            rs.getInt("id_lote"),
                            rs.getInt("id_material"),
                            rs.getBigDecimal("quantidade"),
                            rs.getString("tipo_acao"),
                            rs.getDate("data")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lote;
    }

    public List<Lote> listarLotes() {
        List<Lote> lotes = new ArrayList<>();
        String sql = "SELECT id_lote, id_material, quantidade, tipo_acao, data FROM Lotes";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Lote lote = new Lote(
                        rs.getInt("id_lote"),
                        rs.getInt("id_material"),
                        rs.getBigDecimal("quantidade"),
                        rs.getString("tipo_acao"),
                        rs.getDate("data")
                );
                lotes.add(lote);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lotes;
    }

    public void atualizarLote(Lote lote) {
        String sql = "UPDATE Lotes SET id_material = ?, quantidade = ?, tipo_acao = ?, data = ? WHERE id_lote = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, lote.getCodMaterial());
            stmt.setBigDecimal(2, lote.getQuantidade());
            stmt.setString(3, lote.getTipoAcao());
            stmt.setDate(4, new Date(lote.getDataEntrada().getTime()));
            stmt.setInt(5, lote.getIdLote());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarLote(int id) {
        String sql = "DELETE FROM Lotes WHERE id_lote = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
