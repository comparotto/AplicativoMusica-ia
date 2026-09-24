package comparotto.marcos.aplicativoMusica;

public class Artista extends Pessoa {
    private String nacionalidade;

    public Artista() {
        super();
    }

    public Artista(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }
}