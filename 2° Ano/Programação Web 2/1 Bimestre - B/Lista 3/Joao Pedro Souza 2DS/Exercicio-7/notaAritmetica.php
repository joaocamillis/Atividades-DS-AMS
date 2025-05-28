<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nota Aritmética</title>
    <link rel="stylesheet" href="notaAritmetica.css">
</head>
<body class="b1">
    <?php
    $n1 = $_POST['n1'];
    $n2 = $_POST['n2'];
    $n3 = $_POST['n3'];
    $n4 = $_POST['n4'];
    
    $MA = ($n1 + $n2 + $n3 + $n4) / 4;

   if ($MA >= 6) {
     echo"Aprovado!";
   }
   if ($MA < 3) {
    echo"Retido!";
  }

  elseif ($MA >= 3 && $MA < 6) {
     echo "Exame";
  }
   
  
    
    ?>
    
</body>
</html>