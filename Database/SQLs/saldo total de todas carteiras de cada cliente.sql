select cli.id, cli.nome, (select sum(ca.saldo)
from bitcointrade.tb_carteira as ca inner join bitcointrade.tb_clientes as cl
    on ca.cliente_id = cl.id
    where cl.id = cli.id
) from bitcointrade.tb_clientes as cli