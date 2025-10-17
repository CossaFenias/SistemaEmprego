package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.Provincia;

public class ProvinciaDAO {
    private final Connection connection;

    public ProvinciaDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Provincia provincia) throws SQLException {
        String sql = "INSERT INTO Provincia (nome) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, provincia.getNome());
            stmt.executeUpdate();
        }
    }

    public Provincia read(int idProvincia) throws SQLException {
        String sql = "SELECT * FROM Provincia WHERE id_provincia = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idProvincia);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Provincia(rs.getInt("id_provincia"), rs.getString("nome"));
            }
        }
        return null;
    }

    public List<Provincia> readAll() throws SQLException {
        List<Provincia> provincias = new ArrayList<>();
        String sql = "SELECT * FROM Provincia";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                provincias.add(new Provincia(rs.getInt("id_provincia"), rs.getString("nome")));
            }
        }
        return provincias;
    }

    public void update(Provincia provincia) throws SQLException {
        String sql = "UPDATE Provincia SET nome = ? WHERE id_provincia = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, provincia.getNome());
            stmt.setInt(2, provincia.getIdProvincia());
            stmt.executeUpdate();
        }
    }

    public void delete(int idProvincia) throws SQLException {
        String sql = "DELETE FROM Provincia WHERE id_provincia = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idProvincia);
            stmt.executeUpdate();
        }
    }
}