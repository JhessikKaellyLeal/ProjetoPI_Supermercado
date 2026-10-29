/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;
import Conection.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author JordanaLeite
 */
public class ITENS_VENDAS_CONTROLLER {
    
    // Cadastrar item da venda
    public boolean cadastrar(ItensVendas item) {
        String sql = "INSERT INTO itensVendas "
                + "(quantidade, precoUnitario, desconto, valorTotal, vendas_idvendas) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, item.getQuantidade());
            stmt.setDouble(2, item.getPrecoUnitario());
            stmt.setDouble(3, item.getDesconto());
            stmt.setDouble(4, item.getValorTotal());
            stmt.setInt(5, item.getVendasIdvendas());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar item da venda: " + e.getMessage());
            return false;
        }
    }

    // Listar todos os itens das vendas
    public List<ItensVendas> listarTodos() {
        List<ItensVendas> lista = new ArrayList<>();

        String sql = "SELECT * FROM itensVendas";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ItensVendas item = new ItensVendas();

                item.setIditensVendas(rs.getInt("iditensVendas"));
                item.setQuantidade(rs.getInt("quantidade"));
                item.setPrecoUnitario(rs.getDouble("precoUnitario"));
                item.setDesconto(rs.getDouble("desconto"));
                item.setValorTotal(rs.getDouble("valorTotal"));
                item.setVendasIdvendas(rs.getInt("vendas_idvendas"));

                lista.add(item);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar itens das vendas: " + e.getMessage());
        }

        return lista;
    }

    // Buscar item pelo ID
    public ItensVendas buscarPorId(int id) {
        String sql = "SELECT * FROM itensVendas WHERE iditensVendas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ItensVendas item = new ItensVendas();

                    item.setIditensVendas(rs.getInt("iditensVendas"));
                    item.setQuantidade(rs.getInt("quantidade"));
                    item.setPrecoUnitario(rs.getDouble("precoUnitario"));
                    item.setDesconto(rs.getDouble("desconto"));
                    item.setValorTotal(rs.getDouble("valorTotal"));
                    item.setVendasIdvendas(rs.getInt("vendas_idvendas"));

                    return item;
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar item da venda: " + e.getMessage());
        }

        return null;
    }

    // Listar itens de uma venda específica
    public List<ItensVendas> listarPorVenda(int idVenda) {
        List<ItensVendas> lista = new ArrayList<>();

        String sql = "SELECT * FROM itensVendas WHERE vendas_idvendas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idVenda);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ItensVendas item = new ItensVendas();

                    item.setIditensVendas(rs.getInt("iditensVendas"));
                    item.setQuantidade(rs.getInt("quantidade"));
                    item.setPrecoUnitario(rs.getDouble("precoUnitario"));
                    item.setDesconto(rs.getDouble("desconto"));
                    item.setValorTotal(rs.getDouble("valorTotal"));
                    item.setVendasIdvendas(rs.getInt("vendas_idvendas"));

                    lista.add(item);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar itens da venda: " + e.getMessage());
        }

        return lista;
    }

    // Atualizar item da venda
    public boolean atualizar(ItensVendas item) {
        String sql = "UPDATE itensVendas SET "
                + "quantidade = ?, precoUnitario = ?, desconto = ?, "
                + "valorTotal = ?, vendas_idvendas = ? "
                + "WHERE iditensVendas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, item.getQuantidade());
            stmt.setDouble(2, item.getPrecoUnitario());
            stmt.setDouble(3, item.getDesconto());
            stmt.setDouble(4, item.getValorTotal());
            stmt.setInt(5, item.getVendasIdvendas());
            stmt.setInt(6, item.getIditensVendas());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar item da venda: " + e.getMessage());
            return false;
        }
    }

    // Excluir item da venda
    public boolean excluir(int id) {
        String sql = "DELETE FROM itensVendas WHERE iditensVendas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao excluir item da venda: " + e.getMessage());
            return false;
        }
    }
    
}
