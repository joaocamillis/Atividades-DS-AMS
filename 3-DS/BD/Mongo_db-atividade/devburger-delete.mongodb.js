// AULA 33 - CRUD NoSQL
//sistema DevBurger
//DELETE

use("devburger");

// 1. Excluir o produto Stack Overflow Fries

db.produtos.deleteOne({
    nome: "Stack Overflow Fries"
});

// 2. Excluir todos os produtos indisponíveis

db.produtos.deleteMany({
    disponivel: false
});