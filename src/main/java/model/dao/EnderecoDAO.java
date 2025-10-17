package model.dao;

import model.vo.Endereco;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDAO {
    private final Connection connection;

    public EnderecoDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Endereco endereco) throws SQLException {
        String sql = "INSERT INTO Endereco (id_distrito, rua_avenida, numero) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, endereco.getIdDistrito());
            stmt.setString(2, endereco.getRuaAvenida());
            stmt.setString(3, endereco.getNumero());
            stmt.executeUpdate();
        }
    }

    public Endereco read(int idEndereco) throws SQLException {
        String sql = "SELECT * FROM Endereco WHERE id_endereco = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idEndereco);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Endereco(
                    rs.getInt("id_endereco"),
                    rs.getInt("id_distrito"),
                    rs.getString("rua_avenida"),
                    rs.getString("numero")
                );
            }
        }
        return null;
    }

    public List<Endereco> readAll() throws SQLException {
        List<Endereco> enderecos = new ArrayList<>();
        String sql = "SELECT * FROM Endereco";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                enderecos.add(new Endereco(
                    rs.getInt("id_endereco"),
                    rs.getInt("id_distrito"),
                    rs.getString("rua_avenida"),
                    rs.getString("numero")
                ));
            }
        }
        return enderecos;
    }

    public void update(Endereco endereco) throws SQLException {
        String sql = "UPDATE Endereco SET id_distrito = ?, rua_avenida = ?, numero = ? WHERE id_endereco = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, endereco.getIdDistrito());
            stmt.setString(2, endereco.getRuaAvenida());
            stmt.setString(3, endereco.getNumero());
            stmt.setInt(4, endereco.getIdEndereco());
            stmt.executeUpdate();
        }
    }

    public void delete(int idEndereco) throws SQLException {
        String sql = "DELETE FROM Endereco WHERE id_endereco = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idEndereco);
            stmt.executeUpdate();
        }
    }
}