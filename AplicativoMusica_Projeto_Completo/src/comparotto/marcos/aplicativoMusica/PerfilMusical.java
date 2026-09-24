package comparotto.marcos.aplicativoMusica;

public class PerfilMusical {
    private int id;
    private String generoFavorito;

    public PerfilMusical() {
    }

    public PerfilMusical(int id, String generoFavorito) {
        this.id = id;
        this.generoFavorito = generoFavorito;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGeneroFavorito() {
        return generoFavorito;
    }

    public void setGeneroFavorito(String generoFavorito) {
        this.generoFavorito = generoFavorito;
    }
}
