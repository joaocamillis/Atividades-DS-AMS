<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Media do aluno</title>
    <link rel="stylesheet" href="nota.css">
</head>
<body class="b1">
    <?php
    $n1 = $_POST['n1'];
    $n2 = $_POST['n2'];
    $n3 = $_POST['n3'];
    $n4 = $_POST['n4'];
    
    
        $mediaF = ($n1 + $n2 + $n3 + $n4) / 4; 

   if ($mediaF > 5) {
    # code...
    echo"media do aluno: ".$mediaF;
    echo"O aluno foi aprovado!!!";
   }

   else{
    echo"O aluno foi reprovado!";
   }
    ?>
</body>
</html>