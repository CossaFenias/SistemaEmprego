package view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxUI; // Import necessário para estilizar o JComboBox
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.net.URL; // Para carregar o ícone

public class CadastroCandidato extends JFrame {

    private JLabel title, lblApelido, lblNomes, lblGenero, lblDataNasc, lblEmail, lblNacionalidade,
            lblNaturalidade, lblTelefones, lblEndereco, lblProvincia, lblDistrito, lblRuaAvenida,
            lblNumero, lblNomePai, lblNomeMae, lblNumBI, lblDataEmissao, lblNuit;

    private JTextField txtApelido, txtNomes, txtGenero, txtDataNasc, txtEmail, txtNacionalidade, txtNaturalidade,
            txtTel1, txtTell2, txtRuaAvenida, txtNumero, txtNomePai, txtNomeMae, txtNumBI, txtDataEmissao, txtNuit;

    private JButton btnAddFormacao, btnAddExperiencia, btnSalvar, btnCancelar;
    private JComboBox<String> cbProvincia, cbDistrito;
    private JSeparator sep1, sep2;
    private JScrollPane formScrollPane;

    public CadastroCandidato() {
        super("Cadastro de Candidato - Sistema de Gestão de Emprego"); // Título moderno

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1310, 720);
        // Centraliza a janela na tela
        setLocationRelativeTo(null);

        // Define um ícone para a janela (substitua "icon.png" pelo caminho do seu ícone)
        // Certifique-se de que "icon.png" esteja no classpath ou use um caminho absoluto.
        try {
            URL iconURL = getClass().getResource("/images/icon.png"); // Exemplo: dentro de uma pasta 'images' no classpath
            if (iconURL != null) {
                ImageIcon frameIcon = new ImageIcon(iconURL);
                setIconImage(frameIcon.getImage());
            } else {
                System.out.println("Ícone não encontrado: /images/icon.png");
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar o ícone: " + e.getMessage());
        }

        // Cor de fundo suave para o JFrame
        getContentPane().setBackground(new Color(245, 245, 245)); // Light Grayish White

        // Mantém o layout nulo para o JFrame, conforme a estrutura original
        setLayout(null);

        init();
        addComponents();

        setResizable(false);
        setVisible(true);
    }

    public void init() {
        // As cores dos labels foram ajustadas, mas suas posições (setBounds)
        // e tamanhos originais foram mantidos.

        title = createLabel("Cadastro de Candidato", 460, 30, 361, 39, Font.BOLD, 32, new Color(0, 0, 0)); // Cor azul esverdeado para o título

        lblApelido = createLabel("Apelido:", 30, 15, 98, 29, Font.PLAIN, 18, new Color(70, 70, 70)); // Fonte e cor modernas
        lblNomes = createLabel("Outros Nomes:", 310, 15, 176, 29, Font.PLAIN, 18, new Color(70, 70, 70));
        lblGenero = createLabel("Gênero:", 940, 15, 93, 29, Font.PLAIN, 18, new Color(70, 70, 70));
        lblDataNasc = createLabel("Data Nasc.:", 30, 125, 137, 29, Font.PLAIN, 18, new Color(70, 70, 70));
        lblEmail = createLabel("Email:", 630, 125, 71, 29, Font.PLAIN, 18, new Color(70, 70, 70));
        lblNacionalidade = createLabel("Nacionalidade:", 30, 235, 175, 29, Font.PLAIN, 18, new Color(70, 70, 70));
        lblNaturalidade = createLabel("Naturalidade:", 630, 235, 159, 29, Font.PLAIN, 18, new Color(70, 70, 70));
        lblTelefones = createLabel("Telefones:", 30, 363, 123, 29, Font.PLAIN, 18, new Color(70, 70, 70));
        lblEndereco = createLabel("Endereço:", 30, 472, 119, 29, Font.PLAIN, 18, new Color(70, 70, 70));
        lblProvincia = createLabel("Província:", 30, 514, 117, 29, Font.PLAIN, 18, new Color(70, 70, 70));
        lblDistrito = createLabel("Distrito:", 260, 514, 95, 29, Font.PLAIN, 18, new Color(70, 70, 70));
        lblRuaAvenida = createLabel("Rua/Avenida:", 520, 514, 156, 29, Font.PLAIN, 18, new Color(70, 70, 70));
        lblNumero = createLabel("Número:", 1000, 514, 100, 29, Font.PLAIN, 18, new Color(70, 70, 70));
        lblNomePai = createLabel("Nome do Pai:", 30, 644, 152, 29, Font.PLAIN, 18, new Color(70, 70, 70));
        lblNomeMae = createLabel("Nome da Mãe:", 630, 644, 167, 29, Font.PLAIN, 18, new Color(70, 70, 70));
        lblNumBI = createLabel("Nº. BI:", 30, 756, 71, 29, Font.PLAIN, 18, new Color(70, 70, 70));
        lblDataEmissao = createLabel("Data de Emissão:", 630, 754, 202, 29, Font.PLAIN, 18, new Color(70, 70, 70));
        lblNuit = createLabel("NUIT:", 30, 864, 65, 29, Font.PLAIN, 18, new Color(70, 70, 70));

        // ComboBoxes (posições e tamanhos originais mantidos, altura ajustada para 35)
        cbProvincia = createComboBox(30, 545, 200, 35);
        cbDistrito = createComboBox(260, 545, 230, 35);

        // TextFields (posições e tamanhos originais mantidos, altura ajustada para 35)
        txtApelido = createTextField(30, 44, 250, 35);
        txtNomes = createTextField(310, 44, 600, 35);
        txtGenero = createTextField(940, 44, 260, 35);
        txtDataNasc = createTextField(30, 154, 570, 35);
        txtEmail = createTextField(630, 154, 570, 35);
        txtNacionalidade = createTextField(30, 264, 570, 35);
        txtNaturalidade = createTextField(630, 264, 570, 35);
        txtTel1 = createTextField(30, 392, 570, 35);
        txtTell2 = createTextField(630, 392, 570, 35);
        txtRuaAvenida = createTextField(520, 545, 450, 35);
        txtNumero = createTextField(1000, 545, 200, 35);
        txtNomePai = createTextField(30, 673, 570, 35);
        txtNomeMae = createTextField(630, 673, 570, 35);
        txtNumBI = createTextField(30, 783, 570, 35);
        txtDataEmissao = createTextField(630, 783, 570, 35);
        txtNuit = createTextField(30, 893, 570, 35);

        // Separadores (posições e tamanhos originais mantidos)
        sep1 = createSeparator(15, 345, 1200, 1);
        sep2 = createSeparator(15, 626, 1200, 1);

        // Botões de ação do formulário (posições e tamanhos originais mantidos, altura ajustada para 45)
        btnAddFormacao = createButton("Adicionar Formação Académica", 630, 893, 270, 45, new Color(0x606060), Color.WHITE, Font.BOLD, 16);
        btnAddExperiencia = createButton("Adicionar Experiência Profissional", 930, 893, 270, 45, new Color(0x606060), Color.WHITE, Font.BOLD, 16);

        // Botões de Salvar/Cancelar (posições e tamanhos originais mantidos, altura ajustada para 45)
        // Note que estes botões, no código original, estão fora do formScrollPane, direto no JFrame
        btnSalvar = createButton("Salvar", 825, 630, 200, 45, new Color(60, 179, 113), Color.WHITE, Font.BOLD, 18);
        btnCancelar = createButton("Cancelar", 1055, 630, 200, 45, new Color(220, 20, 60), Color.WHITE, Font.BOLD, 18);

        // Configuração do JScrollPane
        // A posição e tamanho do JScrollPane permanecem os mesmos
        formScrollPane = new JScrollPane(getFormPanel());
        formScrollPane.setBounds(25, 100, 1245, 500);
        formScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        formScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        formScrollPane.getVerticalScrollBar().setUnitIncrement(16); // Scroll mais suave
        formScrollPane.setBorder(BorderFactory.createEmptyBorder()); // Remove a borda padrão do JScrollPane
        formScrollPane.getViewport().setBackground(new Color(245, 245, 245)); // Cor de fundo do viewport
    }

    // Métodos de create components
    // Mantendo os parâmetros de posição e tamanho para ser compatível com o layout nulo.
    private JLabel createLabel(String text, int x, int y, int width, int height, int fontStyle, int fontSize, Color foregroundColor) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        label.setFont(new Font("Segoe UI", fontStyle, fontSize)); // Fonte moderna
        label.setForeground(foregroundColor); // Cor do texto
        return label;
    }

    // Atualizado: TextField com bordas limpas e foco destacado
    // Mantendo os parâmetros de posição e tamanho.
    private JTextField createTextField(int x, int y, int width, int height) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, width, height);
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 16)); // Fonte moderna e tamanho
        textField.setForeground(new Color(70, 70, 70)); // Cor do texto cinza escuro
        textField.setBackground(Color.WHITE); // Fundo branco
        textField.setCaretColor(new Color(60, 140, 160)); // Cor do cursor

        // Borda padrão e borda de foco
        Border defaultBorder = new CompoundBorder(
                new LineBorder(new Color(200, 200, 200), 1), // Borda cinza suave
                new EmptyBorder(8, 10, 8, 10) // Espaçamento interno
        );
        Border focusedBorder = new CompoundBorder(
                new LineBorder(new Color(70, 70, 70), 2), // Borda azul/verde mais grossa ao focar
                new EmptyBorder(8, 10, 8, 10)
        );

        textField.setBorder(defaultBorder);

        // Adiciona listener para mudar a borda ao focar
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                textField.setBorder(focusedBorder);
            }

            @Override
            public void focusLost(FocusEvent e) {
                textField.setBorder(defaultBorder);
            }
        });
        return textField;
    }

    // Atualizado: ComboBox com estilo moderno
    // Mantendo os parâmetros de posição e tamanho.
    private JComboBox<String> createComboBox(int x, int y, int width, int height) {
        JComboBox<String> comboBox = new JComboBox<>(new String[]{}); // Mantém o construtor original com array vazio
        comboBox.setBounds(x, y, width, height);
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 16)); // Fonte moderna
        comboBox.setBackground(Color.WHITE);
        comboBox.setForeground(new Color(70, 70, 70));
        comboBox.setBorder(new CompoundBorder(
                new LineBorder(new Color(200, 200, 200), 1), // Borda cinza suave
                new EmptyBorder(5, 5, 5, 5) // Espaçamento interno
        ));
        // Melhoria visual para o seletor (arrow button)
        comboBox.setUI(new BasicComboBoxUI());
        return comboBox;
    }

    // Mantém JSeparator, mas com cores mais coerentes
    // Mantendo os parâmetros de posição e tamanho.
    private JSeparator createSeparator(int x, int y, int width, int height) {
        JSeparator separator = new JSeparator();
        separator.setBounds(x, y, width, height);
        separator.setBackground(new Color(200, 200, 200)); // Cinza mais suave
        separator.setForeground(new Color(200, 200, 200));
        return separator;
    }

    // Atualizado: Botões não arredondados e sem sombra, com cores modernas
    // Mantendo os parâmetros de posição e tamanho.
    private JButton createButton(String text, int x, int y, int width, int height, Color backgroundColor, Color foregroundColor, int fontStyle, int fontSize) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setBackground(backgroundColor);
        button.setForeground(foregroundColor);
        button.setFont(new Font("Segoe UI", fontStyle, fontSize)); // Fonte moderna
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Borda limpa, sem arredondamento
        button.setFocusPainted(false); // Remove o foco da borda
        // Efeito hover simples
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(backgroundColor.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(backgroundColor);
            }
        });
        return button;
    }

    public JPanel getFormPanel() {
        JPanel panel = new JPanel(null); // MUITO IMPORTANTE: Manteve o layout nulo para o painel do formulário
        panel.setBounds(25, 100, 1230, 974); // As bounds originais foram mantidas para o JPanel
        panel.setBackground(new Color(0xd9d9d9)); // Cor de fundo suave para o painel

        // Adiciona todos os componentes ao painel do formulário, mantendo suas posições originais
        panel.add(lblApelido);
        panel.add(lblNomes);
        panel.add(lblGenero);
        panel.add(lblDataNasc);
        panel.add(lblEmail);
        panel.add(lblNacionalidade);
        panel.add(lblNaturalidade);
        panel.add(lblTelefones);
        panel.add(lblEndereco);
        panel.add(lblProvincia);
        panel.add(lblDistrito);
        panel.add(lblRuaAvenida);
        panel.add(lblNumero);
        panel.add(lblNomePai);
        panel.add(lblNomeMae);
        panel.add(lblNumBI);
        panel.add(lblDataEmissao);
        panel.add(lblNuit);

        panel.add(txtApelido);
        panel.add(txtNomes);
        panel.add(txtGenero);
        panel.add(txtDataNasc);
        panel.add(txtEmail);
        panel.add(txtNacionalidade);
        panel.add(txtNaturalidade);
        panel.add(txtTel1);
        panel.add(txtTell2);
        panel.add(txtRuaAvenida);
        panel.add(txtNumero);
        panel.add(txtNomePai);
        panel.add(txtNomeMae);
        panel.add(txtNumBI);
        panel.add(txtDataEmissao);
        panel.add(txtNuit);

        panel.add(sep1);
        panel.add(sep2);

        panel.add(cbProvincia);
        panel.add(cbDistrito);

        panel.add(btnAddFormacao);
        panel.add(btnAddExperiencia);

        panel.setPreferredSize(new Dimension(1230,974)); // Mantém o tamanho preferencial original

        return panel;
    }

    public void addComponents() {
        // Adiciona os componentes diretamente ao JFrame com layout nulo,
        // mantendo as posições originais do seu código.
        add(title); // Adicionado diretamente ao JFrame
        add(formScrollPane); // O JScrollPane mantém sua posição original no JFrame

        // Os botões Salvar e Cancelar estão no JFrame, e não no JScrollPane ou no formPanel
        // Isso mantém a estrutura original da sua interface.
        add(btnSalvar);
        add(btnCancelar);
    }

    public JTextField getTxtApelido() {
        return txtApelido;
    }

    public JTextField getTxtNomes() {
        return txtNomes;
    }

    public JTextField getTxtGenero() {
        return txtGenero;
    }

    public JTextField getTxtDataNasc() {
        return txtDataNasc;
    }

    public JTextField getTxtEmail() {
        return txtEmail;
    }

    public JTextField getTxtNacionalidade() {
        return txtNacionalidade;
    }

    public JTextField getTxtNaturalidade() {
        return txtNaturalidade;
    }

    public JTextField getTxtTel1() {
        return txtTel1;
    }

    public JTextField getTxtTell2() {
        return txtTell2;
    }

    public JTextField getTxtRuaAvenida() {
        return txtRuaAvenida;
    }

    public JTextField getTxtNumero() {
        return txtNumero;
    }

    public JTextField getTxtNomePai() {
        return txtNomePai;
    }

    public JTextField getTxtNomeMae() {
        return txtNomeMae;
    }

    public JTextField getTxtNumBI() {
        return txtNumBI;
    }

    public JTextField getTxtDataEmissao() {
        return txtDataEmissao;
    }

    public JTextField getTxtNuit() {
        return txtNuit;
    }

    public JButton getBtnAddFormacao() {
        return btnAddFormacao;
    }

    public JButton getBtnAddExperiencia() {
        return btnAddExperiencia;
    }

    public JButton getBtnSalvar() {
        return btnSalvar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public static void main(String[] args) {
        // Garante que a GUI seja construída na Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(CadastroCandidato::new);
    }
}