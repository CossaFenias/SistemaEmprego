package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PainelCandidatoServicos extends JPanel {
    // Declaração dos componentes
    private JPanel mainPanel;
    private JPanel contentPanel;
    private JPanel filterButtonsPanel;
    private JButton todosServicosButton;
    private JButton servicosContratadosButton;
    private JButton servicosDisponiveisButton;
    private JTable servicosTable;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;

    public PainelCandidatoServicos() {
        initializeComponents();
        setupLayout();
    }

    private void initializeComponents() {
        mainPanel = new JPanel(new BorderLayout(20, 20));
        contentPanel = new JPanel();
        filterButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));

        todosServicosButton = createFilterButton("todos serviços");
        servicosContratadosButton = createFilterButton("serviços contratados");
        servicosDisponiveisButton = createFilterButton("serviços disponíveis");

        // Inicialização da tabela
        String[] columnNames = {"Serviço", "Descrição", "Preço", "Status", "Data de Contratação"};
        Object[][] data = {
                {"Criação de Sites Profissionais", "Desenvolvimento de sites responsivos", "R$ 1.500,00", "Disponível", "-"},
                {"Consultoria de Carreira", "Orientação profissional personalizada", "R$ 300,00", "Disponível", "-"},
                {"Revisão de Currículo", "Análise e melhoria do seu currículo", "R$ 150,00", "Contratado", "2023-10-15"},
                {"Aulas Particulares de Inglês", "Aulas online de inglês básico ao avançado", "R$ 80,00/hora", "Disponível", "-"},
                {"Preparação para Entrevistas", "Simulações e dicas para entrevistas", "R$ 200,00", "Disponível", "-"},
                {"Otimização de LinkedIn", "Melhoria do perfil profissional", "R$ 120,00", "Contratado", "2023-10-20"},
                {"Desenvolvimento de Portfólio", "Criação de portfólio online", "R$ 800,00", "Disponível", "-"},
                {"Mentoria em Programação", "Acompanhamento para desenvolvedores", "R$ 100,00/hora", "Disponível", "-"},
                {"Criação de Sites Profissionais", "Desenvolvimento de sites responsivos", "R$ 1.500,00", "Disponível", "-"},
                {"Consultoria de Carreira", "Orientação profissional personalizada", "R$ 300,00", "Disponível", "-"},
                {"Revisão de Currículo", "Análise e melhoria do seu currículo", "R$ 150,00", "Contratado", "2023-10-15"},
                {"Aulas Particulares de Inglês", "Aulas online de inglês básico ao avançado", "R$ 80,00/hora", "Disponível", "-"},
                {"Preparação para Entrevistas", "Simulações e dicas para entrevistas", "R$ 200,00", "Disponível", "-"},
                {"Otimização de LinkedIn", "Melhoria do perfil profissional", "R$ 120,00", "Contratado", "2023-10-20"},
                {"Desenvolvimento de Portfólio", "Criação de portfólio online", "R$ 800,00", "Disponível", "-"},
                {"Mentoria em Programação", "Acompanhamento para desenvolvedores", "R$ 100,00/hora", "Disponível", "-"},
        };

        tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        servicosTable = new JTable(tableModel);
        scrollPane = new JScrollPane(servicosTable);
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
        filterButtonsPanel.add(todosServicosButton);
        filterButtonsPanel.add(servicosContratadosButton);
        filterButtonsPanel.add(servicosDisponiveisButton);
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
        servicosTable.setFont(new Font("Arial", Font.PLAIN, 14));
        servicosTable.setRowHeight(28);
        servicosTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        servicosTable.getTableHeader().setBackground(new Color(220, 220, 220));
        servicosTable.getTableHeader().setForeground(Color.BLACK);
        servicosTable.setFillsViewportHeight(true);
        servicosTable.setSelectionBackground(new Color(200, 220, 255));
        servicosTable.setGridColor(Color.LIGHT_GRAY);

        // Ajusta a largura das colunas
        servicosTable.getColumnModel().getColumn(0).setPreferredWidth(250); // Serviço
        servicosTable.getColumnModel().getColumn(1).setPreferredWidth(300); // Descrição
        servicosTable.getColumnModel().getColumn(2).setPreferredWidth(120); // Preço
        servicosTable.getColumnModel().getColumn(3).setPreferredWidth(120); // Status
        servicosTable.getColumnModel().getColumn(4).setPreferredWidth(150); // Data de Contratação

        // Centraliza o texto nas células
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < servicosTable.getColumnCount(); i++) {
            servicosTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Adiciona listener para cliques na tabela (substitui o comportamento dos painéis antigos)
        servicosTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = servicosTable.rowAtPoint(evt.getPoint());
                int col = servicosTable.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    String servico = (String) tableModel.getValueAt(row, 0);
                    String descricao = (String) tableModel.getValueAt(row, 1);
                    String preco = (String) tableModel.getValueAt(row, 2);
                    String status = (String) tableModel.getValueAt(row, 3);

                    JOptionPane.showMessageDialog(PainelCandidatoServicos.this,
                            "Serviço: " + servico + "\n" +
                                    "Descrição: " + descricao + "\n" +
                                    "Preço: " + preco + "\n" +
                                    "Status: " + status,
                            "Detalhes do Serviço",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    // Método auxiliar para criar botões de filtro
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