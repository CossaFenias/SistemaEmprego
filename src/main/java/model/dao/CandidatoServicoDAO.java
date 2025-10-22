package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.CandidatoServico;

public class CandidatoServicoDAO {
    private final Connection connection;

    public CandidatoServicoDAO() {
        this.connection = ConexaoDAO.getConnection();
    }

    public void create(CandidatoServico candidatoServico) throws SQLException {
        String sql = "INSERT INTO CandidatoServico (id_candidato, id_servico) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, candidatoServico.getIdCandidato());
            stmt.setInt(2, candidatoServico.getIdServico());
            stmt.executeUpdate();
        }
    }

    public CandidatoServico read(int idCandidato, int idServico) throws SQLException {
        String sql = "SELECT * FROM CandidatoServico WHERE id_candidato = ? AND id_servico = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCandidato);
            stmt.setInt(2, idServico);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new CandidatoServico(rs.getInt("id_candidato"), rs.getInt("id_servico"));
            }
        }
        return null;
    }

    public List<CandidatoServico> readAll() throws SQLException {
        List<CandidatoServico> candidatoServicos = new ArrayList<>();
        String sql = "SELECT * FROM CandidatoServico";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                candidatoServicos.add(new CandidatoServico(rs.getInt("id_candidato"), rs.getInt("id_servico")));
            }
        }
        return candidatoServicos;
    }

    public void update(CandidatoServico candidatoServico) throws SQLException {
        String sql = "UPDATE CandidatoServico SET id_servico = ? WHERE id_candidato = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, candidatoServico.getIdServico());
            stmt.setInt(2, candidatoServico.getIdCandidato());
            stmt.executeUpdate();
        }
    }

    public void delete(int idCandidato, int idServico) throws SQLException {
        String sql = "DELETE FROM CandidatoServico WHERE id_candidato = ? AND id_servico = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCandidato);
            stmt.setInt(2, idServico);
            stmt.executeUpdate();
        }
    }
}