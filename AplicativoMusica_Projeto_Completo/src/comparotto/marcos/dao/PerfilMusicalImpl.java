package comparotto.marcos.dao;
import comparotto.marcos.aplicativoMusica.PerfilMusical;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PerfilMusicalImpl implements PerfilMusicalDao {
    @Override
    public void inserir(PerfilMusical obj) {
        String sql = "INSERT INTO perfis_musicais (genero_favorito) VALUES (?)";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, obj.getGeneroFavorito());
            stmt.executeUpdate();
        } catch (SQLException e) { System.err.println("Erro ao inserir PerfilMusical: " + e.getMessage()); }
    }
    @Override
    public void atualizar(PerfilMusical obj) {
        String sql = "UPDATE perfis_musicais SET genero_favorito = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, obj.getGeneroFavorito()); stmt.setInt(2, obj.getId());
            stmt.executeUpdate();
        } catch (SQLException e) { System.err.println("Erro ao atualizar PerfilMusical: " + e.getMessage()); }
    }
    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM perfis_musicais WHERE id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) { System.err.println("Erro ao deletar PerfilMusical: " + e.getMessage()); }
    }
    @Override
    public PerfilMusical buscarPorId(int id) {
        String sql = "SELECT * FROM perfis_musicais WHERE id = ?";
        PerfilMusical obj = null;
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    obj = new PerfilMusical();
                    obj.setId(rs.getInt("id")); obj.setGeneroFavorito(rs.getString("genero_favorito"));
                }
            }
        } catch (SQLException e) { System.err.println("Erro ao buscar PerfilMusical: " + e.getMessage()); }
        return obj;
    }
    @Override
    public List<PerfilMusical> listarTodos() {
        String sql = "SELECT * FROM perfis_musicais";
        List<PerfilMusical> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                PerfilMusical obj = new PerfilMusical();
                obj.setId(rs.getInt("id")); obj.setGeneroFavorito(rs.getString("genero_favorito"));
                lista.add(obj);
            }
        } catch (SQLException e) { System.err.println("Erro ao listar PerfilMusical: " + e.getMessage()); }
        return lista;
    }
}