package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class PainelClientePerfil extends JPanel {
    // Declaração dos componentes
    private JPanel mainPanel;
    private JPanel contentPanel;
    private JPanel footerPanel;
    private JTextField nomeCompletoField;
    private JTextField emailField;
    private JTextField contacto1Field;
    private JTextField contacto2Field;
    private JTextField localizacaoField;
    private JButton editarButton;

    // Variável para controlar o tema (claro/escuro) - Opcional
    private final boolean darkMode = false; // Defina como true para ativar o modo escuro

    public PainelClientePerfil() {
        initializeComponents();
        setupLayout();
        // Os listeners são configurados após o setup do layout para garantir acesso às cores do tema
        setupListeners();
    }

    private void initializeComponents() {
        // Os painéis principais são inicializados aqui, as cores e bordas serão definidas no setupLayout
        mainPanel = new JPanel(new BorderLayout(20, 20)); // Espaçamento entre as secções
        contentPanel = new JPanel(new GridBagLayout());
        footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0)); // FlowLayout para o botão

        // Campos de texto
        nomeCompletoField = createTextField();
        emailField = createTextField();
        contacto1Field = createTextField();
        contacto2Field = createTextField();
        localizacaoField = createTextField();

        // Botão
        editarButton = new JButton("Editar");
        editarButton.setBackground(darkMode ? new Color(80, 80, 80) : new Color(64, 64, 64));
        editarButton.setForeground(darkMode ? Color.WHITE : Color.WHITE);
    }

    @SuppressWarnings("unused")
    private void setupLayout() {
        setLayout(new BorderLayout(0, 0)); // Define o layout do JPanel principal

        // --- Definição das Cores e Fontes baseadas no Tema ---
        // Cores originais: Fundo Branco, Fundo do contentPanel 240,240,240, Borda LIGHT_GRAY
        // Botão: 64,64,64 (fundo), WHITE (texto), borda 64,64,64

        // Modo Claro (default):
        Color primaryBackgroundLight = Color.WHITE;
        Color contentBackgroundLight = new Color(245, 245, 245); // Um cinza bem claro, mais suave que 240,240,240
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
        Font titleFont = new Font("Segoe UI", Font.BOLD, 28); // Título interno (se houvesse) ou para uma JLabel principal do painel
        Font labelFont = new Font("Segoe UI", Font.BOLD, 16); // Fontes mais modernas e legíveis
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 16); // Fontes para os campos de texto
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 14); // Fonte para o botão

        // --- Configuração dos Painéis ---
        // Painel principal (fundo)
        mainPanel.setBackground(currentPrimaryBackground);
        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30)); // Padding maior e mais simétrico

        // Painel de conteúdo (onde ficam os campos)
        contentPanel.setBackground(currentContentBackground);
        contentPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(currentContentBorder, 1), // Borda fina e sem arredondamento
                BorderFactory.createEmptyBorder(30, 30, 30, 30) // Padding interno
        ));

        // --- Configuração do GridBagLayout para o contentPanel ---
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15); // Espaçamento entre os componentes
        gbc.fill = GridBagConstraints.HORIZONTAL; // Preenche horizontalmente
        gbc.weightx = 1.0; // Distribui o espaço horizontalmente

        // Nome Completo
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(createLabel("Nome Completo", labelFont, currentLabelText), gbc);
        gbc.gridy = 1;
        gbc.ipady = 8; // Altura interna do campo
        nomeCompletoField.setFont(fieldFont); // Define a fonte
        nomeCompletoField.setBackground(currentFieldBackground); // Define a cor de fundo
        nomeCompletoField.setForeground(currentLabelText); // Cor do texto digitado
        nomeCompletoField.setBorder(BorderFactory.createCompoundBorder( // Borda fina e padding interno
                new LineBorder(currentFieldBorder, 1),
                new EmptyBorder(8, 10, 8, 10)
        ));
        nomeCompletoField.setCaretColor(currentLabelText); // Cor do cursor
        contentPanel.add(nomeCompletoField, gbc);

        // Email
        gbc.gridx = 1;
        gbc.gridy = 0;
        contentPanel.add(createLabel("Email", labelFont, currentLabelText), gbc);
        gbc.gridy = 1;
        emailField.setFont(fieldFont);
        emailField.setBackground(currentFieldBackground);
        emailField.setForeground(currentLabelText);
        emailField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(currentFieldBorder, 1),
                new EmptyBorder(8, 10, 8, 10)
        ));
        emailField.setCaretColor(currentLabelText);
        contentPanel.add(emailField, gbc);

        // Primeiro Contacto
        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPanel.add(createLabel("Contacto Principal", labelFont, currentLabelText), gbc); // Alterado texto para clareza
        gbc.gridy = 3;
        contacto1Field.setFont(fieldFont);
        contacto1Field.setBackground(currentFieldBackground);
        contacto1Field.setForeground(currentLabelText);
        contacto1Field.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(currentFieldBorder, 1),
                new EmptyBorder(8, 10, 8, 10)
        ));
        contacto1Field.setCaretColor(currentLabelText);
        contentPanel.add(contacto1Field, gbc);

        // Segundo Contacto (Opcional)
        gbc.gridx = 1;
        gbc.gridy = 2;
        contentPanel.add(createLabel("Contacto Alternativo", labelFont, currentLabelText), gbc); // Alterado texto
        gbc.gridy = 3;
        contacto2Field.setFont(fieldFont);
        contacto2Field.setBackground(currentFieldBackground);
        contacto2Field.setForeground(currentLabelText);
        contacto2Field.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(currentFieldBorder, 1),
                new EmptyBorder(8, 10, 8, 10)
        ));
        contacto2Field.setCaretColor(currentLabelText);
        contentPanel.add(contacto2Field, gbc);

        // Localização
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; // Ocupa duas colunas
        contentPanel.add(createLabel("Localização", labelFont, currentLabelText), gbc);
        gbc.gridy = 5;
        gbc.ipady = 8;
        localizacaoField.setFont(fieldFont);
        localizacaoField.setBackground(currentFieldBackground);
        localizacaoField.setForeground(currentLabelText);
        localizacaoField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(currentFieldBorder, 1),
                new EmptyBorder(8, 10, 8, 10)
        ));
        localizacaoField.setCaretColor(currentLabelText);
        contentPanel.add(localizacaoField, gbc);
        gbc.gridwidth = 1; // Reset para o padrão

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // --- Rodapé com Botão "Editar" ---
        footerPanel.setBackground(currentPrimaryBackground);
        footerPanel.setBorder(new EmptyBorder(20, 0, 0, 20)); // Padding superior para separar do contentPanel

        editarButton.setBackground(currentButtonBackground); // Cor original 64,64,64
        editarButton.setForeground(currentButtonText);
        editarButton.setFont(buttonFont);
        editarButton.setFocusPainted(false);
        editarButton.setBorder(BorderFactory.createCompoundBorder( // Borda fina, reta e padding interno
                new LineBorder(currentButtonBackground, 1), // Borda da mesma cor do fundo para efeito flat, ou uma cor mais escura se quiser borda visível
                new EmptyBorder(10, 30, 10, 30)
        ));
        // setMargin é redundante se EmptyBorder já define o padding, mas mantido para consistência
        editarButton.setMargin(new Insets(10, 30, 10, 30));

        footerPanel.add(editarButton);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);
    }

    private void setupListeners() {
        // Cores de foco para os campos de texto
        Color currentFieldBorder = darkMode ? new Color(90, 90, 90) : new Color(200, 200, 200);
        Color currentFieldFocusBorder = darkMode ? new Color(150, 150, 150) : new Color(0x464646); // 0x464646 original

        // Listener de foco para todos os campos de texto
        FocusAdapter fieldFocusListener = new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                JTextField field = (JTextField) e.getSource();
                field.setBorder(BorderFactory.createCompoundBorder(
                        new LineBorder(currentFieldFocusBorder, 1),
                        new EmptyBorder(8, 10, 8, 10)
                ));
            }

            @Override
            public void focusLost(FocusEvent e) {
                JTextField field = (JTextField) e.getSource();
                field.setBorder(BorderFactory.createCompoundBorder(
                        new LineBorder(currentFieldBorder, 1),
                        new EmptyBorder(8, 10, 8, 10)
                ));
            }
        };

        nomeCompletoField.addFocusListener(fieldFocusListener);
        emailField.addFocusListener(fieldFocusListener);
        contacto1Field.addFocusListener(fieldFocusListener);
        contacto2Field.addFocusListener(fieldFocusListener);
        localizacaoField.addFocusListener(fieldFocusListener);

        // Cores de hover para o botão Editar
        Color currentButtonBackground = darkMode ? new Color(80, 80, 80) : new Color(64, 64, 64);
        Color currentButtonHoverBackground = darkMode ? new Color(110, 110, 110) : new Color(90, 90, 90);

        // Listener de hover para o botão Editar
        editarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                editarButton.setBackground(currentButtonHoverBackground);
                editarButton.setBorder(BorderFactory.createCompoundBorder( // Atualiza a borda para refletir o hover
                        new LineBorder(currentButtonHoverBackground, 1),
                        new EmptyBorder(10, 30, 10, 30)
                ));
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                editarButton.setBackground(currentButtonBackground);
                editarButton.setBorder(BorderFactory.createCompoundBorder( // Volta à borda original
                        new LineBorder(currentButtonBackground, 1),
                        new EmptyBorder(10, 30, 10, 30)
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

    // Método auxiliar para criar campos de texto, não mais usado diretamente no setupLayout
    // Mantido para manter a estrutura original, mas as propriedades são definidas no setupLayout
    private JTextField createTextField() {
        JTextField field = new JTextField(30);
        return field;
    }
}