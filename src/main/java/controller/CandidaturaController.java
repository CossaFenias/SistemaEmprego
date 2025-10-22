package controller;

import model.dao.CandidaturaDAO;
import model.vo.Candidatura;
import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Controller para gestão de Candidaturas (Relação entre Candidatos e Vagas)
 */
public class CandidaturaController {
    private CandidaturaDAO candidaturaDAO;

    public CandidaturaController() {
        this.candidaturaDAO = new CandidaturaDAO();
    }

    /**
     * Realiza uma nova candidatura
     */
    public boolean realizarCandidatura(int idCandidato, int idVaga) {
        try {
            // Verificar se já existe candidatura
            if (verificarCandidaturaExistente(idCandidato, idVaga)) {
                JOptionPane.showMessageDialog(null,
                        "Você já se candidatou a esta vaga!", "Aviso",
                        JOptionPane.WARNING_MESSAGE);
                return false;
            }

            Candidatura candidatura = new Candidatura();
            candidatura.setIdCandidato(idCandidato);
            candidatura.setIdVaga(idVaga);
            candidatura.setDataCandidatura(LocalDate.now());
            candidatura.setStatus("Pendente");

            candidaturaDAO.create(candidatura);

            JOptionPane.showMessageDialog(null,
                    "Candidatura realizada com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao realizar candidatura: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Busca candidatura por ID
     */
    public Candidatura buscarCandidatura(int idCandidatura) {
        try {
            return candidaturaDAO.read(idCandidatura);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao buscar candidatura: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    /**
     * Lista todas as candidaturas
     */
    public List<Candidatura> listarTodasCandidaturas() {
        try {
            return candidaturaDAO.readAll();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao listar candidaturas: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    /**
     * Lista candidaturas por candidato
     */
    public List<Candidatura> listarCandidaturasPorCandidato(int idCandidato) {
        try {
            List<Candidatura> todas = candidaturaDAO.readAll();
            return todas.stream()
                    .filter(c -> c.getIdCandidato() == idCandidato)
                    .toList();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao listar candidaturas do candidato: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    /**
     * Lista candidaturas por vaga
     */
    public List<Candidatura> listarCandidaturasPorVaga(int idVaga) {
        try {
            List<Candidatura> todas = candidaturaDAO.readAll();
            return todas.stream()
                    .filter(c -> c.getIdVaga() == idVaga)
                    .toList();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao listar candidaturas da vaga: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    /**
     * Atualiza status da candidatura
     */
    public boolean atualizarStatusCandidatura(int idCandidatura, String novoStatus) {
        try {
            Candidatura candidatura = candidaturaDAO.read(idCandidatura);
            if (candidatura != null) {
                candidatura.setStatus(novoStatus);
                candidaturaDAO.update(candidatura);

                JOptionPane.showMessageDialog(null,
                        "Status da candidatura atualizado para: " + novoStatus, "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
            return false;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao atualizar status: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Cancela uma candidatura
     */
    public boolean cancelarCandidatura(int idCandidatura) {
        try {
            candidaturaDAO.delete(idCandidatura);
            JOptionPane.showMessageDialog(null,
                    "Candidatura cancelada com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao cancelar candidatura: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Verifica se já existe candidatura para a vaga
     */
    public boolean verificarCandidaturaExistente(int idCandidato, int idVaga) {
        try {
            List<Candidatura> todas = candidaturaDAO.readAll();
            return todas.stream()
                    .anyMatch(c -> c.getIdCandidato() == idCandidato && c.getIdVaga() == idVaga);
        } catch (SQLException e) {
            return false;
        }
    }
}
