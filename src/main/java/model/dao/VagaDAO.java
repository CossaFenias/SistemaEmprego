package model.dao;

import model.vo.Vaga;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VagaDAO {
    private final Connection connection;

    public VagaDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Vaga vaga) throws SQLException {
        String sql = "INSERT INTO Vaga (titulo, estado, requisitos, data_publicacao, descricao, actividades, regime, data_encerramento, id_empresa) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, vaga.getTitulo());
            stmt.setString(2, vaga.getEstado());
            stmt.setString(3, vaga.getRequisitos());
            stmt.setDate(4, Date.valueOf(vaga.getDataPublicacao()));
            stmt.setString(5, vaga.getDescricao());
            stmt.setString(6, vaga.getActividades());
            stmt.setString(7, vaga.getRegime());
            stmt.setDate(8, Date.valueOf(vaga.getDataEncerramento()));
            stmt.setInt(9, vaga.getIdEmpresa());
            stmt.executeUpdate();
        }
    }

    public Vaga read(int idVaga) throws SQLException {
        String sql = "SELECT * FROM Vaga WHERE id_vaga = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idVaga);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Vaga(
                    rs.getInt("id_vaga"),
                    rs.getString("titulo"),
                    rs.getString("estado"),
                    rs.getString("requisitos"),
                    rs.getDate("data_publicacao").toLocalDate(),
                    rs.getString("descricao"),
                    rs.getString("actividades"),
                    rs.getString("regime"),
                    rs.getDate("data_encerramento").toLocalDate(),
                    rs.getInt("id_empresa")
                );
            }
        }
        return null;
    }

    public List<Vaga> readAll() throws SQLException {
        List<Vaga> vagas = new ArrayList<>();
        String sql = "SELECT * FROM Vaga";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                vagas.add(new Vaga(
                    rs.getInt("id_vaga"),
                    rs.getString("titulo"),
                    rs.getString("estado"),
                    rs.getString("requisitos"),
                    rs.getDate("data_publicacao").toLocalDate(),
                    rs.getString("descricao"),
                    rs.getString("actividades"),
                    rs.getString("regime"),
                    rs.getDate("data_encerramento").toLocalDate(),
                    rs.getInt("id_empresa")
                ));
            }
        }
        return vagas;
    }

    public void update(Vaga vaga) throws SQLException {
        String sql = "UPDATE Vaga SET titulo = ?, estado = ?, requisitos = ?, data_publicacao = ?, descricao = ?, actividades = ?, regime = ?, data_encerramento = ?, id_empresa = ? WHERE id_vaga = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, vaga.getTitulo());
            stmt.setString(2, vaga.getEstado());
            stmt.setString(3, vaga.getRequisitos());
            stmt.setDate(4, Date.valueOf(vaga.getDataPublicacao()));
            stmt.setString(5, vaga.getDescricao());
            stmt.setString(6, vaga.getActividades());
            stmt.setString(7, vaga.getRegime());
            stmt.setDate(8, Date.valueOf(vaga.getDataEncerramento()));
            stmt.setInt(9, vaga.getIdEmpresa());
            stmt.setInt(10, vaga.getIdVaga());
            stmt.executeUpdate();
        }
    }

    public void delete(int idVaga) throws SQLException {
        String sql = "DELETE FROM Vaga WHERE id_vaga = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idVaga);
            stmt.executeUpdate();
        }
    }
}