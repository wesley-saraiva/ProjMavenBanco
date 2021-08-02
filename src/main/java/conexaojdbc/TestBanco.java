/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexaojdbc;

import dao.UserPosDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.BeanUserFone;
import model.Telefone;
import model.UserPosJava;
import org.junit.Test;

/**
 *
 * @author wesle
 */
public class TestBanco {
    
    @Test
    public void initBanco() {
        
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
    
    public void initListar() {
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
    
    public void initBuscar() {
        UserPosDAO dao = new UserPosDAO();
        
        try {
            UserPosJava userPosJava = dao.buscar(8L);
            
            System.out.println(userPosJava);
            System.out.println("============================");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void initAtualizar() {
        try {
            UserPosDAO dao = new UserPosDAO();
            
            UserPosJava objetoBanco = dao.buscar(5L);
            objetoBanco.setNome("Nome mudado com metodo atualizar");
            
            dao.atualizar(objetoBanco);
            
            System.out.println(objetoBanco.getNome());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    @Test
    public void initDeletar() {
        try {
            UserPosDAO dao = new UserPosDAO();
            dao.deletar(2L);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
    @Test
    public void testInsertTelefone() {
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
    
    @Test
    public void testCarregarFonesUser() {
        
        UserPosDAO dao = new UserPosDAO();
        
        List<BeanUserFone> beanUserFones = dao.listaUserfone(16L);
        
        for (BeanUserFone beanUserFone : beanUserFones) {
            System.out.println(beanUserFone);
            System.out.println("=============================================");
        }
    }

    @Test
    public void testDeleteUserFone() {
        
        UserPosDAO dao = new UserPosDAO();
        dao.deleteFonesPorUser(3L);
        
    }
}
