package controller;

import model.dao.EmpresaServicoDAO;
import model.vo.EmpresaServico;
import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

/**
 * Controller para gestão da relação entre Empresas e Serviços (N:N)
 */
public class EmpresaServicoController {
    private EmpresaServicoDAO empresaServicoDAO;

    public EmpresaServicoController() {
        this.empresaServicoDAO = new EmpresaServicoDAO();
    }

    /**
     * Associa uma empresa a um serviço
     */
    public boolean associarEmpresaServico(int idEmpresa, int idServico) {
        try {
            EmpresaServico associacao = new EmpresaServico(idEmpresa, idServico);
            empresaServicoDAO.create(associacao);

            JOptionPane.showMessageDialog(null,
                    "Serviço associado à empresa com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao associar serviço: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Remove associação entre empresa e serviço
     */
    public boolean removerAssociacaoEmpresaServico(int idEmpresa, int idServico) {
        try {
            empresaServicoDAO.delete(idEmpresa, idServico);

            JOptionPane.showMessageDialog(null,
                    "Associação removida com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao remover associação: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Lista todos os serviços de uma empresa
     */
    public List<EmpresaServico> listarServicosPorEmpresa(int idEmpresa) {
        try {
            List<EmpresaServico> todas = empresaServicoDAO.readAll();
            return todas.stream()
                    .filter(es -> es.getIdEmpresa() == idEmpresa)
                    .toList();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao listar serviços da empresa: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    /**
     * Verifica se existe associação entre empresa e serviço
     */
    public boolean verificarAssociacaoExistente(int idEmpresa, int idServico) {
        try {
            List<EmpresaServico> todas = empresaServicoDAO.readAll();
            return todas.stream()
                    .anyMatch(es -> es.getIdEmpresa() == idEmpresa && es.getIdServico() == idServico);
        } catch (SQLException e) {
            return false;
        }
    }
}