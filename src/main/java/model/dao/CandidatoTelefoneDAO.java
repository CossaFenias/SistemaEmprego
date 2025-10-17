package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.vo.CandidatoTelefone;

public class CandidatoTelefoneDAO {
    private final Connection connection;

    public CandidatoTelefoneDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(CandidatoTelefone candidatoTelefone) throws SQLException {
        String sql = "INSERT INTO CandidatoTelefone (id_candidato, telefone) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, candidatoTelefone.getIdCandidato());
            statement.setString(2, candidatoTelefone.getTelefone());
            statement.executeUpdate();
        }
    }

    public CandidatoTelefone read(int idCandidato, String telefone) throws SQLException {
        String sql = "SELECT * FROM CandidatoTelefone WHERE id_candidato = ? AND telefone = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idCandidato);
            statement.setString(2, telefone);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new CandidatoTelefone(resultSet.getInt("id_candidato"), resultSet.getString("telefone"));
            }
        }
        return null;
    }

    public List<CandidatoTelefone> readAll(int idCandidato) throws SQLException {
        List<CandidatoTelefone> telefones = new ArrayList<>();
        String sql = "SELECT * FROM CandidatoTelefone WHERE id_candidato = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idCandidato);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                telefones.add(new CandidatoTelefone(resultSet.getInt("id_candidato"), resultSet.getString("telefone")));
            }
        }
        return telefones;
    }

    public void update(CandidatoTelefone candidatoTelefone) throws SQLException {
        String sql = "UPDATE CandidatoTelefone SET telefone = ? WHERE id_candidato = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, candidatoTelefone.getTelefone());
            statement.setInt(2, candidatoTelefone.getIdCandidato());
            statement.executeUpdate();
        }
    }

    public void delete(int idCandidato, String telefone) throws SQLException {
        String sql = "DELETE FROM CandidatoTelefone WHERE id_candidato = ? AND telefone = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idCandidato);
            statement.setString(2, telefone);
            statement.executeUpdate();
        }
    }
}