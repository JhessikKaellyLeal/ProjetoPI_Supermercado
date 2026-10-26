/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import Conection.Conexao;
import Model.Escala;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author IsraelSantos
 */
public class EscalaController {
    // CADASTRAR ESCALA
    public boolean cadastrar(Escala escala) {
        String sql = "INSERT INTO escala "
                + "(periodoInicial, periodoFinal, Funcionario_idFuncionario) "
                + "VALUES (?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, escala.getPeriodoInicial());
            stmt.setString(2, escala.getPeriodoFinal());
            stmt.setInt(3, escala.getFuncionario_idFuncionario());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar escala: " + e.getMessage());
            return false;
        }
    }

    // LISTAR TODAS AS ESCALAS
    public List<Escala> listarTodos() {
        List<Escala> escalas = new ArrayList<>();

        String sql = "SELECT e.idescala, e.periodoInicial, e.periodoFinal, "
                + "e.Funcionario_idFuncionario, f.cargos_idcargos, "
                + "f.setores_idsetores, f.turnos_idturnos "
                + "FROM escala e "
                + "INNER JOIN Funcionario f "
                + "ON e.Funcionario_idFuncionario = f.idFuncionario";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Escala escala = new Escala();

                escala.setIddiasTrabalhados(rs.getInt("idescala"));
                escala.setPeriodoInicial(rs.getString("periodoInicial"));
                escala.setPeriodoFinal(rs.getString("periodoFinal"));
                escala.setFuncionario_idFuncionario(
                        rs.getInt("Funcionario_idFuncionario"));
                escala.setFuncionario_cargos_idcargos(
                        rs.getInt("cargos_idcargos"));
                escala.setFuncionario_setores_idsetores(
                        rs.getInt("setores_idsetores"));
                escala.setFuncionario_turnos_idturnos(
                        rs.getInt("turnos_idturnos"));

                escalas.add(escala);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar escalas: " + e.getMessage());
        }

        return escalas;
    }

    // BUSCAR ESCALA POR ID
    public Escala buscarPorId(int id) {
        String sql = "SELECT e.idescala, e.periodoInicial, e.periodoFinal, "
                + "e.Funcionario_idFuncionario, f.cargos_idcargos, "
                + "f.setores_idsetores, f.turnos_idturnos "
                + "FROM escala e "
                + "INNER JOIN Funcionario f "
                + "ON e.Funcionario_idFuncionario = f.idFuncionario "
                + "WHERE e.idescala = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Escala escala = new Escala();

                    escala.setIddiasTrabalhados(rs.getInt("idescala"));
                    escala.setPeriodoInicial(rs.getString("periodoInicial"));
                    escala.setPeriodoFinal(rs.getString("periodoFinal"));
                    escala.setFuncionario_idFuncionario(
                            rs.getInt("Funcionario_idFuncionario"));
                    escala.setFuncionario_cargos_idcargos(
                            rs.getInt("cargos_idcargos"));
                    escala.setFuncionario_setores_idsetores(
                            rs.getInt("setores_idsetores"));
                    escala.setFuncionario_turnos_idturnos(
                            rs.getInt("turnos_idturnos"));

                    return escala;
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar escala: " + e.getMessage());
        }

        return null;
    }

    // ATUALIZAR ESCALA
    public boolean atualizar(int idOriginal, Escala escala) {
        String sql = "UPDATE escala SET "
                + "periodoInicial = ?, periodoFinal = ?, "
                + "Funcionario_idFuncionario = ? "
                + "WHERE idescala = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, escala.getPeriodoInicial());
            stmt.setString(2, escala.getPeriodoFinal());
            stmt.setInt(3, escala.getFuncionario_idFuncionario());
            stmt.setInt(4, idOriginal);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar escala: " + e.getMessage());
            return false;
        }
    }

    // EXCLUIR ESCALA
    public boolean excluir(int id) {
        String sql = "DELETE FROM escala WHERE idescala = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir escala: " + e.getMessage());
            return false;
        }
    }
}
