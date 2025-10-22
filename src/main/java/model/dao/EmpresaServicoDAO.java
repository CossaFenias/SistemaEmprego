package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.vo.EmpresaServico;

public class EmpresaServicoDAO {

    private final Connection connection;

    public EmpresaServicoDAO() {
        this.connection = ConexaoDAO.getConnection();
    }

    public void create(EmpresaServico empresaServico) throws SQLException {
        String sql = "INSERT INTO EmpresaServico (id_empresa, id_servico) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, empresaServico.getIdEmpresa());
            stmt.setInt(2, empresaServico.getIdServico());
            stmt.executeUpdate();
        }
    }

    public List<EmpresaServico> readAll() throws SQLException {
        List<EmpresaServico> lista = new ArrayList<>();
        String sql = "SELECT * FROM EmpresaServico";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                EmpresaServico empresaServico = new EmpresaServico();
                empresaServico.setIdEmpresa(rs.getInt("id_empresa"));
                empresaServico.setIdServico(rs.getInt("id_servico"));
                lista.add(empresaServico);
            }
        }
        return lista;
    }

    public void update(EmpresaServico empresaServico) throws SQLException {
        String sql = "UPDATE EmpresaServico SET id_servico = ? WHERE id_empresa = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, empresaServico.getIdServico());
            stmt.setInt(2, empresaServico.getIdEmpresa());
            stmt.executeUpdate();
        }
    }

    public void delete(int idEmpresa, int idServico) throws SQLException {
        String sql = "DELETE FROM EmpresaServico WHERE id_empresa = ? AND id_servico = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idEmpresa);
            stmt.setInt(2, idServico);
            stmt.executeUpdate();
        }
    }
}