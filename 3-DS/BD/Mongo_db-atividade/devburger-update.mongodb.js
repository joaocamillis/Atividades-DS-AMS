// AULA 33 - CRUD NoSQL
//Sistema devburger
//UPDATE

use("devburger");

// 1. Atualizar o preço do hambúrguer
// Adicionar cebola caramelizada aos ingredientes

db.produtos.updateOne(
    { nome: "Bug-Buster Burger" },
    {
        $inc: { preco: 3.50 },
        $addToSet: {
            ingredientes: "Cebola Caramelizada"
        }
    }
);

// 2. Tornar a salada disponível

db.produtos.updateOne(
    { nome: "404 Not Found Salad" },
    {
        $set: { disponivel: true }
    }
);

// 3. Aplicar desconto de 10% nas bebidas

db.produtos.updateMany(
    { categoria: "Bebidas" },
    {
        $mul: { preco: 0.90 }
    }
);