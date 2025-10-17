package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class PainelClientePublicarServicos extends JPanel {
    private JTextField nomeServicoField;
    private JTextField areaServicoField;
    private JTextField provinciaField;
    private JTextField distritoField;
    private JTextField ruaAvenidaField;
    private JTextField numeroField;
    private JTextArea descricaoArea;
    private JButton publicarButton;

    // Variável para controlar o tema (claro/escuro) - Opcional
    private final boolean darkMode = false; // Defina como true para ativar o modo escuro

    public PainelClientePublicarServicos() {
        initializeComponents();
        setupLayout();
        setupListeners(); // Configurar listeners após o layout
    }

    private void initializeComponents() {
        // Campos de texto e área de texto
        nomeServicoField = createTextField(); // Remove o placeholder do construtor
        areaServicoField = createTextField();
        provinciaField = createTextField();
        distritoField = createTextField();
        ruaAvenidaField = createTextField();
        numeroField = createTextField();
        descricaoArea = createTextArea(); // Remove o placeholder do construtor

        // Botão
        publicarButton = new JButton("Publicar Serviço"); // Passa o texto para o construtor do JButton
    }

    @SuppressWarnings("unused")
    private void setupLayout() {
        setLayout(new BorderLayout(0, 0)); // Layout principal do JPanel
        // Cores serão definidas dinamicamente

        // --- Definição das Cores e Fontes baseadas no Tema ---
        // Cores originais: Fundo Branco, Fundo do contentPanel 240,240,240, Borda LIGHT_GRAY ou GRAY
        // Botão: 64,64,64 (fundo), WHITE (texto), borda sem cor explícita, mas com padding

        // Modo Claro (default):
        Color primaryBackgroundLight = Color.WHITE;
        Color contentBackgroundLight = new Color(245, 245, 245); // Um cinza bem claro, mais suave
        Color contentBorderLight = new Color(220, 220, 220); // Borda suave
        Color labelTextLight = new Color(50, 50, 50); // Texto das labels
        Color fieldBackgroundLight = Color.WHITE; // Campos de texto brancos
        Color fieldBorderLight = new Color(200, 200, 200); // Borda dos campos discreta
        Color fieldFocusBorderLight = new Color(0x464646); // Borda ao focar (do MainFrame)
        Color buttonBackgroundLight = new Color(64, 64, 64); // Cor original do botão
        Color buttonTextLight = Color.WHITE;
        Color buttonHoverBackgroundLight = new Color(90, 90, 90); // Ligeiramente mais escuro para hover

        // Modo Escuro (se darkMode = true):
        Color primaryBackgroundDark = new Color(30, 30, 30);
        Color contentBackgroundDark = new Color(45, 45, 45);
        Color contentBorderDark = new Color(70, 70, 70);
        Color labelTextDark = Color.WHITE;
        Color fieldBackgroundDark = new Color(60, 60, 60);
        Color fieldBorderDark = new Color(90, 90, 90);
        Color fieldFocusBorderDark = new Color(150, 150, 150);
        Color buttonBackgroundDark = new Color(80, 80, 80);
        Color buttonTextDark = Color.WHITE;
        Color buttonHoverBackgroundDark = new Color(110, 110, 110);

        // Cores Atuais
        Color currentPrimaryBackground = darkMode ? primaryBackgroundDark : primaryBackgroundLight;
        Color currentContentBackground = darkMode ? contentBackgroundDark : contentBackgroundLight;
        Color currentContentBorder = darkMode ? contentBorderDark : contentBorderLight;
        Color currentLabelText = darkMode ? labelTextDark : labelTextLight;
        Color currentFieldBackground = darkMode ? fieldBackgroundDark : fieldBackgroundLight;
        Color currentFieldBorder = darkMode ? fieldBorderDark : fieldBorderLight;
        Color currentFieldFocusBorder = darkMode ? fieldFocusBorderDark : fieldFocusBorderLight;
        Color currentButtonBackground = darkMode ? buttonBackgroundDark : buttonBackgroundLight;
        Color currentButtonText = darkMode ? buttonTextDark : buttonTextLight;
        Color currentButtonHoverBackground = darkMode ? buttonHoverBackgroundDark : buttonHoverBackgroundLight;

        // Fontes
        Font labelFont = new Font("Segoe UI", Font.BOLD, 14); // Fontes mais modernas e legíveis
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 14); // Fontes para os campos de texto
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 14); // Fonte para o botão

        // --- Configuração do Painel Principal e de Conteúdo ---
        setBackground(currentPrimaryBackground); // Fundo do JPanel principal
        setBorder(new EmptyBorder(30, 30, 30, 30)); // Padding simétrico

        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(currentContentBackground);
        contentPanel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(currentContentBorder, 1), // Borda fina e reta
                new EmptyBorder(30, 30, 30, 30) // Padding interno
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10); // Espaçamento entre os componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // --- Nome do Serviço ---
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4; // Ocupa todas as 4 colunas para o campo e label
        contentPanel.add(createLabel("Nome do Serviço", labelFont, currentLabelText), gbc);
        gbc.gridy++;
        gbc.ipady = 8; // Altura interna do campo
        nomeServicoField.setFont(fieldFont);
        nomeServicoField.setBackground(currentFieldBackground);
        nomeServicoField.setForeground(currentLabelText);
        nomeServicoField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(currentFieldBorder, 1),
                new EmptyBorder(5, 10, 5, 10)
        ));
        nomeServicoField.setCaretColor(currentLabelText);
        contentPanel.add(nomeServicoField, gbc);

        // --- Área do Serviço ---
        gbc.gridy++;
        gbc.ipady = 0; // Resetar ipady para labels
        contentPanel.add(createLabel("Área do Serviço", labelFont, currentLabelText), gbc);
        gbc.gridy++;
        gbc.ipady = 8; // Altura interna do campo
        areaServicoField.setFont(fieldFont);
        areaServicoField.setBackground(currentFieldBackground);
        areaServicoField.setForeground(currentLabelText);
        areaServicoField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(currentFieldBorder, 1),
                new EmptyBorder(5, 10, 5, 10)
        ));
        areaServicoField.setCaretColor(currentLabelText);
        contentPanel.add(areaServicoField, gbc);
        gbc.gridwidth = 1; // Reseta para 1 coluna

        // --- Endereço - Labels ---
        gbc.gridy++;
        gbc.ipady = 0; // Resetar ipady para labels
        gbc.insets = new Insets(15, 10, 5, 10); // Mais espaçamento antes da seção de endereço
        gbc.gridx = 0;
        contentPanel.add(createLabel("Província", labelFont, currentLabelText), gbc);
        gbc.gridx = 1;
        contentPanel.add(createLabel("Distrito", labelFont, currentLabelText), gbc);
        gbc.gridx = 2;
        contentPanel.add(createLabel("Rua/Avenida", labelFont, currentLabelText), gbc);
        gbc.gridx = 3;
        contentPanel.add(createLabel("Número", labelFont, currentLabelText), gbc); // Alterado "Numero" para "Número"

        // --- Endereço - Campos ---
        gbc.gridy++;
        gbc.insets = new Insets(5, 10, 10, 10); // Menos espaçamento após a seção de endereço
        gbc.ipady = 8; // Altura interna do campo
        gbc.gridx = 0;
        provinciaField.setFont(fieldFont);
        provinciaField.setBackground(currentFieldBackground);
        provinciaField.setForeground(currentLabelText);
        provinciaField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(currentFieldBorder, 1),
                new EmptyBorder(5, 10, 5, 10)
        ));
        provinciaField.setCaretColor(currentLabelText);
        contentPanel.add(provinciaField, gbc);

        gbc.gridx = 1;
        distritoField.setFont(fieldFont);
        distritoField.setBackground(currentFieldBackground);
        distritoField.setForeground(currentLabelText);
        distritoField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(currentFieldBorder, 1),
                new EmptyBorder(5, 10, 5, 10)
        ));
        distritoField.setCaretColor(currentLabelText);
        contentPanel.add(distritoField, gbc);

        gbc.gridx = 2;
        ruaAvenidaField.setFont(fieldFont);
        ruaAvenidaField.setBackground(currentFieldBackground);
        ruaAvenidaField.setForeground(currentLabelText);
        ruaAvenidaField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(currentFieldBorder, 1),
                new EmptyBorder(5, 10, 5, 10)
        ));
        ruaAvenidaField.setCaretColor(currentLabelText);
        contentPanel.add(ruaAvenidaField, gbc);

        gbc.gridx = 3;
        numeroField.setFont(fieldFont);
        numeroField.setBackground(currentFieldBackground);
        numeroField.setForeground(currentLabelText);
        numeroField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(currentFieldBorder, 1),
                new EmptyBorder(5, 10, 5, 10)
        ));
        numeroField.setCaretColor(currentLabelText);
        contentPanel.add(numeroField, gbc);

        // --- Descrição ---
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 4;
        gbc.ipady = 0; // Resetar ipady para labels
        gbc.insets = new Insets(15, 10, 5, 10); // Mais espaçamento antes da seção de descrição
        contentPanel.add(createLabel("Descrição do Serviço", labelFont, currentLabelText), gbc); // Alterado texto
        gbc.gridy++;
        gbc.weighty = 1.0; // Permite que a área de texto se expanda verticalmente
        gbc.fill = GridBagConstraints.BOTH; // Permite preencher tanto horizontal quanto verticalmente
        gbc.insets = new Insets(5, 10, 10, 10); // Espaçamento para o scrollPane
        descricaoArea.setFont(fieldFont);
        descricaoArea.setBackground(currentFieldBackground);
        descricaoArea.setForeground(currentLabelText);
        descricaoArea.setCaretColor(currentLabelText);
        JScrollPane scrollPane = new JScrollPane(descricaoArea);
        scrollPane.setPreferredSize(new Dimension(500, 120)); // Tamanho inicial da área de texto, um pouco menor
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(currentFieldBorder, 1), // Borda da área de texto
                new EmptyBorder(5, 10, 5, 10) // Padding interno
        ));
        contentPanel.add(scrollPane, gbc);
        gbc.weighty = 0; // Reseta o peso vertical
        gbc.fill = GridBagConstraints.HORIZONTAL; // Reseta o preenchimento
        gbc.gridwidth = 1; // Reseta a largura

        add(contentPanel, BorderLayout.CENTER);

        // --- Rodapé com Botão "Publicar" ---
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        southPanel.setBackground(currentPrimaryBackground);
        southPanel.setBorder(new EmptyBorder(10, 0, 10, 30)); // Padding maior e mais simétrico

        publicarButton.setBackground(currentButtonBackground); // Cor original 64,64,64
        publicarButton.setForeground(currentButtonText);
        publicarButton.setFont(buttonFont);
        publicarButton.setFocusPainted(false);
        // Borda flat com padding interno
        publicarButton.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(currentButtonBackground, 1), // Borda da mesma cor do fundo para efeito flat
                new EmptyBorder(10, 25, 10, 25)
        ));
        // setPreferredSize é redundante com o padding e fill, mas mantido para consistência
        publicarButton.setPreferredSize(new Dimension(180, 40));

        southPanel.add(publicarButton);
        add(southPanel, BorderLayout.SOUTH);
    }

    private void setupListeners() {
        // Cores de foco para os campos de texto e área de texto
        Color currentFieldBorder = darkMode ? new Color(90, 90, 90) : new Color(200, 200, 200);
        Color currentFieldFocusBorder = darkMode ? new Color(150, 150, 150) : new Color(0x464646); // 0x464646 original

        // Listener de foco para JTextFields
        FocusAdapter fieldFocusListener = new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                JTextField field = (JTextField) e.getSource();
                field.setBorder(BorderFactory.createCompoundBorder(
                        new LineBorder(currentFieldFocusBorder, 1),
                        new EmptyBorder(5, 10, 5, 10)
                ));
            }

            @Override
            public void focusLost(FocusEvent e) {
                JTextField field = (JTextField) e.getSource();
                field.setBorder(BorderFactory.createCompoundBorder(
                        new LineBorder(currentFieldBorder, 1),
                        new EmptyBorder(5, 10, 5, 10)
                ));
            }
        };

        nomeServicoField.addFocusListener(fieldFocusListener);
        areaServicoField.addFocusListener(fieldFocusListener);
        provinciaField.addFocusListener(fieldFocusListener);
        distritoField.addFocusListener(fieldFocusListener);
        ruaAvenidaField.addFocusListener(fieldFocusListener);
        numeroField.addFocusListener(fieldFocusListener);

        // Listener de foco para JTextArea (com tratamento do JScrollPane)
        descricaoArea.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                JScrollPane scrollPane = (JScrollPane) descricaoArea.getParent().getParent(); // Acessa o JScrollPane
                scrollPane.setBorder(BorderFactory.createCompoundBorder(
                        new LineBorder(currentFieldFocusBorder, 1),
                        new EmptyBorder(5, 10, 5, 10)
                ));
            }

            @Override
            public void focusLost(FocusEvent e) {
                JScrollPane scrollPane = (JScrollPane) descricaoArea.getParent().getParent();
                scrollPane.setBorder(BorderFactory.createCompoundBorder(
                        new LineBorder(currentFieldBorder, 1),
                        new EmptyBorder(5, 10, 5, 10)
                ));
            }
        });

        // Cores de hover para o botão Publicar
        Color currentButtonBackground = darkMode ? new Color(80, 80, 80) : new Color(64, 64, 64);
        Color currentButtonHoverBackground = darkMode ? new Color(110, 110, 110) : new Color(90, 90, 90);

        // Listener de hover para o botão Publicar
        publicarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                publicarButton.setBackground(currentButtonHoverBackground);
                publicarButton.setBorder(BorderFactory.createCompoundBorder( // Atualiza a borda para refletir o hover
                        new LineBorder(currentButtonHoverBackground, 1),
                        new EmptyBorder(10, 25, 10, 25)
                ));
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                publicarButton.setBackground(currentButtonBackground);
                publicarButton.setBorder(BorderFactory.createCompoundBorder( // Volta à borda original
                        new LineBorder(currentButtonBackground, 1),
                        new EmptyBorder(10, 25, 10, 25)
                ));
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

    // Métodos auxiliares
    private JLabel createLabel(String text, Font font, Color foreground) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(foreground);
        return label;
    }

    private JTextField createTextField() {
        JTextField field = new JTextField();
        return field;
    }

    private JTextArea createTextArea() {
        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        return textArea;
    }
}