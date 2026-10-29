/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;
import Conection.Conexao;
import Model.PagamentoVenda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PAGAMENTO_VENDAS_CONTROLLER {
    
    // Cadastrar pagamento da venda
    public boolean cadastrar(PagamentoVenda pagamento) {
        String sql = "INSERT INTO pagamentoVenda "
                + "(valor, valorRecebido, troco, situacao, referenciaTransacao, "
                + "vendas_idvendas, vendas_cupom_idcupom, "
                + "formasPagamento_idformasPagamento, "
                + "condicoesPagamento_idcondicoesPagamento, "
                + "contasFinanceiras_idcontasFinanceiras) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, pagamento.getValor());
            stmt.setDouble(2, pagamento.getValorRecebido());
            stmt.setDouble(3, pagamento.getTroco());
            stmt.setString(4, pagamento.getSituacao());
            stmt.setString(5, pagamento.getReferenciaTransacao());
            stmt.setInt(6, pagamento.getVendasIdvendas());
            stmt.setInt(7, pagamento.getVendasCupomIdcupom());
            stmt.setInt(8, pagamento.getFormasPagamentoIdformasPagamento());
            stmt.setInt(9, pagamento.getCondicoesPagamentoIdcondicoesPagamento());
            stmt.setInt(10, pagamento.getContasFinanceirasIdcontasFinanceiras());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar pagamento: " + e.getMessage());
            return false;
        }
    }

    // Listar todos os pagamentos
    public List<PagamentoVenda> listarTodos() {
        List<PagamentoVenda> lista = new ArrayList<>();

        String sql = "SELECT * FROM pagamentoVenda";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                PagamentoVenda pagamento = new PagamentoVenda();

                pagamento.setIdpagamentoVendas(
                        rs.getInt("idpagamentoVendas"));
                pagamento.setValor(rs.getDouble("valor"));
                pagamento.setValorRecebido(rs.getDouble("valorRecebido"));
                pagamento.setTroco(rs.getDouble("troco"));
                pagamento.setSituacao(rs.getString("situacao"));
                pagamento.setReferenciaTransacao(
                        rs.getString("referenciaTransacao"));
                pagamento.setVendasIdvendas(
                        rs.getInt("vendas_idvendas"));
                pagamento.setVendasCupomIdcupom(
                        rs.getInt("vendas_cupom_idcupom"));
                pagamento.setFormasPagamentoIdformasPagamento(
                        rs.getInt("formasPagamento_idformasPagamento"));
                pagamento.setCondicoesPagamentoIdcondicoesPagamento(
                        rs.getInt("condicoesPagamento_idcondicoesPagamento"));
                pagamento.setContasFinanceirasIdcontasFinanceiras(
                        rs.getInt("contasFinanceiras_idcontasFinanceiras"));

                lista.add(pagamento);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar pagamentos: " + e.getMessage());
        }

        return lista;
    }

    // Buscar pagamento pelo ID
    public PagamentoVenda buscarPorId(int id) {
        String sql = "SELECT * FROM pagamentoVenda "
                + "WHERE idpagamentoVendas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    PagamentoVenda pagamento = new PagamentoVenda();

                    pagamento.setIdpagamentoVendas(
                            rs.getInt("idpagamentoVendas"));
                    pagamento.setValor(rs.getDouble("valor"));
                    pagamento.setValorRecebido(rs.getDouble("valorRecebido"));
                    pagamento.setTroco(rs.getDouble("troco"));
                    pagamento.setSituacao(rs.getString("situacao"));
                    pagamento.setReferenciaTransacao(
                            rs.getString("referenciaTransacao"));
                    pagamento.setVendasIdvendas(
                            rs.getInt("vendas_idvendas"));
                    pagamento.setVendasCupomIdcupom(
                            rs.getInt("vendas_cupom_idcupom"));
                    pagamento.setFormasPagamentoIdformasPagamento(
                            rs.getInt("formasPagamento_idformasPagamento"));
                    pagamento.setCondicoesPagamentoIdcondicoesPagamento(
                            rs.getInt("condicoesPagamento_idcondicoesPagamento"));
                    pagamento.setContasFinanceirasIdcontasFinanceiras(
                            rs.getInt("contasFinanceiras_idcontasFinanceiras"));

                    return pagamento;
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar pagamento: " + e.getMessage());
        }

        return null;
    }

    // Listar pagamentos de uma venda específica
    public List<PagamentoVenda> listarPorVenda(int idVenda) {
        List<PagamentoVenda> lista = new ArrayList<>();

        String sql = "SELECT * FROM pagamentoVenda WHERE vendas_idvendas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idVenda);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    PagamentoVenda pagamento = new PagamentoVenda();

                    pagamento.setIdpagamentoVendas(
                            rs.getInt("idpagamentoVendas"));
                    pagamento.setValor(rs.getDouble("valor"));
                    pagamento.setValorRecebido(rs.getDouble("valorRecebido"));
                    pagamento.setTroco(rs.getDouble("troco"));
                    pagamento.setSituacao(rs.getString("situacao"));
                    pagamento.setReferenciaTransacao(
                            rs.getString("referenciaTransacao"));
                    pagamento.setVendasIdvendas(
                            rs.getInt("vendas_idvendas"));
                    pagamento.setVendasCupomIdcupom(
                            rs.getInt("vendas_cupom_idcupom"));
                    pagamento.setFormasPagamentoIdformasPagamento(
                            rs.getInt("formasPagamento_idformasPagamento"));
                    pagamento.setCondicoesPagamentoIdcondicoesPagamento(
                            rs.getInt("condicoesPagamento_idcondicoesPagamento"));
                    pagamento.setContasFinanceirasIdcontasFinanceiras(
                            rs.getInt("contasFinanceiras_idcontasFinanceiras"));

                    lista.add(pagamento);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar pagamentos da venda: "
                    + e.getMessage());
        }

        return lista;
    }

    // Atualizar pagamento
    public boolean atualizar(PagamentoVenda pagamento) {
        String sql = "UPDATE pagamentoVenda SET "
                + "valor = ?, valorRecebido = ?, troco = ?, "
                + "situacao = ?, referenciaTransacao = ?, "
                + "vendas_idvendas = ?, vendas_cupom_idcupom = ?, "
                + "formasPagamento_idformasPagamento = ?, "
                + "condicoesPagamento_idcondicoesPagamento = ?, "
                + "contasFinanceiras_idcontasFinanceiras = ? "
                + "WHERE idpagamentoVendas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, pagamento.getValor());
            stmt.setDouble(2, pagamento.getValorRecebido());
            stmt.setDouble(3, pagamento.getTroco());
            stmt.setString(4, pagamento.getSituacao());
            stmt.setString(5, pagamento.getReferenciaTransacao());
            stmt.setInt(6, pagamento.getVendasIdvendas());
            stmt.setInt(7, pagamento.getVendasCupomIdcupom());
            stmt.setInt(8, pagamento.getFormasPagamentoIdformasPagamento());
            stmt.setInt(9, pagamento.getCondicoesPagamentoIdcondicoesPagamento());
            stmt.setInt(10, pagamento.getContasFinanceirasIdcontasFinanceiras());
            stmt.setInt(11, pagamento.getIdpagamentoVendas());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar pagamento: " + e.getMessage());
            return false;
        }
    }

    // Excluir pagamento
    public boolean excluir(int id) {
        String sql = "DELETE FROM pagamentoVenda "
                + "WHERE idpagamentoVendas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao excluir pagamento: " + e.getMessage());
            return false;
        }
    }
    
}
