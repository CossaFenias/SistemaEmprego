// Arquivo: src/main/java/controller/CandidatoPrincipalController.java
package controller;

import view.PainelCandidatoPrincipal;

import javax.swing.*;

/**
 * Controller principal para gerenciar a navegação do candidato
 * Controla o painel principal e coordena os outros controllers
 */
public class CandidatoPrincipalController {
    private PainelCandidatoPrincipal viewPrincipal;
    private CandidatoController candidatoController;
    private CandidatoServicoController servicoController;

    public CandidatoPrincipalController() {
        initControllers();
        initView();
        configurarNavegacao();
    }

    /**
     * Inicializa os controllers necessários
     */
    private void initControllers() {
        this.candidatoController = new CandidatoController();
        this.servicoController = new CandidatoServicoController();
    }

    /**
     * Inicializa a view principal
     */
    private void initView() {
        this.viewPrincipal = new PainelCandidatoPrincipal();
        this.viewPrincipal.setVisible(true);
    }

    /**
     * Configura a navegação entre os painéis
     */
    private void configurarNavegacao() {
        // Em uma implementação completa, aqui seriam configurados os listeners
        // para alternar entre os diferentes painéis (Perfil, Vagas, Serviços)
        // e integrar com os controllers correspondentes

        JOptionPane.showMessageDialog(viewPrincipal,
                "Sistema de Gestão de Candidatos inicializado com sucesso!\n" +
                        "Controllers configurados e prontos para uso.",
                "Sistema Inicializado", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Método principal para iniciar a aplicação
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new CandidatoPrincipalController();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Erro ao inicializar a aplicação: " + e.getMessage(),
                        "Erro de Inicialização", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        });
    }

    public PainelCandidatoPrincipal getViewPrincipal() {
        return viewPrincipal;
    }
}