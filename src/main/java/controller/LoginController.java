package controller;

import view.LoginFrame;
import view.MainFrame;
import view.PainelCandidatoPrincipal;
import view.PainelClientePrincipal;
import view.PainelEmpresaPrincipal;
import javax.swing.*;

/**
 * Controller para gestão de Autenticação e Login
 */
public class LoginController {
    private LoginFrame loginView;

    public LoginController() {
        this.loginView = new LoginFrame();
        configurarEventosLogin();
    }

    /**
     * Configura eventos da tela de login
     */
    private void configurarEventosLogin() {
        // Evento do botão Entrar
        loginView.getBtnEntrar().addActionListener(e -> realizarLogin());

        // Evento do botão Cancelar
        loginView.getBtnCancelar().addActionListener(e -> voltarParaMain());
    }

    /**
     * Realiza o processo de login
     */
    private void realizarLogin() {
        String email = loginView.getTxtEmail().getText().trim();
        String senha = new String(loginView.getSenha().getPassword()).trim();

        // Validações básicas
        if (email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(loginView,
                    "Por favor, preencha todos os campos!", "Validação",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Aqui seria implementada a lógica real de autenticação
        // Por enquanto, vamos simular um login básico
        if (autenticarUsuario(email, senha)) {
            redirecionarParaPainel(email);
        } else {
            JOptionPane.showMessageDialog(loginView,
                    "Email ou senha incorretos!", "Erro de Login",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Simula autenticação de usuário
     * Em uma implementação real, isso verificaria no banco de dados
     */
    private boolean autenticarUsuario(String email, String senha) {
        // Simulação básica - em produção, isso verificaria no banco
        return !email.isEmpty() && !senha.isEmpty();
    }

    /**
     * Redireciona para o painel apropriado baseado no tipo de usuário
     */
    private void redirecionarParaPainel(String email) {
        // Em uma implementação real, isso determinaria o tipo de usuário
        // baseado nos dados do banco. Por enquanto, vamos simular.

        // Simulação: se o email contém "candidato", vai para painel candidato
        if (email.toLowerCase().contains("candidato")) {
            PainelCandidatoPrincipal painelCandidato = new PainelCandidatoPrincipal();
            painelCandidato.setVisible(true);
        }
        // Se contém "cliente", vai para painel cliente
        else if (email.toLowerCase().contains("cliente")) {
            PainelClientePrincipal painelCliente = new PainelClientePrincipal();
            painelCliente.setVisible(true);
        }
        // Se contém "empresa", vai para painel empresa
        else if (email.toLowerCase().contains("empresa")) {
            PainelEmpresaPrincipal painelEmpresa = new PainelEmpresaPrincipal();
            painelEmpresa.setVisible(true);
        }
        // Padrão: painel candidato
        else {
            PainelCandidatoPrincipal painelCandidato = new PainelCandidatoPrincipal();
            painelCandidato.setVisible(true);
        }

        loginView.dispose();
        JOptionPane.showMessageDialog(null,
                "Login realizado com sucesso! Bem-vindo(a)!", "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Volta para a tela principal
     */
    private void voltarParaMain() {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
        loginView.dispose();
    }

    /**
     * Inicia o processo de recuperação de senha
     */
    public void iniciarRecuperacaoSenha() {
        String email = JOptionPane.showInputDialog(loginView,
                "Digite seu email para recuperação de senha:",
                "Recuperação de Senha", JOptionPane.QUESTION_MESSAGE);

        if (email != null && !email.trim().isEmpty()) {
            JOptionPane.showMessageDialog(loginView,
                    "Instruções de recuperação enviadas para: " + email,
                    "Recuperação de Senha", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}