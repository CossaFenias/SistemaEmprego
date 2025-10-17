package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PainelCandidatoVagas extends JPanel {
    // Declaração dos componentes
    private JPanel mainPanel;
    private JPanel contentPanel;
    private JPanel filterButtonsPanel;
    private JButton vagasAbertasButton;
    private JButton vagasFechadasButton;
    private JButton candidaturasButton;
    private JTable vagasTable;
    private JScrollPane scrollPane;

    public PainelCandidatoVagas() {
        initializeComponents();
        setupLayout();
    }

    private void initializeComponents() {
        mainPanel = new JPanel(new BorderLayout(20, 20));
        contentPanel = new JPanel();
        filterButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));

        vagasAbertasButton = createFilterButton("vagas abertas");
        vagasFechadasButton = createFilterButton("vagas fechadas");
        candidaturasButton = createFilterButton("candidaturas");

        // Inicialização da tabela
        String[] columnNames = {"Título da Vaga", "Empresa", "Localização", "Tipo de Contrato", "Data de Publicação"};
        Object[][] data = {
                {"Desenvolvedor Java Sênior", "Tech Solutions Ltda.", "São Paulo, SP", "CLT", "2023-10-26"},
                {"Analista de Marketing Digital", "Creative Minds Ag.", "Rio de Janeiro, RJ", "PJ", "2023-10-25"},
                {"UX/UI Designer Pleno", "Innovate Systems", "Belo Horizonte, MG", "CLT", "2023-10-24"},
                {"Engenheiro de Dados", "Data Insights Corp.", "Remoto", "CLT", "2023-10-23"},
                {"Gerente de Projetos", "Global Connect", "Curitiba, PR", "PJ", "2023-10-22"},
                {"Suporte Técnico N1", "HelpDesk Services", "Porto Alegre, RS", "CLT", "2023-10-21"},
                {"Contador Júnior", "Finanças Essenciais", "Campinas, SP", "CLT", "2023-10-20"},
                {"Desenvolvedor Front-end", "Web Crafters", "Recife, PE", "PJ", "2023-10-19"},
                {"Analista de RH", "Pessoas & Talentos", "Brasília, DF", "CLT", "2023-10-18"},
                {"Cientista de Dados", "Deep Learning Co.", "São Paulo, SP", "CLT", "2023-10-17"},
        };

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        vagasTable = new JTable(tableModel);
        scrollPane = new JScrollPane(vagasTable);
    }

    private void setupLayout() {
        setLayout(new BorderLayout(0, 0));

        // Configuração do painel principal
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Configuração do painel de conteúdo
        contentPanel.setBackground(new Color(240, 240, 240));
        contentPanel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.LIGHT_GRAY, 1),
                new EmptyBorder(20, 20, 20, 20)
        ));
        contentPanel.setLayout(new BorderLayout(15, 15));

        // Configuração do painel de botões de filtro
        filterButtonsPanel.setBackground(new Color(240, 240, 240));
        filterButtonsPanel.add(vagasAbertasButton);
        filterButtonsPanel.add(vagasFechadasButton);
        filterButtonsPanel.add(candidaturasButton);
        contentPanel.add(filterButtonsPanel, BorderLayout.NORTH);

        // Configuração da tabela
        configureTable();

        // Configuração do scroll pane
        scrollPane.setPreferredSize(new Dimension(800, 400));
        scrollPane.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(contentPanel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);
    }

    private void configureTable() {
        vagasTable.setFont(new Font("Arial", Font.PLAIN, 14));
        vagasTable.setRowHeight(28);
        vagasTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        vagasTable.getTableHeader().setBackground(new Color(220, 220, 220));
        vagasTable.getTableHeader().setForeground(Color.BLACK);
        vagasTable.setFillsViewportHeight(true);
        vagasTable.setSelectionBackground(new Color(200, 220, 255));
        vagasTable.setGridColor(Color.LIGHT_GRAY);

        // Ajusta a largura das colunas
        vagasTable.getColumnModel().getColumn(0).setPreferredWidth(250);
        vagasTable.getColumnModel().getColumn(1).setPreferredWidth(180);
        vagasTable.getColumnModel().getColumn(2).setPreferredWidth(180);
        vagasTable.getColumnModel().getColumn(3).setPreferredWidth(120);
        vagasTable.getColumnModel().getColumn(4).setPreferredWidth(150);

        // Centraliza o texto nas células
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < vagasTable.getColumnCount(); i++) {
            vagasTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    // Método auxiliar (mantido)
    private JButton createFilterButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.BLACK, 1),
                BorderFactory.createEmptyBorder(5, 12, 5, 12)));
        return button;
    }

    // REMOVER O MÉTODO main()
    // public static void main(String[] args) { ... }
}