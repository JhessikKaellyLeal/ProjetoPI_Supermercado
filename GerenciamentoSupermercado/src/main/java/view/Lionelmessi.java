package view;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

/**
 * Tela principal de gestão (interface Swing).
 * Módulo isolado das telas de gestão. A interface inclui formulários, tabelas, pesquisa,
 * ações locais e painéis laterais. Persistência deve ser ligada aos DAO do projeto da equipe.
 */
public class Lionelmessi extends JFrame {
    private final Color navy = new Color(28, 43, 67);
    private final Color blue = new Color(53, 103, 168);
    private final Color bg = new Color(246, 248, 251);
    private final Color ink = new Color(40, 52, 70);
    private JTabbedPane tabs;

    public Lionelmessi() {
        setTitle("Módulo de Gestão");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1360, 820);
        setMinimumSize(new Dimension(1080, 680));
        setLocationRelativeTo(null);
        getContentPane().setBackground(bg);
        setLayout(new BorderLayout());
        tabs = new JTabbedPane();
        tabs.setFont(new Font("SansSerif", Font.BOLD, 13));
        tabs.setBackground(Color.WHITE);
        tabs.setForeground(ink);
        tabs.addTab("Contatos", buildContacts());
        tabs.addTab("Categorias", buildGeneric("Categorias", new String[]{"Código", "Categoria", "Descrição", "Status"},
                new String[]{"Ex.: CAT-001", "Nome da categoria", "Descrição", "Ativo"}, new Object[][]{
                {"CAT-001","Alimentação","Produtos alimentícios","Ativo"},{"CAT-002","Limpeza","Materiais de limpeza","Ativo"}}));
        tabs.addTab("Contratos", buildGeneric("Contratos", new String[]{"Nº contrato","Fornecedor/Parte","Data início","Data fim","Valor","Status"},
                new String[]{"Número do contrato","Fornecedor ou parte","dd/mm/aaaa","dd/mm/aaaa","R$ 0,00","Vigente"}, new Object[][]{
                {"CTR-2025-001","Fornecedor Exemplo","01/01/2025","31/12/2025","R$ 12.500,00","Vigente"}}));
        tabs.addTab("Documentos", buildGeneric("Documentos", new String[]{"Código","Documento","Categoria","Emissão","Validade","Status"},
                new String[]{"DOC-001","Nome do documento","Tipo","dd/mm/aaaa","dd/mm/aaaa","Válido"}, new Object[][]{
                {"DOC-001","Contrato social","Institucional","10/01/2025","10/01/2027","Válido"},
                {"DOC-002","Certidão negativa","Fiscal","05/02/2025","05/08/2025","A vencer"}}));
        tabs.addTab("Avaliações", buildGeneric("Avaliações", new String[]{"Código","Fornecedor/Parte","Data","Nota","Critério","Observação"},
                new String[]{"AVL-001","Fornecedor ou parte","dd/mm/aaaa","0 a 5","Critério avaliado","Observação"}, new Object[][]{
                {"AVL-001","Fornecedor Exemplo","15/02/2025","4,5","Qualidade","Entrega conforme combinado"}}));
        tabs.addTab("Cadastros", buildGeneric("Cadastros", new String[]{"Código","Nome","Tipo","Responsável","Status"},
                new String[]{"CAD-001","Nome do cadastro","Tipo","Responsável","Ativo"}, new Object[][]{}));
        tabs.addTab("Cond. de pagamentos", buildGeneric("Condições de pagamento", new String[]{"Código","Condição","Prazo","Parcelas","Desconto","Status"},
                new String[]{"CP-001","Nome da condição","Ex.: 30 dias","Quantidade","0%","Ativo"}, new Object[][]{
                {"CP-001","À vista","Imediato","1","2%","Ativo"},{"CP-002","30 dias","30 dias","1","0%","Ativo"}}));
        tabs.addTab("Ocorrências", buildGeneric("Ocorrências", new String[]{"Código","Data","Tipo","Prioridade","Descrição","Status"},
                new String[]{"OCO-001","dd/mm/aaaa","Tipo de ocorrência","Baixa/Média/Alta","Descrição","Aberta"}, new Object[][]{
                {"OCO-001","12/03/2025","Atraso na entrega","Média","Entrega fora do prazo previsto","Em análise"}}));
        tabs.addTab("Histórico", buildGeneric("Histórico de compras", new String[]{"Data","Documento","Item/Descrição","Quantidade","Valor total","Observação"},
                new String[]{"dd/mm/aaaa","NF/ pedido","Item ou descrição","0","R$ 0,00","Observação"}, new Object[][]{
                {"20/02/2025","NF-1024","Materiais de escritório","15","R$ 850,00","Compra concluída"}}));
        add(tabs, BorderLayout.CENTER);
    }

    private JComponent buildTop() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        p.setBorder(new EmptyBorder(18, 24, 16, 24));
        JLabel title = new JLabel("Gestão de cadastros");
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(navy);
        JLabel sub = new JLabel("Gerencie contatos, contratos, documentos e informações relacionadas.");
        sub.setFont(new Font("SansSerif", Font.PLAIN, 13));
        sub.setForeground(new Color(105, 117, 133));
        JPanel text = new JPanel();
        text.setOpaque(false);
        text.setLayout(new BoxLayout(text, BoxLayout.Y_AXIS));
        text.add(title); text.add(Box.createVerticalStrut(5)); text.add(sub);
        p.add(text, BorderLayout.WEST);
        p.add(new JSeparator(), BorderLayout.SOUTH);
        return p;
    }

    private JPanel buildContacts() {
        JPanel root = page("Contatos", "Cadastre e mantenha os canais de contato atualizados.");
        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(Color.WHITE);
        form.setBorder(new CompoundBorder(new LineBorder(new Color(224,230,238)), new EmptyBorder(16,16,16,16)));
        String[] labels = {"Nome completo", "Cargo / função", "E-mail", "Telefone", "Celular", "Tipo de contato"};
        JTextField[] fields = new JTextField[labels.length];
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6,8,6,8); c.fill = GridBagConstraints.HORIZONTAL; c.weightx = 1;
        for (int i=0;i<labels.length;i++) {
            c.gridx = (i%3)*2; c.gridy = (i/3)*2; c.weightx=0;
            JLabel l = new JLabel(labels[i]); l.setForeground(ink); l.setFont(new Font("SansSerif",Font.BOLD,12));
            form.add(l,c);
            c.gridx++; c.weightx=1;
            fields[i] = new JTextField();
            fields[i].setPreferredSize(new Dimension(160,34));
            fields[i].setBorder(new CompoundBorder(new LineBorder(new Color(211,219,229)),new EmptyBorder(5,8,5,8)));
            if(i==5) { // editable combo represented as field to keep consistent simple form
                fields[i].setText("Comercial / Financeiro / Técnico");
            }
            form.add(fields[i],c);
        }
        JPanel actions = new JPanel(new FlowLayout(FlowLayout.LEFT,8,0)); actions.setOpaque(false);
        JButton save = primary("Salvar contato"), clear = secondary("Limpar campos");
        actions.add(save); actions.add(clear);
        JPanel formWrap = new JPanel(new BorderLayout(0,10)); formWrap.setOpaque(false);
        formWrap.add(sectionLabel("Dados do contato"),BorderLayout.NORTH); formWrap.add(form,BorderLayout.CENTER); formWrap.add(actions,BorderLayout.SOUTH);
        String[] cols={"ID","Nome","Cargo / função","E-mail","Telefone","Celular","Tipo"};
        DefaultTableModel model = model(cols, new Object[][]{
                {"001","Maria Oliveira","Compras","maria@exemplo.com","(63) 3333-0000","(63) 99999-0000","Comercial"},
                {"002","João Santos","Financeiro","joao@exemplo.com","(63) 3333-0001","(63) 98888-0000","Financeiro"}});
        JTable table = styledTable(model);
        JTextField search = new JTextField(22);
        search.setPreferredSize(new Dimension(220,32));
        JButton edit=secondary("Carregar selecionado"), del=danger("Excluir selecionado");
        JPanel bar = new JPanel(new BorderLayout(8,0)); bar.setOpaque(false);
        bar.add(sectionLabel("Contatos cadastrados"),BorderLayout.WEST);
        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT,6,0)); right.setOpaque(false);
        right.add(new JLabel("Pesquisar:")); right.add(search); right.add(edit); right.add(del); bar.add(right,BorderLayout.EAST);
        JPanel tablePanel = new JPanel(new BorderLayout(0,10)); tablePanel.setOpaque(false);
        tablePanel.add(bar,BorderLayout.NORTH); tablePanel.add(new JScrollPane(table),BorderLayout.CENTER);
        save.addActionListener(e -> {
            if(fields[0].getText().trim().isEmpty()) { msg("Informe o nome do contato."); return; }
            model.addRow(new Object[]{String.format("%03d",model.getRowCount()+1), fields[0].getText(),fields[1].getText(),fields[2].getText(),fields[3].getText(),fields[4].getText(),fields[5].getText()});
            clearFields(fields);
        });
        clear.addActionListener(e -> clearFields(fields));
        edit.addActionListener(e -> {
            int r=table.getSelectedRow(); if(r<0){msg("Selecione um contato na tabela.");return;}
            for(int i=0;i<fields.length;i++) fields[i].setText(String.valueOf(model.getValueAt(table.convertRowIndexToModel(r),i+1)));
        });
        del.addActionListener(e -> { int r=table.getSelectedRow(); if(r>=0) model.removeRow(table.convertRowIndexToModel(r)); else msg("Selecione um contato para excluir."); });
        search.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
            void filter(){String q=search.getText().toLowerCase(); javax.swing.table.TableRowSorter<DefaultTableModel> sorter=new javax.swing.table.TableRowSorter<>(model); table.setRowSorter(sorter); sorter.setRowFilter(q.isEmpty()?null:javax.swing.RowFilter.regexFilter("(?i)"+java.util.regex.Pattern.quote(q)));}
            public void insertUpdate(javax.swing.event.DocumentEvent e){filter();} public void removeUpdate(javax.swing.event.DocumentEvent e){filter();} public void changedUpdate(javax.swing.event.DocumentEvent e){filter();}
        });
        root.add(formWrap,BorderLayout.NORTH); root.add(Box.createVerticalStrut(18),BorderLayout.CENTER);
        JPanel content = new JPanel(new BorderLayout(0,16)); content.setOpaque(false); content.add(formWrap,BorderLayout.NORTH); content.add(tablePanel,BorderLayout.CENTER);
        root.add(content,BorderLayout.CENTER);
        root.add(buildSummarySidebar("Resumo de contatos", new String[]{"Contatos cadastrados", "Canais preenchidos", "Última atualização"}, new String[]{String.valueOf(model.getRowCount()), "E-mail e telefone", "Hoje"}), BorderLayout.EAST);
        return root;
    }

    private JPanel buildGeneric(String title, String[] columns, String[] fields, Object[][] rows) {
        JPanel root = page(title, "Consulte, pesquise e mantenha os registros desta seção.");
        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(Color.WHITE);
        form.setBorder(new CompoundBorder(new LineBorder(new Color(224,230,238)),new EmptyBorder(12,14,12,14)));
        JTextField[] inputs = new JTextField[fields.length];
        GridBagConstraints c=new GridBagConstraints(); c.insets=new Insets(5,7,5,7); c.fill=GridBagConstraints.HORIZONTAL;
        for(int i=0;i<fields.length;i++){
            int col=(i%3)*2, row=(i/3)*2;
            c.gridx=col;c.gridy=row;c.weightx=0;
            JLabel l=new JLabel(columns[Math.min(i,columns.length-1)]);l.setFont(new Font("SansSerif",Font.BOLD,12));l.setForeground(ink);form.add(l,c);
            c.gridx=col+1;c.weightx=1;inputs[i]=new JTextField();inputs[i].setToolTipText(fields[i]);inputs[i].setPreferredSize(new Dimension(150,32));inputs[i].setBorder(new CompoundBorder(new LineBorder(new Color(211,219,229)),new EmptyBorder(5,8,5,8)));form.add(inputs[i],c);
        }
        DefaultTableModel model=model(columns,rows); JTable table=styledTable(model);
        JButton add=primary("Adicionar"), update=secondary("Carregar selecionado"), remove=danger("Excluir selecionado"), clear=secondary("Limpar");
        JPanel buttons=new JPanel(new FlowLayout(FlowLayout.LEFT,7,0));buttons.setOpaque(false);buttons.add(add);buttons.add(update);buttons.add(remove);buttons.add(clear);
        JPanel formWrap=new JPanel(new BorderLayout(0,8));formWrap.setOpaque(false);formWrap.add(sectionLabel("Dados do registro"),BorderLayout.NORTH);formWrap.add(form,BorderLayout.CENTER);formWrap.add(buttons,BorderLayout.SOUTH);
        JTextField search=new JTextField(18);search.setPreferredSize(new Dimension(180,30));
        JPanel head=new JPanel(new BorderLayout());head.setOpaque(false);head.add(sectionLabel("Registros"),BorderLayout.WEST);
        JPanel sr=new JPanel(new FlowLayout(FlowLayout.RIGHT,6,0));sr.setOpaque(false);sr.add(new JLabel("Pesquisar:"));sr.add(search);head.add(sr,BorderLayout.EAST);
        JPanel tableWrap=new JPanel(new BorderLayout(0,8));tableWrap.setOpaque(false);tableWrap.add(head,BorderLayout.NORTH);tableWrap.add(new JScrollPane(table),BorderLayout.CENTER);
        add.addActionListener(e->{Vector<Object> v=new Vector<>();for(JTextField f:inputs)v.add(f.getText().trim());while(v.size()<columns.length)v.add(""); boolean empty=true;for(Object o:v)if(!o.toString().isEmpty())empty=false;if(empty){msg("Preencha ao menos um campo antes de adicionar.");return;}model.addRow(v);clearFields(inputs);});
        update.addActionListener(e->{int r=table.getSelectedRow();if(r<0){msg("Selecione um registro.");return;}r=table.convertRowIndexToModel(r);for(int i=0;i<inputs.length;i++)inputs[i].setText(i<model.getColumnCount()?String.valueOf(model.getValueAt(r,i)):"");});
        remove.addActionListener(e->{int r=table.getSelectedRow();if(r<0)msg("Selecione um registro para excluir.");else model.removeRow(table.convertRowIndexToModel(r));});
        clear.addActionListener(e->clearFields(inputs));
        search.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){void f(){String q=search.getText();javax.swing.table.TableRowSorter<DefaultTableModel>s=new javax.swing.table.TableRowSorter<>(model);table.setRowSorter(s);s.setRowFilter(q.isEmpty()?null:javax.swing.RowFilter.regexFilter("(?i)"+java.util.regex.Pattern.quote(q)));}public void insertUpdate(javax.swing.event.DocumentEvent e){f();}public void removeUpdate(javax.swing.event.DocumentEvent e){f();}public void changedUpdate(javax.swing.event.DocumentEvent e){f();}});
        JPanel content=new JPanel(new BorderLayout(0,16));content.setOpaque(false);content.add(formWrap,BorderLayout.NORTH);content.add(tableWrap,BorderLayout.CENTER);root.add(content,BorderLayout.CENTER);
        root.add(buildSummarySidebar("Resumo da seção", new String[]{"Registros cadastrados", "Status geral", "Ação recomendada"}, new String[]{String.valueOf(model.getRowCount()), "Em acompanhamento", "Revise os dados"}), BorderLayout.EAST);
        return root;
    }

    private JPanel buildSummarySidebar(String title, String[] labels, String[] values){
        JPanel side = new JPanel();
        side.setPreferredSize(new Dimension(235, 10));
        side.setBackground(new Color(250,251,253));
        side.setBorder(new CompoundBorder(new MatteBorder(0,1,0,0,new Color(224,230,238)), new EmptyBorder(14,14,14,14)));
        side.setLayout(new BoxLayout(side, BoxLayout.Y_AXIS));
        JLabel h = new JLabel(title); h.setFont(new Font("SansSerif",Font.BOLD,15)); h.setForeground(navy); h.setAlignmentX(Component.LEFT_ALIGNMENT); side.add(h);
        side.add(Box.createVerticalStrut(14));
        for(int i=0;i<labels.length;i++){
            JPanel card = new JPanel(); card.setLayout(new BoxLayout(card,BoxLayout.Y_AXIS)); card.setBackground(Color.WHITE);
            card.setBorder(new CompoundBorder(new LineBorder(new Color(226,232,240)),new EmptyBorder(12,12,12,12)));
            card.setAlignmentX(Component.LEFT_ALIGNMENT); card.setMaximumSize(new Dimension(Integer.MAX_VALUE,78));
            JLabel l = new JLabel(labels[i]); l.setFont(new Font("SansSerif",Font.PLAIN,11)); l.setForeground(new Color(105,117,133));
            JLabel v = new JLabel(values[i]); v.setFont(new Font("SansSerif",Font.BOLD,14)); v.setForeground(navy);
            card.add(l); card.add(Box.createVerticalStrut(7)); card.add(v); side.add(card); side.add(Box.createVerticalStrut(10));
        }
        JLabel activity = new JLabel("Atalhos"); activity.setFont(new Font("SansSerif",Font.BOLD,13)); activity.setForeground(navy); activity.setAlignmentX(Component.LEFT_ALIGNMENT); side.add(Box.createVerticalStrut(6)); side.add(activity); side.add(Box.createVerticalStrut(8));
        String[] tips = {"• Preencha os campos obrigatórios", "• Pesquise antes de duplicar", "• Revise os registros periodicamente"};
        for(String tip:tips){ JLabel t=new JLabel("<html><div style='width:180px'>"+tip+"</div></html>"); t.setFont(new Font("SansSerif",Font.PLAIN,11)); t.setForeground(ink); t.setAlignmentX(Component.LEFT_ALIGNMENT); side.add(t); side.add(Box.createVerticalStrut(9)); }
        side.add(Box.createVerticalGlue());
        return side;
    }

    private JPanel page(String title,String subtitle){
        JPanel p=new JPanel(new BorderLayout(0,16));p.setBackground(bg);p.setBorder(new EmptyBorder(20,24,22,24));
        JLabel h=new JLabel(title);h.setFont(new Font("SansSerif",Font.BOLD,20));h.setForeground(navy);
        JLabel s=new JLabel(subtitle);s.setFont(new Font("SansSerif",Font.PLAIN,12));s.setForeground(new Color(111,123,140));
        JPanel heading=new JPanel();heading.setOpaque(false);heading.setLayout(new BoxLayout(heading,BoxLayout.Y_AXIS));heading.add(h);heading.add(Box.createVerticalStrut(5));heading.add(s);p.add(heading,BorderLayout.NORTH);return p;
    }
    private JLabel sectionLabel(String s){JLabel l=new JLabel(s);l.setFont(new Font("SansSerif",Font.BOLD,14));l.setForeground(navy);return l;}
    private DefaultTableModel model(String[] cols,Object[][] rows){return new DefaultTableModel(rows,cols){public boolean isCellEditable(int r,int c){return false;}};}
    private JTable styledTable(DefaultTableModel m){JTable t=new JTable(m);t.setRowHeight(34);t.setFont(new Font("SansSerif",Font.PLAIN,12));t.setForeground(ink);t.setGridColor(new Color(232,236,242));t.setSelectionBackground(new Color(220,234,250));t.setSelectionForeground(navy);t.getTableHeader().setFont(new Font("SansSerif",Font.BOLD,12));t.getTableHeader().setBackground(new Color(239,243,248));t.getTableHeader().setForeground(navy);t.setFillsViewportHeight(true);return t;}
    private JButton primary(String s){JButton b=new JButton(s);b.setFocusPainted(false);b.setForeground(Color.WHITE);b.setBackground(blue);b.setFont(new Font("SansSerif",Font.BOLD,12));b.setBorder(new EmptyBorder(9,14,9,14));return b;}
    private JButton secondary(String s){JButton b=new JButton(s);b.setFocusPainted(false);b.setForeground(navy);b.setBackground(Color.WHITE);b.setFont(new Font("SansSerif",Font.BOLD,12));b.setBorder(new CompoundBorder(new LineBorder(new Color(207,216,228)),new EmptyBorder(8,12,8,12)));return b;}
    private JButton danger(String s){JButton b=secondary(s);b.setForeground(new Color(174,54,54));return b;}
    private void clearFields(JTextField[] f){for(JTextField x:f)x.setText("");}
    private void msg(String s){JOptionPane.showMessageDialog(this,s,"Atenção",JOptionPane.INFORMATION_MESSAGE);}
    public static void main(String[] args){SwingUtilities.invokeLater(()->new Lionelmessi().setVisible(true));}
}
