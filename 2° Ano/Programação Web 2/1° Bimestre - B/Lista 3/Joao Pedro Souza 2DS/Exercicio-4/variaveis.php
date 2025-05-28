<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Troca Variaveis php</title>
    <link rel="stylesheet" href="variaveis.css">
</head>
<body class="b1">
    <?php
    $A = $_POST['A'];
    $B = $_POST['B'];
    $troca = $A;
    $A = $B;
    $B = $troca;

    echo "O valor de A antes da troca era: " . $B . " agora o valor de A é: " . $A . "<br>" . 
    "O valor de B antes da troca era: " . $A . " agora o valor de B é: " .$B ;
    
    ?>
    
</body>
</html>