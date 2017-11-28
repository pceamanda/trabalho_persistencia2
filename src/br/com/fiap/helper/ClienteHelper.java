package br.com.fiap.helper;

import br.com.fiap.config.HibernateUtil;
import br.com.fiap.entity.Cliente;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class ClienteHelper {

    Session session = null;
    Transaction transaction = null;

    public void encerrar(){
        session.close();
    }

    public String salvar(Cliente cliente){
        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(cliente);
            transaction.commit();

            encerrar();

            return "Cliente salva";

        }catch(Exception e){
            return e.getMessage();
        }
    }

    public Cliente getCliente(int idCliente){
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return session.load(Cliente.class, idCliente);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Cliente> listarCliente(){
        List<Cliente> clientes = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            TypedQuery<Cliente> query = session.createQuery("from Cliente", Cliente.class);
            clientes = query.getResultList();
            encerrar();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public List<Cliente> listarCliente(String nome){
        List<Cliente> clientes = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            TypedQuery<Cliente> query = session.createQuery("from Cliente where nome = :nome) ", Cliente.class);
            query.setParameter("nome", nome);
            clientes = query.getResultList();
            encerrar();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientes;
    }


}
