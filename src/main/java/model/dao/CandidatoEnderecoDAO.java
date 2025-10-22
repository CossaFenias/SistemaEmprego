package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.vo.CandidatoEndereco;

public class CandidatoEnderecoDAO {

    private final Connection connection;

    public CandidatoEnderecoDAO() {
        this.connection = ConexaoDAO.getConnection();
    }

    public void create(CandidatoEndereco candidatoEndereco) throws SQLException {
        String sql = "INSERT INTO CandidatoEndereco (id_candidato, id_endereco) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, candidatoEndereco.getIdCandidato());
            stmt.setInt(2, candidatoEndereco.getIdEndereco());
            stmt.executeUpdate();
        }
    }

    public CandidatoEndereco read(int idCandidato) throws SQLException {
        String sql = "SELECT * FROM CandidatoEndereco WHERE id_candidato = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCandidato);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new CandidatoEndereco(rs.getInt("id_candidato"), rs.getInt("id_endereco"));
            }
        }
        return null;
    }

    public void update(CandidatoEndereco candidatoEndereco) throws SQLException {
        String sql = "UPDATE CandidatoEndereco SET id_endereco = ? WHERE id_candidato = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, candidatoEndereco.getIdEndereco());
            stmt.setInt(2, candidatoEndereco.getIdCandidato());
            stmt.executeUpdate();
        }
    }

    public void delete(int idCandidato) throws SQLException {
        String sql = "DELETE FROM CandidatoEndereco WHERE id_candidato = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCandidato);
            stmt.executeUpdate();
        }
    }
}