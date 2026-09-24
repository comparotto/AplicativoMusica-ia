package comparotto.marcos.dao;
import comparotto.marcos.aplicativoMusica.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaImpl implements PessoaDao {
    @Override
    public void inserir(Pessoa obj) {
        String sql = "INSERT INTO pessoas (nome) VALUES (?)";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, obj.getNome());
            stmt.executeUpdate();
        } catch (SQLException e) { System.err.println("Erro ao inserir Pessoa: " + e.getMessage()); }
    }
    @Override
    public void atualizar(Pessoa obj) {
        String sql = "UPDATE pessoas SET nome = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, obj.getNome()); stmt.setInt(2, obj.getId());
            stmt.executeUpdate();
        } catch (SQLException e) { System.err.println("Erro ao atualizar Pessoa: " + e.getMessage()); }
    }
    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM pessoas WHERE id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) { System.err.println("Erro ao deletar Pessoa: " + e.getMessage()); }
    }
    @Override
    public Pessoa buscarPorId(int id) {
        String sql = "SELECT * FROM pessoas WHERE id = ?";
        Pessoa obj = null;
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    obj = new Pessoa();
                    obj.setId(rs.getInt("id")); obj.setNome(rs.getString("nome"));
                }
            }
        } catch (SQLException e) { System.err.println("Erro ao buscar Pessoa: " + e.getMessage()); }
        return obj;
    }
    @Override
    public List<Pessoa> listarTodos() {
        String sql = "SELECT * FROM pessoas";
        List<Pessoa> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Pessoa obj = new Pessoa();
                obj.setId(rs.getInt("id")); obj.setNome(rs.getString("nome"));
                lista.add(obj);
            }
        } catch (SQLException e) { System.err.println("Erro ao listar Pessoa: " + e.getMessage()); }
        return lista;
    }
}