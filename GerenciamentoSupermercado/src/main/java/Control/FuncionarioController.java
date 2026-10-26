/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import Conection.Conexao;
import Model.Funcinario;
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
public class FuncionarioController {
    // CADASTRAR FUNCIONÁRIO
    public boolean cadastrar(Funcinario funcionario) {
        String sql = "INSERT INTO Funcionario "
                + "(nome, dataNascimento, cpf, telefone, dataAdmissao, "
                + "statusFuncionario, setores_idsetores, cargos_idcargos, "
                + "turnos_idturnos) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getDataNascimento());
            stmt.setString(3, funcionario.getCpf());
            stmt.setString(4, funcionario.getTelefone());
            stmt.setString(5, funcionario.getDataAdmissao());
            stmt.setBoolean(6, funcionario.isStatusFuncionario());
            stmt.setInt(7, funcionario.getSetores_idsetores());
            stmt.setInt(8, funcionario.getCargos_idcargos());
            stmt.setInt(9, funcionario.getTurnos_idturnos());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar funcionário: "
                    + e.getMessage());
            return false;
        }
    }

    // LISTAR TODOS OS FUNCIONÁRIOS
    public List<Funcinario> listarTodos() {
        List<Funcinario> funcionarios = new ArrayList<>();

        String sql = "SELECT idFuncionario, nome, dataNascimento, cpf, "
                + "telefone, dataAdmissao, statusFuncionario, "
                + "setores_idsetores, cargos_idcargos, turnos_idturnos "
                + "FROM Funcionario";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Funcinario funcionario = new Funcinario();

                funcionario.setIdFuncionario(
                        rs.getInt("idFuncionario"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setDataNascimento(
                        rs.getString("dataNascimento"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setDataAdmissao(
                        rs.getString("dataAdmissao"));
                funcionario.setStatusFuncionario(
                        rs.getBoolean("statusFuncionario"));
                funcionario.setSetores_idsetores(
                        rs.getInt("setores_idsetores"));
                funcionario.setCargos_idcargos(
                        rs.getInt("cargos_idcargos"));
                funcionario.setTurnos_idturnos(
                        rs.getInt("turnos_idturnos"));

                funcionarios.add(funcionario);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar funcionários: "
                    + e.getMessage());
        }

        return funcionarios;
    }

    // BUSCAR FUNCIONÁRIO POR ID
    public Funcinario buscarPorId(int id) {
        String sql = "SELECT idFuncionario, nome, dataNascimento, cpf, "
                + "telefone, dataAdmissao, statusFuncionario, "
                + "setores_idsetores, cargos_idcargos, turnos_idturnos "
                + "FROM Funcionario WHERE idFuncionario = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Funcinario funcionario = new Funcinario();

                    funcionario.setIdFuncionario(
                            rs.getInt("idFuncionario"));
                    funcionario.setNome(rs.getString("nome"));
                    funcionario.setDataNascimento(
                            rs.getString("dataNascimento"));
                    funcionario.setCpf(rs.getString("cpf"));
                    funcionario.setTelefone(rs.getString("telefone"));
                    funcionario.setDataAdmissao(
                            rs.getString("dataAdmissao"));
                    funcionario.setStatusFuncionario(
                            rs.getBoolean("statusFuncionario"));
                    funcionario.setSetores_idsetores(
                            rs.getInt("setores_idsetores"));
                    funcionario.setCargos_idcargos(
                            rs.getInt("cargos_idcargos"));
                    funcionario.setTurnos_idturnos(
                            rs.getInt("turnos_idturnos"));

                    return funcionario;
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar funcionário: "
                    + e.getMessage());
        }

        return null;
    }

    // ATUALIZAR FUNCIONÁRIO
    public boolean atualizar(int idOriginal, Funcinario funcionario) {
        String sql = "UPDATE Funcionario SET "
                + "nome = ?, dataNascimento = ?, cpf = ?, telefone = ?, "
                + "dataAdmissao = ?, statusFuncionario = ?, "
                + "setores_idsetores = ?, cargos_idcargos = ?, "
                + "turnos_idturnos = ? "
                + "WHERE idFuncionario = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getDataNascimento());
            stmt.setString(3, funcionario.getCpf());
            stmt.setString(4, funcionario.getTelefone());
            stmt.setString(5, funcionario.getDataAdmissao());
            stmt.setBoolean(6, funcionario.isStatusFuncionario());
            stmt.setInt(7, funcionario.getSetores_idsetores());
            stmt.setInt(8, funcionario.getCargos_idcargos());
            stmt.setInt(9, funcionario.getTurnos_idturnos());
            stmt.setInt(10, idOriginal);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar funcionário: "
                    + e.getMessage());
            return false;
        }
    }

    // EXCLUIR FUNCIONÁRIO
    public boolean excluir(int id) {
        String sql = "DELETE FROM Funcionario WHERE idFuncionario = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir funcionário: "
                    + e.getMessage());
            return false;
        }
    }
}
