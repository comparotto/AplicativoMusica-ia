package comparotto.marcos.dao;
import comparotto.marcos.aplicativoMusica.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioImpl implements UsuarioDao {
    @Override
    public void inserir(Usuario obj) {
        String sql = "INSERT INTO usuarios (nome, email) VALUES (?, ?)";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, obj.getNome()); stmt.setString(2, obj.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) { System.err.println("Erro ao inserir Usuario: " + e.getMessage()); }
    }
    @Override
    public void atualizar(Usuario obj) {
        String sql = "UPDATE usuarios SET nome = ?, email = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, obj.getNome()); stmt.setString(2, obj.getEmail()); stmt.setInt(3, obj.getId());
            stmt.executeUpdate();
        } catch (SQLException e) { System.err.println("Erro ao atualizar Usuario: " + e.getMessage()); }
    }
    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) { System.err.println("Erro ao deletar Usuario: " + e.getMessage()); }
    }
    @Override
    public Usuario buscarPorId(int id) {
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        Usuario obj = null;
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    obj = new Usuario();
                    obj.setId(rs.getInt("id")); obj.setNome(rs.getString("nome")); obj.setEmail(rs.getString("email"));
                }
            }
        } catch (SQLException e) { System.err.println("Erro ao buscar Usuario: " + e.getMessage()); }
        return obj;
    }
    @Override
    public List<Usuario> listarTodos() {
        String sql = "SELECT * FROM usuarios";
        List<Usuario> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Usuario obj = new Usuario();
                obj.setId(rs.getInt("id")); obj.setNome(rs.getString("nome")); obj.setEmail(rs.getString("email"));
                lista.add(obj);
            }
        } catch (SQLException e) { System.err.println("Erro ao listar Usuario: " + e.getMessage()); }
        return lista;
    }
}