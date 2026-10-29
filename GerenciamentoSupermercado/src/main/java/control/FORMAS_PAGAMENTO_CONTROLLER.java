/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;
import Conection.Conexao;
import Model.FormasPagamento;

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

public class FORMAS_PAGAMENTO_CONTROLLER {
    
    // CADASTRAR FORMA DE PAGAMENTO
    public boolean cadastrar(FormasPagamento formaPagamento) {
        String sql = "INSERT INTO formasPagamento (nome) VALUES (?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, formaPagamento.getNome());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar forma de pagamento: "
                    + e.getMessage());
            return false;
        }
    }

    // LISTAR TODAS AS FORMAS DE PAGAMENTO
    public List<FormasPagamento> listarTodos() {
        List<FormasPagamento> formasPagamento = new ArrayList<>();

        String sql = "SELECT idformasPagamento, nome "
                + "FROM formasPagamento";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                FormasPagamento formaPagamento = new FormasPagamento();

                formaPagamento.setIdformasPagamento(
                        rs.getInt("idformasPagamento"));

                formaPagamento.setNome(rs.getString("nome"));

                formasPagamento.add(formaPagamento);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar formas de pagamento: "
                    + e.getMessage());
        }

        return formasPagamento;
    }

    // BUSCAR FORMA DE PAGAMENTO PELO ID
    public FormasPagamento buscarPorId(int id) {
        String sql = "SELECT idformasPagamento, nome "
                + "FROM formasPagamento "
                + "WHERE idformasPagamento = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    FormasPagamento formaPagamento = new FormasPagamento();

                    formaPagamento.setIdformasPagamento(
                            rs.getInt("idformasPagamento"));

                    formaPagamento.setNome(rs.getString("nome"));

                    return formaPagamento;
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar forma de pagamento: "
                    + e.getMessage());
        }

        return null;
    }

    // ATUALIZAR FORMA DE PAGAMENTO PELO ID
    public boolean atualizar(int id, FormasPagamento formaPagamento) {
        String sql = "UPDATE formasPagamento SET nome = ? "
                + "WHERE idformasPagamento = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, formaPagamento.getNome());
            stmt.setInt(2, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar forma de pagamento: "
                    + e.getMessage());
            return false;
        }
    }

    // EXCLUIR FORMA DE PAGAMENTO PELO ID
    public boolean excluir(int id) {
        String sql = "DELETE FROM formasPagamento "
                + "WHERE idformasPagamento = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir forma de pagamento: "
                    + e.getMessage());
            return false;
        }
    }
   
}
