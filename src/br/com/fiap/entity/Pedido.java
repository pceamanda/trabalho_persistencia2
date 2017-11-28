package br.com.fiap.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name="PEDIDO")
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PEDIDO")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CLIENTE")
    private Cliente cliente;

    @Column(name = "DATA")
    private LocalDate data;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name="VALOR")
    private Double valor;

    public Pedido(){

    }

    public Pedido(LocalDate data, String descricao, Double valor, Integer id, Cliente cliente) {
        super();
        this.data = data;
        this.descricao = descricao;
        this.valor = valor;
        this.id=id;
        this.cliente= cliente;
    }

    public Pedido(LocalDate data, String descricao, Double valor, Cliente cliente) {
        super();
        this.data = data;
        this.descricao = descricao;
        this.valor = valor;
        this.cliente = cliente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pedido pedido = (Pedido) o;

        if (id != null ? !id.equals(pedido.id) : pedido.id != null) return false;
        if (cliente != null ? !cliente.equals(pedido.cliente) : pedido.cliente != null) return false;
        if (data != null ? !data.equals(pedido.data) : pedido.data != null) return false;
        if (descricao != null ? !descricao.equals(pedido.descricao) : pedido.descricao != null) return false;
        return valor != null ? valor.equals(pedido.valor) : pedido.valor == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (cliente != null ? cliente.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        result = 31 * result + (valor != null ? valor.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", data=" + data +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                '}';
    }

}
