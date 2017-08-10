package br.com.ayrton.bitcointrade.model;

/**
 * Created by ayrton on 28/07/2017.
 */

public class Cliente {
    private int id;
    private String nome;
    private String email;
    private String telefone;
    private TipoCliente tipo;

    public Cliente(int id, String nome, String email, String telefone, TipoCliente tipo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.tipo = tipo;
    }

    public Cliente(String nome, String email, String telefone, TipoCliente tipo) {
        this(0, nome, email, telefone, tipo);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public TipoCliente getTipo() {
        return tipo;
    }

    public void setTipo(TipoCliente tipo) {
        this.tipo = tipo;
    }
}
