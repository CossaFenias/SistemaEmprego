package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.FormacaoAcademica;

public class FormacaoAcademicaDAO {
    private final Connection connection;

    public FormacaoAcademicaDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(FormacaoAcademica formacao) throws SQLException {
        String sql = "INSERT INTO FormacaoAcademica (curso, instituicao, nivel, ano_formacao, id_candidato) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, formacao.getCurso());
            stmt.setString(2, formacao.getInstituicao());
            stmt.setString(3, formacao.getNivel());
            stmt.setInt(4, formacao.getAnoFormacao());
            stmt.setInt(5, formacao.getIdCandidato());
            stmt.executeUpdate();
        }
    }

    public FormacaoAcademica read(int idFormacao) throws SQLException {
        String sql = "SELECT * FROM FormacaoAcademica WHERE id_formacao = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idFormacao);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new FormacaoAcademica(
                    rs.getInt("id_formacao"),
                    rs.getString("curso"),
                    rs.getString("instituicao"),
                    rs.getString("nivel"),
                    rs.getInt("ano_formacao"),
                    rs.getInt("id_candidato")
                );
            }
        }
        return null;
    }

    public List<FormacaoAcademica> readAll() throws SQLException {
        List<FormacaoAcademica> formacoes = new ArrayList<>();
        String sql = "SELECT * FROM FormacaoAcademica";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                formacoes.add(new FormacaoAcademica(
                    rs.getInt("id_formacao"),
                    rs.getString("curso"),
                    rs.getString("instituicao"),
                    rs.getString("nivel"),
                    rs.getInt("ano_formacao"),
                    rs.getInt("id_candidato")
                ));
            }
        }
        return formacoes;
    }

    public void update(FormacaoAcademica formacao) throws SQLException {
        String sql = "UPDATE FormacaoAcademica SET curso = ?, instituicao = ?, nivel = ?, ano_formacao = ?, id_candidato = ? WHERE id_formacao = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, formacao.getCurso());
            stmt.setString(2, formacao.getInstituicao());
            stmt.setString(3, formacao.getNivel());
            stmt.setInt(4, formacao.getAnoFormacao());
            stmt.setInt(5, formacao.getIdCandidato());
            stmt.setInt(6, formacao.getIdFormacao());
            stmt.executeUpdate();
        }
    }

    public void delete(int idFormacao) throws SQLException {
        String sql = "DELETE FROM FormacaoAcademica WHERE id_formacao = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idFormacao);
            stmt.executeUpdate();
        }
    }
}