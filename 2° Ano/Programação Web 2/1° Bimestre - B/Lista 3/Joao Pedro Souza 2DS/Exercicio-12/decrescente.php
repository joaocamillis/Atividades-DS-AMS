<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ordem Decrescente</title>
    <link rel="stylesheet" href="decrescente.css">
</head>
<body class="b1">
    <?php
    $n1 = $_POST['n1'];
    $n2 = $_POST['n2'];
    $imp = null;

   for ($i = $n1; $i >= $n2; $i--) {
        if ($i % 2 == 1){
         
      $imp .=" - ".$i;
}
   } 
   echo "os numeros impares entre " .$n1. " e " .$n2. " são: <br> " .$imp;
    ?>
    
</body>
</html>