package controller;

import model.dao.*;
import model.vo.*;
import view.CadastroCliente;
import view.PainelClientePrincipal;
import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

/**
 * Controller para gestão de Clientes
 * Agrega operações de Cliente, Telefone, Endereço
 */
public class ClienteController {
    private final ClienteDAO clienteDAO;
    private final ClienteTelefoneDAO telefoneDAO;
    private final ClienteEnderecoDAO enderecoDAO;
    private CadastroCliente viewCadastro;
    private PainelClientePrincipal viewPrincipal;

    public ClienteController() {
        this.clienteDAO = new ClienteDAO();
        this.telefoneDAO = new ClienteTelefoneDAO();
        this.enderecoDAO = new ClienteEnderecoDAO();
    }

    /**
     * Inicializa a view de cadastro e configura os listeners
     */
    public void iniciarCadastro() {
        viewCadastro = new CadastroCliente();
        configurarEventosCadastro();
        viewCadastro.setVisible(true);
    }

    /**
     * Inicializa a view principal do cliente
     */
    public void iniciarPainelPrincipal() {
        viewPrincipal = new PainelClientePrincipal();
        viewPrincipal.setVisible(true);
    }

    /**
     * Configura todos os eventos da tela de cadastro
     */
    private void configurarEventosCadastro() {
        // Evento do botão Salvar
        viewCadastro.getBtnSalvar().addActionListener(e -> salvarCliente());

        // Evento do botão Cancelar
        viewCadastro.getBtnCancelar().addActionListener(e -> viewCadastro.dispose());
    }

    /**
     * Salva um novo cliente com todos os dados relacionados
     */
    public void salvarCliente() {
        try {
            // Validar dados obrigatórios
            if (!validarDadosCliente()) {
                return;
            }

            // Criar e salvar o cliente principal
            Cliente cliente = criarClienteFromView();
            clienteDAO.create(cliente);

            // Buscar o ID gerado
            List<Cliente> clientes = clienteDAO.readAll();
            Cliente clienteSalvo = clientes.get(clientes.size() - 1);
            int idCliente = clienteSalvo.getIdCliente();

            // Salvar telefones
            salvarTelefones(idCliente);

            JOptionPane.showMessageDialog(viewCadastro,
                    "Cliente cadastrado com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);

            viewCadastro.dispose();
            iniciarPainelPrincipal();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewCadastro,
                    "Erro ao salvar cliente: " + ex.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Valida os dados obrigatórios do cliente
     */
    private boolean validarDadosCliente() {
        if (viewCadastro.getTxtNome().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(viewCadastro,
                    "O campo Nome é obrigatório!", "Validação",
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
     * Cria objeto Cliente a partir dos dados da view
     */
    private Cliente criarClienteFromView() {
        Cliente cliente = new Cliente();
        cliente.setNome(viewCadastro.getTxtNome().getText().trim());
        cliente.setEmail(viewCadastro.getTxtEmail().getText().trim());
        return cliente;
    }

    /**
     * Salva os telefones do cliente
     */
    private void salvarTelefones(int idCliente) throws SQLException {
        String telefone1 = viewCadastro.getTxtTel1().getText().trim();
        String telefone2 = viewCadastro.getTxtTel2().getText().trim();

        if (!telefone1.isEmpty()) {
            ClienteTelefone tel1 = new ClienteTelefone(idCliente, telefone1);
            telefoneDAO.create(tel1);
        }

        if (!telefone2.isEmpty()) {
            ClienteTelefone tel2 = new ClienteTelefone(idCliente, telefone2);
            telefoneDAO.create(tel2);
        }
    }

    /**
     * Busca cliente por ID
     */
    public Cliente buscarCliente(int idCliente) {
        try {
            return clienteDAO.read(idCliente);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao buscar cliente: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    /**
     * Lista todos os clientes
     */
    public List<Cliente> listarTodosClientes() {
        try {
            return clienteDAO.readAll();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao listar clientes: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    /**
     * Atualiza dados do cliente
     */
    public boolean atualizarCliente(Cliente cliente) {
        try {
            clienteDAO.update(cliente);
            JOptionPane.showMessageDialog(null,
                    "Cliente atualizado com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao atualizar cliente: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Exclui cliente por ID
     */
    public boolean excluirCliente(int idCliente) {
        try {
            clienteDAO.delete(idCliente);
            JOptionPane.showMessageDialog(null,
                    "Cliente excluído com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao excluir cliente: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}