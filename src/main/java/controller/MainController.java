package controller;

import view.MainFrame;
import view.LoginFrame;
import view.UserChoice;
import javax.swing.*;

/**
 * Controller principal para coordenação geral da aplicação
 */
public class MainController {
    private MainFrame mainFrame;
    private LoginFrame loginFrame;
    private UserChoice userChoice;

    public MainController() {
        iniciarAplicacao();
    }

    /**
     * Inicializa a aplicação mostrando a tela principal
     */
    public void iniciarAplicacao() {
        mainFrame = new MainFrame();
        configurarEventosMainFrame();
        mainFrame.setVisible(true);
    }

    /**
     * Configura eventos da tela principal
     */
    private void configurarEventosMainFrame() {
        mainFrame.getBtnEntrar().addActionListener(e -> abrirLogin());
        mainFrame.getBtnCadastro().addActionListener(e -> abrirUserChoice());
    }

    /**
     * Abre tela de login
     */
    private void abrirLogin() {
        loginFrame = new LoginFrame();
        mainFrame.dispose();
    }

    /**
     * Abre tela de escolha de usuário para cadastro
     */
    private void abrirUserChoice() {
        userChoice = new UserChoice();
        configurarEventosUserChoice();
        mainFrame.dispose();
    }

    /**
     * Configura eventos da tela de escolha de usuário
     */
    private void configurarEventosUserChoice() {
        userChoice.getCandidato().addActionListener(e -> abrirCadastroCandidato());
        userChoice.getCliente().addActionListener(e -> abrirCadastroCliente());
        userChoice.getEmpresa().addActionListener(e -> abrirCadastroEmpresa());
        userChoice.getVoltar().addActionListener(e -> voltarParaMain());
    }

    /**
     * Abre cadastro de candidato
     */
    private void abrirCadastroCandidato() {
        CandidatoController candidatoController = new CandidatoController();
        candidatoController.iniciarCadastro();
        userChoice.dispose();
    }

    /**
     * Abre cadastro de cliente
     */
    private void abrirCadastroCliente() {
        ClienteController clienteController = new ClienteController();
        clienteController.iniciarCadastro();
        userChoice.dispose();
    }

    /**
     * Abre cadastro de empresa
     */
    private void abrirCadastroEmpresa() {
        EmpresaController empresaController = new EmpresaController();
        empresaController.iniciarCadastro();
        userChoice.dispose();
    }

    /**
     * Volta para a tela principal
     */
    private void voltarParaMain() {
        mainFrame = new MainFrame();
        configurarEventosMainFrame();
        mainFrame.setVisible(true);
        userChoice.dispose();
    }

    /**
     * Método principal para iniciar a aplicação
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainController();
        });
    }
}