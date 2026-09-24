package comparotto.marcos.dao;
import comparotto.marcos.aplicativoMusica.Artista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtistaImpl implements ArtistaDao {
    @Override
    public void inserir(Artista obj) {
        String sql = "INSERT INTO artistas (nome, nacionalidade) VALUES (?, ?)";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, obj.getNome()); stmt.setString(2, obj.getNacionalidade());
            stmt.executeUpdate();
        } catch (SQLException e) { System.err.println("Erro ao inserir Artista: " + e.getMessage()); }
    }
    @Override
    public void atualizar(Artista obj) {
        String sql = "UPDATE artistas SET nome = ?, nacionalidade = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, obj.getNome()); stmt.setString(2, obj.getNacionalidade()); stmt.setInt(3, obj.getId());
            stmt.executeUpdate();
        } catch (SQLException e) { System.err.println("Erro ao atualizar Artista: " + e.getMessage()); }
    }
    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM artistas WHERE id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) { System.err.println("Erro ao deletar Artista: " + e.getMessage()); }
    }
    @Override
    public Artista buscarPorId(int id) {
        String sql = "SELECT * FROM artistas WHERE id = ?";
        Artista obj = null;
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    obj = new Artista();
                    obj.setId(rs.getInt("id")); obj.setNome(rs.getString("nome")); obj.setNacionalidade(rs.getString("nacionalidade"));
                }
            }
        } catch (SQLException e) { System.err.println("Erro ao buscar Artista: " + e.getMessage()); }
        return obj;
    }
    @Override
    public List<Artista> listarTodos() {
        String sql = "SELECT * FROM artistas";
        List<Artista> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Artista obj = new Artista();
                obj.setId(rs.getInt("id")); obj.setNome(rs.getString("nome")); obj.setNacionalidade(rs.getString("nacionalidade"));
                lista.add(obj);
            }
        } catch (SQLException e) { System.err.println("Erro ao listar Artista: " + e.getMessage()); }
        return lista;
    }
}