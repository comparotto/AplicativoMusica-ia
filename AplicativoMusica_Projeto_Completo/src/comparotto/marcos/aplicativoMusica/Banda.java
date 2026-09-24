package comparotto.marcos.aplicativoMusica;

public class Banda {
    private int id;
    private String nomeBanda;

    public Banda() {
    }

    public Banda(int id, String nomeBanda) {
        this.id = id;
        this.nomeBanda = nomeBanda;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeBanda() {
        return nomeBanda;
    }

    public void setNomeBanda(String nomeBanda) {
        this.nomeBanda = nomeBanda;
    }
}