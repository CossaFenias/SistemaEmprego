package controller;

import model.dao.CandidatoServicoDAO;
import model.vo.CandidatoServico;
import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

/**
 * Controller para gestão da relação entre Candidatos e Serviços (N:N)
 */
public class CandidatoServicoController {
    private final CandidatoServicoDAO candidatoServicoDAO;

    public CandidatoServicoController() {
        this.candidatoServicoDAO = new CandidatoServicoDAO();
    }

    /**
     * Associa um candidato a um serviço
     */
    public boolean associarCandidatoServico(int idCandidato, int idServico) {
        try {
            CandidatoServico associacao = new CandidatoServico(idCandidato, idServico);
            candidatoServicoDAO.create(associacao);

            JOptionPane.showMessageDialog(null,
                    "Serviço associado ao candidato com sucesso!", "Sucesso",
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
     * Remove associação entre candidato e serviço
     */
    public boolean removerAssociacaoCandidatoServico(int idCandidato, int idServico) {
        try {
            candidatoServicoDAO.delete(idCandidato, idServico);

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
     * Lista todos os serviços de um candidato
     */
    public List<CandidatoServico> listarServicosPorCandidato(int idCandidato) {
        try {
            // Implementar método específico no DAO se necessário
            return candidatoServicoDAO.readAll();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao listar serviços do candidato: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    /**
     * Verifica se existe associação entre candidato e serviço
     */
    public boolean verificarAssociacaoExistente(int idCandidato, int idServico) {
        try {
            CandidatoServico associacao = candidatoServicoDAO.read(idCandidato, idServico);
            return associacao != null;
        } catch (SQLException e) {
            return false;
        }
    }
}