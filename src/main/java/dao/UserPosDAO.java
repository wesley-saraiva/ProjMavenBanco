/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexaojdbc.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.BeanUserFone;
import model.Telefone;
import model.UserPosJava;

/**
 *
 * @author wesle
 */
public class UserPosDAO {

    private Connection connection;

    public UserPosDAO() {
        connection = SingleConnection.getConnection();
    }

    public void salvar(UserPosJava userposjava) {
        try {
            String sql = "insert into userposjava (nome, email) values (?,?)";//String do SQL
            //Retorna o objeto de instrução
            PreparedStatement insert;
            insert = connection.prepareStatement(sql);
            insert.setString(1, userposjava.getNome());//Parametro sendo adicionados
            insert.setString(2, userposjava.getEmail());
            insert.execute();//SQL sendo executado no banco de dados
            connection.commit();// salva os dados no banco
        } catch (Exception ex) {
            try {
                connection.rollback();//reverte a operação
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
            ex.printStackTrace();
        }
    }

    public void salvarTelefone(Telefone tel) {

        try {
            String sql = "INSERT INTO telefoneuser(numero, tipo, usuariopessoa)"
                    + " VALUES (?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, tel.getNumero());
            statement.setString(2, tel.getTipo());
            statement.setLong(3, tel.getUsuario());
            statement.execute();
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }

    }

    public List<UserPosJava> listar() throws SQLException {
        List<UserPosJava> list = new ArrayList<UserPosJava>();

        String sql = "select * from userposjava";

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultado = statement.executeQuery();

        while (resultado.next()) {
            UserPosJava userposjava = new UserPosJava();
            userposjava.setId(resultado.getLong("id"));
            userposjava.setNome(resultado.getString("nome"));
            userposjava.setEmail(resultado.getString("email"));

            list.add(userposjava);
        }
        return list;
    }

    public UserPosJava buscar(Long id) throws SQLException {
        UserPosJava retorno = new UserPosJava();

        String sql = "select * from userposjava where id = " + id;

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultado = statement.executeQuery();

        while (resultado.next()) {

            retorno.setId(resultado.getLong("id"));
            retorno.setNome(resultado.getString("nome"));
            retorno.setEmail(resultado.getString("email"));

        }
        return retorno;
    }

    public List<BeanUserFone> listaUserfone(Long idUser) {
        List<BeanUserFone> beanUserFones = new ArrayList<>();

        String sql = "select nome,numero from telefoneuser as fone ";
        sql += " inner join userposjava as userp ";
        sql += " on fone.usuariopessoa = userp.id ";
        sql += " where userp.id = " + idUser;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                BeanUserFone userFone = new BeanUserFone();

                userFone.setNome(resultSet.getString("nome"));
                userFone.setNumero(resultSet.getString("numero"));

                beanUserFones.add(userFone);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return beanUserFones;
    }

    public void atualizar(UserPosJava userPosJava) {

        try {
            String sql = "update userposjava set nome = ? where id = "
                    + userPosJava.getId();

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userPosJava.getNome());

            statement.execute();
            connection.commit();

        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }

            ex.printStackTrace();
        }
    }

    public void deletar(Long id) {
        try {
            String sql = "delete from userposjava where id =" + id;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }

    }

    public void deleteFonesPorUser(Long idUser) {

        String sqlFone = "delete from telefoneuser where usuariopessoa = " + idUser;
        String sqlUser = "delete from userposjava where id = " + idUser;

        try {
            PreparedStatement statement = connection.prepareStatement(sqlFone);
            statement.executeUpdate();
            connection.commit();

            statement = connection.prepareStatement(sqlUser);
            statement.executeUpdate();
            connection.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
        }

    }

}
