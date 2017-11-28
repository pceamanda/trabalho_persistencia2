package br.com.fiap;

import br.com.fiap.entity.Cliente;
import br.com.fiap.entity.Pedido;
import br.com.fiap.helper.ClienteHelper;
import br.com.fiap.helper.PedidoHelper;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class TestaAplicacao {

    static PedidoHelper pedidoHelper = new PedidoHelper();
    static ClienteHelper clienteHelper = new ClienteHelper();

    public static void main(String[] args) {

        incluirCliente("Amanda Prado");
        incluirCliente("Outro Cliente");

        List<Cliente> clientes = clienteHelper.listarCliente();

        incluirPedido(clientes.get(0));
        incluirPedido(clientes.get(0));
        incluirPedido(clientes.get(1));


        System.out.println("\n LISTANDO PEDIDOS CLIENTE: " + clientes.get(0).getNome());
        listarPedidosPorCliente(clientes.get(0));

        System.out.println("\n LISTANDO PEDIDOS CLIENTE: " + clientes.get(1).getNome());
        listarPedidosPorCliente(clientes.get(1));
    }
    private static void incluirCliente(String nome){
        Cliente cliente = new Cliente(nome, "pce.amanda@gmail.com", null);
        System.out.println(clienteHelper.salvar(cliente));
    }

    private static void incluirPedido(Cliente cliente){
            Pedido pedido = new Pedido();
            pedido.setData(LocalDate.now());
            pedido.setDescricao("Materiais Escritório");
            pedido.setValor(9000D);
            pedido.setCliente(cliente);

            System.out.println(pedidoHelper.salvar(pedido));
    }

    private static void listarPedidosPorCliente(Cliente cliente) {
        System.out.println(pedidoHelper.getPedidos(cliente.getId()).toString());
    }


}
