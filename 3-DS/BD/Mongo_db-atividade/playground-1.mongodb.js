//AULA 33 - CRUD NoSQL
//sistema devBurger

// Seleciona o banco de dados
use("devburger");

// CREATE - Inserindo um produto
db.produtos.insertOne({
    nome: "Bug-Buster Burger",
    ingredientes: [
        "Pão Australiano",
        "Hambúrguer 200g",
        "Queijo Cheddar",
        "Bacon",
        "Molho Especial"
    ],
    preco: 38.50,
    categoria: "Lanches",
    disponivel: true
});

// CREATE - Inserindo vários produtos
db.produtos.insertMany([
    {
        nome: "Stack Overflow Fries",
        ingredientes: [
            "Batata Frita",
            "Queijo Melted",
            "Crispy de Bacon"
        ],
        preco: 22.00,
        categoria: "Porções",
        disponivel: true
    },
    {
        nome: "NullPointer Refri",
        tipo: "Cola 350ml",
        preco: 8.00,
        categoria: "Bebidas",
        disponivel: true
    },
    {
        nome: "404 Not Found Salad",
        ingredientes: [
            "Alface",
            "Tomate",
            "Croutons",
            "Molho Caesar"
        ],
        preco: 18.00,
        categoria: "Saladas",
        disponivel: false
    }
]);

