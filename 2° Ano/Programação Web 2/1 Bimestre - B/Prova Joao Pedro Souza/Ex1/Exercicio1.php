<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>

<form method="POST">

Nome do produto:<br><input type="text" name="n1" id="" required><br>
Valor do produto:  <br><input type="number" name="v1" step=".01" id="" required>
<br>
<input type="submit" name="enviar" value="enviar" id="">



</form>

<?php

if($_SERVER ["REQUEST_METHOD"] == "POST"){

$name = $_POST['n1'];
$v1 = $_POST['v1'];
 
$vd = 16 / 100;
$vp = $v1 * $vd;

$vpf = $v1 + $vp;
$vpp = $vpf / 10;

echo"O valor da parcela do produto " .$name." é de: " . $vpp . "<br>" .  "E o valor total da compra é de: ".$vpf;
}

else{

    echo"Preencha o formulario";

}
?>
    
</body>
</html>