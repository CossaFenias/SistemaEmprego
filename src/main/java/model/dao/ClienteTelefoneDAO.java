package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.vo.ClienteTelefone;

public class ClienteTelefoneDAO {
    private final Connection connection;

    public ClienteTelefoneDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(ClienteTelefone clienteTelefone) throws SQLException {
        String sql = "INSERT INTO ClienteTelefone (id_cliente, telefone) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, clienteTelefone.getIdCliente());
            stmt.setString(2, clienteTelefone.getTelefone());
            stmt.executeUpdate();
        }
    }

    public ClienteTelefone read(int idCliente, String telefone) throws SQLException {
        String sql = "SELECT * FROM ClienteTelefone WHERE id_cliente = ? AND telefone = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            stmt.setString(2, telefone);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new ClienteTelefone(rs.getInt("id_cliente"), rs.getString("telefone"));
            }
        }
        return null;
    }

    public List<ClienteTelefone> readAll(int idCliente) throws SQLException {
        List<ClienteTelefone> telefones = new ArrayList<>();
        String sql = "SELECT * FROM ClienteTelefone WHERE id_cliente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                telefones.add(new ClienteTelefone(rs.getInt("id_cliente"), rs.getString("telefone")));
            }
        }
        return telefones;
    }

    public void update(ClienteTelefone clienteTelefone) throws SQLException {
        String sql = "UPDATE ClienteTelefone SET telefone = ? WHERE id_cliente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, clienteTelefone.getTelefone());
            stmt.setInt(2, clienteTelefone.getIdCliente());
            stmt.executeUpdate();
        }
    }

    public void delete(int idCliente, String telefone) throws SQLException {
        String sql = "DELETE FROM ClienteTelefone WHERE id_cliente = ? AND telefone = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            stmt.setString(2, telefone);
            stmt.executeUpdate();
        }
    }
}