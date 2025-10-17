package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class PainelCandidatoPerfil extends JPanel {
    // Declaração dos componentes
    private JPanel mainPanel;
    private JPanel contentPanel;
    private JPanel footerPanel;
    private JTextField nomeCompletoField;
    private JTextField emailField;
    private JTextField localizacaoField;
    private JTextField contactosField;
    private JTextArea experienciaArea;
    private JTextArea formacaoArea;
    private JScrollPane experienciaScrollPane;
    private JScrollPane formacaoScrollPane;
    private JButton editarButton;

    public PainelCandidatoPerfil() {
        initializeComponents();
        setupLayout();
    }

    private void initializeComponents() {
        mainPanel = new JPanel(new BorderLayout(20, 20));
        contentPanel = new JPanel(new GridBagLayout());
        footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));

        nomeCompletoField = createTextField();
        emailField = createTextField();
        localizacaoField = createTextField();
        contactosField = createTextField();
        experienciaArea = createTextArea();
        formacaoArea = createTextArea();
        experienciaScrollPane = new JScrollPane(experienciaArea);
        formacaoScrollPane = new JScrollPane(formacaoArea);
        editarButton = new JButton("Editar");
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

        // Nome Completo
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(createLabel("Nome Completo:", labelFont), gbc);
        gbc.gridy = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0.05;
        contentPanel.add(nomeCompletoField, gbc);

        // Email
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(createLabel("Email:", labelFont), gbc);
        gbc.gridy = 1;
        contentPanel.add(emailField, gbc);

        // Localização
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(createLabel("Localização:", labelFont), gbc);
        gbc.gridy = 3;
        contentPanel.add(localizacaoField, gbc);

        // Contactos
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(createLabel("Contactos:", labelFont), gbc);
        gbc.gridy = 3;
        contentPanel.add(contactosField, gbc);

        // Experiência Profissional
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(createLabel("Experiência Profissional:", labelFont), gbc);
        gbc.gridy = 5;
        gbc.weighty = 0.5;
        experienciaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        contentPanel.add(experienciaScrollPane, gbc);

        // Formação Profissional
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weighty = 0.1;
        contentPanel.add(createLabel("Formação Profissional:", labelFont), gbc);
        gbc.gridy = 5;
        gbc.weighty = 0.5;
        formacaoScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        contentPanel.add(formacaoScrollPane, gbc);

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Configuração do rodapé
        footerPanel.setBackground(Color.WHITE);
        footerPanel.setBorder(new EmptyBorder(0, 0, 0, 20));

        editarButton.setBackground(new Color(64, 64, 64));
        editarButton.setForeground(Color.WHITE);
        editarButton.setFont(new Font("Arial", Font.BOLD, 14));
        editarButton.setFocusPainted(false);
        editarButton.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(64, 64, 64), 1, true),
                BorderFactory.createEmptyBorder(10, 30, 10, 30)
        ));
        editarButton.setMargin(new Insets(10, 30, 10, 30));

        footerPanel.add(editarButton);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);
    }

    // Métodos auxiliares (mantidos)
    private JLabel createLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(Color.BLACK);
        return label;
    }

    private JTextField createTextField() {
        JTextField field = new JTextField(30);
        field.setEditable(false);
        field.setCaretColor(field.getBackground());
        field.setFont(new Font("Arial", Font.PLAIN, 30));
        field.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.LIGHT_GRAY, 1),
                new EmptyBorder(8, 10, 8, 10)
        ));
        return field;
    }

    private JTextArea createTextArea() {
        JTextArea area = new JTextArea(8, 30);
        area.setFont(new Font("Arial", Font.PLAIN, 30));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.LIGHT_GRAY, 1),
                new EmptyBorder(8, 10, 8, 10)
        ));
        area.setEditable(false);
        area.setCaretColor(area.getBackground());
        return area;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public JPanel getFooterPanel() {
        return footerPanel;
    }

    public JTextField getNomeCompletoField() {
        return nomeCompletoField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JTextField getLocalizacaoField() {
        return localizacaoField;
    }

    public JTextField getContactosField() {
        return contactosField;
    }

    public JTextArea getExperienciaArea() {
        return experienciaArea;
    }

    public JTextArea getFormacaoArea() {
        return formacaoArea;
    }

    public JScrollPane getExperienciaScrollPane() {
        return experienciaScrollPane;
    }

    public JScrollPane getFormacaoScrollPane() {
        return formacaoScrollPane;
    }

    public JButton getEditarButton() {
        return editarButton;
    }
}