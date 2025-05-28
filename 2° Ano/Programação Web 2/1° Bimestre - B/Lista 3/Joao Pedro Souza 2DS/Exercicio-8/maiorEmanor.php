<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nota Aritmética</title>
    <link rel="stylesheet" href="maiorEmanor.css">
</head>
<body class="b1">
    <?php
    $n1 = $_POST['n1'];
    $n2 = $_POST['n2'];
    $n3 = $_POST['n3'];
    
// n1 é o maior numero e n3 o menor 
   if ($n1 >= $n2 && $n1 >= $n3 && $n2 >= $n3) {
    echo"O maior numero é " . $n1 . " e o menor numero é " .$n3;
   }
  
   // n1 é o maior numero e n2 o menor 
   if ($n1 >= $n2 && $n1 >= $n3 && $n3 >= $n2) {
    echo"O maior numero é " . $n1 . " e o menor numero é " .$n2;
   }
  
   // n2 é o maior numero e n1 o menor 
   if ($n2 >= $n1 && $n2 >= $n3 && $n3 >= $n1) {
    echo"O maior numero é " . $n2 . " e o menor numero é " .$n1;
   }

   // n2 é o maior numero e n3 o menor 
   if ($n2 >= $n1 && $n2 >= $n3 && $n1 >= $n3) {
    echo"O maior numero é " . $n2 . " e o menor numero é " .$n3;
   }

   // n3 é o maior numero e n2 o menor 
   if ($n3 >= $n1 && $n3 >= $n2 && $n1 >= $n2) {
    echo"O maior numero é " . $n3 . " e o menor numero é " .$n2;
   }

   // n3 é o maior numero e n1 o menor 
   if ($n3 >= $n1 && $n3 >= $n2 && $n2 >= $n1) {
    echo"O maior numero é " . $n3 . " e o menor numero é " .$n1;
   }
    
    ?>
    
</body>
</html>