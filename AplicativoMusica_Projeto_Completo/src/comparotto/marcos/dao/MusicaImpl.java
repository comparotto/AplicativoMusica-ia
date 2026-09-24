package comparotto.marcos.dao;
import comparotto.marcos.aplicativoMusica.Musica;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MusicaImpl implements MusicaDao {
    @Override
    public void inserir(Musica obj) {
        String sql = "INSERT INTO musicas (titulo, duracao) VALUES (?, ?)";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, obj.getTitulo()); stmt.setInt(2, obj.getDuracao());
            stmt.executeUpdate();
        } catch (SQLException e) { System.err.println("Erro ao inserir Musica: " + e.getMessage()); }
    }
    @Override
    public void atualizar(Musica obj) {
        String sql = "UPDATE musicas SET titulo = ?, duracao = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, obj.getTitulo()); stmt.setInt(2, obj.getDuracao()); stmt.setInt(3, obj.getId());
            stmt.executeUpdate();
        } catch (SQLException e) { System.err.println("Erro ao atualizar Musica: " + e.getMessage()); }
    }
    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM musicas WHERE id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) { System.err.println("Erro ao deletar Musica: " + e.getMessage()); }
    }
    @Override
    public Musica buscarPorId(int id) {
        String sql = "SELECT * FROM musicas WHERE id = ?";
        Musica obj = null;
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    obj = new Musica();
                    obj.setId(rs.getInt("id")); obj.setTitulo(rs.getString("titulo")); obj.setDuracao(rs.getInt("duracao"));
                }
            }
        } catch (SQLException e) { System.err.println("Erro ao buscar Musica: " + e.getMessage()); }
        return obj;
    }
    @Override
    public List<Musica> listarTodos() {
        String sql = "SELECT * FROM musicas";
        List<Musica> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Musica obj = new Musica();
                obj.setId(rs.getInt("id")); obj.setTitulo(rs.getString("titulo")); obj.setDuracao(rs.getInt("duracao"));
                lista.add(obj);
            }
        } catch (SQLException e) { System.err.println("Erro ao listar Musica: " + e.getMessage()); }
        return lista;
    }
}