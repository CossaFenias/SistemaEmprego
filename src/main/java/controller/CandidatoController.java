// Arquivo: src/main/java/controller/CandidatoController.java
package controller;

import model.dao.*;
import model.vo.*;
import view.CadastroCandidato;
import view.PainelCandidatoPerfil;

import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Controller principal para gerenciar operações relacionadas ao Candidato
 * Agrega operações de: Candidato, Telefones, Endereço e Filiação
 */
public class CandidatoController {
    private CadastroCandidato viewCadastro;
    private PainelCandidatoPerfil viewPerfil;
    private final CandidatoDAO candidatoDAO;
    private final CandidatoTelefoneDAO telefoneDAO;
    private final CandidatoEnderecoDAO enderecoDAO;
    private final FiliacaoDAO filiacaoDAO;

    public CandidatoController() {
        this.candidatoDAO = new CandidatoDAO();
        this.telefoneDAO = new CandidatoTelefoneDAO();
        this.enderecoDAO = new CandidatoEnderecoDAO();
        this.filiacaoDAO = new FiliacaoDAO();
    }

    /**
     * Inicializa a view de cadastro e configura os listeners
     */
    public void initCadastroView() {
        this.viewCadastro = new CadastroCandidato();
        configurarEventosCadastro();
    }

    /**
     * Inicializa a view de perfil e configura os listeners
     */
    public void initPerfilView() {
        this.viewPerfil = new PainelCandidatoPerfil();
        configurarEventosPerfil();
    }

    /**
     * Configura os eventos da tela de cadastro
     */
    private void configurarEventosCadastro() {
        // Evento do botão Salvar
        viewCadastro.getBtnSalvar().addActionListener(e -> salvarCandidato());

        // Evento do botão Cancelar
        viewCadastro.getBtnCancelar().addActionListener(e -> limparFormulario());

        // Evento do botão Adicionar Formação
        viewCadastro.getBtnAddFormacao().addActionListener(e -> adicionarFormacao());

        // Evento do botão Adicionar Experiência
        viewCadastro.getBtnAddExperiencia().addActionListener(e -> adicionarExperiencia());
    }

    /**
     * Configura os eventos da tela de perfil
     */
    private void configurarEventosPerfil() {
        // Evento do botão Editar
        viewPerfil.getEditarButton().addActionListener(e -> editarPerfil());
    }

    /**
     * Salva um novo candidato com todos os dados relacionados
     */
    public void salvarCandidato() {
        try {
            // Validar dados básicos
            if (!validarDadosCadastro()) {
                return;
            }

            // Criar e salvar o candidato principal
            Candidato candidato = criarCandidatoFromForm();
            candidatoDAO.create(candidato);

            // Obter o ID gerado (assumindo que é auto-increment)
            // Nota: Em implementação real, precisaria recuperar o ID gerado
            int idCandidato = obterUltimoIdCandidato();

            // Salvar telefones
            salvarTelefones(idCandidato);

            // Salvar filiação
            salvarFiliacao(idCandidato);

            // Salvar endereço (se houver dados)
            salvarEndereco(idCandidato);

            JOptionPane.showMessageDialog(viewCadastro,
                    "Candidato salvo com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            limparFormulario();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewCadastro,
                    "Erro ao salvar candidato: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(viewCadastro,
                    "Erro inesperado: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    /**
     * Cria objeto Candidato a partir dos dados do formulário
     */
    private Candidato criarCandidatoFromForm() {
        String apelido = viewCadastro.getTxtApelido().getText();
        String nome = viewCadastro.getTxtNomes().getText();
        char genero = viewCadastro.getTxtGenero().getText().isEmpty() ? ' ' :
                viewCadastro.getTxtGenero().getText().charAt(0);
        LocalDate dataNasc = parseData(viewCadastro.getTxtDataNasc().getText());
        String email = viewCadastro.getTxtEmail().getText();
        String nacionalidade = viewCadastro.getTxtNacionalidade().getText();
        String naturalidade = viewCadastro.getTxtNaturalidade().getText();

        return new Candidato(0, apelido, nome, genero, dataNasc, email, nacionalidade, naturalidade);
    }

    /**
     * Salva os telefones do candidato
     */
    private void salvarTelefones(int idCandidato) throws SQLException {
        String telefone1 = viewCadastro.getTxtTel1().getText();
        String telefone2 = viewCadastro.getTxtTell2().getText();

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
        String nomePai = viewCadastro.getTxtNomePai().getText();
        String nomeMae = viewCadastro.getTxtNomeMae().getText();

        if (!nomePai.isEmpty() || !nomeMae.isEmpty()) {
            Filiacao filiacao = new Filiacao(idCandidato, nomePai, nomeMae);
            filiacaoDAO.create(filiacao);
        }
    }

    /**
     * Salva o endereço do candidato
     * Nota: Assumindo que id_endereco é obtido de outra forma
     */
    private void salvarEndereco(int idCandidato) throws SQLException {
        // Em implementação real, precisaria criar o endereço primeiro
        // e depois relacionar com o candidato
        // int idEndereco = criarEnderecoFromForm();
        // CandidatoEndereco endereco = new CandidatoEndereco(idCandidato, idEndereco);
        // enderecoDAO.create(endereco);
    }

    /**
     * Valida os dados do formulário de cadastro
     */
    private boolean validarDadosCadastro() {
        // Validar campos obrigatórios
        if (viewCadastro.getTxtApelido().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(viewCadastro,
                    "Apelido é obrigatório", "Validação", JOptionPane.WARNING_MESSAGE);
            viewCadastro.getTxtApelido().requestFocus();
            return false;
        }

        if (viewCadastro.getTxtNomes().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(viewCadastro,
                    "Nome é obrigatório", "Validação", JOptionPane.WARNING_MESSAGE);
            viewCadastro.getTxtNomes().requestFocus();
            return false;
        }

        // Validar email
        String email = viewCadastro.getTxtEmail().getText();
        if (!email.isEmpty() && !email.contains("@")) {
            JOptionPane.showMessageDialog(viewCadastro,
                    "Email inválido", "Validação", JOptionPane.WARNING_MESSAGE);
            viewCadastro.getTxtEmail().requestFocus();
            return false;
        }

        // Validar data de nascimento
        String dataNasc = viewCadastro.getTxtDataNasc().getText();
        if (!dataNasc.isEmpty() && parseData(dataNasc) == null) {
            JOptionPane.showMessageDialog(viewCadastro,
                    "Data de nascimento inválida. Use o formato AAAA-MM-DD",
                    "Validação", JOptionPane.WARNING_MESSAGE);
            viewCadastro.getTxtDataNasc().requestFocus();
            return false;
        }

        return true;
    }

    /**
     * Converte string para LocalDate
     */
    private LocalDate parseData(String data) {
        if (data == null || data.trim().isEmpty()) {
            return null;
        }
        try {
            return LocalDate.parse(data);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Obtém o último ID de candidato inserido
     * Nota: Em implementação real, isso seria obtido do retorno do INSERT
     */
    private int obterUltimoIdCandidato() throws SQLException {
        // Esta é uma simplificação - em produção, o ID deveria ser retornado
        // pelo método create da DAO ou obtido via generated keys
        List<Candidato> candidatos = candidatoDAO.readAll();
        return candidatos.stream()
                .mapToInt(Candidato::getIdCandidato)
                .max()
                .orElse(0);
    }

    /**
     * Limpa todos os campos do formulário
     */
    private void limparFormulario() {
        viewCadastro.getTxtApelido().setText("");
        viewCadastro.getTxtNomes().setText("");
        viewCadastro.getTxtGenero().setText("");
        viewCadastro.getTxtDataNasc().setText("");
        viewCadastro.getTxtEmail().setText("");
        viewCadastro.getTxtNacionalidade().setText("");
        viewCadastro.getTxtNaturalidade().setText("");
        viewCadastro.getTxtTel1().setText("");
        viewCadastro.getTxtTell2().setText("");
        viewCadastro.getTxtNomePai().setText("");
        viewCadastro.getTxtNomeMae().setText("");
        viewCadastro.getTxtNumBI().setText("");
        viewCadastro.getTxtDataEmissao().setText("");
        viewCadastro.getTxtNuit().setText("");
    }

    /**
     * Carrega os dados do candidato para edição
     */
    public void carregarCandidatoParaEdicao(int idCandidato) {
        try {
            Candidato candidato = candidatoDAO.read(idCandidato);
            if (candidato != null) {
                preencherFormulario(candidato);
                carregarDadosRelacionados(idCandidato);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewCadastro,
                    "Erro ao carregar candidato: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Preenche o formulário com dados do candidato
     */
    private void preencherFormulario(Candidato candidato) {
        viewCadastro.getTxtApelido().setText(candidato.getApelido());
        viewCadastro.getTxtNomes().setText(candidato.getNome());
        viewCadastro.getTxtGenero().setText(String.valueOf(candidato.getGenero()));
        viewCadastro.getTxtDataNasc().setText(candidato.getDataNascimento().toString());
        viewCadastro.getTxtEmail().setText(candidato.getEmail());
        viewCadastro.getTxtNacionalidade().setText(candidato.getNacionalidade());
        viewCadastro.getTxtNaturalidade().setText(candidato.getNaturalidade());
    }

    /**
     * Carrega dados relacionados (telefones, filiação, endereço)
     */
    private void carregarDadosRelacionados(int idCandidato) throws SQLException {
        // Carregar telefones
        List<CandidatoTelefone> telefones = telefoneDAO.readAll(idCandidato);
        if (!telefones.isEmpty()) {
            viewCadastro.getTxtTel1().setText(telefones.get(0).getTelefone());
            if (telefones.size() > 1) {
                viewCadastro.getTxtTell2().setText(telefones.get(1).getTelefone());
            }
        }

        // Carregar filiação
        Filiacao filiacao = filiacaoDAO.read(idCandidato);
        if (filiacao != null) {
            viewCadastro.getTxtNomePai().setText(filiacao.getNomePai());
            viewCadastro.getTxtNomeMae().setText(filiacao.getNomeMae());
        }
    }

    /**
     * Atualiza o perfil do candidato
     */
    public void atualizarPerfil(int idCandidato) {
        try {
            Candidato candidato = criarCandidatoFromForm();
            candidato.setIdCandidato(idCandidato);
            candidatoDAO.update(candidato);

            // Atualizar dados relacionados
            atualizarDadosRelacionados(idCandidato);

            JOptionPane.showMessageDialog(viewCadastro,
                    "Perfil atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewCadastro,
                    "Erro ao atualizar perfil: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Atualiza dados relacionados do candidato
     */
    private void atualizarDadosRelacionados(int idCandidato) throws SQLException {
        // Atualizar filiação
        Filiacao filiacao = new Filiacao(idCandidato,
                viewCadastro.getTxtNomePai().getText(),
                viewCadastro.getTxtNomeMae().getText());

        if (filiacaoDAO.read(idCandidato) != null) {
            filiacaoDAO.update(filiacao);
        } else {
            filiacaoDAO.create(filiacao);
        }
    }

    /**
     * Método para adicionar formação (a implementar)
     */
    private void adicionarFormacao() {
        JOptionPane.showMessageDialog(viewCadastro,
                "Funcionalidade de adicionar formação será implementada em breve",
                "Em Desenvolvimento", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Método para adicionar experiência (a implementar)
     */
    private void adicionarExperiencia() {
        JOptionPane.showMessageDialog(viewCadastro,
                "Funcionalidade de adicionar experiência será implementada em breve",
                "Em Desenvolvimento", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Método para editar perfil (a implementar)
     */
    private void editarPerfil() {
        JOptionPane.showMessageDialog(viewPerfil,
                "Funcionalidade de editar perfil será implementada em breve",
                "Em Desenvolvimento", JOptionPane.INFORMATION_MESSAGE);
    }

    // Getters para as views
    public CadastroCandidato getViewCadastro() {
        return viewCadastro;
    }

    public PainelCandidatoPerfil getViewPerfil() {
        return viewPerfil;
    }
}