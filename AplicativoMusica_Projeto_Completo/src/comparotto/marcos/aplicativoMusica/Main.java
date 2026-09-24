package comparotto.marcos.aplicativoMusica;

import comparotto.marcos.dao.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        UsuarioDao usuarioDao = new UsuarioImpl();
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome("Comparotto");
        novoUsuario.setEmail("marcos.gomes@exemplo.com");

        usuarioDao.inserir(novoUsuario);


        System.out.println("\n--- Lista de Usuários no Banco ---");
        List<Usuario> usuarios = usuarioDao.listarTodos();

        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário encontrado. Verifique a conexão.");
        } else {
            for (Usuario u : usuarios) {
                System.out.println("ID: " + u.getId() + " | Nome: " + u.getNome() + " | E-mail: " + u.getEmail());
            }
        }
    }
}