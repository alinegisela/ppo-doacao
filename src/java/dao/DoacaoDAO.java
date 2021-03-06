/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Conexao.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import negocio.Doacao;
import negocio.Doador;
import negocio.Instituicao;

/**
 *
 * @author Penguin
 */
public class DoacaoDAO {

    private static DoacaoDAO myself = null;
    private Connection con;

    public DoacaoDAO() throws ClassNotFoundException {
        this.con = new ConnectionFactory().getConnection();
    }

    public static DoacaoDAO getInstance() throws ClassNotFoundException {
        if (myself == null) {
            myself = new DoacaoDAO();
        }

        return myself;
    }

    //Funcionannndooooooooooo
    public boolean inserir(int codigo, Doador doador, Instituicao instituicao, String status, Date dataDoacao, Date dataVisita) {
        try {
            PreparedStatement insereDoadorSTM = null;
            String insereDoadorSQL = "insert into doacao (codigo, id_doador, id_instituicao, status, datadoacao, datavisita)"
                    + "values (?,?,?,?,?,?)";

            insereDoadorSTM = con.prepareStatement(insereDoadorSQL);

            insereDoadorSTM.setInt(1, codigo);
            insereDoadorSTM.setString(2, doador.getCpf());
            insereDoadorSTM.setString(3, instituicao.getCnpj());
            insereDoadorSTM.setString(4, status);
            insereDoadorSTM.setDate(5, new java.sql.Date(dataDoacao.getTime()));
            insereDoadorSTM.setDate(6, new java.sql.Date(dataVisita.getTime()));

            if (insereDoadorSTM.executeUpdate() == 1) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean inserirMateriais(int id_doacao, int id_material) throws SQLException {
        try {
            PreparedStatement insereMateriaisSTM = null;
            String insereMateriaisSQL = "insert into doacao_material (id_doacao, id_material)"
                    + "values (?,?)";

            insereMateriaisSTM = con.prepareStatement(insereMateriaisSQL);

            insereMateriaisSTM.setInt(1, id_doacao);
            insereMateriaisSTM.setInt(2, id_material);

            if (insereMateriaisSTM.executeUpdate() == 1) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean alterar(int codigo, Doador doador, Instituicao instituicao, String status, Date dataDoacao, Date dataVisita) {

        String sql = "update doacao set codigo=?, id_doador=?, id_instituicao=?, status=?,dataDoacao=?, dataVisita=? where codigo=codigo";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, codigo);
            stmt.setString(2, doador.getCpf());
            stmt.setString(3, instituicao.getCnpj());
            stmt.setString(4, status);
            stmt.setDate(5, new java.sql.Date(dataDoacao.getTime()));
            stmt.setDate(6, new java.sql.Date(dataVisita.getTime()));
            stmt.execute();

            if (stmt.executeUpdate() == 1) {
                return true;
            }
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean alterarStatus(String status, int codigo) {

        String sql = "update doacao set status=? where codigo=?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, status);
            stmt.setInt(2, codigo);

            stmt.execute();

            if (stmt.executeUpdate() == 1) {
                return true;
            }
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public void excluir(Doacao doacao) {
        try {
            PreparedStatement stmt = con.prepareStatement("delete from doacao where codigo=?");
            stmt.setInt(1, doacao.getCodigo());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List recuperarTodos() throws ClassNotFoundException {
        try {
            List<Doacao> doacaoList = new ArrayList<Doacao>();
            PreparedStatement stmt = this.con.
                    prepareStatement("select * from doacao");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // criando o objeto Contato
                Doacao doacao = new Doacao();
                doacao.setCodigo(rs.getInt("codigo"));
                doacao.setDoador(DoadorDAO.getInstance().recuperar(rs.getString("id_doador")));
                doacao.setInstituicao(InstituicaoDAO.getInstance().recuperar(rs.getString("id_instituicao")));
                doacao.setStatus(rs.getString("status"));
                doacao.setDataDoacao(rs.getDate("dataDoacao"));
                doacao.setDataVisita(rs.getDate("dataVisita"));

                doacaoList.add(doacao);
            }
            rs.close();
            stmt.close();
            return doacaoList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List recuperarTodosDoador(String cpf) throws ClassNotFoundException {
        try {
            List<Doacao> doacaoList = new ArrayList<Doacao>();
            PreparedStatement stmt = this.con.
                    prepareStatement("select * from doacao where id_doador=?");
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // criando o objeto Contato
                Doacao doacao = new Doacao();
                doacao.setCodigo(rs.getInt("codigo"));
                doacao.setDoador(DoadorDAO.getInstance().recuperar(rs.getString("id_doador")));
                doacao.setInstituicao(InstituicaoDAO.getInstance().recuperar(rs.getString("id_instituicao")));
                doacao.setStatus(rs.getString("status"));
                doacao.setDataDoacao(rs.getDate("dataDoacao"));
                doacao.setDataVisita(rs.getDate("dataVisita"));

                doacaoList.add(doacao);
            }
            rs.close();
            stmt.close();
            return doacaoList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Doacao recuperar(int codigo) throws ClassNotFoundException {
        try {
            List<Doacao> doacaoList = new ArrayList<Doacao>();
            PreparedStatement stmt = this.con.
                    prepareStatement("select * from doacao where codigo=?");
            stmt.setInt(1, codigo);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // criando o objeto Contato
                Doacao doacao = new Doacao();
                doacao.setCodigo(rs.getInt("codigo"));
                doacao.setDoador(DoadorDAO.getInstance().recuperar(rs.getString("id_doador")));
                doacao.setInstituicao(InstituicaoDAO.getInstance().recuperar(rs.getString("id_instituicao")));
                doacao.setStatus(rs.getString("status"));
                doacao.setDataDoacao(rs.getDate("dataDoacao"));
                doacao.setDataVisita(rs.getDate("dataVisita"));
                doacaoList.add(doacao);
            }
            /*
                // montando a data através do Calendar
                Calendar data = Calendar.getInstance();
                data.setTime(rs.getDate("dataNascimento"));
                contato.setDataNascimento(data);
             */
            // adicionando o objeto à lista
            rs.close();
            stmt.close();
            return doacaoList.get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Doacao> recuperarPorStatus(String status, String cnpj) throws ClassNotFoundException {
        try {
            List<Doacao> doacaoList = new ArrayList<Doacao>();
            PreparedStatement stmt = this.con.
                    prepareStatement("select * from doacao where status=? and id_instituicao=?");
            stmt.setString(1, status);
            stmt.setString(2, cnpj);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // criando o objeto Contato
                Doacao doacao = new Doacao();
                doacao.setCodigo(rs.getInt("codigo"));
                doacao.setDoador(DoadorDAO.getInstance().recuperar(rs.getString("id_doador")));
                doacao.setInstituicao(InstituicaoDAO.getInstance().recuperar(rs.getString("id_instituicao")));
                doacao.setStatus(rs.getString("status"));
                doacao.setDataDoacao(rs.getDate("dataDoacao"));
                doacao.setDataVisita(rs.getDate("dataVisita"));

                doacaoList.add(doacao);
            }
            rs.close();
            stmt.close();
            return doacaoList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
