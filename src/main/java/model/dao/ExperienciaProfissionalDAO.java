package model.dao;

import model.vo.ExperienciaProfissional;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExperienciaProfissionalDAO {
    private final Connection connection;

    public ExperienciaProfissionalDAO() {
        this.connection = ConexaoDAO.getConnection();
    }

    public void create(ExperienciaProfissional experiencia) throws SQLException {
        String sql = "INSERT INTO ExperienciaProfissional (cargo, instituicao_empresa, data_inicio, data_fim, descricao, id_candidato) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, experiencia.getCargo());
            stmt.setString(2, experiencia.getInstituicaoEmpresa());
            stmt.setDate(3, Date.valueOf(experiencia.getDataInicio()));
            stmt.setDate(4, experiencia.getDataFim() != null ? Date.valueOf(experiencia.getDataFim()) : null);
            stmt.setString(5, experiencia.getDescricao());
            stmt.setInt(6, experiencia.getIdCandidato());
            stmt.executeUpdate();
        }
    }

    public ExperienciaProfissional read(int id) throws SQLException {
        String sql = "SELECT * FROM ExperienciaProfissional WHERE id_experiencia = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new ExperienciaProfissional(
                        rs.getInt("id_experiencia"),
                        rs.getString("cargo"),
                        rs.getString("instituicao_empresa"),
                        rs.getDate("data_inicio").toLocalDate(),
                        rs.getDate("data_fim") != null ? rs.getDate("data_fim").toLocalDate() : null,
                        rs.getString("descricao"),
                        rs.getInt("id_candidato"));
            }
        }
        return null;
    }

    public List<ExperienciaProfissional> readAll() throws SQLException {
        List<ExperienciaProfissional> experiencias = new ArrayList<>();
        String sql = "SELECT * FROM ExperienciaProfissional";
        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                experiencias.add(new ExperienciaProfissional(
                        rs.getInt("id_experiencia"),
                        rs.getString("cargo"),
                        rs.getString("instituicao_empresa"),
                        rs.getDate("data_inicio").toLocalDate(),
                        rs.getDate("data_fim") != null ? rs.getDate("data_fim").toLocalDate() : null,
                        rs.getString("descricao"),
                        rs.getInt("id_candidato")));
            }
        }
        return experiencias;
    }

    public void update(ExperienciaProfissional experiencia) throws SQLException {
        String sql = "UPDATE ExperienciaProfissional SET cargo = ?, instituicao_empresa = ?, data_inicio = ?, data_fim = ?, descricao = ? WHERE id_experiencia = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, experiencia.getCargo());
            stmt.setString(2, experiencia.getInstituicaoEmpresa());
            stmt.setDate(3, Date.valueOf(experiencia.getDataInicio()));
            stmt.setDate(4, experiencia.getDataFim() != null ? Date.valueOf(experiencia.getDataFim()) : null);
            stmt.setString(5, experiencia.getDescricao());
            stmt.setInt(6, experiencia.getIdExperiencia());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM ExperienciaProfissional WHERE id_experiencia = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}