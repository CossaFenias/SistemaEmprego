package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.vo.EmpresaTelefone;

public class EmpresaTelefoneDAO {

    private final Connection connection;

    public EmpresaTelefoneDAO() {
        this.connection = ConexaoDAO.getConnection();
    }

    public void create(EmpresaTelefone empresaTelefone) throws SQLException {
        String sql = "INSERT INTO EmpresaTelefone (id_empresa, telefone) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, empresaTelefone.getIdEmpresa());
            stmt.setString(2, empresaTelefone.getTelefone());
            stmt.executeUpdate();
        }
    }

    public List<EmpresaTelefone> read(int idEmpresa) throws SQLException {
        List<EmpresaTelefone> telefones = new ArrayList<>();
        String sql = "SELECT * FROM EmpresaTelefone WHERE id_empresa = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idEmpresa);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                EmpresaTelefone empresaTelefone = new EmpresaTelefone();
                empresaTelefone.setIdEmpresa(rs.getInt("id_empresa"));
                empresaTelefone.setTelefone(rs.getString("telefone"));
                telefones.add(empresaTelefone);
            }
        }
        return telefones;
    }

    public void update(EmpresaTelefone empresaTelefone) throws SQLException {
        String sql = "UPDATE EmpresaTelefone SET telefone = ? WHERE id_empresa = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, empresaTelefone.getTelefone());
            stmt.setInt(2, empresaTelefone.getIdEmpresa());
            stmt.executeUpdate();
        }
    }

    public void delete(int idEmpresa, String telefone) throws SQLException {
        String sql = "DELETE FROM EmpresaTelefone WHERE id_empresa = ? AND telefone = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idEmpresa);
            stmt.setString(2, telefone);
            stmt.executeUpdate();
        }
    }
}