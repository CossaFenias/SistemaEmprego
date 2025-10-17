package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class MainFrame extends JFrame {
    // Declaração dos componentes
    private JPanel mainPanel;
    private JPanel titlePanel;
    private JPanel separatorPanel;
    private JLabel title;
    private JSeparator separator;
    private JButton btnEntrar;
    private JButton btnCadastro;

    // Cores e configurações
    private final boolean darkMode = false;
    private Color primaryBackgroundLight;
    private Color accentColorLight;
    private Color textColorLight;
    private Color primaryBackgroundDark;
    private Color accentColorDark;
    private Color textColorDark;

    public MainFrame() {
        super("Sistema de Gestão de Emprego");
        initializeColors();
        initializeComponents();
        setupLayout();
        setupListeners();
        setupFrame();
    }

    private void initializeColors() {
        // Paleta de cores
        primaryBackgroundLight = Color.WHITE;
        accentColorLight = new Color(0x464646);
        textColorLight = new Color(50, 50, 50);

        primaryBackgroundDark = new Color(30, 30, 30);
        accentColorDark = new Color(80, 80, 80);
        textColorDark = Color.WHITE;
    }

    private void initializeComponents() {
        mainPanel = new JPanel(new GridBagLayout());
        titlePanel = new JPanel(new BorderLayout());
        separatorPanel = new JPanel(new BorderLayout());

        title = new JLabel("SISTEMA DE GESTÃO DE EMPREGO");
        separator = new JSeparator(JSeparator.HORIZONTAL);
        btnEntrar = new JButton("Entrar");
        btnCadastro = new JButton("Cadastro");
    }

    private void setupLayout() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // Configura ícone da aplicação
        setupIcon();

        // Configura cores atuais baseadas no tema
        Color currentPrimaryBackground = darkMode ? primaryBackgroundDark : primaryBackgroundLight;
        Color currentAccentColor = darkMode ? accentColorDark : accentColorLight;
        Color currentTextColor = darkMode ? textColorDark : textColorLight;

        // Configuração do painel principal
        mainPanel.setBackground(currentPrimaryBackground);
        getContentPane().add(mainPanel, BorderLayout.CENTER);

        // Configuração do título
        title.setForeground(currentTextColor);
        title.setFont(new Font("Segoe UI", Font.BOLD, 36));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        titlePanel.setBackground(currentPrimaryBackground);
        titlePanel.add(title, BorderLayout.CENTER);
        titlePanel.setBorder(new EmptyBorder(50, 20, 30, 20));

        // Configuração do separador
        separator.setForeground(currentAccentColor);
        separator.setBackground(currentAccentColor);

        separatorPanel.setBackground(currentPrimaryBackground);
        separatorPanel.add(separator, BorderLayout.CENTER);
        separatorPanel.setBorder(new EmptyBorder(0, 100, 30, 100));

        // Configuração dos botões
        setupButtons(currentAccentColor);

        // Adiciona componentes ao painel principal usando GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridy = 0;
        mainPanel.add(titlePanel, gbc);

        gbc.gridy = 1;
        mainPanel.add(separatorPanel, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(10, 150, 10, 150);
        mainPanel.add(btnEntrar, gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(10, 150, 10, 150);
        mainPanel.add(btnCadastro, gbc);
    }

    private void setupIcon() {
        try {
            Image icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/job_icon.png"))).getImage();
            setIconImage(icon);
        } catch (Exception e) {
            System.err.println("Ícone não encontrado: /images/job_icon.png. Usando ícone padrão.");
        }
    }

    private void setupButtons(Color currentAccentColor) {
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 24);

        // Botão Entrar
        btnEntrar.setBackground(currentAccentColor);
        btnEntrar.setForeground(Color.WHITE);
        btnEntrar.setFocusPainted(false);
        btnEntrar.setFont(buttonFont);
        btnEntrar.setBorder(new EmptyBorder(15, 40, 15, 40));
        btnEntrar.setBorderPainted(false);

        // Botão Cadastro
        Color secondaryBackground = darkMode ? new Color(60, 60, 60) : Color.WHITE;
        Color secondaryForeground = darkMode ? Color.WHITE : currentAccentColor;

        btnCadastro.setBackground(secondaryBackground);
        btnCadastro.setForeground(secondaryForeground);
        btnCadastro.setFocusPainted(false);
        btnCadastro.setFont(buttonFont);
        btnCadastro.setBorder(new EmptyBorder(15, 40, 15, 40));
        btnCadastro.setBorderPainted(false);
    }

    private void setupListeners() {
        // Cores para estados de hover
        Color btnEntrarHoverColor = darkMode ? new Color(100, 100, 100) : new Color(0x303030);
        Color btnCadastroHoverBackground = darkMode ? new Color(80, 80, 80) : new Color(240, 240, 240);
        Color btnCadastroHoverForeground = darkMode ? Color.WHITE : new Color(0x303030);

        // Listener para o botão Entrar
        btnEntrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnEntrar.setBackground(btnEntrarHoverColor);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Color currentAccentColor = darkMode ? accentColorDark : accentColorLight;
                btnEntrar.setBackground(currentAccentColor);
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        // Listener para o botão Cadastro
        btnCadastro.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnCadastro.setBackground(btnCadastroHoverBackground);
                btnCadastro.setForeground(btnCadastroHoverForeground);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Color secondaryBackground = darkMode ? new Color(60, 60, 60) : Color.WHITE;
                Color currentAccentColor = darkMode ? Color.WHITE : accentColorLight;
                btnCadastro.setBackground(secondaryBackground);
                btnCadastro.setForeground(currentAccentColor);
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        btnEntrar.addActionListener(_ -> {
            SwingUtilities.invokeLater(LoginFrame::new);
            this.dispose();
        });

        btnCadastro.addActionListener(_ -> {
            SwingUtilities.invokeLater(UserChoice::new);
            this.dispose();
        });
    }

    private void setupFrame() {
        setResizable(false);
        setVisible(true);
    }

    // Métodos getters para acesso aos componentes
    public JButton getBtnEntrar() {
        return btnEntrar;
    }

    public JButton getBtnCadastro() {
        return btnCadastro;
    }

    public JSeparator getSeparator() {
        return separator;
    }

    public static void main(String[] args) {
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
//            e.printStackTrace();
//        }
        SwingUtilities.invokeLater(MainFrame::new);
    }
}