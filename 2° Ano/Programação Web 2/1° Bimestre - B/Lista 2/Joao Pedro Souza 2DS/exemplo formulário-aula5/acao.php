<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Acao - Recebimento dos dados de um Formulario</title>
</head>
<body>
    
</body>
</html>

<?php
$Vnome=$_POST["nome"];
$Vidade=$_POST["idade"];

echo"Oi !! " . $Vnome . "." . "<br>" . "Você tem " . $Vidade . "anos !! ";

?>