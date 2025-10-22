package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.vo.ClienteEndereco;

public class ClienteEnderecoDAO {

    private final Connection connection;

    public ClienteEnderecoDAO() {
        this.connection = ConexaoDAO.getConnection();
    }

    public void create(ClienteEndereco clienteEndereco) throws SQLException {
        String sql = "INSERT INTO ClienteEndereco (id_cliente, id_endereco) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, clienteEndereco.getIdCliente());
            stmt.setInt(2, clienteEndereco.getIdEndereco());
            stmt.executeUpdate();
        }
    }

    public ClienteEndereco read(int idCliente, int idEndereco) throws SQLException {
        String sql = "SELECT * FROM ClienteEndereco WHERE id_cliente = ? AND id_endereco = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            stmt.setInt(2, idEndereco);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new ClienteEndereco(rs.getInt("id_cliente"), rs.getInt("id_endereco"));
            }
        }
        return null;
    }

    public List<ClienteEndereco> readAll() throws SQLException {
        List<ClienteEndereco> clienteEnderecos = new ArrayList<>();
        String sql = "SELECT * FROM ClienteEndereco";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                clienteEnderecos.add(new ClienteEndereco(rs.getInt("id_cliente"), rs.getInt("id_endereco")));
            }
        }
        return clienteEnderecos;
    }

    public void update(ClienteEndereco clienteEndereco) throws SQLException {
        String sql = "UPDATE ClienteEndereco SET id_endereco = ? WHERE id_cliente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, clienteEndereco.getIdEndereco());
            stmt.setInt(2, clienteEndereco.getIdCliente());
            stmt.executeUpdate();
        }
    }

    public void delete(int idCliente, int idEndereco) throws SQLException {
        String sql = "DELETE FROM ClienteEndereco WHERE id_cliente = ? AND id_endereco = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            stmt.setInt(2, idEndereco);
            stmt.executeUpdate();
        }
    }
}