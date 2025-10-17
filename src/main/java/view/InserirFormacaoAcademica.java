package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

public class InserirFormacaoAcademica extends JFrame {

    private JLabel title, lblCurso, lblInstituicao, lblNivelAcademico, lblAnoFormacao;
    private JTextField txtCurso, txtInstituicao, txtAnoFormacao;
    private JComboBox<String> cbNivelAcademico;
    private JButton btnConfirmar, btnCancelar;

    public InserirFormacaoAcademica() {
        super("Formação Académica");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1280, 702);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);

        init();
        addComponents();

        setResizable(false);
        setVisible(true);
    }

    public final void init() {
        // Título principal
        title = createLabel("Inserir Formação Académica", 417, 44, 446, 39, Font.BOLD, 32, Color.WHITE);

        // Labels dos campos
        lblCurso = createLabel("Curso:", 30, 15, 86, 29, Font.PLAIN, 24, Color.WHITE);
        lblInstituicao = createLabel("Instituição:", 30, 125, 139, 29, Font.PLAIN, 24, Color.WHITE);
        lblNivelAcademico = createLabel("Nível Académico:", 30, 235, 213, 29, Font.PLAIN, 24, Color.WHITE);
        lblAnoFormacao = createLabel("Ano Formação:", 30, 345, 184, 29, Font.PLAIN, 24, Color.WHITE);

        // Campos de texto
        txtCurso = createTextField(30, 44, 1170, 66);
        txtInstituicao = createTextField(30, 154, 1170, 66);
        txtAnoFormacao = createTextField(30, 374, 1170, 66);

        // Combobox para nível acadêmico
        cbNivelAcademico = createComboBox(30, 264, 1170, 66);
        cbNivelAcademico.setModel(new DefaultComboBoxModel<>(new String[]{
                "Ensino Primário", "Ensino Secundário", "Técnico Médio",
                "Bacharelato", "Licenciatura", "Mestrado", "Doutoramento"
        }));

        // Botões
        btnConfirmar = createButton("Confirmar", 810, 595, 200, 50, new Color(0x464646), Color.WHITE, Font.BOLD, 24);
        btnCancelar = createButton("Cancelar", 1040, 595, 200, 50, Color.WHITE, new Color(0x464646), Font.BOLD, 24);
    }

    // Métodos de create components (mesmo padrão das outras classes)
    private JLabel createLabel(String text, int x, int y, int width, int height, int fontStyle, int fontSize, Color backgroundColor) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        label.setBackground(backgroundColor);
        label.setFont(new Font("Inter", fontStyle, fontSize));
        return label;
    }

    private JTextField createTextField(int x, int y, int width, int height) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, width, height);
        textField.setBackground(Color.WHITE);
        textField.setForeground(new Color(0x464646));
        textField.setBorder(new LineBorder(new Color(0x464646), 1, true));
        textField.setFont(new Font("Inter", Font.PLAIN, 20));
        return textField;
    }

    private JComboBox<String> createComboBox(int x, int y, int width, int height) {
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setBounds(x, y, width, height);
        comboBox.setBackground(Color.WHITE);
        comboBox.setForeground(new Color(0x464646));
        comboBox.setFont(new Font("Inter", Font.PLAIN, 20));
        return comboBox;
    }

    private JButton createButton(String text, int x, int y, int width, int height, Color backgroundColor, Color foregroundColor, int fontStyle, int fontSize) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setBackground(backgroundColor);
        button.setForeground(foregroundColor);
        button.setFont(new Font("Inter", fontStyle, fontSize));

        if (backgroundColor.equals(Color.WHITE)) {
            button.setBorder(new LineBorder(new Color(0x464646), 1, true));
        }

        return button;
    }

    public JPanel getFormPanel() {
        JPanel panel = new JPanel(null);
        panel.setBounds(25, 115, 1215, 462);
        panel.setBackground(new Color(0xD9D9D9));


        panel.add(lblCurso);
        panel.add(lblInstituicao);
        panel.add(lblNivelAcademico);
        panel.add(lblAnoFormacao);

        panel.add(txtCurso);
        panel.add(txtInstituicao);
        panel.add(txtAnoFormacao);

        panel.add(cbNivelAcademico);

        panel.add(btnConfirmar);
        panel.add(btnCancelar);

        return panel;
    }
    private void addComponents(){
        add(title);
        add(getFormPanel());
        add(btnConfirmar);
        add(btnCancelar);


    }

    // Getters para os componentes
    public JTextField getTxtCurso() {
        return txtCurso;
    }

    public JTextField getTxtInstituicao() {
        return txtInstituicao;
    }

    public JTextField getTxtAnoFormacao() {
        return txtAnoFormacao;
    }

    public JComboBox<String> getCbNivelAcademico() {
        return cbNivelAcademico;
    }

    public JButton getBtnConfirmar() {
        return btnConfirmar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InserirFormacaoAcademica::new);
    }
}