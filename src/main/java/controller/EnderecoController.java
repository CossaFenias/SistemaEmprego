package controller;

import model.dao.EnderecoDAO;
import model.dao.DistritoDAO;
import model.dao.ProvinciaDAO;
import model.vo.Endereco;
import model.vo.Distrito;
import model.vo.Provincia;
import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

/**
 * Controller para gestão de Endereços
 */
public class EnderecoController {
    private EnderecoDAO enderecoDAO;
    private DistritoDAO distritoDAO;
    private ProvinciaDAO provinciaDAO;

    public EnderecoController() {
        this.enderecoDAO = new EnderecoDAO();
        this.distritoDAO = new DistritoDAO();
        this.provinciaDAO = new ProvinciaDAO();
    }

    /**
     * Cadastra um novo endereço
     */
    public boolean cadastrarEndereco(Endereco endereco) {
        try {
            if (!validarEndereco(endereco)) {
                return false;
            }

            enderecoDAO.create(endereco);
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao cadastrar endereço: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Busca endereço por ID
     */
    public Endereco buscarEndereco(int idEndereco) {
        try {
            return enderecoDAO.read(idEndereco);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao buscar endereço: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    /**
     * Lista todos os endereços
     */
    public List<Endereco> listarTodosEnderecos() {
        try {
            return enderecoDAO.readAll();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao listar endereços: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    /**
     * Atualiza um endereço
     */
    public boolean atualizarEndereco(Endereco endereco) {
        try {
            enderecoDAO.update(endereco);
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao atualizar endereço: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Exclui um endereço
     */
    public boolean excluirEndereco(int idEndereco) {
        try {
            enderecoDAO.delete(idEndereco);
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao excluir endereço: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Lista todas as províncias
     */
    public List<Provincia> listarTodasProvincias() {
        try {
            return provinciaDAO.readAll();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao listar províncias: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    /**
     * Lista distritos por província
     */
    public List<Distrito> listarDistritosPorProvincia(int idProvincia) {
        try {
            List<Distrito> todos = distritoDAO.readAll();
            return todos.stream()
                    .filter(distrito -> distrito.getIdProvincia() == idProvincia)
                    .toList();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao listar distritos: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    /**
     * Valida dados do endereço
     */
    private boolean validarEndereco(Endereco endereco) {
        if (endereco.getRuaAvenida() == null || endereco.getRuaAvenida().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "O campo Rua/Avenida é obrigatório!", "Validação",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (endereco.getNumero() == null || endereco.getNumero().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "O campo Número é obrigatório!", "Validação",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }
}