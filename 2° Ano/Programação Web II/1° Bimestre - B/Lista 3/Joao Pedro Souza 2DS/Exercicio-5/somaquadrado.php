<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>soma dos quadrados</title>
    <link rel="stylesheet" href="somaquadrado.css">
</head>
<body class="b1">
    <?php
    $v1 = $_POST['num1'];
    $v2 = $_POST['num2'];
    $v3 = $_POST['num3'];
    
    $resut = pow($v1, 2) +  pow($v2, 2) + pow($v3, 2);

    echo "O resultado é: " .$resut;

   
    
    ?>
    
</body>
</html>