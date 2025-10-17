package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PainelEmpresaPerfil extends JPanel {
    // Declaração dos componentes
    private JPanel mainPanel;
    private JPanel contentPanel;
    private JPanel footerPanel;
    private JTextField nomeEmpresaField;
    private JTextField emailField;
    private JTextField localizacaoField;
    private JTextField contacto1Field;
    private JTextField sectorField;
    private JTextField contacto2Field;
    private JButton editarButton;

    public PainelEmpresaPerfil() {
        initializeComponents();
        setupLayout();
    }

    private void initializeComponents() {
        mainPanel = new JPanel(new BorderLayout(20, 20));
        contentPanel = new JPanel(new GridBagLayout());
        footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));

        nomeEmpresaField = createTextField();
        emailField = createTextField();
        localizacaoField = createTextField();
        contacto1Field = createTextField();
        sectorField = createTextField();
        contacto2Field = createTextField();
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

        // Nome da Empresa
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(createLabel("Nome da Empresa", labelFont), gbc);
        gbc.gridy = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0;
        contentPanel.add(nomeEmpresaField, gbc);

        // Email
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(createLabel("Email", labelFont), gbc);
        gbc.gridy = 1;
        contentPanel.add(emailField, gbc);

        // Localização
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(createLabel("Localização", labelFont), gbc);
        gbc.gridy = 3;
        contentPanel.add(localizacaoField, gbc);

        // Contacto 1
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(createLabel("Contacto", labelFont), gbc);
        gbc.gridy = 3;
        contentPanel.add(contacto1Field, gbc);

        // Sector
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(createLabel("Sector", labelFont), gbc);
        gbc.gridy = 5;
        contentPanel.add(sectorField, gbc);

        // Contacto 2
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(createLabel("Contacto", labelFont), gbc);
        gbc.gridy = 5;
        contentPanel.add(contacto2Field, gbc);

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Rodapé com Botão "Editar"
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
}