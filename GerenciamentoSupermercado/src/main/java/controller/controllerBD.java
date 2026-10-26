/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import Conection.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author CleicianeGomes
 */
public class controllerBD {
   
public class CategoriasController {

    public boolean cadastrar(String nome, String descricao,
                             boolean status) throws SQLException {
        String sql = "INSERT INTO categorias (nome, descricao, status) "
                   + "VALUES (?, ?, ?)";

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, nome);
            ps.setString(2, descricao);
            ps.setBoolean(3, status);
            return ps.executeUpdate() > 0;
        }
    }

    public List<Map<String, Object>> listar() throws SQLException {
        List<Map<String, Object>> lista = new ArrayList<>();
        String sql = "SELECT * FROM categorias ORDER BY nome";

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Map<String, Object> m = new LinkedHashMap<>();
                m.put("idcategorias", rs.getInt("idcategorias"));
                m.put("nome", rs.getString("nome"));
                m.put("descricao", rs.getString("descricao"));
                m.put("status", rs.getBoolean("status"));
                lista.add(m);
            }
        }
        return lista;
    }

    public boolean editar(int id, String nome, String descricao,
                          boolean status) throws SQLException {
        String sql = "UPDATE categorias SET nome=?, descricao=?, "
                   + "status=? WHERE idcategorias=?";

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, nome);
            ps.setString(2, descricao);
            ps.setBoolean(3, status);
            ps.setInt(4, id);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean desativar(int id) throws SQLException {
        String sql = "UPDATE categorias SET status=0 WHERE idcategorias=?";

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}

public class MarcasController {

    public boolean cadastrar(String nome, boolean status,
                             String descricao) throws SQLException {
        String sql = "INSERT INTO marcas (nome, status, descricao) "
                   + "VALUES (?, ?, ?)";

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, nome);
            ps.setBoolean(2, status);
            ps.setString(3, descricao);
            return ps.executeUpdate() > 0;
        }
    }

    public List<Map<String, Object>> listar() throws SQLException {
        List<Map<String, Object>> lista = new ArrayList<>();
        String sql = "SELECT * FROM marcas ORDER BY nome";

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Map<String, Object> m = new LinkedHashMap<>();
                m.put("idmarcas", rs.getInt("idmarcas"));
                m.put("nome", rs.getString("nome"));
                m.put("status", rs.getBoolean("status"));
                m.put("descricao", rs.getString("descricao"));
                lista.add(m);
            }
        }
        return lista;
    }

    public boolean editar(int id, String nome, boolean status,
                          String descricao) throws SQLException {
        String sql = "UPDATE marcas SET nome=?, status=?, descricao=? "
                   + "WHERE idmarcas=?";

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, nome);
            ps.setBoolean(2, status);
            ps.setString(3, descricao);
            ps.setInt(4, id);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean desativar(int id) throws SQLException {
        String sql = "UPDATE marcas SET status=0 WHERE idmarcas=?";

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}

public class UnidadeMedidasController {

    public boolean cadastrar(int codigo, String nome, boolean status,
                             String descricao) throws SQLException {
        String sql = "INSERT INTO unidadeMedidas "
                   + "(codigo, nome, status, descricao) VALUES (?, ?, ?, ?)";

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            ps.setString(2, nome);
            ps.setBoolean(3, status);
            ps.setString(4, descricao);
            return ps.executeUpdate() > 0;
        }
    }

    public List<Map<String, Object>> listar() throws SQLException {
        List<Map<String, Object>> lista = new ArrayList<>();
        String sql = "SELECT * FROM unidadeMedidas ORDER BY nome";

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Map<String, Object> m = new LinkedHashMap<>();
                m.put("idunidadeMedidas",
                      rs.getInt("idunidadeMedidas"));
                m.put("codigo", rs.getInt("codigo"));
                m.put("nome", rs.getString("nome"));
                m.put("status", rs.getBoolean("status"));
                m.put("descricao", rs.getString("descricao"));
                lista.add(m);
            }
        }
        return lista;
    }

    public boolean editar(int id, int codigo, String nome,
                          boolean status, String descricao)
                          throws SQLException {
        String sql = "UPDATE unidadeMedidas SET codigo=?, nome=?, "
                   + "status=?, descricao=? WHERE idunidadeMedidas=?";

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            ps.setString(2, nome);
            ps.setBoolean(3, status);
            ps.setString(4, descricao);
            ps.setInt(5, id);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean desativar(int id) throws SQLException {
        String sql = "UPDATE unidadeMedidas SET status=0 "
                   + "WHERE idunidadeMedidas=?";

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}

public class ProdutosController {

    public boolean cadastrar(
            String nome, String codigo, String descricao,
            double precoCusto, double precoVenda, int situacao,
            int estoque, String validade, byte[] imagem,
            int idCategoria, int idMarca, int idUnidade)
            throws SQLException {

        String sql = "INSERT INTO produtos "
                + "(nome, codigo, descricao, precoCusto, precoVenda, "
                + "situacao, estoque, validade, imagem, "
                + "categorias_idcategorias, marcas_idmarcas, "
                + "unidadeMedidas_idunidadeMedidas) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, nome);
            ps.setString(2, codigo);
            ps.setString(3, descricao);
            ps.setDouble(4, precoCusto);
            ps.setDouble(5, precoVenda);
            ps.setInt(6, situacao);
            ps.setInt(7, estoque);
            ps.setString(8, validade);

            if (imagem == null) {
                ps.setNull(9, Types.BLOB);
            } else {
                ps.setBytes(9, imagem);
            }

            ps.setInt(10, idCategoria);
            ps.setInt(11, idMarca);
            ps.setInt(12, idUnidade);

            return ps.executeUpdate() > 0;
        }
    }

    public List<Map<String, Object>> listar() throws SQLException {
        List<Map<String, Object>> lista = new ArrayList<>();

        String sql = "SELECT p.*, c.nome AS categoria, "
                + "m.nome AS marca, u.nome AS unidade "
                + "FROM produtos p "
                + "JOIN categorias c "
                + "ON p.categorias_idcategorias = c.idcategorias "
                + "JOIN marcas m ON p.marcas_idmarcas = m.idmarcas "
                + "JOIN unidadeMedidas u "
                + "ON p.unidadeMedidas_idunidadeMedidas = "
                + "u.idunidadeMedidas "
                + "ORDER BY p.nome";

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> m = new LinkedHashMap<>();

                m.put("idprodutos", rs.getInt("idprodutos"));
                m.put("nome", rs.getString("nome"));
                m.put("codigo", rs.getString("codigo"));
                m.put("descricao", rs.getString("descricao"));
                m.put("precoCusto", rs.getDouble("precoCusto"));
                m.put("precoVenda", rs.getDouble("precoVenda"));
                m.put("situacao", rs.getInt("situacao"));
                m.put("estoque", rs.getInt("estoque"));
                m.put("validade", rs.getString("validade"));
                m.put("imagem", rs.getBytes("imagem"));

                m.put("categorias_idcategorias",
                      rs.getInt("categorias_idcategorias"));
                m.put("marcas_idmarcas",
                      rs.getInt("marcas_idmarcas"));
                m.put("unidadeMedidas_idunidadeMedidas",
                      rs.getInt("unidadeMedidas_idunidadeMedidas"));

                m.put("categoria", rs.getString("categoria"));
                m.put("marca", rs.getString("marca"));
                m.put("unidade", rs.getString("unidade"));

                lista.add(m);
            }
        }

        return lista;
    }

    public boolean editar(
            int id, String nome, String codigo, String descricao,
            double precoCusto, double precoVenda, int situacao,
            int estoque, String validade, byte[] imagem,
            int idCategoria, int idMarca, int idUnidade)
            throws SQLException {

        String sql = "UPDATE produtos SET nome=?, codigo=?, "
                + "descricao=?, precoCusto=?, precoVenda=?, "
                + "situacao=?, estoque=?, validade=?, imagem=?, "
                + "categorias_idcategorias=?, marcas_idmarcas=?, "
                + "unidadeMedidas_idunidadeMedidas=? "
                + "WHERE idprodutos=?";

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, nome);
            ps.setString(2, codigo);
            ps.setString(3, descricao);
            ps.setDouble(4, precoCusto);
            ps.setDouble(5, precoVenda);
            ps.setInt(6, situacao);
            ps.setInt(7, estoque);
            ps.setString(8, validade);

            if (imagem == null) {
                ps.setNull(9, Types.BLOB);
            } else {
                ps.setBytes(9, imagem);
            }

            ps.setInt(10, idCategoria);
            ps.setInt(11, idMarca);
            ps.setInt(12, idUnidade);
            ps.setInt(13, id);

            return ps.executeUpdate() > 0;
        }
    }

    public boolean desativar(int id) throws SQLException {
        String sql = "UPDATE produtos SET situacao=0 "
                   + "WHERE idprodutos=?";

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    public List<Map<String, Object>> pesquisar(String termo)
            throws SQLException {

        List<Map<String, Object>> lista = new ArrayList<>();

        String sql = "SELECT idprodutos, nome, codigo, precoVenda, "
                + "estoque, situacao FROM produtos "
                + "WHERE nome LIKE ? OR codigo LIKE ? ORDER BY nome";

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, "%" + termo + "%");
            ps.setString(2, "%" + termo + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> m = new LinkedHashMap<>();

                    m.put("idprodutos", rs.getInt("idprodutos"));
                    m.put("nome", rs.getString("nome"));
                    m.put("codigo", rs.getString("codigo"));
                    m.put("precoVenda", rs.getDouble("precoVenda"));
                    m.put("estoque", rs.getInt("estoque"));
                    m.put("situacao", rs.getInt("situacao"));

                    lista.add(m);
                }
            }
        }

        return lista;
    }
}

public class CodigoBarrasController {

    public boolean cadastrar(
            String codigo, byte[] imagem, String tipo,
            boolean status, String descricao,
            int idProduto, int idCategoria,
            int idMarca, int idUnidade) throws SQLException {

        String sql = "INSERT INTO codigoBarras "
                + "(codigo, imagem, tipo, status, descricao, "
                + "produtos_idprodutos, "
                + "produtos_categorias_idcategorias, "
                + "produtos_marcas_idmarcas, "
                + "produtos_unidadeMedidas_idunidadeMedidas) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, codigo);

            if (imagem == null) {
                ps.setNull(2, Types.BLOB);
            } else {
                ps.setBytes(2, imagem);
            }

            ps.setString(3, tipo);
            ps.setBoolean(4, status);
            ps.setString(5, descricao);
            ps.setInt(6, idProduto);
            ps.setInt(7, idCategoria);
            ps.setInt(8, idMarca);
            ps.setInt(9, idUnidade);

            return ps.executeUpdate() > 0;
        }
    }

    public List<Map<String, Object>> listar() throws SQLException {
        List<Map<String, Object>> lista = new ArrayList<>();

        String sql = "SELECT cb.*, p.nome AS produto "
                + "FROM codigoBarras cb "
                + "JOIN produtos p "
                + "ON cb.produtos_idprodutos = p.idprodutos "
                + "ORDER BY cb.codigo";

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> m = new LinkedHashMap<>();

                m.put("idcodigoBarras", rs.getInt("idcodigoBarras"));
                m.put("codigo", rs.getString("codigo"));
                m.put("imagem", rs.getBytes("imagem"));
                m.put("tipo", rs.getString("tipo"));
                m.put("status", rs.getBoolean("status"));
                m.put("descricao", rs.getString("descricao"));
                m.put("produtos_idprodutos",
                      rs.getInt("produtos_idprodutos"));
                m.put("produtos_categorias_idcategorias",
                      rs.getInt("produtos_categorias_idcategorias"));
                m.put("produtos_marcas_idmarcas",
                      rs.getInt("produtos_marcas_idmarcas"));
                m.put("produtos_unidadeMedidas_idunidadeMedidas",
                      rs.getInt("produtos_unidadeMedidas_idunidadeMedidas"));
                m.put("produto", rs.getString("produto"));

                lista.add(m);
            }
        }

        return lista;
    }

    public boolean editar(
            int id, String codigo, byte[] imagem, String tipo,
            boolean status, String descricao,
            int idProduto, int idCategoria,
            int idMarca, int idUnidade) throws SQLException {

        String sql = "UPDATE codigoBarras SET codigo=?, imagem=?, "
                + "tipo=?, status=?, descricao=?, "
                + "produtos_idprodutos=?, "
                + "produtos_categorias_idcategorias=?, "
                + "produtos_marcas_idmarcas=?, "
                + "produtos_unidadeMedidas_idunidadeMedidas=? "
                + "WHERE idcodigoBarras=?";

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, codigo);

            if (imagem == null) {
                ps.setNull(2, Types.BLOB);
            } else {
                ps.setBytes(2, imagem);
            }

            ps.setString(3, tipo);
            ps.setBoolean(4, status);
            ps.setString(5, descricao);
            ps.setInt(6, idProduto);
            ps.setInt(7, idCategoria);
            ps.setInt(8, idMarca);
            ps.setInt(9, idUnidade);
            ps.setInt(10, id);

            return ps.executeUpdate() > 0;
        }
    }

    public boolean desativar(int id) throws SQLException {
        String sql = "UPDATE codigoBarras SET status=0 "
                   + "WHERE idcodigoBarras=?";

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}

public class EtiquetasController {

    public boolean cadastrar(String tipo, byte[] imagem)
            throws SQLException {

        String sql = "INSERT INTO etiquetas (tipo, imagem) VALUES (?, ?)";

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, tipo);
            ps.setBytes(2, imagem);

            return ps.executeUpdate() > 0;
        }
    }

    public List<Map<String, Object>> listar() throws SQLException {
        List<Map<String, Object>> lista = new ArrayList<>();

        String sql = "SELECT idetiquetas, tipo, imagem "
                   + "FROM etiquetas ORDER BY idetiquetas";

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> m = new LinkedHashMap<>();

                m.put("idetiquetas", rs.getInt("idetiquetas"));
                m.put("tipo", rs.getString("tipo"));
                m.put("imagem", rs.getBytes("imagem"));

                lista.add(m);
            }
        }

        return lista;
    }

    public boolean editar(int id, String tipo, byte[] imagem)
            throws SQLException {

        String sql = "UPDATE etiquetas SET tipo=?, imagem=? "
                   + "WHERE idetiquetas=?";

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, tipo);
            ps.setBytes(2, imagem);
            ps.setInt(3, id);

            return ps.executeUpdate() > 0;
        }
    }

    public boolean excluir(int id) throws SQLException {
        String sql = "DELETE FROM etiquetas WHERE idetiquetas=?";

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
}
