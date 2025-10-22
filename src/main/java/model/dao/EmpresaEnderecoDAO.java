package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.vo.EmpresaEndereco;

public class EmpresaEnderecoDAO {

    private final Connection connection;

    public EmpresaEnderecoDAO() {
        this.connection = ConexaoDAO.getConnection();
    }

    public void create(EmpresaEndereco empresaEndereco) throws SQLException {
        String sql = "INSERT INTO EmpresaEndereco (id_empresa, id_endereco) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, empresaEndereco.getIdEmpresa());
            stmt.setInt(2, empresaEndereco.getIdEndereco());
            stmt.executeUpdate();
        }
    }

    public EmpresaEndereco read(int idEmpresa, int idEndereco) throws SQLException {
        String sql = "SELECT * FROM EmpresaEndereco WHERE id_empresa = ? AND id_endereco = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idEmpresa);
            stmt.setInt(2, idEndereco);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new EmpresaEndereco(rs.getInt("id_empresa"), rs.getInt("id_endereco"));
            }
        }
        return null;
    }

    public void update(EmpresaEndereco empresaEndereco) throws SQLException {
        String sql = "UPDATE EmpresaEndereco SET id_endereco = ? WHERE id_empresa = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, empresaEndereco.getIdEndereco());
            stmt.setInt(2, empresaEndereco.getIdEmpresa());
            stmt.executeUpdate();
        }
    }

    public void delete(int idEmpresa, int idEndereco) throws SQLException {
        String sql = "DELETE FROM EmpresaEndereco WHERE id_empresa = ? AND id_endereco = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idEmpresa);
            stmt.setInt(2, idEndereco);
            stmt.executeUpdate();
        }
    }
}