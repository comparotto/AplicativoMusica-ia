package comparotto.marcos.aplicativoMusica;

import comparotto.marcos.dao.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando Aplicativo de Musica (Versao Completa c/ SQL)...\n");

        // Instanciando a implementação de Usuario
        UsuarioDao usuarioDao = new UsuarioImpl();

        // 1. Criando e Inserindo um novo Usuário
        System.out.println("Testando a inserção no banco de dados...");
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome("Marcos Comparotto");
        novoUsuario.setEmail("marcos.comparotto@exemplo.com"); // Mude o e-mail se rodar mais de uma vez (é UNIQUE)

        usuarioDao.inserir(novoUsuario);

        // 2. Buscando e Listando todos os Usuários do banco
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