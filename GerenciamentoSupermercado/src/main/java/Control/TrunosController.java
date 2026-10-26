/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import Conection.Conexao;
import Model.Turnos;
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
public class TrunosController {
    // CADASTRAR TURNO
    public boolean cadastrar(Turnos turno) {
        String sql = "INSERT INTO turnos "
                + "(nome, descricao, horaInicio, horaSaida, "
                + "intervaloInicio, intervaloFinal, duracaoTurno, "
                + "turnoNoturno, status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, turno.getNome());
            stmt.setString(2, turno.getDescriacao());
            stmt.setString(3, turno.getHorainicio());
            stmt.setString(4, turno.getHorasaida());
            stmt.setString(5, turno.getIntervaloIncio());
            stmt.setString(6, turno.getIntervaloFinal());
            stmt.setInt(7, turno.getDuracaoTurno());
            stmt.setBoolean(8, turno.isTurnoNoturno());
            stmt.setBoolean(9, turno.isStatus());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar turno: " + e.getMessage());
            return false;
        }
    }

    // LISTAR TODOS OS TURNOS
    public List<Turnos> listarTodos() {
        List<Turnos> turnos = new ArrayList<>();

        String sql = "SELECT idturnos, nome, descricao, horaInicio, "
                + "horaSaida, intervaloInicio, intervaloFinal, "
                + "duracaoTurno, turnoNoturno, status FROM turnos";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Turnos turno = new Turnos();

                turno.setIdturnos(rs.getInt("idturnos"));
                turno.setNome(rs.getString("nome"));
                turno.setDescriacao(rs.getString("descricao"));
                turno.setHorainicio(rs.getString("horaInicio"));
                turno.setHorasaida(rs.getString("horaSaida"));
                turno.setIntervaloIncio(rs.getString("intervaloInicio"));
                turno.setIntervaloFinal(rs.getString("intervaloFinal"));
                turno.setDuracaoTurno(rs.getInt("duracaoTurno"));
                turno.setTurnoNoturno(rs.getBoolean("turnoNoturno"));
                turno.setStatus(rs.getBoolean("status"));

                turnos.add(turno);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar turnos: " + e.getMessage());
        }

        return turnos;
    }

    // BUSCAR TURNO POR ID
    public Turnos buscarPorId(int id) {
        String sql = "SELECT idturnos, nome, descricao, horaInicio, "
                + "horaSaida, intervaloInicio, intervaloFinal, "
                + "duracaoTurno, turnoNoturno, status "
                + "FROM turnos WHERE idturnos = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Turnos turno = new Turnos();

                    turno.setIdturnos(rs.getInt("idturnos"));
                    turno.setNome(rs.getString("nome"));
                    turno.setDescriacao(rs.getString("descricao"));
                    turno.setHorainicio(rs.getString("horaInicio"));
                    turno.setHorasaida(rs.getString("horaSaida"));
                    turno.setIntervaloIncio(rs.getString("intervaloInicio"));
                    turno.setIntervaloFinal(rs.getString("intervaloFinal"));
                    turno.setDuracaoTurno(rs.getInt("duracaoTurno"));
                    turno.setTurnoNoturno(rs.getBoolean("turnoNoturno"));
                    turno.setStatus(rs.getBoolean("status"));

                    return turno;
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar turno: " + e.getMessage());
        }

        return null;
    }

    // ATUALIZAR TURNO
    public boolean atualizar(int idOriginal, Turnos turno) {
        String sql = "UPDATE turnos SET "
                + "nome = ?, descricao = ?, horaInicio = ?, "
                + "horaSaida = ?, intervaloInicio = ?, "
                + "intervaloFinal = ?, duracaoTurno = ?, "
                + "turnoNoturno = ?, status = ? "
                + "WHERE idturnos = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, turno.getNome());
            stmt.setString(2, turno.getDescriacao());
            stmt.setString(3, turno.getHorainicio());
            stmt.setString(4, turno.getHorasaida());
            stmt.setString(5, turno.getIntervaloIncio());
            stmt.setString(6, turno.getIntervaloFinal());
            stmt.setInt(7, turno.getDuracaoTurno());
            stmt.setBoolean(8, turno.isTurnoNoturno());
            stmt.setBoolean(9, turno.isStatus());
            stmt.setInt(10, idOriginal);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar turno: " + e.getMessage());
            return false;
        }
    }

    // EXCLUIR TURNO
    public boolean excluir(int id) {
        String sql = "DELETE FROM turnos WHERE idturnos = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir turno: " + e.getMessage());
            return false;
        }
    }
}
