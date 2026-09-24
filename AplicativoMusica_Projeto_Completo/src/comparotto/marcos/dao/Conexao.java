package comparotto.marcos.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Conexao {
    private static final String URL = "jdbc:postgresql://localhost:5432/AplicativoMusica";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Aqui nao"; // trocar a senha para testar
    public static Connection getConnection() {
        try { return DriverManager.getConnection(URL, USER, PASSWORD); }
        catch (SQLException e) { throw new RuntimeException(e); }
    }
}