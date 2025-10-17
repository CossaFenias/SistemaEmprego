package view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class PainelEmpresaPrincipal extends JFrame {
    // Declaração dos componentes
    private JPanel mainContentPane;
    private JPanel topNavPanel;
    private JPanel cardsPanel;
    private CardLayout cardLayout;
    private JButton minhaEmpresaButton;
    private JButton publicarVagaButton;
    private JButton minhasVagasButton;
    private Map<JButton, Color> defaultButtonBackgrounds;
    private Map<JButton, Color> defaultButtonForegrounds;
    private Map<JButton, Border> defaultButtonBorders;

    public PainelEmpresaPrincipal() {
        initializeComponents();
        setupLayout();
        setupListeners();
    }

    private void initializeComponents() {
        mainContentPane = new JPanel(new BorderLayout());
        topNavPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        cardLayout = new CardLayout();
        cardsPanel = new JPanel(cardLayout);

        minhaEmpresaButton = new JButton("Minha Empresa");
        publicarVagaButton = new JButton("Publicar Vaga");
        minhasVagasButton = new JButton("Minhas Vagas");

        defaultButtonBackgrounds = new HashMap<>();
        defaultButtonForegrounds = new HashMap<>();
        defaultButtonBorders = new HashMap<>();
    }

    private void setupLayout() {
        setTitle("Sistema de Gestão de Emprego - Painel Empresa");
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
        minhaEmpresaButton = createNavButton("Minha Empresa", 150);
        publicarVagaButton = createNavButton("Publicar Vaga", 150);
        minhasVagasButton = createNavButton("Minhas Vagas", 150);

        topNavPanel.add(minhaEmpresaButton);
        topNavPanel.add(publicarVagaButton);
        topNavPanel.add(minhasVagasButton);
        mainContentPane.add(topNavPanel, BorderLayout.NORTH);

        // Configuração do painel de cards
        cardsPanel.setBackground(Color.WHITE);

        PainelEmpresaPerfil perfilPanel = new PainelEmpresaPerfil();
        PainelEmpresaPublicarVaga publicarVagaPanel = new PainelEmpresaPublicarVaga();
        PainelEmpresaMinhasVagas minhasVagasPanel = new PainelEmpresaMinhasVagas();

        cardsPanel.add(perfilPanel, "Minha Empresa");
        cardsPanel.add(publicarVagaPanel, "Publicar Vaga");
        cardsPanel.add(minhasVagasPanel, "Minhas Vagas");

        mainContentPane.add(cardsPanel, BorderLayout.CENTER);

        // Inicializa mostrando o painel de perfil
        showCard("Minha Empresa", minhaEmpresaButton);
    }

    private void setupListeners() {
        minhaEmpresaButton.addActionListener(e -> showCard("Minha Empresa", minhaEmpresaButton));
        publicarVagaButton.addActionListener(e -> showCard("Publicar Vaga", publicarVagaButton));
        minhasVagasButton.addActionListener(e -> showCard("Minhas Vagas", minhasVagasButton));
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
        for (JButton button : new JButton[]{minhaEmpresaButton, publicarVagaButton, minhasVagasButton}) {
            button.setBackground(defaultButtonBackgrounds.get(button));
            button.setForeground(defaultButtonForegrounds.get(button));
            button.setBorder(defaultButtonBorders.get(button));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PainelEmpresaPrincipal().setVisible(true);
        });
    }
}