package comparotto.marcos.dao;
import comparotto.marcos.aplicativoMusica.PlaylistM;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistMImpl implements PlaylistMDao {
    @Override
    public void inserir(PlaylistM obj) {
        String sql = "INSERT INTO playlists_musicas (playlist_id, musica_id) VALUES (?, ?)";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, obj.getPlaylistId());
            stmt.setInt(2, obj.getMusicaId());
            stmt.executeUpdate();
        } catch (SQLException e) { System.err.println("Erro ao inserir PlaylistM: " + e.getMessage()); }
    }
    @Override
    public void atualizar(PlaylistM obj) {
        System.err.println("Atualizacao ignorada. Para relacionamentos N:M (PlaylistM), exclua e insira novamente.");
    }
    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM playlists_musicas WHERE playlist_id = ?"; 
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) { System.err.println("Erro ao deletar PlaylistM: " + e.getMessage()); }
    }
    @Override
    public PlaylistM buscarPorId(int id) {
        return null;
    }
    @Override
    public List<PlaylistM> listarTodos() {
        String sql = "SELECT * FROM playlists_musicas";
        List<PlaylistM> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                PlaylistM obj = new PlaylistM();
                obj.setPlaylistId(rs.getInt("playlist_id"));
                obj.setMusicaId(rs.getInt("musica_id"));
                lista.add(obj);
            }
        } catch (SQLException e) { System.err.println("Erro ao listar PlaylistM: " + e.getMessage()); }
        return lista;
    }
}