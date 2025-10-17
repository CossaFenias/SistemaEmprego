package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PainelEmpresaPublicarVaga extends JPanel {
    // Declaração dos componentes
    private JPanel mainPanel;
    private JPanel contentPanel;
    private JPanel footerPanel;
    private JTextField tituloVagaField;
    private JTextField regimeField;
    private JTextField localTrabalhoField;
    private JTextField dataEncerramentoField;
    private JTextArea descricaoArea;
    private JTextArea requisitosArea;
    private JScrollPane descricaoScrollPane;
    private JScrollPane requisitosScrollPane;
    private JButton salvarRascunhoButton;
    private JButton publicarButton;

    public PainelEmpresaPublicarVaga() {
        initializeComponents();
        setupLayout();
    }

    private void initializeComponents() {
        mainPanel = new JPanel(new BorderLayout(20, 20));
        contentPanel = new JPanel(new GridBagLayout());
        footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));

        tituloVagaField = createTextField();
        regimeField = createTextField();
        localTrabalhoField = createTextField();
        dataEncerramentoField = createTextField();
        descricaoArea = createTextArea(5);
        requisitosArea = createTextArea(5);
        descricaoScrollPane = new JScrollPane(descricaoArea);
        requisitosScrollPane = new JScrollPane(requisitosArea);
        salvarRascunhoButton = new JButton("salvar rascunho");
        publicarButton = new JButton("publicar");
    }

    private void setupLayout() {
        setLayout(new BorderLayout(0, 0));

        // Configuração do painel principal
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Configuração do painel de conteúdo
        contentPanel.setBackground(new Color(240, 240, 240));
        contentPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true),
                BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.BOTH;

        Font labelFont = new Font("Arial", Font.BOLD, 20);

        // Título da Vaga
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(createLabel("Título da Vaga", labelFont), gbc);
        gbc.gridy = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0;
        contentPanel.add(tituloVagaField, gbc);

        // Regime
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(createLabel("Regime", labelFont), gbc);
        gbc.gridy = 1;
        contentPanel.add(regimeField, gbc);

        // Local de Trabalho
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(createLabel("Local de Trabalho", labelFont), gbc);
        gbc.gridy = 3;
        contentPanel.add(localTrabalhoField, gbc);

        // Data de Encerramento
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(createLabel("Data de Encerramento", labelFont), gbc);
        gbc.gridy = 3;
        contentPanel.add(dataEncerramentoField, gbc);

        // Descrição
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(createLabel("Descrição", labelFont), gbc);
        gbc.gridy = 5;
        gbc.weighty = 0.4;
        contentPanel.add(descricaoScrollPane, gbc);

        // Requisitos
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(createLabel("Requisitos", labelFont), gbc);
        gbc.gridy = 7;
        gbc.weighty = 0.4;
        contentPanel.add(requisitosScrollPane, gbc);

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Configuração do rodapé
        footerPanel.setBackground(Color.WHITE);
        footerPanel.setBorder(new EmptyBorder(0, 0, 0, 20));

        // Configuração do botão "salvar rascunho"
        salvarRascunhoButton.setBackground(Color.WHITE);
        salvarRascunhoButton.setForeground(Color.BLACK);
        salvarRascunhoButton.setFont(new Font("Arial", Font.BOLD, 12));
        salvarRascunhoButton.setFocusPainted(false);
        salvarRascunhoButton.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)));

        // Configuração do botão "publicar"
        publicarButton.setBackground(new Color(64, 64, 64));
        publicarButton.setForeground(Color.WHITE);
        publicarButton.setFont(new Font("Arial", Font.BOLD, 12));
        publicarButton.setFocusPainted(false);
        publicarButton.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(64, 64, 64), 1, true),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));

        footerPanel.add(salvarRascunhoButton);
        footerPanel.add(publicarButton);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);
    }

    // Métodos auxiliares
    private JLabel createLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(Color.BLACK);
        return label;
    }

    private JTextField createTextField() {
        JTextField field = new JTextField(30);
        field.setFont(new Font("Arial", Font.PLAIN, 30));
        field.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.LIGHT_GRAY, 1),
                new EmptyBorder(8, 10, 8, 10)
        ));
        return field;
    }

    private JTextArea createTextArea(int rows) {
        JTextArea area = new JTextArea(rows, 30);
        area.setFont(new Font("Arial", Font.PLAIN, 30));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.LIGHT_GRAY, 1),
                new EmptyBorder(8, 10, 8, 10)
        ));
        return area;
    }
}