package br.com.fiap.helper;

import br.com.fiap.config.HibernateUtil;
import br.com.fiap.entity.Pedido;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class PedidoHelper {

    Session session = null;
    Transaction transaction = null;

    public void encerrar(){
        session.close();
    }

    public String salvar(Pedido pedido){
        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(pedido);
            transaction.commit();

            encerrar();

            return "Pedido salvo";
        }catch(Exception e){
            return e.getMessage();
        }
    }

    public Pedido getPedido(Integer idPedido){

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Pedido pedido = session.load(Pedido.class, idPedido);

            encerrar();

            return pedido;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Pedido> getPedidos(){

        List<Pedido> pedidos = new ArrayList<>();

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            TypedQuery<Pedido> query = session.createQuery("from Pedido ", Pedido.class);

            pedidos = query.getResultList();

            encerrar();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return pedidos;
    }

    public List<Pedido> getPedidos(Integer idCliente){

        List<Pedido> pedidos = new ArrayList<>();

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            TypedQuery<Pedido> query = session.createQuery("from Pedido where cliente.id = :idCliente", Pedido.class);

            query.setParameter("idCliente", idCliente);
            pedidos = query.getResultList();

            for (Pedido pedido : pedidos) {
                Hibernate.initialize(pedido.getCliente());
            }

            encerrar();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return pedidos;
    }

}
