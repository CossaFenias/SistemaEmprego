package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.Distrito;

public class DistritoDAO {
    private final Connection connection;

    public DistritoDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Distrito distrito) throws SQLException {
        String sql = "INSERT INTO Distrito (nome, id_provincia) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, distrito.getNome());
            stmt.setInt(2, distrito.getIdProvincia());
            stmt.executeUpdate();
        }
    }

    public Distrito read(int idDistrito) throws SQLException {
        String sql = "SELECT * FROM Distrito WHERE id_distrito = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idDistrito);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Distrito(rs.getInt("id_distrito"), rs.getString("nome"), rs.getInt("id_provincia"));
            }
        }
        return null;
    }

    public List<Distrito> readAll() throws SQLException {
        List<Distrito> distritos = new ArrayList<>();
        String sql = "SELECT * FROM Distrito";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                distritos.add(new Distrito(rs.getInt("id_distrito"), rs.getString("nome"), rs.getInt("id_provincia")));
            }
        }
        return distritos;
    }

    public void update(Distrito distrito) throws SQLException {
        String sql = "UPDATE Distrito SET nome = ?, id_provincia = ? WHERE id_distrito = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, distrito.getNome());
            stmt.setInt(2, distrito.getIdProvincia());
            stmt.setInt(3, distrito.getIdDistrito());
            stmt.executeUpdate();
        }
    }

    public void delete(int idDistrito) throws SQLException {
        String sql = "DELETE FROM Distrito WHERE id_distrito = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idDistrito);
            stmt.executeUpdate();
        }
    }
}