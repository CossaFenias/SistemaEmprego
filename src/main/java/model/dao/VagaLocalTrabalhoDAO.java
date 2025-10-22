package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.vo.VagaLocalTrabalho;

public class VagaLocalTrabalhoDAO {

    private final Connection connection;

    public VagaLocalTrabalhoDAO() {
        this.connection = ConexaoDAO.getConnection();
    }

    public void create(VagaLocalTrabalho vagaLocalTrabalho) throws SQLException {
        String sql = "INSERT INTO VagaLocalTrabalho (id_vaga, id_endereco) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, vagaLocalTrabalho.getIdVaga());
            stmt.setInt(2, vagaLocalTrabalho.getIdEndereco());
            stmt.executeUpdate();
        }
    }

    public VagaLocalTrabalho read(int idVaga, int idEndereco) throws SQLException {
        String sql = "SELECT * FROM VagaLocalTrabalho WHERE id_vaga = ? AND id_endereco = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idVaga);
            stmt.setInt(2, idEndereco);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new VagaLocalTrabalho(rs.getInt("id_vaga"), rs.getInt("id_endereco"));
            }
        }
        return null;
    }

    public List<VagaLocalTrabalho> readAll() throws SQLException {
        List<VagaLocalTrabalho> lista = new ArrayList<>();
        String sql = "SELECT * FROM VagaLocalTrabalho";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new VagaLocalTrabalho(rs.getInt("id_vaga"), rs.getInt("id_endereco")));
            }
        }
        return lista;
    }

    public void update(VagaLocalTrabalho vagaLocalTrabalho) throws SQLException {
        String sql = "UPDATE VagaLocalTrabalho SET id_endereco = ? WHERE id_vaga = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, vagaLocalTrabalho.getIdEndereco());
            stmt.setInt(2, vagaLocalTrabalho.getIdVaga());
            stmt.executeUpdate();
        }
    }

    public void delete(int idVaga, int idEndereco) throws SQLException {
        String sql = "DELETE FROM VagaLocalTrabalho WHERE id_vaga = ? AND id_endereco = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idVaga);
            stmt.setInt(2, idEndereco);
            stmt.executeUpdate();
        }
    }
}