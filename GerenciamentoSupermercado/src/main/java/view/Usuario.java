package View;



import javax.swing.ImageIcon;

// Guarda os dados de quem entrou no sistema. Repasse este objeto às telas.
public class Usuario {
    private final String nome;
    private final String perfil;
    private final ImageIcon foto;

    public Usuario(String nome, String perfil, ImageIcon foto) {
        this.nome = nome;
        this.perfil = perfil;
        this.foto = foto;
    }

    public String getNome() { return nome; }
    public String getPerfil() { return perfil; }
    public ImageIcon getFoto() { return foto; }
}
