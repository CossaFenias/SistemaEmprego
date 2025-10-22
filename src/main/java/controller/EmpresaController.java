package controller;

import model.dao.*;
import model.vo.*;
import view.CadastroEmpresa;
import view.PainelEmpresaPrincipal;
import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

/**
 * Controller para gestão de Empresas
 * Agrega operações de Empresa, Telefone, Endereço
 */
public class EmpresaController {
    private EmpresaDAO empresaDAO;
    private EmpresaTelefoneDAO telefoneDAO;
    private EmpresaEnderecoDAO enderecoDAO;
    private CadastroEmpresa viewCadastro;
    private PainelEmpresaPrincipal viewPrincipal;

    public EmpresaController() {
        this.empresaDAO = new EmpresaDAO();
        this.telefoneDAO = new EmpresaTelefoneDAO();
        this.enderecoDAO = new EmpresaEnderecoDAO();
    }

    /**
     * Inicializa a view de cadastro e configura os listeners
     */
    public void iniciarCadastro() {
        viewCadastro = new CadastroEmpresa();
        configurarEventosCadastro();
        viewCadastro.setVisible(true);
    }

    /**
     * Inicializa a view principal da empresa
     */
    public void iniciarPainelPrincipal() {
        viewPrincipal = new PainelEmpresaPrincipal();
        viewPrincipal.setVisible(true);
    }

    /**
     * Configura todos os eventos da tela de cadastro
     */
    private void configurarEventosCadastro() {
        // Evento do botão Salvar
        viewCadastro.getBtnSalvar().addActionListener(e -> salvarEmpresa());

        // Evento do botão Cancelar
        viewCadastro.getBtnCancelar().addActionListener(e -> viewCadastro.dispose());
    }

    /**
     * Salva uma nova empresa com todos os dados relacionados
     */
    public void salvarEmpresa() {
        try {
            // Validar dados obrigatórios
            if (!validarDadosEmpresa()) {
                return;
            }

            // Criar e salvar a empresa principal
            Empresa empresa = criarEmpresaFromView();
            empresaDAO.create(empresa);

            // Buscar o ID gerado
            List<Empresa> empresas = empresaDAO.readAll();
            Empresa empresaSalva = empresas.get(empresas.size() - 1);
            int idEmpresa = empresaSalva.getIdEmpresa();

            // Salvar telefones
            salvarTelefones(idEmpresa);

            JOptionPane.showMessageDialog(viewCadastro,
                    "Empresa cadastrada com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);

            viewCadastro.dispose();
            iniciarPainelPrincipal();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewCadastro,
                    "Erro ao salvar empresa: " + ex.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Valida os dados obrigatórios da empresa
     */
    private boolean validarDadosEmpresa() {
        if (viewCadastro.getTxtNomeEmpresa().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(viewCadastro,
                    "O campo Nome da Empresa é obrigatório!", "Validação",
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
     * Cria objeto Empresa a partir dos dados da view
     */
    private Empresa criarEmpresaFromView() {
        Empresa empresa = new Empresa();
        empresa.setNome(viewCadastro.getTxtNomeEmpresa().getText().trim());
        empresa.setSector(viewCadastro.getTxtSector().getText().trim());
        empresa.setEmail(viewCadastro.getTxtEmail().getText().trim());
        return empresa;
    }

    /**
     * Salva os telefones da empresa
     */
    private void salvarTelefones(int idEmpresa) throws SQLException {
        String telefone1 = viewCadastro.getTxtTel1().getText().trim();
        String telefone2 = viewCadastro.getTxtTel2().getText().trim();

        if (!telefone1.isEmpty()) {
            EmpresaTelefone tel1 = new EmpresaTelefone(idEmpresa, telefone1);
            telefoneDAO.create(tel1);
        }

        if (!telefone2.isEmpty()) {
            EmpresaTelefone tel2 = new EmpresaTelefone(idEmpresa, telefone2);
            telefoneDAO.create(tel2);
        }
    }

    /**
     * Busca empresa por ID
     */
    public Empresa buscarEmpresa(int idEmpresa) {
        try {
            return empresaDAO.read(idEmpresa);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao buscar empresa: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    /**
     * Lista todas as empresas
     */
    public List<Empresa> listarTodasEmpresas() {
        try {
            return empresaDAO.readAll();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao listar empresas: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    /**
     * Atualiza dados da empresa
     */
    public boolean atualizarEmpresa(Empresa empresa) {
        try {
            empresaDAO.update(empresa);
            JOptionPane.showMessageDialog(null,
                    "Empresa atualizada com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao atualizar empresa: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Exclui empresa por ID
     */
    public boolean excluirEmpresa(int idEmpresa) {
        try {
            empresaDAO.delete(idEmpresa);
            JOptionPane.showMessageDialog(null,
                    "Empresa excluída com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao excluir empresa: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}