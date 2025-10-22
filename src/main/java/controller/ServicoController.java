package controller;

import model.dao.ServicoDAO;
import model.vo.Servico;
import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

/**
 * Controller para gestão de Serviços
 */
public class ServicoController {
    private ServicoDAO servicoDAO;

    public ServicoController() {
        this.servicoDAO = new ServicoDAO();
    }

    /**
     * Cadastra um novo serviço
     */
    public boolean cadastrarServico(Servico servico) {
        try {
            if (!validarServico(servico)) {
                return false;
            }

            servicoDAO.create(servico);
            JOptionPane.showMessageDialog(null,
                    "Serviço cadastrado com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao cadastrar serviço: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Busca serviço por ID
     */
    public Servico buscarServico(int idServico) {
        try {
            return servicoDAO.read(idServico);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao buscar serviço: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    /**
     * Lista todos os serviços
     */
    public List<Servico> listarTodosServicos() {
        try {
            return servicoDAO.readAll();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao listar serviços: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    /**
     * Lista serviços por área
     */
    public List<Servico> listarServicosPorArea(String area) {
        try {
            List<Servico> todos = servicoDAO.readAll();
            return todos.stream()
                    .filter(servico -> area.equalsIgnoreCase(servico.getAreaServico()))
                    .toList();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao listar serviços por área: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    /**
     * Atualiza um serviço
     */
    public boolean atualizarServico(Servico servico) {
        try {
            servicoDAO.update(servico);
            JOptionPane.showMessageDialog(null,
                    "Serviço atualizado com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao atualizar serviço: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Exclui um serviço
     */
    public boolean excluirServico(int idServico) {
        try {
            servicoDAO.delete(idServico);
            JOptionPane.showMessageDialog(null,
                    "Serviço excluído com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao excluir serviço: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Valida dados do serviço
     */
    private boolean validarServico(Servico servico) {
        if (servico.getNomeServico() == null || servico.getNomeServico().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "O campo Nome do Serviço é obrigatório!", "Validação",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (servico.getAreaServico() == null || servico.getAreaServico().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "O campo Área do Serviço é obrigatório!", "Validação",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }
}