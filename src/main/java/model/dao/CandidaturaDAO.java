package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.Candidatura;

public class CandidaturaDAO {
    private final Connection connection;

    public CandidaturaDAO() {
        this.connection = ConexaoDAO.getConnection();
    }

    public void create(Candidatura candidatura) throws SQLException {
        String sql = "INSERT INTO Candidatura (id_candidato, id_vaga, data_candidatura, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, candidatura.getIdCandidato());
            stmt.setInt(2, candidatura.getIdVaga());
            stmt.setDate(3, Date.valueOf(candidatura.getDataCandidatura()));
            stmt.setString(4, candidatura.getStatus());
            stmt.executeUpdate();
        }
    }

    public Candidatura read(int idCandidatura) throws SQLException {
        String sql = "SELECT * FROM Candidatura WHERE id_candidatura = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCandidatura);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Candidatura(
                    rs.getInt("id_candidatura"),
                    rs.getInt("id_candidato"),
                    rs.getInt("id_vaga"),
                    rs.getDate("data_candidatura").toLocalDate(),
                    rs.getString("status")
                );
            }
        }
        return null;
    }

    public List<Candidatura> readAll() throws SQLException {
        List<Candidatura> candidaturas = new ArrayList<>();
        String sql = "SELECT * FROM Candidatura";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                candidaturas.add(new Candidatura(
                    rs.getInt("id_candidatura"),
                    rs.getInt("id_candidato"),
                    rs.getInt("id_vaga"),
                    rs.getDate("data_candidatura").toLocalDate(),
                    rs.getString("status")
                ));
            }
        }
        return candidaturas;
    }

    public void update(Candidatura candidatura) throws SQLException {
        String sql = "UPDATE Candidatura SET id_candidato = ?, id_vaga = ?, data_candidatura = ?, status = ? WHERE id_candidatura = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, candidatura.getIdCandidato());
            stmt.setInt(2, candidatura.getIdVaga());
            stmt.setDate(3, Date.valueOf(candidatura.getDataCandidatura()));
            stmt.setString(4, candidatura.getStatus());
            stmt.setInt(5, candidatura.getIdCandidatura());
            stmt.executeUpdate();
        }
    }

    public void delete(int idCandidatura) throws SQLException {
        String sql = "DELETE FROM Candidatura WHERE id_candidatura = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCandidatura);
            stmt.executeUpdate();
        }
    }
}