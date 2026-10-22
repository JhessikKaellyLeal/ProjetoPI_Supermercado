package view;

import java.awt.*;
import java.text.ParseException;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.MaskFormatter;

/** Tela Cadastro de Fornecedores em um único arquivo Java Swing. */
public class Cadastro extends JPanel {
    private JLabel lbTitulo, lbSubtitulo;
    private JTextField txtCodigo, txtRazaoSocial, txtNomeFantasia, txtInscricaoEstadual,
            txtInscricaoMunicipal, txtEmail, txtTelefone, txtCelular, txtSite,
            txtCep, txtEndereco, txtNumero, txtComplemento, txtBairro, txtCidade;
    private JFormattedTextField ftxtCnpjCpf;
    private JComboBox<String> cbTipoPessoa, cbCategoria, cbUf, cbStatus;
    private JTextArea txtObservacoes;
    private JButton btnNovo, btnLimpar, btnCancelar, btnSalvar;
    private JTabbedPane tbCadastro;

    private final Color azul = new Color(39, 91, 150);
    private final Color fundo = new Color(246, 248, 251);
    private final Color texto = new Color(43, 52, 65);
    private final Color borda = new Color(220, 226, 233);

    public Cadastro() { initComponents(); }

    private void initComponents() {
        setName("Cadastro");
        setLayout(null);
        setBackground(fundo);
        setPreferredSize(new Dimension(1920, 1080));
        setMinimumSize(new Dimension(1100, 700));

        lbTitulo = new JLabel("Cadastro de Fornecedores");
        lbTitulo.setFont(new Font("Segoe UI", Font.BOLD, 30));
        lbTitulo.setForeground(texto);
        lbTitulo.setBounds(48, 28, 700, 44);
        add(lbTitulo);

        lbSubtitulo = new JLabel("Cadastre e mantenha os dados dos fornecedores da empresa.");
        lbSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lbSubtitulo.setForeground(new Color(112, 122, 135));
        lbSubtitulo.setBounds(50, 76, 900, 28);
        add(lbSubtitulo);

        tbCadastro = new JTabbedPane(JTabbedPane.TOP);
        tbCadastro.setFont(new Font("Segoe UI", Font.BOLD, 16));
        tbCadastro.setBackground(Color.WHITE);
        tbCadastro.setBounds(48, 125, 1824, 735);
        tbCadastro.addTab("Dados do fornecedor", criarAbaDados());
        tbCadastro.addTab("Contato", criarAbaContato());
        tbCadastro.addTab("Endereço", criarAbaEndereco());
        tbCadastro.addTab("Informações adicionais", criarAbaObservacoes());
        add(tbCadastro);

        btnNovo = botao("Novo", false);
        btnNovo.setBounds(1392, 900, 110, 46);
        btnNovo.addActionListener(e -> limparCampos());
        add(btnNovo);

        btnLimpar = botao("Limpar", false);
        btnLimpar.setBounds(1512, 900, 110, 46);
        btnLimpar.addActionListener(e -> limparCampos());
        add(btnLimpar);

        btnCancelar = botao("Cancelar", false);
        btnCancelar.setBounds(1632, 900, 110, 46);
        btnCancelar.addActionListener(e -> {
            Window w = SwingUtilities.getWindowAncestor(this);
            if (w != null) w.dispose();
        });
        add(btnCancelar);

        btnSalvar = botao("Salvar", true);
        btnSalvar.setBounds(1752, 900, 120, 46);
        btnSalvar.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Layout de cadastro pronto. Conecte o botão à sua rotina de gravação.",
                "Cadastro de Fornecedores", JOptionPane.INFORMATION_MESSAGE));
        add(btnSalvar);
    }

    private JPanel criarAbaDados() {
        JPanel p = abaBase();
        JPanel pnl = criarPainel("Identificação do fornecedor", 24, 24, 1760, 390);
        p.add(pnl);
        txtCodigo = campo(pnl, "Código", 24, 48, 180, "");
        cbTipoPessoa = combo(pnl, "Tipo de pessoa", 230, 48, 220, new String[]{"Pessoa Jurídica", "Pessoa Física"});
        ftxtCnpjCpf = campoFormatado(pnl, "CNPJ / CPF", 470, 48, 300, "##.###.###/####-##");
        txtRazaoSocial = campo(pnl, "Razão social / Nome", 790, 48, 900, "");
        txtNomeFantasia = campo(pnl, "Nome fantasia", 24, 142, 500, "");
        txtInscricaoEstadual = campo(pnl, "Inscrição estadual", 544, 142, 300, "");
        txtInscricaoMunicipal = campo(pnl, "Inscrição municipal", 864, 142, 300, "");
        cbCategoria = combo(pnl, "Categoria", 1184, 142, 260, new String[]{"Selecione...", "Matéria-prima", "Serviços", "Equipamentos", "Outros"});
        cbStatus = combo(pnl, "Status", 1464, 142, 260, new String[]{"Ativo", "Inativo", "Em análise"});
        return p;
    }

    private JPanel criarAbaContato() {
        JPanel p = abaBase();
        JPanel pnl = criarPainel("Informações de contato", 24, 24, 1760, 300);
        p.add(pnl);
        txtEmail = campo(pnl, "E-mail", 24, 48, 620, "");
        txtTelefone = campo(pnl, "Telefone", 664, 48, 300, "");
        txtCelular = campo(pnl, "Celular", 984, 48, 300, "");
        txtSite = campo(pnl, "Site", 24, 142, 900, "");
        return p;
    }

    private JPanel criarAbaEndereco() {
        JPanel p = abaBase();
        JPanel pnl = criarPainel("Endereço do fornecedor", 24, 24, 1760, 340);
        p.add(pnl);
        txtCep = campo(pnl, "CEP", 24, 48, 220, "");
        txtEndereco = campo(pnl, "Logradouro", 264, 48, 650, "");
        txtNumero = campo(pnl, "Número", 934, 48, 160, "");
        txtComplemento = campo(pnl, "Complemento", 1114, 48, 360, "");
        txtBairro = campo(pnl, "Bairro", 24, 142, 420, "");
        txtCidade = campo(pnl, "Cidade", 464, 142, 520, "");
        cbUf = combo(pnl, "UF", 1004, 142, 220, new String[]{"Selecione...", "AC","AL","AP","AM","BA","CE","DF","ES","GO","MA","MT","MS","MG","PA","PB","PR","PE","PI","RJ","RN","RS","RO","RR","SC","SP","SE","TO"});
        return p;
    }

    private JPanel criarAbaObservacoes() {
        JPanel p = abaBase();
        JPanel pnl = criarPainel("Informações adicionais", 24, 24, 1760, 400);
        p.add(pnl);
        JLabel lbObservacoes = new JLabel("Observações");
        lbObservacoes.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lbObservacoes.setForeground(texto);
        lbObservacoes.setBounds(24, 48, 180, 22);
        pnl.add(lbObservacoes);
        txtObservacoes = new JTextArea();
        txtObservacoes.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        txtObservacoes.setLineWrap(true);
        txtObservacoes.setWrapStyleWord(true);
        JScrollPane sp = new JScrollPane(txtObservacoes);
        sp.setBounds(24, 76, 1700, 260);
        sp.setBorder(BorderFactory.createLineBorder(borda));
        pnl.add(sp);
        return p;
    }

    private JPanel abaBase() {
        JPanel p = new JPanel(null);
        p.setBackground(fundo);
        return p;
    }

    private JPanel criarPainel(String titulo, int x, int y, int w, int h) {
        JPanel p = new JPanel(null);
        p.setBackground(Color.WHITE);
        p.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(borda), titulo,
                TitledBorder.LEADING, TitledBorder.TOP, new Font("Segoe UI", Font.BOLD, 17), azul));
        p.setBounds(x, y, w, h);
        return p;
    }

    private JTextField campo(JPanel p, String rotulo, int x, int y, int w, String valor) {
        JLabel lb = new JLabel(rotulo);
        lb.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lb.setForeground(texto);
        lb.setBounds(x, y, w, 22);
        p.add(lb);
        JTextField tf = new JTextField(valor);
        tf.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        tf.setBounds(x, y + 27, w, 38);
        tf.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(borda),
                BorderFactory.createEmptyBorder(4, 9, 4, 9)));
        p.add(tf);
        return tf;
    }

    private JFormattedTextField campoFormatado(JPanel p, String rotulo, int x, int y, int w, String mascara) {
        JLabel lb = new JLabel(rotulo);
        lb.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lb.setForeground(texto);
        lb.setBounds(x, y, w, 22);
        p.add(lb);
        JFormattedTextField tf;
        try {
            MaskFormatter mf = new MaskFormatter(mascara);
            mf.setPlaceholderCharacter('_');
            tf = new JFormattedTextField(mf);
        } catch (ParseException ex) { tf = new JFormattedTextField(); }
        tf.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        tf.setBounds(x, y + 27, w, 38);
        tf.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(borda),
                BorderFactory.createEmptyBorder(4, 9, 4, 9)));
        p.add(tf);
        return tf;
    }

    private JComboBox<String> combo(JPanel p, String rotulo, int x, int y, int w, String[] itens) {
        JLabel lb = new JLabel(rotulo);
        lb.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lb.setForeground(texto);
        lb.setBounds(x, y, w, 22);
        p.add(lb);
        JComboBox<String> cb = new JComboBox<>(itens);
        cb.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        cb.setBounds(x, y + 27, w, 38);
        cb.setBackground(Color.WHITE);
        p.add(cb);
        return cb;
    }

    private JButton botao(String titulo, boolean primario) {
        JButton b = new JButton(titulo);
        b.setFont(new Font("Segoe UI", Font.BOLD, 15));
        b.setFocusPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        if (primario) {
            b.setBackground(azul); b.setForeground(Color.WHITE);
            b.setBorder(BorderFactory.createEmptyBorder(8, 18, 8, 18));
        } else {
            b.setBackground(Color.WHITE); b.setForeground(texto);
            b.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(borda),
                    BorderFactory.createEmptyBorder(8, 18, 8, 18)));
        }
        return b;
    }

    private void limparCampos() {
        limparDentro(this);
    }
    private void limparDentro(Container c) {
        for (Component comp : c.getComponents()) {
            if (comp instanceof JTextField) ((JTextField) comp).setText("");
            else if (comp instanceof JTextArea) ((JTextArea) comp).setText("");
            else if (comp instanceof JComboBox && ((JComboBox<?>) comp).getItemCount() > 0) ((JComboBox<?>) comp).setSelectedIndex(0);
            else if (comp instanceof Container) limparDentro((Container) comp);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Cadastro de Fornecedores");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new JScrollPane(new Cadastro()));
            frame.setSize(1440, 900);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
