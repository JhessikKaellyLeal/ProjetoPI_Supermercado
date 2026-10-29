/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;
import Conection.Conexao;
import Model.Vendas;

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

public class VENDAS_CONTROLLER {
    
    // CADASTRAR VENDA
    public boolean cadastrar(Vendas venda) {
        String sql = "INSERT INTO vendas "
                + "(dataVenda, horarioVenda, valorTotal, valorPago, "
                + "troco, quantidadeItens, statusVenda, "
                + "cliente_idcliente, usuario_idusuario, "
                + "caixas_idcaixas, cupom_idcupom) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, venda.getDataVenda());
            stmt.setString(2, venda.getHorarioVenda());
            stmt.setDouble(3, venda.getValorTotal());
            stmt.setDouble(4, venda.getValorPago());
            stmt.setDouble(5, venda.getTroco());
            stmt.setInt(6, venda.getQuantidadeItens());
            stmt.setString(7, venda.getStatusVenda());
            stmt.setInt(8, venda.getClienteIdcliente());
            stmt.setInt(9, venda.getUsuarioIdusuario());
            stmt.setInt(10, venda.getCaixasIdcaixas());
            stmt.setInt(11, venda.getCupomIdcupom());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar venda: "
                    + e.getMessage());
            return false;
        }
    }

    // LISTAR TODAS AS VENDAS
    public List<Vendas> listarTodos() {
        List<Vendas> vendas = new ArrayList<>();

        String sql = "SELECT idvendas, dataVenda, horarioVenda, "
                + "valorTotal, valorPago, troco, quantidadeItens, "
                + "statusVenda, cliente_idcliente, usuario_idusuario, "
                + "caixas_idcaixas, cupom_idcupom "
                + "FROM vendas";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Vendas venda = new Vendas();

                venda.setIdvendas(rs.getInt("idvendas"));
                venda.setDataVenda(rs.getString("dataVenda"));
                venda.setHorarioVenda(rs.getString("horarioVenda"));
                venda.setValorTotal(rs.getDouble("valorTotal"));
                venda.setValorPago(rs.getDouble("valorPago"));
                venda.setTroco(rs.getDouble("troco"));
                venda.setQuantidadeItens(
                        rs.getInt("quantidadeItens"));
                venda.setStatusVenda(rs.getString("statusVenda"));

                venda.setClienteIdcliente(
                        rs.getInt("cliente_idcliente"));
                venda.setUsuarioIdusuario(
                        rs.getInt("usuario_idusuario"));
                venda.setCaixasIdcaixas(
                        rs.getInt("caixas_idcaixas"));
                venda.setCupomIdcupom(
                        rs.getInt("cupom_idcupom"));

                vendas.add(venda);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar vendas: "
                    + e.getMessage());
        }

        return vendas;
    }

    // BUSCAR VENDA PELO ID
    public Vendas buscarPorId(int id) {
        String sql = "SELECT idvendas, dataVenda, horarioVenda, "
                + "valorTotal, valorPago, troco, quantidadeItens, "
                + "statusVenda, cliente_idcliente, usuario_idusuario, "
                + "caixas_idcaixas, cupom_idcupom "
                + "FROM vendas WHERE idvendas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Vendas venda = new Vendas();

                    venda.setIdvendas(rs.getInt("idvendas"));
                    venda.setDataVenda(rs.getString("dataVenda"));
                    venda.setHorarioVenda(
                            rs.getString("horarioVenda"));
                    venda.setValorTotal(rs.getDouble("valorTotal"));
                    venda.setValorPago(rs.getDouble("valorPago"));
                    venda.setTroco(rs.getDouble("troco"));
                    venda.setQuantidadeItens(
                            rs.getInt("quantidadeItens"));
                    venda.setStatusVenda(
                            rs.getString("statusVenda"));

                    venda.setClienteIdcliente(
                            rs.getInt("cliente_idcliente"));
                    venda.setUsuarioIdusuario(
                            rs.getInt("usuario_idusuario"));
                    venda.setCaixasIdcaixas(
                            rs.getInt("caixas_idcaixas"));
                    venda.setCupomIdcupom(
                            rs.getInt("cupom_idcupom"));

                    return venda;
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar venda: "
                    + e.getMessage());
        }

        return null;
    }

    // ATUALIZAR VENDA PELO ID
    public boolean atualizar(int id, Vendas venda) {
        String sql = "UPDATE vendas SET "
                + "dataVenda = ?, horarioVenda = ?, "
                + "valorTotal = ?, valorPago = ?, troco = ?, "
                + "quantidadeItens = ?, statusVenda = ?, "
                + "cliente_idcliente = ?, usuario_idusuario = ?, "
                + "caixas_idcaixas = ?, cupom_idcupom = ? "
                + "WHERE idvendas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, venda.getDataVenda());
            stmt.setString(2, venda.getHorarioVenda());
            stmt.setDouble(3, venda.getValorTotal());
            stmt.setDouble(4, venda.getValorPago());
            stmt.setDouble(5, venda.getTroco());
            stmt.setInt(6, venda.getQuantidadeItens());
            stmt.setString(7, venda.getStatusVenda());
            stmt.setInt(8, venda.getClienteIdcliente());
            stmt.setInt(9, venda.getUsuarioIdusuario());
            stmt.setInt(10, venda.getCaixasIdcaixas());
            stmt.setInt(11, venda.getCupomIdcupom());
            stmt.setInt(12, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar venda: "
                    + e.getMessage());
            return false;
        }
    }

    // EXCLUIR VENDA PELO ID
    public boolean excluir(int id) {
        String sql = "DELETE FROM vendas WHERE idvendas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir venda: "
                    + e.getMessage());
            return false;
        }
    }
}
