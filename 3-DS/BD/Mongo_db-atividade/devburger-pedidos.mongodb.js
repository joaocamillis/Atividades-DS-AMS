// AULA 33 - CRUD NoSQL
// Sistema DevBurger
// Desafio Prático - Coleção Pedidos

use("devburger");

db.pedidos.find();

// CREATE - Inserir um novo pedido

/* db.pedidos.insertOne({
    nomeCliente: "João Pedro",
    valorTotal: 42.00,
    status: "Pendente"
}); */

/*
 UPDATE - Alterar status do pedido

db.pedidos.updateOne(
    {
        nomeCliente: "João Pedro",
        status: "Pendente"
    },
    {
        $set: { status: "Entregue" }
    }
); */


/*
 READ - Consultar pedidos acima de R$ 30,00

db.pedidos.find({
    valorTotal: { $gt: 30.00 }
}); */


/*
 DELETE - Excluir o pedido cadastrado

db.pedidos.deleteOne({
    nomeCliente: "João Pedro",
    status: "Entregue"
}); */