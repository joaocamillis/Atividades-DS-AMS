// AULA 33 - CRUD NoSQL
//READ - DevBurger

use("devburger");

// Consultar todos os produtos cadastrados
 db.produtos.find();

// Consultar somente os lanches
// db.produtos.find({ categoria: "Lanches" });

// Consultar produtos com preço inferior a R$ 25
// db.produtos.find({ preco: { $lt: 25.00 } });


// Consultar produtos disponíveis
// Retornar apenas nome e preço
// * db.produtos.find(
//    { disponivel: true },
//    { nome: 1, preco: 1, _id: 0 }
// );



