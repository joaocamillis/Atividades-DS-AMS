<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
<form method="POST">
        Digite a distancia total percorrida (em Km)<br><input type="number" name="km" step=".01" id="" required> <br>
        quantidade de combustivel consumida para percorre-la (em litros) <br> <input type="number" name="li" step=".01" id="" required>

        <input type="submit" name="enviar" value="enviar" id="">
    </form>

    <?php
    if($_SERVER["REQUEST_METHOD"] == "POST"){
    $km = $_POST ['km'];
    $li = $_POST ['li'];

    $cm = $km / $li;

    echo"O consumo médio de combustivel é de: " .$cm;
    }
    else{
        echo"preencha os campos do formulario";
    }
    ?>
    
</body>
</html>