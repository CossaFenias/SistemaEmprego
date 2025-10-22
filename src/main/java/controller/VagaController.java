package controller;

import model.dao.VagaDAO;
import model.dao.VagaLocalTrabalhoDAO;
import model.vo.Vaga;
import model.vo.VagaLocalTrabalho;
import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Controller para gestão de Vagas
 */
public class VagaController {
    private VagaDAO vagaDAO;
    private VagaLocalTrabalhoDAO vagaLocalTrabalhoDAO;

    public VagaController() {
        this.vagaDAO = new VagaDAO();
        this.vagaLocalTrabalhoDAO = new VagaLocalTrabalhoDAO();
    }

    /**
     * Publica uma nova vaga
     */
    public boolean publicarVaga(Vaga vaga) {
        try {
            if (!validarVaga(vaga)) {
                return false;
            }

            vagaDAO.create(vaga);

            // Buscar ID da vaga recém-criada
            List<Vaga> vagas = vagaDAO.readAll();
            Vaga vagaSalva = vagas.get(vagas.size() - 1);

            JOptionPane.showMessageDialog(null,
                    "Vaga publicada com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao publicar vaga: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Busca vaga por ID
     */
    public Vaga buscarVaga(int idVaga) {
        try {
            return vagaDAO.read(idVaga);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao buscar vaga: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    /**
     * Lista todas as vagas
     */
    public List<Vaga> listarTodasVagas() {
        try {
            return vagaDAO.readAll();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao listar vagas: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    /**
     * Lista vagas por empresa
     */
    public List<Vaga> listarVagasPorEmpresa(int idEmpresa) {
        try {
            List<Vaga> todas = vagaDAO.readAll();
            return todas.stream()
                    .filter(vaga -> vaga.getIdEmpresa() == idEmpresa)
                    .toList();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao listar vagas da empresa: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    /**
     * Lista vagas abertas
     */
    public List<Vaga> listarVagasAbertas() {
        try {
            List<Vaga> todas = vagaDAO.readAll();
            return todas.stream()
                    .filter(vaga -> "Aberta".equalsIgnoreCase(vaga.getEstado()))
                    .toList();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao listar vagas abertas: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    /**
     * Atualiza uma vaga
     */
    public boolean atualizarVaga(Vaga vaga) {
        try {
            vagaDAO.update(vaga);
            JOptionPane.showMessageDialog(null,
                    "Vaga atualizada com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao atualizar vaga: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Fecha uma vaga (altera estado para "Fechada")
     */
    public boolean fecharVaga(int idVaga) {
        try {
            Vaga vaga = vagaDAO.read(idVaga);
            if (vaga != null) {
                vaga.setEstado("Fechada");
                vagaDAO.update(vaga);
                JOptionPane.showMessageDialog(null,
                        "Vaga fechada com sucesso!", "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
            return false;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao fechar vaga: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Exclui uma vaga
     */
    public boolean excluirVaga(int idVaga) {
        try {
            vagaDAO.delete(idVaga);
            JOptionPane.showMessageDialog(null,
                    "Vaga excluída com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao excluir vaga: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Associa um local de trabalho à vaga
     */
    public boolean associarLocalTrabalho(int idVaga, int idEndereco) {
        try {
            VagaLocalTrabalho associacao = new VagaLocalTrabalho(idVaga, idEndereco);
            vagaLocalTrabalhoDAO.create(associacao);
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao associar local de trabalho: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Valida dados da vaga
     */
    private boolean validarVaga(Vaga vaga) {
        if (vaga.getTitulo() == null || vaga.getTitulo().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "O campo Título é obrigatório!", "Validação",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (vaga.getDescricao() == null || vaga.getDescricao().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "O campo Descrição é obrigatório!", "Validação",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (vaga.getDataPublicacao() == null) {
            vaga.setDataPublicacao(LocalDate.now());
        }

        if (vaga.getDataEncerramento() == null) {
            JOptionPane.showMessageDialog(null,
                    "A data de encerramento é obrigatória!", "Validação",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }
}