package comparotto.marcos.dao;
import comparotto.marcos.aplicativoMusica.Playlist;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistImpl implements PlaylistDao {
    @Override
    public void inserir(Playlist obj) {
        String sql = "INSERT INTO playlists (nome) VALUES (?)";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, obj.getNome());
            stmt.executeUpdate();
        } catch (SQLException e) { System.err.println("Erro ao inserir Playlist: " + e.getMessage()); }
    }
    @Override
    public void atualizar(Playlist obj) {
        String sql = "UPDATE playlists SET nome = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, obj.getNome()); stmt.setInt(2, obj.getId());
            stmt.executeUpdate();
        } catch (SQLException e) { System.err.println("Erro ao atualizar Playlist: " + e.getMessage()); }
    }
    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM playlists WHERE id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) { System.err.println("Erro ao deletar Playlist: " + e.getMessage()); }
    }
    @Override
    public Playlist buscarPorId(int id) {
        String sql = "SELECT * FROM playlists WHERE id = ?";
        Playlist obj = null;
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    obj = new Playlist();
                    obj.setId(rs.getInt("id")); obj.setNome(rs.getString("nome"));
                }
            }
        } catch (SQLException e) { System.err.println("Erro ao buscar Playlist: " + e.getMessage()); }
        return obj;
    }
    @Override
    public List<Playlist> listarTodos() {
        String sql = "SELECT * FROM playlists";
        List<Playlist> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Playlist obj = new Playlist();
                obj.setId(rs.getInt("id")); obj.setNome(rs.getString("nome"));
                lista.add(obj);
            }
        } catch (SQLException e) { System.err.println("Erro ao listar Playlist: " + e.getMessage()); }
        return lista;
    }
}