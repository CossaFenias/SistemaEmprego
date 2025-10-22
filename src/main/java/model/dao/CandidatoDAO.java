package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.Candidato;

public class CandidatoDAO {
    private final Connection connection;

    public CandidatoDAO() {
        this.connection = ConexaoDAO.getConnection();
    }

    public void create(Candidato candidato) throws SQLException {
        String sql = "INSERT INTO Candidato (apelido, nome, genero, data_nascimento, email, nacionalidade, naturalidade) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, candidato.getApelido());
            stmt.setString(2, candidato.getNome());
            stmt.setString(3, String.valueOf(candidato.getGenero()));
            stmt.setDate(4, Date.valueOf(candidato.getDataNascimento()));
            stmt.setString(5, candidato.getEmail());
            stmt.setString(6, candidato.getNacionalidade());
            stmt.setString(7, candidato.getNaturalidade());
            stmt.executeUpdate();
        }
    }

    public Candidato read(int idCandidato) throws SQLException {
        String sql = "SELECT * FROM Candidato WHERE id_candidato = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCandidato);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Candidato(
                    rs.getInt("id_candidato"),
                    rs.getString("apelido"),
                    rs.getString("nome"),
                    rs.getString("genero").charAt(0),
                    rs.getDate("data_nascimento").toLocalDate(),
                    rs.getString("email"),
                    rs.getString("nacionalidade"),
                    rs.getString("naturalidade")
                );
            }
        }
        return null;
    }

    public List<Candidato> readAll() throws SQLException {
        List<Candidato> candidatos = new ArrayList<>();
        String sql = "SELECT * FROM Candidato";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                candidatos.add(new Candidato(
                    rs.getInt("id_candidato"),
                    rs.getString("apelido"),
                    rs.getString("nome"),
                    rs.getString("genero").charAt(0),
                    rs.getDate("data_nascimento").toLocalDate(),
                    rs.getString("email"),
                    rs.getString("nacionalidade"),
                    rs.getString("naturalidade")
                ));
            }
        }
        return candidatos;
    }

    public void update(Candidato candidato) throws SQLException {
        String sql = "UPDATE Candidato SET apelido = ?, nome = ?, genero = ?, data_nascimento = ?, email = ?, nacionalidade = ?, naturalidade = ? WHERE id_candidato = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, candidato.getApelido());
            stmt.setString(2, candidato.getNome());
            stmt.setString(3, String.valueOf(candidato.getGenero()));
            stmt.setDate(4, Date.valueOf(candidato.getDataNascimento()));
            stmt.setString(5, candidato.getEmail());
            stmt.setString(6, candidato.getNacionalidade());
            stmt.setString(7, candidato.getNaturalidade());
            stmt.setInt(8, candidato.getIdCandidato());
            stmt.executeUpdate();
        }
    }

    public void delete(int idCandidato) throws SQLException {
        String sql = "DELETE FROM Candidato WHERE id_candidato = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCandidato);
            stmt.executeUpdate();
        }
    }
}