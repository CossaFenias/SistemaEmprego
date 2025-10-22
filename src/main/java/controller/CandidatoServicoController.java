// Arquivo: src/main/java/controller/CandidatoServicoController.java
package controller;

import model.dao.CandidatoServicoDAO;
import model.vo.CandidatoServico;
import view.PainelCandidatoServicos;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

/**
 * Controller para gerenciar a relação entre Candidatos e Serviços
 * Controla a entidade associativa CandidatoServico
 */
public class CandidatoServicoController {
    private PainelCandidatoServicos viewServicos;
    private CandidatoServicoDAO candidatoServicoDAO;

    public CandidatoServicoController() {
        this.candidatoServicoDAO = new CandidatoServicoDAO();
        initView();
    }

    /**
     * Inicializa a view e configura os eventos
     */
    private void initView() {
        this.viewServicos = new PainelCandidatoServicos();
        configurarEventosServicos();
        carregarServicos();
    }

    /**
     * Configura os eventos da tela de serviços
     */
    private void configurarEventosServicos() {
        // Configurar eventos dos botões de filtro
        viewServicos.getTodosServicosButton().addActionListener(e -> filtrarTodosServicos());
        viewServicos.getServicosContratadosButton().addActionListener(e -> filtrarServicosContratados());
        viewServicos.getServicosDisponiveisButton().addActionListener(e -> filtrarServicosDisponiveis());
    }

    /**
     * Carrega todos os serviços do candidato
     */
    public void carregarServicos() {
        try {
            List<CandidatoServico> servicos = candidatoServicoDAO.readAll();
            // Aqui você atualizaria a tabela na view com os serviços
            atualizarTabelaServicos(servicos);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewServicos,
                    "Erro ao carregar serviços: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Contrata um novo serviço para o candidato
     */
    public void contratarServico(int idCandidato, int idServico) {
        try {
            CandidatoServico candidatoServico = new CandidatoServico(idCandidato, idServico);

            // Verificar se já existe
            CandidatoServico existente = candidatoServicoDAO.read(idCandidato, idServico);
            if (existente != null) {
                JOptionPane.showMessageDialog(viewServicos,
                        "Serviço já contratado", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            candidatoServicoDAO.create(candidatoServico);
            JOptionPane.showMessageDialog(viewServicos,
                    "Serviço contratado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewServicos,
                    "Erro ao contratar serviço: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Remove um serviço contratado pelo candidato
     */
    public void removerServico(int idCandidato, int idServico) {
        try {
            int confirm = JOptionPane.showConfirmDialog(viewServicos,
                    "Tem certeza que deseja cancelar este serviço?",
                    "Confirmar Cancelamento", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                candidatoServicoDAO.delete(idCandidato, idServico);
                JOptionPane.showMessageDialog(viewServicos,
                        "Serviço cancelado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                carregarServicos(); // Recarregar a lista
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewServicos,
                    "Erro ao cancelar serviço: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Filtra e exibe todos os serviços
     */
    private void filtrarTodosServicos() {
        carregarServicos();
        JOptionPane.showMessageDialog(viewServicos,
                "Exibindo todos os serviços", "Filtro", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Filtra e exibe apenas serviços contratados
     */
    private void filtrarServicosContratados() {
        // Implementar lógica de filtro para serviços contratados
        JOptionPane.showMessageDialog(viewServicos,
                "Exibindo serviços contratados", "Filtro", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Filtra e exibe apenas serviços disponíveis
     */
    private void filtrarServicosDisponiveis() {
        // Implementar lógica de filtro para serviços disponíveis
        JOptionPane.showMessageDialog(viewServicos,
                "Exibindo serviços disponíveis", "Filtro", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Atualiza a tabela de serviços na view
     */
    private void atualizarTabelaServicos(List<CandidatoServico> servicos) {
        // Implementar atualização da tabela na view
        // Esta é uma implementação simplificada
        System.out.println("Carregados " + servicos.size() + " serviços");
    }

    /**
     * Obtém todos os serviços de um candidato específico
     */
    public List<CandidatoServico> getServicosPorCandidato(int idCandidato) {
        try {
            // Nota: Seria necessário adicionar este método na DAO
            // return candidatoServicoDAO.readAllByCandidato(idCandidato);
            return candidatoServicoDAO.readAll();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewServicos,
                    "Erro ao buscar serviços: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return List.of();
        }
    }

    public PainelCandidatoServicos getViewServicos() {
        return viewServicos;
    }
}