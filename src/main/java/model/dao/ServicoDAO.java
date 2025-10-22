package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.Servico;

public class ServicoDAO {
    private final Connection connection;

    public ServicoDAO() {
        this.connection = ConexaoDAO.getConnection();
    }

    public void create(Servico servico) throws SQLException {
        String sql = "INSERT INTO Servico (nome_servico, area_servico, descricao) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, servico.getNomeServico());
            stmt.setString(2, servico.getAreaServico());
            stmt.setString(3, servico.getDescricao());
            stmt.executeUpdate();
        }
    }

    public Servico read(int idServico) throws SQLException {
        String sql = "SELECT * FROM Servico WHERE id_servico = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idServico);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Servico(
                    rs.getInt("id_servico"),
                    rs.getString("nome_servico"),
                    rs.getString("area_servico"),
                    rs.getString("descricao")
                );
            }
        }
        return null;
    }

    public List<Servico> readAll() throws SQLException {
        List<Servico> servicos = new ArrayList<>();
        String sql = "SELECT * FROM Servico";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                servicos.add(new Servico(
                    rs.getInt("id_servico"),
                    rs.getString("nome_servico"),
                    rs.getString("area_servico"),
                    rs.getString("descricao")
                ));
            }
        }
        return servicos;
    }

    public void update(Servico servico) throws SQLException {
        String sql = "UPDATE Servico SET nome_servico = ?, area_servico = ?, descricao = ? WHERE id_servico = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, servico.getNomeServico());
            stmt.setString(2, servico.getAreaServico());
            stmt.setString(3, servico.getDescricao());
            stmt.setInt(4, servico.getIdServico());
            stmt.executeUpdate();
        }
    }

    public void delete(int idServico) throws SQLException {
        String sql = "DELETE FROM Servico WHERE id_servico = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idServico);
            stmt.executeUpdate();
        }
    }
}