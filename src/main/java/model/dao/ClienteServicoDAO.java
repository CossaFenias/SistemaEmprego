package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.ClienteServico;

public class ClienteServicoDAO {
    private final Connection connection;

    public ClienteServicoDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(ClienteServico clienteServico) throws SQLException {
        String sql = "INSERT INTO ClienteServico (id_cliente, id_servico) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, clienteServico.getIdCliente());
            statement.setInt(2, clienteServico.getIdServico());
            statement.executeUpdate();
        }
    }

    public ClienteServico read(int idCliente, int idServico) throws SQLException {
        String sql = "SELECT * FROM ClienteServico WHERE id_cliente = ? AND id_servico = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idCliente);
            statement.setInt(2, idServico);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new ClienteServico(resultSet.getInt("id_cliente"), resultSet.getInt("id_servico"));
            }
        }
        return null;
    }

    public List<ClienteServico> readAll() throws SQLException {
        List<ClienteServico> clienteServicos = new ArrayList<>();
        String sql = "SELECT * FROM ClienteServico";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                clienteServicos.add(new ClienteServico(resultSet.getInt("id_cliente"), resultSet.getInt("id_servico")));
            }
        }
        return clienteServicos;
    }

    public void update(ClienteServico clienteServico) throws SQLException {
        String sql = "UPDATE ClienteServico SET id_servico = ? WHERE id_cliente = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, clienteServico.getIdServico());
            statement.setInt(2, clienteServico.getIdCliente());
            statement.executeUpdate();
        }
    }

    public void delete(int idCliente, int idServico) throws SQLException {
        String sql = "DELETE FROM ClienteServico WHERE id_cliente = ? AND id_servico = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idCliente);
            statement.setInt(2, idServico);
            statement.executeUpdate();
        }
    }
}