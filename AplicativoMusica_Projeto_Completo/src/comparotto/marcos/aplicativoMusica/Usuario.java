package comparotto.marcos.aplicativoMusica;

public class Usuario extends Pessoa {
    private String email;

    public Usuario() {
        super();
    }

    public Usuario(String email) {
        this.email = email;
    }

    public Usuario(int id, String nome, String email) {
        super(id, nome);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}