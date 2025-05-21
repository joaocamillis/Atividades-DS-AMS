<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Soma dos numeros impares</title>
    <link rel="stylesheet" href="somaImpares.css">
</head>
<body class="b1">
    <?php
    $n1 = $_POST['n1'];
    $n2 = $_POST['n2'];
    $somaimpares = 0;

    for ($i = $n1; $i <= $n2; $i++){
        if ($i % 2 == 1){
            $somaimpares += $i;

    }

}   
   echo "O resultado da soma dos numeros impares é: " . $somaimpares;

    ?>
    
</body>
</html>