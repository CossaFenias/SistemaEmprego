package controller;

import model.dao.FormacaoAcademicaDAO;
import model.vo.FormacaoAcademica;
import view.InserirFormacaoAcademica;
import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

/**
 * Controller para gestão de Formações Acadêmicas
 */
public class FormacaoAcademicaController {
    private FormacaoAcademicaDAO formacaoDAO;
    private InserirFormacaoAcademica view;

    public FormacaoAcademicaController() {
        this.formacaoDAO = new FormacaoAcademicaDAO();
    }

    /**
     * Inicializa a view de cadastro de formação acadêmica
     */
    public void iniciarCadastroFormacao() {
        view = new InserirFormacaoAcademica();
        configurarEventosFormacao();
        view.setVisible(true);
    }

    /**
     * Configura eventos da tela de formação acadêmica
     */
    private void configurarEventosFormacao() {
        view.getBtnConfirmar().addActionListener(e -> salvarFormacao());
        view.getBtnCancelar().addActionListener(e -> view.dispose());
    }

    /**
     * Salva uma nova formação acadêmica
     */
    public void salvarFormacao() {
        try {
            if (!validarDadosFormacao()) {
                return;
            }

            FormacaoAcademica formacao = criarFormacaoFromView();
            formacaoDAO.create(formacao);

            JOptionPane.showMessageDialog(view,
                    "Formação acadêmica salva com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);

            view.dispose();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view,
                    "Erro ao salvar formação: " + ex.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Valida dados da formação acadêmica
     */
    private boolean validarDadosFormacao() {
        if (view.getTxtCurso().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(view,
                    "O campo Curso é obrigatório!", "Validação",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (view.getTxtInstituicao().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(view,
                    "O campo Instituição é obrigatório!", "Validação",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    /**
     * Cria objeto FormacaoAcademica a partir da view
     */
    private FormacaoAcademica criarFormacaoFromView() {
        FormacaoAcademica formacao = new FormacaoAcademica();
        formacao.setCurso(view.getTxtCurso().getText().trim());
        formacao.setInstituicao(view.getTxtInstituicao().getText().trim());
        formacao.setNivel(view.getCbNivelAcademico().getSelectedItem().toString());

        try {
            int anoFormacao = Integer.parseInt(view.getTxtAnoFormacao().getText().trim());
            formacao.setAnoFormacao(anoFormacao);
        } catch (NumberFormatException e) {
            formacao.setAnoFormacao(0); // Valor padrão
        }

        // ID do candidato deve ser definido conforme o contexto
        // formacao.setIdCandidato(idCandidato);

        return formacao;
    }

    /**
     * Busca formação por ID
     */
    public FormacaoAcademica buscarFormacao(int idFormacao) {
        try {
            return formacaoDAO.read(idFormacao);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao buscar formação: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    /**
     * Lista formações por candidato
     */
    public List<FormacaoAcademica> listarFormacoesPorCandidato(int idCandidato) {
        try {
            List<FormacaoAcademica> todas = formacaoDAO.readAll();
            return todas.stream()
                    .filter(f -> f.getIdCandidato() == idCandidato)
                    .toList();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao listar formações: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}