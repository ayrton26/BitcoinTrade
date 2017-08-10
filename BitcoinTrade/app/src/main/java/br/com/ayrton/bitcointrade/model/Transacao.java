package br.com.ayrton.bitcointrade.model;

import java.util.Date;

/**
 * Created by ayrton on 28/07/2017.
 */

public class Transacao {
    private int id;
    private double valor;
    private Date dataHora;
    private Carteira carteira;
    private TipoTransacao tipo;

    public Transacao(int id, double valor, Date dataHora, Carteira carteira, TipoTransacao tipo) {
        this.id = id;
        this.valor = valor;
        this.dataHora = dataHora;
        this.carteira = carteira;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public Carteira getCarteira() {
        return carteira;
    }

    public void setCarteira(Carteira carteira) {
        this.carteira = carteira;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransacao tipo) {
        this.tipo = tipo;
    }
}
