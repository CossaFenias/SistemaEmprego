package view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.net.URL;

public class CadastroCliente extends JFrame {

    private JLabel title, lblNome, lblEmail, lblTelefones, lblEndereco,
            lblProvincia, lblDistrito, lblRuaAvenida, lblNumero;

    private JTextField txtNome, txtEmail, txtTel1, txtTel2,
            txtRuaAvenida, txtNumero;

    private JComboBox<String> cbProvincia, cbDistrito;
    private JButton btnSalvar, btnCancelar;

    public CadastroCliente() {
        super("Cadastro de Cliente - Sistema de Gestão de Emprego");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1310, 690);
        setLocationRelativeTo(null);

        // Define um ícone para a janela
        try {
            URL iconURL = getClass().getResource("/images/icon.png");
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
        getContentPane().setBackground(new Color(245, 245, 245));

        // Mantém o layout nulo para o JFrame
        setLayout(null);

        init();
        addComponents();

        setResizable(false);
        setVisible(true);
    }

    public void init() {
        // Título
        title = createLabel("Cadastro de Cliente", 482, 30, 311, 39, Font.BOLD, 32, new Color(0, 0, 0));

        // Labels - ajustadas para cores modernas
        lblNome = createLabel("Nome:", 30, 42, 76, 29, Font.PLAIN, 18, new Color(70, 70, 70));
        lblEmail = createLabel("Email:", 630, 42, 71, 29, Font.PLAIN, 18, new Color(70, 70, 70));
        lblTelefones = createLabel("Telefones:", 30, 152, 123, 29, Font.PLAIN, 18, new Color(70, 70, 70));
        lblEndereco = createLabel("Endereço:", 30, 261, 119, 29, Font.PLAIN, 18, new Color(70, 70, 70));
        lblProvincia = createLabel("Província:", 30, 303, 117, 29, Font.PLAIN, 18, new Color(70, 70, 70));
        lblDistrito = createLabel("Distrito:", 260, 303, 95, 29, Font.PLAIN, 18, new Color(70, 70, 70));
        lblRuaAvenida = createLabel("Rua/Avenida:", 520, 303, 156, 29, Font.PLAIN, 18, new Color(70, 70, 70));
        lblNumero = createLabel("Número:", 1000, 303, 100, 29, Font.PLAIN, 18, new Color(70, 70, 70));

        // Campos de texto - atualizados para estilo moderno
        txtNome = createTextField(30, 77, 570, 35);
        txtEmail = createTextField(630, 77, 570, 35);
        txtTel1 = createTextField(30, 181, 570, 35);
        txtTel2 = createTextField(630, 181, 570, 35);
        txtRuaAvenida = createTextField(520, 334, 450, 35);
        txtNumero = createTextField(1000, 334, 200, 35);

        // Comboboxes - atualizados para estilo moderno
        cbProvincia = createComboBox(30, 334, 200, 35);
        cbDistrito = createComboBox(260, 334, 230, 35);

        // Botões - atualizados para estilo moderno
        btnSalvar = createButton("Salvar", 825, 560, 200, 45, new Color(60, 179, 113), Color.WHITE, Font.BOLD, 18);
        btnCancelar = createButton("Cancelar", 1055, 560, 200, 45, new Color(220, 20, 60), Color.WHITE, Font.BOLD, 18);
    }

    // Métodos de create components seguindo o padrão do CadastroCandidato
    private JLabel createLabel(String text, int x, int y, int width, int height, int fontStyle, int fontSize, Color foregroundColor) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        label.setFont(new Font("Segoe UI", fontStyle, fontSize));
        label.setForeground(foregroundColor);
        return label;
    }

    private JTextField createTextField(int x, int y, int width, int height) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, width, height);
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        textField.setForeground(new Color(70, 70, 70));
        textField.setBackground(Color.WHITE);
        textField.setCaretColor(new Color(60, 140, 160));

        // Borda padrão e borda de foco
        Border defaultBorder = new CompoundBorder(
                new LineBorder(new Color(200, 200, 200), 1),
                new EmptyBorder(8, 10, 8, 10)
        );
        Border focusedBorder = new CompoundBorder(
                new LineBorder(new Color(70, 70, 70), 2),
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

    private JComboBox<String> createComboBox(int x, int y, int width, int height) {
        JComboBox<String> comboBox = new JComboBox<>(new String[]{});
        comboBox.setBounds(x, y, width, height);
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        comboBox.setBackground(Color.WHITE);
        comboBox.setForeground(new Color(70, 70, 70));
        comboBox.setBorder(new CompoundBorder(
                new LineBorder(new Color(200, 200, 200), 1),
                new EmptyBorder(5, 5, 5, 5)
        ));

        // Melhoria visual para o seletor (arrow button)
        comboBox.setUI(new BasicComboBoxUI());
        return comboBox;
    }

    private JButton createButton(String text, int x, int y, int width, int height, Color backgroundColor, Color foregroundColor, int fontStyle, int fontSize) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setBackground(backgroundColor);
        button.setForeground(foregroundColor);
        button.setFont(new Font("Segoe UI", fontStyle, fontSize));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setFocusPainted(false);

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
        JPanel panel = new JPanel(null);
        panel.setBounds(25, 100, 1230, 430);
        panel.setBackground(new Color(0xd9d9d9));

        panel.add(lblNome);
        panel.add(lblEmail);
        panel.add(lblTelefones);
        panel.add(lblEndereco);
        panel.add(lblProvincia);
        panel.add(lblDistrito);
        panel.add(lblRuaAvenida);
        panel.add(lblNumero);

        panel.add(txtNome);
        panel.add(txtEmail);
        panel.add(txtTel1);
        panel.add(txtTel2);
        panel.add(txtRuaAvenida);
        panel.add(txtNumero);

        panel.add(cbProvincia);
        panel.add(cbDistrito);

        panel.setPreferredSize(new Dimension(1230, 430));

        return panel;
    }

    public void addComponents() {
        add(title);
        add(getFormPanel());
        add(btnSalvar);
        add(btnCancelar);
    }

    // Getters para os componentes
    public JTextField getTxtNome() {
        return txtNome;
    }

    public JTextField getTxtEmail() {
        return txtEmail;
    }

    public JTextField getTxtTel1() {
        return txtTel1;
    }

    public JTextField getTxtTel2() {
        return txtTel2;
    }

    public JTextField getTxtRuaAvenida() {
        return txtRuaAvenida;
    }

    public JTextField getTxtNumero() {
        return txtNumero;
    }

    public JComboBox<String> getCbProvincia() {
        return cbProvincia;
    }

    public JComboBox<String> getCbDistrito() {
        return cbDistrito;
    }

    public JButton getBtnSalvar() {
        return btnSalvar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CadastroCliente::new);
    }
}