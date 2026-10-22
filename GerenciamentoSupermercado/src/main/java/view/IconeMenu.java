package View;

import java.awt.*;
import javax.swing.Icon;

// Ícones vetoriais simples: funcionam sem arquivos de imagem ou fontes especiais.
public class IconeMenu implements Icon {
    private final String tipo;
    private final int tamanho;
    public IconeMenu(String tipo, int tamanho) {
        this.tipo = tipo;
        this.tamanho = tamanho;
    }
    public int getIconWidth() { return tamanho; }
    public int getIconHeight() { return tamanho; }
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D d = (Graphics2D) g.create();
        d.translate(x, y);
        d.scale(tamanho / 24.0, tamanho / 24.0);
        d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        d.setColor(Color.WHITE);
        d.setStroke(new BasicStroke(1.7f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        switch (tipo) {
            case "inicio":
                d.drawPolyline(new int[]{2,12,22},new int[]{11,2,11},3);
                d.drawRect(5,10,14,11); d.drawRect(10,15,4,6); break;
            case "produtos":
                d.drawRect(4,5,16,16); d.drawRect(10,5,4,6);
                d.drawLine(4,5,8,2); d.drawLine(8,2,17,2); d.drawLine(17,2,20,5); break;
            case "estoque":
                d.drawRect(2,13,9,8); d.drawRect(13,13,9,8); d.drawRect(7,3,10,8); break;
            case "fornecedores":
                d.drawRect(3,10,18,11); d.drawPolyline(new int[]{3,9,9,15,15,21},new int[]{10,5,10,5,10,10},6);
                d.drawLine(7,15,7,17); d.drawLine(12,15,12,17); d.drawLine(17,15,17,17); break;
            case "clientes":
                d.drawOval(8,2,8,8); d.drawArc(3,12,18,18,0,180); d.drawLine(3,21,21,21); break;
            case "promocoes":
                d.drawPolygon(new int[]{3,13,22,13,3},new int[]{3,3,12,21,11},5); d.drawOval(6,6,3,3); break;
            case "caixas":
                d.drawRect(3,13,18,8); d.drawRect(7,3,11,7); d.drawLine(12,10,12,13); d.drawLine(7,17,17,17); break;
            case "vendas":
                d.drawPolyline(new int[]{2,5,8,19,22},new int[]{3,3,16,16,6},5); d.drawLine(6,6,22,6);
                d.drawOval(8,19,3,3); d.drawOval(17,19,3,3); break;
            case "pedidos":
                d.drawRect(5,4,14,18); d.drawRect(9,2,6,4);
                for(int a=10;a<20;a+=4) d.drawLine(8,a,16,a); break;
            case "entregas":
                d.drawRect(2,5,12,12); d.drawRect(14,10,8,7);
                d.drawOval(5,17,5,5); d.drawOval(16,17,5,5); break;
            case "rh":
                d.drawOval(3,3,6,6); d.drawOval(15,3,6,6);
                d.drawArc(1,11,11,15,0,180); d.drawArc(12,11,11,15,0,180); break;
            case "financeiro":
                d.drawOval(2,2,20,20); d.setFont(new Font("SansSerif",Font.BOLD,17)); d.drawString("$",7,18); break;
            case "relatorios":
                d.drawLine(3,2,3,22); d.drawLine(3,22,22,22);
                d.drawRect(6,13,3,6); d.drawRect(12,8,3,11); d.drawRect(18,3,3,16); break;
            case "configuracoes":
                d.drawOval(6,6,12,12); d.drawOval(10,10,4,4);
                for(int a=0;a<8;a++){ d.drawLine(12,2,12,5); d.rotate(Math.PI/4,12,12); } break;
            case "sair":
                d.drawRect(3,3,10,18); d.drawLine(9,12,22,12);
                d.drawPolyline(new int[]{18,22,18},new int[]{8,12,16},3); break;
            default: d.drawOval(4,4,16,16);
        }
        d.dispose();
    }
}
