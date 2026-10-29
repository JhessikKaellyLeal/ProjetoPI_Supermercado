/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;
import Conection.Conexao;
import Model.CondicoesPagamento;

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

public class CONDICOES_PAGAMENTO_CONTROLLER {
    
    // CADASTRAR CONDICAO DE PAGAMENTO
    public boolean cadastrar(CondicoesPagamento condicao) {
        String sql = "INSERT INTO condicoesPagamento "
                + "(quantidadeParcelas, intervaloDias) "
                + "VALUES (?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, condicao.getQuantidadeParcelas());
            stmt.setInt(2, condicao.getIntervaloDias());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar condição de pagamento: "
                    + e.getMessage());
            return false;
        }
    }

    // LISTAR TODAS AS CONDICOES DE PAGAMENTO
    public List<CondicoesPagamento> listarTodos() {
        List<CondicoesPagamento> condicoes = new ArrayList<>();

        String sql = "SELECT idcondicoesPagamento, "
                + "quantidadeParcelas, intervaloDias "
                + "FROM condicoesPagamento";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                CondicoesPagamento condicao = new CondicoesPagamento();

                condicao.setIdcondicoesPagamento(
                        rs.getInt("idcondicoesPagamento"));

                condicao.setQuantidadeParcelas(
                        rs.getInt("quantidadeParcelas"));

                condicao.setIntervaloDias(
                        rs.getInt("intervaloDias"));

                condicoes.add(condicao);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar condições de pagamento: "
                    + e.getMessage());
        }

        return condicoes;
    }

    // BUSCAR CONDICAO DE PAGAMENTO PELO ID
    public CondicoesPagamento buscarPorId(int id) {
        String sql = "SELECT idcondicoesPagamento, "
                + "quantidadeParcelas, intervaloDias "
                + "FROM condicoesPagamento "
                + "WHERE idcondicoesPagamento = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    CondicoesPagamento condicao =
                            new CondicoesPagamento();

                    condicao.setIdcondicoesPagamento(
                            rs.getInt("idcondicoesPagamento"));

                    condicao.setQuantidadeParcelas(
                            rs.getInt("quantidadeParcelas"));

                    condicao.setIntervaloDias(
                            rs.getInt("intervaloDias"));

                    return condicao;
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar condição de pagamento: "
                    + e.getMessage());
        }

        return null;
    }

    // ATUALIZAR CONDICAO DE PAGAMENTO PELO ID
    public boolean atualizar(int id, CondicoesPagamento condicao) {
        String sql = "UPDATE condicoesPagamento "
                + "SET quantidadeParcelas = ?, intervaloDias = ? "
                + "WHERE idcondicoesPagamento = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, condicao.getQuantidadeParcelas());
            stmt.setInt(2, condicao.getIntervaloDias());
            stmt.setInt(3, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar condição de pagamento: "
                    + e.getMessage());
            return false;
        }
    }

    // EXCLUIR CONDICAO DE PAGAMENTO PELO ID
    public boolean excluir(int id) {
        String sql = "DELETE FROM condicoesPagamento "
                + "WHERE idcondicoesPagamento = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir condição de pagamento: "
                    + e.getMessage());
            return false;
        }
    }
    
}
