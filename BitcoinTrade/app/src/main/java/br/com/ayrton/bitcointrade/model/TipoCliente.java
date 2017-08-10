package br.com.ayrton.bitcointrade.model;

/**
 * Created by ayrton on 28/07/2017.
 */

public enum TipoCliente {
    VIP(1),
    PREMIUM(2),
    REGULAR(3);

    private int id;

    private TipoCliente (int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static TipoCliente mapping(int id){
        switch (id){
            case 1:
                return VIP;
            case 2:
                return PREMIUM;
            case 3:
                return REGULAR;
            default:
               return REGULAR;
        }
    }
}
