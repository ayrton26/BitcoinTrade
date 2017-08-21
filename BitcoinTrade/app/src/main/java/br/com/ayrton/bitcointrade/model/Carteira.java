package br.com.ayrton.bitcointrade.model;

/**
 * Created by ayrton on 28/07/2017.
 */

public class Carteira {
    private int id;
    private String descricao;
    private double saldo;
    private Cliente cliente;

    public Carteira(int id, String descricao, double saldo, Cliente cliente) {
        this.id = id;
        this.descricao = descricao;
        this.saldo = saldo;
        this.cliente = cliente;
    }

    public Carteira(int id, String descricao, Cliente cliente) {
        this(id, descricao, 0, cliente);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
