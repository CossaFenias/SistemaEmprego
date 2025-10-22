package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.Empresa;

public class EmpresaDAO {
    private final Connection connection;

    public EmpresaDAO() {
        this.connection = ConexaoDAO.getConnection();
    }

    public void create(Empresa empresa) throws SQLException {
        String sql = "INSERT INTO Empresa (nome, sector, email) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, empresa.getNome());
            stmt.setString(2, empresa.getSector());
            stmt.setString(3, empresa.getEmail());
            stmt.executeUpdate();
        }
    }

    public Empresa read(int idEmpresa) throws SQLException {
        String sql = "SELECT * FROM Empresa WHERE id_empresa = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idEmpresa);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Empresa(rs.getInt("id_empresa"), rs.getString("nome"), rs.getString("sector"), rs.getString("email"));
            }
        }
        return null;
    }

    public List<Empresa> readAll() throws SQLException {
        List<Empresa> empresas = new ArrayList<>();
        String sql = "SELECT * FROM Empresa";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                empresas.add(new Empresa(rs.getInt("id_empresa"), rs.getString("nome"), rs.getString("sector"), rs.getString("email")));
            }
        }
        return empresas;
    }

    public void update(Empresa empresa) throws SQLException {
        String sql = "UPDATE Empresa SET nome = ?, sector = ?, email = ? WHERE id_empresa = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, empresa.getNome());
            stmt.setString(2, empresa.getSector());
            stmt.setString(3, empresa.getEmail());
            stmt.setInt(4, empresa.getIdEmpresa());
            stmt.executeUpdate();
        }
    }

    public void delete(int idEmpresa) throws SQLException {
        String sql = "DELETE FROM Empresa WHERE id_empresa = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idEmpresa);
            stmt.executeUpdate();
        }
    }
}