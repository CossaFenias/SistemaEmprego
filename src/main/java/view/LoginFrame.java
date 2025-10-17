package view;

import java.awt.BorderLayout;
import java.awt.Color; // Para um visual mais limpo e sem bordas 3D
import java.awt.Cursor; // Mantido para o foco dos campos de texto
import java.awt.Font;
import java.awt.GridBagConstraints; // Para lidar com o foco dos campos de texto
import java.awt.GridBagLayout;
import java.awt.Image; // Para lidar com o hover dos botões
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class LoginFrame extends JFrame {

    // Declaração dos componentes
    private JPanel mainPanel;
    private JPanel titlePanel;
    private JPanel buttonPanel;
    private JLabel title;
    private JTextField txtEmail; // Renomeado para consistência
    private JPasswordField senha; // JPasswordField para campos de senha
    private JLabel lblEmail, lblSenha;
    private JButton btnEntrar, btnCancelar;
//    private final String option;

    // Variável para controlar o tema (claro/escuro) - Opcional
    private final boolean darkMode = false; // Defina como true para ativar o modo escuro

    // Cores e configurações
    private Color primaryBackgroundLight;
    private Color fieldBackgroundLight;
    private Color fieldBorderLight;
    private Color fieldFocusBorderLight;
    private Color buttonAccentLight;
    private Color textColorLight;
    private Color primaryBackgroundDark;
    private Color fieldBackgroundDark;
    private Color fieldBorderDark;
    private Color fieldFocusBorderDark;
    private Color buttonAccentDark;
    private Color textColorDark;

    public LoginFrame() {
        super("Sistema Emprego - Login");
        initializeColors();
        initializeComponents();
        setupLayout();
        setupListeners();
        setupFrame();
//        this.option = option;
    }

    private void initializeColors() {
        // Modo Claro (default):
        primaryBackgroundLight = Color.WHITE;
        fieldBackgroundLight = new Color(235, 235, 235); // Cinza muito claro para campos
        fieldBorderLight = new Color(200, 200, 200); // Borda fina clara
        fieldFocusBorderLight = new Color(0x464646); // Borda mais escura ao focar
        buttonAccentLight = new Color(0x464646); // Cor principal dos botões
        textColorLight = new Color(50, 50, 50); // Texto geral

        // Modo Escuro (se darkMode = true):
        primaryBackgroundDark = new Color(30, 30, 30);
        fieldBackgroundDark = new Color(50, 50, 50);
        fieldBorderDark = new Color(80, 80, 80);
        fieldFocusBorderDark = new Color(150, 150, 150);
        buttonAccentDark = new Color(80, 80, 80);
        textColorDark = Color.WHITE;
    }

    private void initializeComponents() {
        mainPanel = new JPanel();
        titlePanel = new JPanel(new BorderLayout());
        buttonPanel = new JPanel(new GridBagLayout());

        title = new JLabel("Sistema de Gestão de Emprego - Login");
        lblEmail = new JLabel("Email:");
        lblSenha = new JLabel("Senha:");
        txtEmail = new JTextField();
        senha = new JPasswordField();
        btnEntrar = new JButton("Entrar");
        btnCancelar = new JButton("Cancelar");
    }

    private void setupLayout() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 400); // Tamanho ajustado para um layout mais espaçoso
        // Usaremos BorderLayout para o frame principal e um JPanel central com GridBagLayout
        setLayout(new BorderLayout());
        setLocationRelativeTo(null); // Centraliza a janela na tela

        // Define o ícone da aplicação
        setupIcon();

        // Configura cores atuais baseadas no tema
        Color currentPrimaryBackground = darkMode ? primaryBackgroundDark : primaryBackgroundLight;
        Color currentFieldBackground = darkMode ? fieldBackgroundDark : fieldBackgroundLight;
        Color currentFieldBorder = darkMode ? fieldBorderDark : fieldBorderLight;
        @SuppressWarnings("unused")
        Color currentFieldFocusBorder = darkMode ? fieldFocusBorderDark : fieldFocusBorderLight;
        Color currentButtonAccent = darkMode ? buttonAccentDark : buttonAccentLight;
        Color currentTextColor = darkMode ? textColorDark : textColorLight;

        // Configuração do painel principal para conter o conteúdo
        mainPanel.setBackground(currentPrimaryBackground);
        // Usaremos GridBagLayout para um layout flexível e centralizado
        mainPanel.setLayout(new GridBagLayout());
        getContentPane().add(mainPanel, BorderLayout.CENTER);

        // Configuração do título
        title.setForeground(currentTextColor);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24)); // Fonte moderna e legível, tamanho ajustado
        title.setHorizontalAlignment(SwingConstants.CENTER);
        // Adiciona um painel para o título para controlar o padding
        titlePanel.setBackground(currentPrimaryBackground);
        titlePanel.add(title, BorderLayout.CENTER);
        titlePanel.setBorder(new EmptyBorder(30, 20, 30, 20)); // Padding para o título

        // Configuração das labels e campos de texto
        Font labelFont = new Font("Segoe UI", Font.PLAIN, 14); // Fonte para as labels
        Font inputFont = new Font("Segoe UI", Font.PLAIN, 16); // Fonte para os inputs

        lblEmail.setForeground(currentTextColor);
        lblEmail.setFont(labelFont);

        txtEmail.setFont(inputFont);
        txtEmail.setBackground(currentFieldBackground);
        txtEmail.setForeground(currentTextColor);
        // Borda fina e sem arredondamento
        txtEmail.setBorder(new LineBorder(currentFieldBorder, 1));
        txtEmail.setCaretColor(currentTextColor); // Cor do cursor

        lblSenha.setForeground(currentTextColor);
        lblSenha.setFont(labelFont);

        senha.setFont(inputFont);
        senha.setBackground(currentFieldBackground);
        senha.setForeground(currentTextColor);
        // Borda fina e sem arredondamento
        senha.setBorder(new LineBorder(currentFieldBorder, 1));
        senha.setCaretColor(currentTextColor);

        // Configuração dos botões
        setupButtons(currentButtonAccent);

        // Usando GridBagConstraints para posicionar os componentes no mainPanel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(titlePanel, gbc); // Adiciona o painel do título

        gbc.gridy = 1;
        gbc.insets = new Insets(10, 50, 0, 50); // Padding para a label
        mainPanel.add(lblEmail, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(5, 50, 10, 50); // Padding para o campo
        gbc.ipady = 8; // Altura interna do componente
        mainPanel.add(txtEmail, gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(10, 50, 0, 50);
        gbc.ipady = 0; // Resetar altura interna para a label
        mainPanel.add(lblSenha, gbc);

        gbc.gridy = 4;
        gbc.insets = new Insets(5, 50, 20, 50);
        gbc.ipady = 8; // Altura interna do componente
        mainPanel.add(senha, gbc);

        // Painel para os botões para alinhá-los lado a lado
        buttonPanel.setBackground(currentPrimaryBackground);

        GridBagConstraints gbcButtons = new GridBagConstraints();
        gbcButtons.gridx = 0;
        gbcButtons.gridy = 0;
        gbcButtons.weightx = 0.5; // Divide o espaço igualmente
        gbcButtons.fill = GridBagConstraints.HORIZONTAL;
        gbcButtons.insets = new Insets(0, 0, 0, 10); // Espaçamento entre botões
        gbcButtons.ipady = 10; // Altura interna dos botões
        buttonPanel.add(btnEntrar, gbcButtons);

        gbcButtons.gridx = 1;
        gbcButtons.insets = new Insets(0, 10, 0, 0); // Espaçamento entre botões
        buttonPanel.add(btnCancelar, gbcButtons);

        gbc.gridy = 5;
        gbc.insets = new Insets(10, 50, 30, 50); // Padding para o painel de botões
        gbc.ipady = 0; // Resetar altura interna
        mainPanel.add(buttonPanel, gbc);
    }

    private void setupIcon() {
        // Certifique-se de ter um ícone (ex: login_icon.png) na pasta src/main/resources/images
        try {
            Image icon = new ImageIcon(getClass().getResource("/images/login_icon.png")).getImage();
            setIconImage(icon);
        } catch (Exception e) {
            System.err.println("Ícone não encontrado: /images/login_icon.png. Usando ícone padrão.");
        }
    }

    private void setupButtons(Color currentButtonAccent) {
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 16); // Fonte para os botões

        btnEntrar.setBackground(currentButtonAccent); // Cor de destaque (0x464646)
        btnEntrar.setForeground(Color.WHITE); // Texto branco
        btnEntrar.setFocusPainted(false);
        btnEntrar.setFont(buttonFont);
        btnEntrar.setBorder(new EmptyBorder(10, 20, 10, 20)); // Padding interno
        btnEntrar.setBorderPainted(false);

        // Para o botão Cancelar, as cores são invertidas em relação ao Entrar
        btnCancelar.setBackground(darkMode ? new Color(60, 60, 60) : Color.WHITE); // Fundo branco ou cinza claro
        btnCancelar.setForeground(darkMode ? Color.WHITE : currentButtonAccent); // Texto 0x464646 ou branco
        btnCancelar.setFocusPainted(false);
        btnCancelar.setFont(buttonFont);
        btnCancelar.setBorder(new EmptyBorder(10, 20, 10, 20)); // Padding interno
        btnCancelar.setBorderPainted(false);
    }

    private void setupListeners() {
        // Cores de hover para os botões
        Color btnEntrarHoverColor = darkMode ? new Color(100, 100, 100) : new Color(0x303030); // 0x464646 ligeiramente mais escuro
        Color btnCancelarHoverBackground = darkMode ? new Color(80, 80, 80) : new Color(240, 240, 240); // Branco ligeiramente mais escuro
        Color btnCancelarHoverForeground = darkMode ? Color.WHITE : new Color(0x303030); // Texto mais escuro

        // Listener para o botão Entrar
        btnEntrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnEntrar.setBackground(btnEntrarHoverColor);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Color currentButtonAccent = darkMode ? new Color(80, 80, 80) : new Color(0x464646);
                btnEntrar.setBackground(currentButtonAccent);
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        // Listener para o botão Cancelar
        btnCancelar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnCancelar.setBackground(btnCancelarHoverBackground);
                btnCancelar.setForeground(btnCancelarHoverForeground);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Color secondaryBackground = darkMode ? new Color(60, 60, 60) : Color.WHITE;
                Color currentButtonAccent = darkMode ? Color.WHITE : new Color(0x464646);
                btnCancelar.setBackground(secondaryBackground);
                btnCancelar.setForeground(currentButtonAccent);
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        // Cores de foco para os campos de texto
        Color currentFieldBorder = darkMode ? new Color(80, 80, 80) : new Color(200, 200, 200);
        Color currentFieldFocusBorder = darkMode ? new Color(150, 150, 150) : new Color(0x464646);

        // Listener de foco para txtEmail
        txtEmail.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                txtEmail.setBorder(new LineBorder(currentFieldFocusBorder, 1)); // Borda de destaque ao focar
            }

            @Override
            public void focusLost(FocusEvent e) {
                txtEmail.setBorder(new LineBorder(currentFieldBorder, 1)); // Volta à borda normal
            }
        });

        // Listener de foco para senha
        senha.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                senha.setBorder(new LineBorder(currentFieldFocusBorder, 1));
            }

            @Override
            public void focusLost(FocusEvent e) {
                senha.setBorder(new LineBorder(currentFieldBorder, 1));
            }
        });

        btnCancelar.addActionListener(_ -> {
            SwingUtilities.invokeLater(MainFrame::new);
            dispose();
        });

        btnEntrar.addActionListener(_ -> {

        });
    }

    private void setupFrame() {
        setResizable(false);
        setVisible(true);
    }

    public JTextField getTxtEmail() {
        return txtEmail;
    }

    public JTextField getSenha() {
        return senha;
    }

    public JButton getBtnEntrar() {
        return btnEntrar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }
}