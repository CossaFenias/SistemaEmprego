package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.vo.Identificacao;

public class IdentificacaoDAO {

    private final Connection connection;

    public IdentificacaoDAO() {
        this.connection = ConexaoDAO.getConnection();
    }

    public void create(Identificacao identificacao) throws SQLException {
        String sql = "INSERT INTO Identificacao (id_candidato, numero_bi, data_emissao_bi, nuit) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, identificacao.getIdCandidato());
            stmt.setString(2, identificacao.getNumeroBI());
            stmt.setDate(3, java.sql.Date.valueOf(identificacao.getDataEmissaoBI()));
            stmt.setString(4, identificacao.getNuit());
            stmt.executeUpdate();
        }
    }

    public Identificacao read(int idCandidato) throws SQLException {
        String sql = "SELECT * FROM Identificacao WHERE id_candidato = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCandidato);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Identificacao(
                    rs.getInt("id_candidato"),
                    rs.getString("numero_bi"),
                    rs.getDate("data_emissao_bi").toLocalDate(),
                    rs.getString("nuit")
                );
            }
        }
        return null;
    }

    public void update(Identificacao identificacao) throws SQLException {
        String sql = "UPDATE Identificacao SET numero_bi = ?, data_emissao_bi = ?, nuit = ? WHERE id_candidato = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, identificacao.getNumeroBI());
            stmt.setDate(2, java.sql.Date.valueOf(identificacao.getDataEmissaoBI()));
            stmt.setString(3, identificacao.getNuit());
            stmt.setInt(4, identificacao.getIdCandidato());
            stmt.executeUpdate();
        }
    }

    public void delete(int idCandidato) throws SQLException {
        String sql = "DELETE FROM Identificacao WHERE id_candidato = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCandidato);
            stmt.executeUpdate();
        }
    }
}