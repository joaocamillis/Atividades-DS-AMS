<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Desconto do produto</title>
    <link rel="stylesheet" href="desconto.css">
</head>
<body  class="b1">
    <?php
    $prod = $_POST['num1'];
    $desc = $_POST['num2'];

    $valorD = $desc / 100;
    $valorPD = $prod * $valorD;
    
    echo"O valor do produto com desconto é: " .$valorPD;
    ?>
    
</body>
</html>