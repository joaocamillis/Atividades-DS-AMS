<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Salario liquido</title>
    <link rel="stylesheet" href="salario.css">
</head>
<body class="b1">
    <?php
    $sb = $_POST['sal'];
    
    $slG = $sb * 0.10;
    $im = $sb * 0.20;
    $liq = ($sb + $slG) - $im;

    echo "O seu salario liquido é de R$ : " .$liq;

   
    
    ?>
    
</body>
</html>