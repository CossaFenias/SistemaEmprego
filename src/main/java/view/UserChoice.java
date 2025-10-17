package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserChoice extends JFrame {

    // Declaração dos componentes
    private JPanel mainPanel;
    private JPanel titlePanel;
    private JLabel title;
    private JButton empresa, candidato, cliente, voltar;

    // Cores e configurações
    private final boolean darkMode = false;
    private Color primaryBackgroundLight;
    private Color accentColorLight;
    private Color textColorLight;
    private Color primaryBackgroundDark;
    private Color accentColorDark;
    private Color textColorDark;

    public UserChoice() {
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
        mainPanel = new JPanel();
        titlePanel = new JPanel(new BorderLayout());

        title = new JLabel("SISTEMA DE GESTÃO DE EMPREGO");
        empresa = new JButton("Empresa");
        candidato = new JButton("Candidato");
        cliente = new JButton("Cliente");
        voltar = new JButton("Voltar");
    }

    private void setupLayout() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(655, 510);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // Configura cores atuais baseadas no tema
        Color currentPrimaryBackground = darkMode ? primaryBackgroundDark : primaryBackgroundLight;
        Color currentAccentColor = darkMode ? accentColorDark : accentColorLight;
        Color currentTextColor = darkMode ? textColorDark : textColorLight;

        // Configuração do painel principal
        mainPanel.setBackground(currentPrimaryBackground);
        mainPanel.setLayout(null);
        getContentPane().add(mainPanel, BorderLayout.CENTER);

        // Configuração do título
        title.setBounds(15, 15, 610, 60);
        title.setForeground(currentTextColor);
        title.setFont(new Font("Segoe UI", Font.BOLD, 32));
        title.setHorizontalAlignment(JLabel.CENTER);

        titlePanel.setBackground(currentPrimaryBackground);
        titlePanel.add(title, BorderLayout.CENTER);

        // Configuração dos botões
        setupButtons(currentAccentColor, currentPrimaryBackground);

        // Adiciona componentes ao painel principal
        addComponents();
    }

    private void setupButtons(Color currentAccentColor, Color currentPrimaryBackground) {
        // Botão Empresa
        setupIconButton(empresa, "src/imagens/iconEmpresa.png", 210, 75, 220, 105, currentAccentColor, currentPrimaryBackground);

        // Botão Candidato
        setupIconButton(candidato, "src/imagens/iconCandidato.png", 210, 215, 220, 105, currentAccentColor, currentPrimaryBackground);

        // Botão Cliente
        setupIconButton(cliente, "src/imagens/iconCliente.png", 210, 355, 220, 105, currentAccentColor, currentPrimaryBackground);

        // Botão Voltar
        voltar.setBounds(530, 430, 90, 30); // canto inferior direito
        voltar.setBackground(currentPrimaryBackground);
        voltar.setForeground(currentAccentColor);
        voltar.setBorder(new LineBorder(currentAccentColor, 1));
        voltar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        voltar.setFocusPainted(false);
    }

    private void setupIconButton(JButton button, String iconPath, int x, int y, int width, int height,
                                 Color accentColor, Color backgroundColor) {
        ImageIcon img = new ImageIcon(iconPath);
        button.setBounds(x, y, width, height);
        button.setBackground(backgroundColor);
        button.setForeground(accentColor);
        button.setBorder(new LineBorder(accentColor, 1));
        button.setFont(new Font("Segoe UI", Font.BOLD, 24));
        button.setIcon(img);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.BOTTOM);
        button.setFocusPainted(false);
    }

    private void addComponents() {
        mainPanel.add(title);
        mainPanel.add(empresa);
        mainPanel.add(candidato);
        mainPanel.add(cliente);
        mainPanel.add(voltar);
    }

    private void setupListeners() {
        // Cores para estados de hover
        Color buttonHoverBackground = darkMode ? new Color(60, 60, 60) : new Color(240, 240, 240);
        Color voltarHoverBackground = darkMode ? new Color(100, 100, 100) : new Color(240, 240, 240);

        // Listener para o botão Empresa
        setupButtonHover(empresa, buttonHoverBackground);

        // Listener para o botão Candidato
        setupButtonHover(candidato, buttonHoverBackground);

        // Listener para o botão Cliente
        setupButtonHover(cliente, buttonHoverBackground);

        // Listener para o botão Voltar
        voltar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                voltar.setBackground(voltarHoverBackground);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                voltar.setBackground(darkMode ? primaryBackgroundDark : primaryBackgroundLight);
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        candidato.addActionListener(_ -> {
            SwingUtilities.invokeLater(() -> new CadastroCandidato());
            this.dispose();
        });
        empresa.addActionListener(_ -> {
            SwingUtilities.invokeLater(() -> new CadastroEmpresa().setVisible(true));
            this.dispose();
        });
        cliente.addActionListener(_ -> {
            SwingUtilities.invokeLater(() -> new CadastroCliente());
            this.dispose();
        });

        voltar.addActionListener(_ -> {
            SwingUtilities.invokeLater(MainFrame::new);
            dispose();
        });
    }

    private void setupButtonHover(JButton button, Color hoverBackground) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverBackground);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(darkMode ? primaryBackgroundDark : primaryBackgroundLight);
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

    private void setupFrame() {
        setResizable(false);
        setVisible(true);
    }

    // Métodos getters para acesso aos componentes
    public JButton getEmpresa() {
        return empresa;
    }

    public JButton getCandidato() {
        return candidato;
    }

    public JButton getCliente() {
        return cliente;
    }

    public JButton getVoltar() {
        return voltar;
    }
}