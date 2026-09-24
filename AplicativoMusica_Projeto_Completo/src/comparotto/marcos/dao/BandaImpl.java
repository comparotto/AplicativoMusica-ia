package comparotto.marcos.dao;
import comparotto.marcos.aplicativoMusica.Banda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BandaImpl implements BandaDao {
    @Override
    public void inserir(Banda obj) {
        String sql = "INSERT INTO bandas (nome_banda) VALUES (?)";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, obj.getNomeBanda());
            stmt.executeUpdate();
        } catch (SQLException e) { System.err.println("Erro ao inserir Banda: " + e.getMessage()); }
    }
    @Override
    public void atualizar(Banda obj) {
        String sql = "UPDATE bandas SET nome_banda = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, obj.getNomeBanda()); stmt.setInt(2, obj.getId());
            stmt.executeUpdate();
        } catch (SQLException e) { System.err.println("Erro ao atualizar Banda: " + e.getMessage()); }
    }
    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM bandas WHERE id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) { System.err.println("Erro ao deletar Banda: " + e.getMessage()); }
    }
    @Override
    public Banda buscarPorId(int id) {
        String sql = "SELECT * FROM bandas WHERE id = ?";
        Banda obj = null;
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    obj = new Banda();
                    obj.setId(rs.getInt("id")); obj.setNomeBanda(rs.getString("nome_banda"));
                }
            }
        } catch (SQLException e) { System.err.println("Erro ao buscar Banda: " + e.getMessage()); }
        return obj;
    }
    @Override
    public List<Banda> listarTodos() {
        String sql = "SELECT * FROM bandas";
        List<Banda> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Banda obj = new Banda();
                obj.setId(rs.getInt("id")); obj.setNomeBanda(rs.getString("nome_banda"));
                lista.add(obj);
            }
        } catch (SQLException e) { System.err.println("Erro ao listar Banda: " + e.getMessage()); }
        return lista;
    }
}