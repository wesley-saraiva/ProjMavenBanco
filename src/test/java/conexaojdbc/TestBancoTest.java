/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexaojdbc;

import dao.UserPosDAO;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import model.BeanUserFone;
import model.Telefone;
import model.UserPosJava;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author wesle
 */
public class TestBancoTest {

    public TestBancoTest() {
    }

    @Before
    public void setUp() {
    }

    /**
     * Test of initBanco method, of class TestBanco.
     */
    @Test
    public void testInitBanco() {
        UserPosDAO userposdao = new UserPosDAO();
        UserPosJava userposjava = new UserPosJava();

        try {
            List<UserPosJava> list = userposdao.listar();

            for (UserPosJava userPosJava : list) {
                System.out.println(userPosJava);
                System.out.println("============================");
            }

            String usuNome = JOptionPane.showInputDialog("Digite seu nome");
            String usuEmail = JOptionPane.showInputDialog("Digite seu email");

            userposjava.setNome(usuNome);
            userposjava.setEmail(usuEmail);

            userposdao.salvar(userposjava);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Test of initListar method, of class TestBanco.
     */
    @Test
    public void testInitListar() {
        UserPosDAO dao = new UserPosDAO();

        try {
            List<UserPosJava> list = dao.listar();

            for (UserPosJava userPosJava : list) {
                System.out.println(userPosJava);
                System.out.println("============================");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Test of initBuscar method, of class TestBanco.
     */
    @Test
    public void testInitBuscar() {
        UserPosDAO dao = new UserPosDAO();

        try {
            UserPosJava userPosJava = dao.buscar(1L);

            System.out.println(userPosJava);
            System.out.println("============================");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Test of initAtualizar method, of class TestBanco.
     */
    @Test
    public void testInitAtualizar() {
        try {
            UserPosDAO dao = new UserPosDAO();

            UserPosJava objetoBanco = dao.buscar(1L);
            objetoBanco.setNome("Wesley Marques Saraiva");

            dao.atualizar(objetoBanco);

            System.out.println(objetoBanco.getNome());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Test of initDeletar method, of class TestBanco.
     */
    @Test
    public void testInitDeletar() {
        try {
            UserPosDAO dao = new UserPosDAO();
            dao.deletar(5L);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Test of testInsertTelefone method, of class TestBanco.
     */
    @Test
    public void testTestInsertTelefone() {

        Telefone telefone = new Telefone();

        String num = JOptionPane.showInputDialog("Digite o numero de telefone!");
        String tipo = JOptionPane.showInputDialog("Digite o tipo de telefone!");
        long id1 = Long.parseLong(JOptionPane.showInputDialog("Digite o id do usuario!"));

        telefone.setNumero(num);
        telefone.setTipo(tipo);
        telefone.setUsuario(id1);

        UserPosDAO dao = new UserPosDAO();

        dao.salarTelefone(telefone);

    }

    /**
     * Test of testCarregarFonesUser method, of class TestBanco.
     */
    @Test
    public void testTestCarregarFonesUser() {

        UserPosDAO dao = new UserPosDAO();

        List<BeanUserFone> beanUserFones = dao.listaUserfone(1L);

        for (BeanUserFone beanUserFone : beanUserFones) {
            System.out.println(beanUserFone);
            System.out.println("=============================================");
        }
    }

    /**
     * Test of testDeleteUserFone method, of class TestBanco.
     */
    @Test
    public void testTestDeleteUserFone() {
        UserPosDAO dao = new UserPosDAO();
        dao.deleteFonesPorUser(4L);

    }

}
