package controller;

import model.dao.*;
import model.vo.*;
import view.CadastroCandidato;
import view.PainelCandidatoPrincipal;
import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

/**
 * Controller principal para gestão de Candidatos
 * Agrega operações de Candidato, Telefone, Endereço, Filiacao, Identificacao
 */
public class CandidatoController {
    private final CandidatoDAO candidatoDAO;
    private final CandidatoTelefoneDAO telefoneDAO;
    private final CandidatoEnderecoDAO enderecoDAO;
    private final FiliacaoDAO filiacaoDAO;
    private final IdentificacaoDAO identificacaoDAO;
    private final FormacaoAcademicaDAO formacaoDAO;
    private CadastroCandidato viewCadastro;
    private PainelCandidatoPrincipal viewPrincipal;

    public CandidatoController() {
        this.candidatoDAO = new CandidatoDAO();
        this.telefoneDAO = new CandidatoTelefoneDAO();
        this.enderecoDAO = new CandidatoEnderecoDAO();
        this.filiacaoDAO = new FiliacaoDAO();
        this.identificacaoDAO = new IdentificacaoDAO();
        this.formacaoDAO = new FormacaoAcademicaDAO();
        @SuppressWarnings(value = "unused")
        ExperienciaProfissionalDAO experienciaDAO = new ExperienciaProfissionalDAO();
    }

    /**
     * Inicializa a view de cadastro e configura os listeners
     */
    public void iniciarCadastro() {
        viewCadastro = new CadastroCandidato();
        configurarEventosCadastro();
        viewCadastro.setVisible(true);
    }

    /**
     * Inicializa a view principal do candidato
     */
    public void iniciarPainelPrincipal() {
        viewPrincipal = new PainelCandidatoPrincipal();
        viewPrincipal.setVisible(true);
    }

    /**
     * Configura todos os eventos da tela de cadastro
     */
    private void configurarEventosCadastro() {
        // Evento do botão Salvar
        viewCadastro.getBtnSalvar().addActionListener(e -> salvarCandidato());

        // Evento do botão Cancelar
        viewCadastro.getBtnCancelar().addActionListener(e -> viewCadastro.dispose());

        // Evento do botão Adicionar Formação
        viewCadastro.getBtnAddFormacao().addActionListener(e -> abrirFormacaoAcademica());

        // Evento do botão Adicionar Experiência
        viewCadastro.getBtnAddExperiencia().addActionListener(e -> abrirExperienciaProfissional());
    }

    /**
     * Salva um novo candidato com todos os dados relacionados
     */
    public void salvarCandidato() {
        try {
            // Validar dados obrigatórios
            if (!validarDadosCandidato()) {
                return;
            }

            // Criar e salvar o candidato principal
            Candidato candidato = criarCandidatoFromView();
            // Nota: O ID é gerado automaticamente pelo banco
            candidatoDAO.create(candidato);

            // Buscar o ID gerado (assumindo que é o último inserido)
            List<Candidato> candidatos = candidatoDAO.readAll();
            Candidato candidatoSalvo = candidatos.get(candidatos.size() - 1);
            int idCandidato = candidatoSalvo.getIdCandidato();

            // Salvar telefones
            salvarTelefones(idCandidato);

            // Salvar filiação
            salvarFiliacao(idCandidato);

            // Salvar identificação
            salvarIdentificacao(idCandidato);

            JOptionPane.showMessageDialog(viewCadastro,
                    "Candidato cadastrado com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);

            viewCadastro.dispose();
            iniciarPainelPrincipal();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewCadastro,
                    "Erro ao salvar candidato: " + ex.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Valida os dados obrigatórios do candidato
     */
    private boolean validarDadosCandidato() {
        if (viewCadastro.getTxtApelido().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(viewCadastro,
                    "O campo Apelido é obrigatório!", "Validação",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (viewCadastro.getTxtNomes().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(viewCadastro,
                    "O campo Nomes é obrigatório!", "Validação",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (viewCadastro.getTxtEmail().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(viewCadastro,
                    "O campo Email é obrigatório!", "Validação",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    /**
     * Cria objeto Candidato a partir dos dados da view
     */
    private Candidato criarCandidatoFromView() {
        Candidato candidato = new Candidato();
        candidato.setApelido(viewCadastro.getTxtApelido().getText().trim());
        candidato.setNome(viewCadastro.getTxtNomes().getText().trim());
        candidato.setGenero(viewCadastro.getTxtGenero().getText().charAt(0));
        // Conversão de data necessária aqui
        // candidato.setDataNascimento(...);
        candidato.setEmail(viewCadastro.getTxtEmail().getText().trim());
        candidato.setNacionalidade(viewCadastro.getTxtNacionalidade().getText().trim());
        candidato.setNaturalidade(viewCadastro.getTxtNaturalidade().getText().trim());

        return candidato;
    }

    /**
     * Salva os telefones do candidato
     */
    private void salvarTelefones(int idCandidato) throws SQLException {
        String telefone1 = viewCadastro.getTxtTel1().getText().trim();
        String telefone2 = viewCadastro.getTxtTell2().getText().trim();

        if (!telefone1.isEmpty()) {
            CandidatoTelefone tel1 = new CandidatoTelefone(idCandidato, telefone1);
            telefoneDAO.create(tel1);
        }

        if (!telefone2.isEmpty()) {
            CandidatoTelefone tel2 = new CandidatoTelefone(idCandidato, telefone2);
            telefoneDAO.create(tel2);
        }
    }

    /**
     * Salva os dados de filiação do candidato
     */
    private void salvarFiliacao(int idCandidato) throws SQLException {
        String nomePai = viewCadastro.getTxtNomePai().getText().trim();
        String nomeMae = viewCadastro.getTxtNomeMae().getText().trim();

        Filiacao filiacao = new Filiacao(idCandidato, nomePai, nomeMae);
        filiacaoDAO.create(filiacao);
    }

    /**
     * Salva os dados de identificação do candidato
     */
    private void salvarIdentificacao(int idCandidato) throws SQLException {
        String numeroBI = viewCadastro.getTxtNumBI().getText().trim();
        String nuit = viewCadastro.getTxtNuit().getText().trim();

        // Conversão de data necessária aqui
        // LocalDate dataEmissao = ...;

        Identificacao identificacao = new Identificacao(idCandidato, numeroBI, null, nuit);
        identificacaoDAO.create(identificacao);
    }

    /**
     * Abre a tela de formação acadêmica
     */
    private void abrirFormacaoAcademica() {
        FormacaoAcademicaController formacaoController = new FormacaoAcademicaController();
        formacaoController.iniciarCadastroFormacao();
    }

    /**
     * Abre a tela de experiência profissional
     */
    private void abrirExperienciaProfissional() {
        ExperienciaProfissionalController experienciaController = new ExperienciaProfissionalController();
        experienciaController.iniciarCadastroExperiencia();
    }

    /**
     * Busca candidato por ID
     */
    public Candidato buscarCandidato(int idCandidato) {
        try {
            return candidatoDAO.read(idCandidato);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao buscar candidato: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    /**
     * Lista todos os candidatos
     */
    public List<Candidato> listarTodosCandidatos() {
        try {
            return candidatoDAO.readAll();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao listar candidatos: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    /**
     * Atualiza dados do candidato
     */
    public boolean atualizarCandidato(Candidato candidato) {
        try {
            candidatoDAO.update(candidato);
            JOptionPane.showMessageDialog(null,
                    "Candidato atualizado com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao atualizar candidato: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Exclui candidato por ID
     */
    public boolean excluirCandidato(int idCandidato) {
        try {
            // Primeiro excluir dependências
            telefoneDAO.delete(idCandidato, ""); // Ajustar conforme necessidade
            filiacaoDAO.delete(idCandidato);
            identificacaoDAO.delete(idCandidato);

            // Depois excluir o candidato
            candidatoDAO.delete(idCandidato);

            JOptionPane.showMessageDialog(null,
                    "Candidato excluído com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao excluir candidato: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public CandidatoEnderecoDAO getEnderecoDAO() {
        return enderecoDAO;
    }

    public FormacaoAcademicaDAO getFormacaoDAO() {
        return formacaoDAO;
    }
}