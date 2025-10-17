package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.vo.Filiacao;

public class FiliacaoDAO {

    private final Connection connection;

    public FiliacaoDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Filiacao filiacao) throws SQLException {
        String sql = "INSERT INTO Filiacao (id_candidato, nome_pai, nome_mae) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, filiacao.getIdCandidato());
            stmt.setString(2, filiacao.getNomePai());
            stmt.setString(3, filiacao.getNomeMae());
            stmt.executeUpdate();
        }
    }

    public Filiacao read(int idCandidato) throws SQLException {
        String sql = "SELECT * FROM Filiacao WHERE id_candidato = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCandidato);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Filiacao(rs.getInt("id_candidato"), rs.getString("nome_pai"), rs.getString("nome_mae"));
            }
        }
        return null;
    }

    public void update(Filiacao filiacao) throws SQLException {
        String sql = "UPDATE Filiacao SET nome_pai = ?, nome_mae = ? WHERE id_candidato = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, filiacao.getNomePai());
            stmt.setString(2, filiacao.getNomeMae());
            stmt.setInt(3, filiacao.getIdCandidato());
            stmt.executeUpdate();
        }
    }

    public void delete(int idCandidato) throws SQLException {
        String sql = "DELETE FROM Filiacao WHERE id_candidato = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCandidato);
            stmt.executeUpdate();
        }
    }

    public List<Filiacao> getAll() throws SQLException {
        List<Filiacao> filiacaoList = new ArrayList<>();
        String sql = "SELECT * FROM Filiacao";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                filiacaoList.add(new Filiacao(rs.getInt("id_candidato"), rs.getString("nome_pai"), rs.getString("nome_mae")));
            }
        }
        return filiacaoList;
    }
}