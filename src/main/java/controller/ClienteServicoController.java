package controller;

import model.dao.ClienteServicoDAO;
import model.vo.ClienteServico;
import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

/**
 * Controller para gestão da relação entre Clientes e Serviços (N:N)
 */
public class ClienteServicoController {
    private ClienteServicoDAO clienteServicoDAO;

    public ClienteServicoController() {
        this.clienteServicoDAO = new ClienteServicoDAO();
    }

    /**
     * Associa um cliente a um serviço
     */
    public boolean associarClienteServico(int idCliente, int idServico) {
        try {
            ClienteServico associacao = new ClienteServico(idCliente, idServico);
            clienteServicoDAO.create(associacao);

            JOptionPane.showMessageDialog(null,
                    "Serviço associado ao cliente com sucesso!", "Sucesso",
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
     * Remove associação entre cliente e serviço
     */
    public boolean removerAssociacaoClienteServico(int idCliente, int idServico) {
        try {
            clienteServicoDAO.delete(idCliente, idServico);

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
     * Lista todos os serviços de um cliente
     */
    public List<ClienteServico> listarServicosPorCliente(int idCliente) {
        try {
            List<ClienteServico> todas = clienteServicoDAO.readAll();
            return todas.stream()
                    .filter(cs -> cs.getIdCliente() == idCliente)
                    .toList();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao listar serviços do cliente: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    /**
     * Verifica se existe associação entre cliente e serviço
     */
    public boolean verificarAssociacaoExistente(int idCliente, int idServico) {
        try {
            ClienteServico associacao = clienteServicoDAO.read(idCliente, idServico);
            return associacao != null;
        } catch (SQLException e) {
            return false;
        }
    }
}