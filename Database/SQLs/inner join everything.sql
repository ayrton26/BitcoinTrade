select cl.nome, c.descrição, t.valor, tt.descricao, t.data_hora  from bitcointrade.tb_transacao as t 
inner join bitcointrade.tb_tipo_transacao as tt
on t.tipo_transacao_id = tt.id
inner join bitcointrade.tb_carteira as c 
on t.carteira_id = c.id
inner join bitcointrade.tb_clientes as cl
on c.cliente_id = cl.id