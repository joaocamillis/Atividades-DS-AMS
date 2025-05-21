<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>numero impar ou par</title>
    <link rel="stylesheet" href="numeroParOuImpar.css">
</head>
<body class="b1">
    <?php
    $n1 = $_POST['n1'];


   
        if ($n1 % 2 == 0){
          if($n1 > 0 && $n1 != 0){
            echo "O numero é par";
          }

         
    }
  else{
            echo "O numero é impar";
          }

    ?>
    
</body>
</html>