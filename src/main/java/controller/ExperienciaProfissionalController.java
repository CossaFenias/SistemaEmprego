package controller;

import model.dao.ExperienciaProfissionalDAO;
import model.vo.ExperienciaProfissional;
import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Controller para gestão de Experiências Profissionais
 */
public class ExperienciaProfissionalController {
    private ExperienciaProfissionalDAO experienciaDAO;

    public ExperienciaProfissionalController() {
        this.experienciaDAO = new ExperienciaProfissionalDAO();
    }

    /**
     * Inicializa o cadastro de experiência profissional
     */
    public void iniciarCadastroExperiencia() {
        // Aqui seria criada uma view específica para experiência profissional
        JOptionPane.showMessageDialog(null,
                "Funcionalidade de cadastro de experiência profissional em desenvolvimento",
                "Em Desenvolvimento", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Salva uma nova experiência profissional
     */
    public boolean salvarExperiencia(ExperienciaProfissional experiencia) {
        try {
            experienciaDAO.create(experiencia);
            JOptionPane.showMessageDialog(null,
                    "Experiência profissional salva com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao salvar experiência: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Busca experiência por ID
     */
    public ExperienciaProfissional buscarExperiencia(int idExperiencia) {
        try {
            return experienciaDAO.read(idExperiencia);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao buscar experiência: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    /**
     * Lista todas as experiências de um candidato
     */
    public List<ExperienciaProfissional> listarExperienciasPorCandidato(int idCandidato) {
        try {
            List<ExperienciaProfissional> todas = experienciaDAO.readAll();
            return todas.stream()
                    .filter(exp -> exp.getIdCandidato() == idCandidato)
                    .toList();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao listar experiências: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    /**
     * Atualiza uma experiência profissional
     */
    public boolean atualizarExperiencia(ExperienciaProfissional experiencia) {
        try {
            experienciaDAO.update(experiencia);
            JOptionPane.showMessageDialog(null,
                    "Experiência atualizada com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao atualizar experiência: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Exclui uma experiência profissional
     */
    public boolean excluirExperiencia(int idExperiencia) {
        try {
            experienciaDAO.delete(idExperiencia);
            JOptionPane.showMessageDialog(null,
                    "Experiência excluída com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao excluir experiência: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Valida dados da experiência profissional
     */
    public boolean validarExperiencia(ExperienciaProfissional experiencia) {
        if (experiencia.getCargo() == null || experiencia.getCargo().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "O campo Cargo é obrigatório!", "Validação",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (experiencia.getInstituicaoEmpresa() == null || experiencia.getInstituicaoEmpresa().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "O campo Instituição/Empresa é obrigatório!", "Validação",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (experiencia.getDataInicio() == null) {
            JOptionPane.showMessageDialog(null,
                    "A data de início é obrigatória!", "Validação",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }
}