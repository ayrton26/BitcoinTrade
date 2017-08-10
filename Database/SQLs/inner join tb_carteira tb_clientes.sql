select cli.nome, sum(c.saldo) from bitcointrade.tb_carteira as c inner join bitcointrade.tb_clientes as cli
on c.cliente_id = cli.id where cli.id = 1