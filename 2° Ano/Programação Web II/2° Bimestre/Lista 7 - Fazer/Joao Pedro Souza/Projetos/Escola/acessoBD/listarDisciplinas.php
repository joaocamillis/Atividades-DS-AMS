<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Listar</title>
    <link rel="stylesheet" href="listar.css">
</head>

<body>

    <section>

        <?php

        include_once 'escola.php';
        $p = new Escola();
        $pro_bd = $p->listar();

        ?>


<table>
    <thead>
        <tr>
            <th>codDisciplina</th>
            <th>NomeDisciplina</th>

        </tr>
    </thead>
    <tbody>
        <?php
        foreach ($pro_bd as $pro_mostrar) {
        ?>
            <tr>
                <td><?php echo $pro_mostrar[0]; ?></td>
                <td><?php echo $pro_mostrar[1]; ?></td>
            </tr>
        <?php } ?>
    </tbody>
</table>





    <?php

            echo "<br><br><a href = '../menu.html'><button> Voltar </button> </a> "; ?>

    </section>
</body>

</html>