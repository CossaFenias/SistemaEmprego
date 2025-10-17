package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class PainelCandidatoPrincipal extends JFrame {
    // Declaração dos componentes
    private JPanel mainContentPane;
    private JPanel topNavPanel;
    private JPanel cardsPanel;
    private CardLayout cardLayout;
    private JButton meuPerfilButton;
    private JButton vagasButton;
    private JButton servicosButton;
    private Map<JButton, Color> defaultButtonBackgrounds;
    private Map<JButton, Color> defaultButtonForegrounds;
    private Map<JButton, Border> defaultButtonBorders;

    public PainelCandidatoPrincipal() {
        initializeComponents();
        setupLayout();
        setupListeners();
    }

    private void initializeComponents() {
        mainContentPane = new JPanel(new BorderLayout());
        topNavPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        cardLayout = new CardLayout();
        cardsPanel = new JPanel(cardLayout);

        meuPerfilButton = new JButton("Meu Perfil");
        vagasButton = new JButton("Vagas");
        servicosButton = new JButton("Servicos");

        defaultButtonBackgrounds = new HashMap<>();
        defaultButtonForegrounds = new HashMap<>();
        defaultButtonBorders = new HashMap<>();
    }

    private void setupLayout() {
        setTitle("Sistema de Gestão de Emprego - Painel Candidato");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);

        // Configuração do painel principal
        mainContentPane.setBackground(Color.WHITE);
        setContentPane(mainContentPane);

        // Configuração do painel de navegação superior
        topNavPanel.setBackground(Color.WHITE);
        topNavPanel.setBorder(new EmptyBorder(20, 0, 20, 0));

        // Configuração dos botões de navegação
        meuPerfilButton = createNavButton("Meu Perfil", 120);
        vagasButton = createNavButton("Vagas", 120);
        servicosButton = createNavButton("Servicos", 120);

        topNavPanel.add(meuPerfilButton);
        topNavPanel.add(vagasButton);
        topNavPanel.add(servicosButton);
        mainContentPane.add(topNavPanel, BorderLayout.NORTH);

        // Configuração do painel de cards
        cardsPanel.setBackground(Color.WHITE);

        PainelCandidatoPerfil perfilPanel = new PainelCandidatoPerfil();
        PainelCandidatoVagas vagasPanel = new PainelCandidatoVagas();
        PainelCandidatoServicos servicosPanel = new PainelCandidatoServicos();

        cardsPanel.add(perfilPanel, "Perfil");
        cardsPanel.add(vagasPanel, "Vagas");
        cardsPanel.add(servicosPanel, "Servicos");

        mainContentPane.add(cardsPanel, BorderLayout.CENTER);

        // Inicializa mostrando o painel de perfil
        showCard("Perfil", meuPerfilButton);
    }

    private void setupListeners() {
        meuPerfilButton.addActionListener(_ -> showCard("Perfil", meuPerfilButton));
        vagasButton.addActionListener(_ -> showCard("Vagas", vagasButton));
        servicosButton.addActionListener(_ -> showCard("Servicos", servicosButton));
    }

    private JButton createNavButton(String text, int width) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(width, 40));

        defaultButtonBackgrounds.put(button, Color.WHITE);
        defaultButtonForegrounds.put(button, Color.BLACK);
        defaultButtonBorders.put(button, BorderFactory.createCompoundBorder(
                new LineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)));

        button.setBackground(defaultButtonBackgrounds.get(button));
        button.setForeground(defaultButtonForegrounds.get(button));
        button.setBorder(defaultButtonBorders.get(button));

        return button;
    }

    private void showCard(String cardName, JButton activeButton) {
        cardLayout.show(cardsPanel, cardName);
        resetButtonStyles();
        activeButton.setBackground(new Color(64, 64, 64));
        activeButton.setForeground(Color.WHITE);
        activeButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }

    private void resetButtonStyles() {
        for (JButton button : new JButton[]{meuPerfilButton, vagasButton, servicosButton}) {
            button.setBackground(defaultButtonBackgrounds.get(button));
            button.setForeground(defaultButtonForegrounds.get(button));
            button.setBorder(defaultButtonBorders.get(button));
        }
    }
}