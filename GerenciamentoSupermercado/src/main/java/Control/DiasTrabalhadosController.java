/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import Conection.Conexao;
import Model.DiasTrabalhados;
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
public class DiasTrabalhadosController {
        // Cadastrar dia trabalhado
    public boolean cadastrar(DiasTrabalhados dia, int idEscala) {
        String sql = "INSERT INTO diasTrabalhados "
                   + "(nome, escala_idescala) VALUES (?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dia.getNome());
            stmt.setInt(2, idEscala);

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar dia trabalhado: "
                             + e.getMessage());
            return false;
        }
    }

    // Listar todos os dias trabalhados
    public List<DiasTrabalhados> listarTodos() {
        List<DiasTrabalhados> lista = new ArrayList<>();

        String sql = "SELECT iddiasTrabalhados, nome "
                   + "FROM diasTrabalhados";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                DiasTrabalhados dia = new DiasTrabalhados();

                dia.setIddiasTrabalhados(
                    rs.getInt("iddiasTrabalhados"));
                dia.setNome(rs.getString("nome"));

                lista.add(dia);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar dias trabalhados: "
                             + e.getMessage());
        }

        return lista;
    }

    // Buscar dia trabalhado pelo ID
    public DiasTrabalhados buscarPorId(int id) {
        String sql = "SELECT iddiasTrabalhados, nome "
                   + "FROM diasTrabalhados "
                   + "WHERE iddiasTrabalhados = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    DiasTrabalhados dia = new DiasTrabalhados();

                    dia.setIddiasTrabalhados(
                        rs.getInt("iddiasTrabalhados"));
                    dia.setNome(rs.getString("nome"));

                    return dia;
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar dia trabalhado: "
                             + e.getMessage());
        }

        return null;
    }

    // Atualizar dia trabalhado
    public boolean atualizar(int idOriginal, DiasTrabalhados dia,
                             int idEscala) {
        String sql = "UPDATE diasTrabalhados "
                   + "SET nome = ?, escala_idescala = ? "
                   + "WHERE iddiasTrabalhados = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dia.getNome());
            stmt.setInt(2, idEscala);
            stmt.setInt(3, idOriginal);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar dia trabalhado: "
                             + e.getMessage());
            return false;
        }
    }

    // Excluir dia trabalhado
    public boolean excluir(int id) {
        String sql = "DELETE FROM diasTrabalhados "
                   + "WHERE iddiasTrabalhados = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir dia trabalhado: "
                             + e.getMessage());
            return false;
        }
    }
}
