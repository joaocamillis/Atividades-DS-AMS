<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tabuada.php</title>
    <link rel="stylesheet" href="tabu.css">
</head>
<body class="b1">
    
    <?php
    
    $tabu= $_POST['txt'];
   $i=0;
    while($i<=10){
        $tab = $tabu * $i;
        echo $tabu . 'X' .$i. '=' .$tab. '<br/>';
        $i++;
    }
    
    ?>
</body>
</html>