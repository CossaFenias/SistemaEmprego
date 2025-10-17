package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class PainelEmpresaMinhasVagas extends JPanel {
    // Variável para controlar o tema (true para tema escuro, false para tema claro)
    private final boolean darkMode = false;

    // Cores da paleta (ajustadas para um design mais moderno e flat)
    private final Color primaryBackground = darkMode ? new Color(45, 45, 45) : new Color(245, 248, 250); // Fundo principal
    private final Color secondaryBackground = darkMode ? new Color(60, 63, 65) : new Color(255, 255, 255); // Fundo dos painéis de conteúdo
    private final Color tertiaryBackground = darkMode ? new Color(75, 78, 80) : new Color(230, 235, 240); // Fundo da barra de cabeçalho da tabela/botões
    private final Color foregroundColor = darkMode ? new Color(220, 220, 220) : new Color(50, 50, 50); // Cor do texto
    private final Color accentColor = new Color(0, 123, 255); // Azul moderno para destaques (ex: hover, foco)
    private final Color borderColor = darkMode ? new Color(90, 90, 90) : new Color(200, 200, 200); // Cor da borda fina
    private final Color tableHeaderBackgroundColor = darkMode ? new Color(55, 55, 55) : new Color(230, 230, 230); // Fundo do cabeçalho da tabela
    private final Color tableGridColor = darkMode ? new Color(70, 70, 70) : new Color(220, 220, 220); // Linhas da grade da tabela
    private final Color tableRowEvenColor = darkMode ? new Color(50, 50, 50) : new Color(250, 250, 250); // Cor das linhas pares
    private final Color tableRowOddColor = darkMode ? new Color(45, 45, 45) : new Color(255, 255, 255); // Cor das linhas ímpares
    private final Color tableSelectionBackground = darkMode ? new Color(accentColor.getRed() - 30, accentColor.getGreen() - 30, accentColor.getBlue() - 30) : new Color(200, 220, 255); // Fundo da seleção da linha

    // Fontes modernas
    private final Font headerFont = new Font("Segoe UI", Font.BOLD, 14); // Fonte para cabeçalhos e títulos
    private final Font bodyFont = new Font("Segoe UI", Font.PLAIN, 13); // Fonte para texto normal
    private final Font buttonFont = new Font("Segoe UI", Font.BOLD, 12); // Fonte para botões

    // Declaração dos componentes
    private JPanel mainPanel;
    private JPanel contentPanel;
    private JPanel filterButtonsPanel;
    private JButton vagasAbertasButton;
    private JButton vagasFechadasButton;
    private JTable minhasVagasTable;
    private JScrollPane scrollPane;

    public PainelEmpresaMinhasVagas() {
        initializeComponents();
        setupLayout();
        applyModernStyle(); // Aplica os estilos modernos
    }

    private void initializeComponents() {
        // Inicializa os painéis com o layout e espaçamento desejados
        mainPanel = new JPanel(new BorderLayout(20, 20)); // Espaçamento maior para uma sensação mais clean
        contentPanel = new JPanel(new BorderLayout(15, 15)); // Espaçamento interno do conteúdo
        filterButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0)); // Botões de filtro alinhados à direita

        // Cria os botões de filtro
        vagasAbertasButton = createFilterButton("Vagas Abertas"); // Texto capitalizado para melhor legibilidade
        vagasFechadasButton = createFilterButton("Vagas Fechadas"); // Texto capitalizado para melhor legibilidade

        // Inicialização da tabela com dados de exemplo
        String[] columnNames = {"Título da Vaga", "Local", "Regime", "Publicação", "Status", "Ações"};
        Object[][] data = {
                {"Desenvolvedor Java Jr.", "São Paulo", "CLT", "2023-10-20", "Aberta", "Ver Candidatos"},
                {"Analista de Suporte", "Remoto", "PJ", "2023-10-15", "Aberta", "Ver Candidatos"},
                {"Gerente de Vendas", "Rio de Janeiro", "CLT", "2023-09-30", "Fechada", "Ver Detalhes"},
                {"Estagiário de Design", "Belo Horizonte", "Estágio", "2023-10-25", "Aberta", "Ver Candidatos"},
                {"Cientista de Dados", "São Paulo", "CLT", "2023-10-10", "Fechada", "Ver Detalhes"},
        };

        // Cria o modelo da tabela, impedindo a edição das células
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        minhasVagasTable = new JTable(tableModel);
        scrollPane = new JScrollPane(minhasVagasTable);
    }

    private void setupLayout() {
        // Define o layout do painel principal (este JPanel)
        setLayout(new BorderLayout(0, 0));

        // Adiciona um título ao painel para melhor contexto
        JLabel titleLabel = new JLabel("Minhas Vagas");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22)); // Fonte maior e em negrito para o título
        titleLabel.setForeground(foregroundColor); // Cor do título
        titleLabel.setHorizontalAlignment(JLabel.LEFT); // Alinhamento à esquerda
        // Adiciona um espaçamento superior para o título
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(primaryBackground);
        titlePanel.setBorder(new EmptyBorder(0, 0, 10, 0)); // Espaço abaixo do título
        titlePanel.add(titleLabel, BorderLayout.WEST);

        // O mainPanel agora irá conter o título e o contentPanel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(primaryBackground);
        headerPanel.setBorder(new EmptyBorder(0, 0, 10, 0)); // Espaço entre o título e o conteúdo
        headerPanel.add(titlePanel, BorderLayout.NORTH); // Título na parte superior
        headerPanel.add(filterButtonsPanel, BorderLayout.SOUTH); // Botões de filtro abaixo do título

        // O filterButtonsPanel agora é adicionado diretamente ao headerPanel para melhor organização
        filterButtonsPanel.add(vagasAbertasButton);
        filterButtonsPanel.add(vagasFechadasButton);
        filterButtonsPanel.setBackground(primaryBackground); // Cor de fundo do painel de botões

        // Adiciona o cabeçalho e os botões de filtro no topo do mainPanel
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Adiciona o contentPanel (tabela) no centro do mainPanel
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Finalmente, adiciona o mainPanel a este JPanel
        add(mainPanel, BorderLayout.CENTER);
    }

    // Método para aplicar estilos modernos aos componentes
    private void applyModernStyle() {
        // Configuração do painel principal
        mainPanel.setBackground(primaryBackground);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Padding maior para uma sensação de "respiro"

        // Configuração do painel de conteúdo
        contentPanel.setBackground(secondaryBackground); // Fundo branco ou mais claro para o conteúdo
        contentPanel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(borderColor, 1), // Borda fina e discreta
                new EmptyBorder(15, 15, 15, 15) // Padding interno
        ));

        // Configuração do painel de botões de filtro
        filterButtonsPanel.setBackground(primaryBackground); // Usa a cor de fundo principal

        // Configuração da tabela
        configureTable();

        // Configuração do scroll pane
        scrollPane.setPreferredSize(new Dimension(800, 400)); // Tamanho preferencial
        scrollPane.setBorder(new LineBorder(borderColor, 1)); // Borda fina para o scroll pane
        // Remove a borda padrão do JScrollPane para um visual mais clean
        scrollPane.getViewport().setBackground(secondaryBackground); // Fundo do viewport da tabela

        // Adiciona o scrollPane (com a tabela) ao contentPanel
        contentPanel.add(scrollPane, BorderLayout.CENTER);
    }

    private void configureTable() {
        minhasVagasTable.setFont(bodyFont); // Fonte moderna para o corpo da tabela
        minhasVagasTable.setRowHeight(32); // Altura da linha um pouco maior para melhor espaçamento
        minhasVagasTable.setFillsViewportHeight(true);
        minhasVagasTable.setGridColor(tableGridColor); // Cor das linhas da grade
        minhasVagasTable.setSelectionBackground(tableSelectionBackground); // Cor de seleção suave
        minhasVagasTable.setSelectionForeground(foregroundColor); // Texto com cor de destaque na seleção

        // --- Estilização do cabeçalho da tabela (JTableHeader) ---
        JTableHeader tableHeader = minhasVagasTable.getTableHeader();
        tableHeader.setFont(headerFont); // Fonte moderna e negrito para o cabeçalho
        tableHeader.setBackground(tableHeaderBackgroundColor); // Cor de fundo do cabeçalho
        tableHeader.setForeground(foregroundColor); // Cor do texto do cabeçalho
        tableHeader.setBorder(new LineBorder(borderColor, 1)); // Borda sutil no cabeçalho
        tableHeader.setReorderingAllowed(false); // Impede reordenar colunas
        tableHeader.setResizingAllowed(true); // Permite redimensionar colunas (padrão)

        // Custom renderer para o cabeçalho para centralizar e adicionar padding
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(0, (column == 0 ? 0 : 1), 1, 0, tableGridColor), // Borda à esquerda de cada coluna (exceto a primeira)
                        new EmptyBorder(8, 5, 8, 5) // Padding interno
                ));
                label.setBackground(tableHeaderBackgroundColor);
                label.setForeground(foregroundColor);
                return label;
            }
        };
        for (int i = 0; i < minhasVagasTable.getColumnCount(); i++) {
            minhasVagasTable.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

        // --- Custom renderer para as células da tabela ---
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setHorizontalAlignment(JLabel.CENTER); // Centraliza o texto
                label.setFont(bodyFont); // Aplica a fonte do corpo
                label.setForeground(foregroundColor); // Cor do texto padrão

                // Alterna a cor do fundo das linhas para melhor legibilidade (zebra effect)
                if (isSelected) {
                    label.setBackground(tableSelectionBackground);
                    label.setForeground(Color.WHITE); // Texto branco na seleção para maior contraste
                } else {
                    if (row % 2 == 0) { // Linha par
                        label.setBackground(tableRowEvenColor);
                    } else { // Linha ímpar
                        label.setBackground(tableRowOddColor);
                    }
                }
                label.setBorder(new EmptyBorder(5, 5, 5, 5)); // Padding interno para as células

                return label;
            }
        };

        // Aplica o custom renderer a todas as colunas
        for (int i = 0; i < minhasVagasTable.getColumnCount(); i++) {
            minhasVagasTable.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }

        // Ajusta a largura das colunas
        // Essas larguras podem ser ajustadas dinamicamente ou fixas, dependendo da necessidade
        minhasVagasTable.getColumnModel().getColumn(0).setPreferredWidth(200);
        minhasVagasTable.getColumnModel().getColumn(1).setPreferredWidth(120);
        minhasVagasTable.getColumnModel().getColumn(2).setPreferredWidth(80);
        minhasVagasTable.getColumnModel().getColumn(3).setPreferredWidth(120);
        minhasVagasTable.getColumnModel().getColumn(4).setPreferredWidth(80);
        minhasVagasTable.getColumnModel().getColumn(5).setPreferredWidth(150);
    }

    // Método auxiliar para criar botões de filtro com estilo flat moderno
    private JButton createFilterButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(tertiaryBackground); // Cor de fundo mais suave
        button.setForeground(foregroundColor); // Cor do texto
        button.setFont(buttonFont); // Fonte do botão
        button.setFocusPainted(false); // Remove o contorno de foco padrão
        button.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(borderColor, 1), // Borda fina e discreta
                BorderFactory.createEmptyBorder(8, 15, 8, 15))); // Padding interno para o botão

        // Adiciona efeitos de hover e clique para feedback visual
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(accentColor); // Cor de destaque ao passar o mouse
                button.setForeground(Color.WHITE); // Texto branco no hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(tertiaryBackground); // Volta à cor original
                button.setForeground(foregroundColor); // Volta à cor do texto original
            }

            @Override
            public void mousePressed(MouseEvent e) {
                button.setBackground(accentColor.darker()); // Cor mais escura ao clicar
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // Ao soltar o clique, volta para a cor de hover se o mouse ainda estiver sobre o botão,
                // ou para a cor original se não estiver.
                if (button.contains(e.getPoint())) {
                    button.setBackground(accentColor);
                    button.setForeground(Color.WHITE);
                } else {
                    button.setBackground(tertiaryBackground);
                    button.setForeground(foregroundColor);
                }
            }
        });
        return button;
    }
}