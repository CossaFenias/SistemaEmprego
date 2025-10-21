package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class CriarSenha extends JFrame {
    // Declaração dos componentes
    private JLabel title;
    private JLabel lblNovaSenha;
    private JLabel lblConfirmarSenha;
    private JPasswordField txtNovaSenha;
    private JPasswordField txtConfirmarSenha;
    private JButton btnSalvar;
    private JButton btnCancelar;
    private JCheckBox checkMostrarSenha;

    public CriarSenha() {
        super("Criar Senha - Sistema de Gestão de Emprego");
        initializeComponents();
        setupLayout();
        setupFrame();
    }

    private void initializeComponents() {
        title = new JLabel("Criar Senha");
        lblNovaSenha = new JLabel("Nova Senha:");
        lblConfirmarSenha = new JLabel("Confirmar Senha:");
        txtNovaSenha = new JPasswordField();
        txtConfirmarSenha = new JPasswordField();
        btnSalvar = new JButton("Salvar Senha");
        btnCancelar = new JButton("Cancelar");
        checkMostrarSenha = new JCheckBox("Mostrar senhas");
    }

    private void setupLayout() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        // Configura ícone da aplicação
        setupIcon();

        // Configuração do fundo
        getContentPane().setBackground(new Color(245, 245, 245));

        // Configuração do título
        configureLabel(title, 200, 20, 300, 39, Font.BOLD, 32, new Color(0, 0, 0));

        // Configuração das labels
        configureLabel(lblNovaSenha, 50, 100, 150, 29, Font.PLAIN, 18, new Color(70, 70, 70));
        configureLabel(lblConfirmarSenha, 50, 170, 200, 29, Font.PLAIN, 18, new Color(70, 70, 70));

        // Configuração dos campos de senha
        configurePasswordField(txtNovaSenha, 50, 130, 500, 35);
        configurePasswordField(txtConfirmarSenha, 50, 200, 500, 35);

        // Configuração do checkbox
        checkMostrarSenha.setBounds(50, 250, 150, 25);
        checkMostrarSenha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        checkMostrarSenha.setBackground(new Color(245, 245, 245));
        checkMostrarSenha.setForeground(new Color(70, 70, 70));
        checkMostrarSenha.setFocusPainted(false);

        // Configuração dos botões
        configureButton(btnSalvar, 150, 300, 150, 45, new Color(60, 179, 113), Color.WHITE, Font.BOLD, 16);
        configureButton(btnCancelar, 320, 300, 150, 45, new Color(220, 20, 60), Color.WHITE, Font.BOLD, 16);

        // Adiciona componentes ao frame
        addComponents();

        // Configura listeners
        setupListeners();
    }

    private void setupIcon() {
        try {
            URL iconURL = getClass().getResource("/images/icon.png");
            if (iconURL != null) {
                ImageIcon frameIcon = new ImageIcon(iconURL);
                setIconImage(frameIcon.getImage());
            } else {
                System.out.println("Ícone não encontrado: /images/icon.png");
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar o ícone: " + e.getMessage());
        }
    }

    private void configureLabel(JLabel label, int x, int y, int width, int height, int fontStyle, int fontSize, Color foregroundColor) {
        label.setBounds(x, y, width, height);
        label.setFont(new Font("Segoe UI", fontStyle, fontSize));
        label.setForeground(foregroundColor);
    }

    private void configurePasswordField(JPasswordField passwordField, int x, int y, int width, int height) {
        passwordField.setBounds(x, y, width, height);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        passwordField.setForeground(new Color(70, 70, 70));
        passwordField.setBackground(Color.WHITE);
        passwordField.setCaretColor(new Color(60, 140, 160));
        passwordField.setEchoChar('•');

        // Borda padrão e borda de foco
        Border defaultBorder = new LineBorder(new Color(200, 200, 200), 1);
        Border focusedBorder = new LineBorder(new Color(70, 70, 70), 2);

        passwordField.setBorder(defaultBorder);

        // Adiciona listener para mudar a borda ao focar
        passwordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                passwordField.setBorder(focusedBorder);
            }

            @Override
            public void focusLost(FocusEvent e) {
                passwordField.setBorder(defaultBorder);
            }
        });
    }

    private void configureButton(JButton button, int x, int y, int width, int height, Color backgroundColor, Color foregroundColor, int fontStyle, int fontSize) {
        button.setBounds(x, y, width, height);
        button.setBackground(backgroundColor);
        button.setForeground(foregroundColor);
        button.setFont(new Font("Segoe UI", fontStyle, fontSize));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setFocusPainted(false);

        // Efeito hover
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(backgroundColor.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(backgroundColor);
            }
        });
    }

    private void addComponents() {
        add(title);
        add(lblNovaSenha);
        add(lblConfirmarSenha);
        add(txtNovaSenha);
        add(txtConfirmarSenha);
        add(checkMostrarSenha);
        add(btnSalvar);
        add(btnCancelar);
    }

    private void setupListeners() {
        // Listener para mostrar/ocultar senhas
        checkMostrarSenha.addActionListener(_ -> {
            if (checkMostrarSenha.isSelected()) {
                txtNovaSenha.setEchoChar((char) 0);
                txtConfirmarSenha.setEchoChar((char) 0);
            } else {
                txtNovaSenha.setEchoChar('•');
                txtConfirmarSenha.setEchoChar('•');
            }
        });
    }

    private void setupFrame() {
        setResizable(false);
        setVisible(true);
    }

    // Getters para os componentes
    public JPasswordField getTxtNovaSenha() {
        return txtNovaSenha;
    }

    public JPasswordField getTxtConfirmarSenha() {
        return txtConfirmarSenha;
    }

    public JButton getBtnSalvar() {
        return btnSalvar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public JCheckBox getCheckMostrarSenha() {
        return checkMostrarSenha;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CriarSenha::new);
    }
}