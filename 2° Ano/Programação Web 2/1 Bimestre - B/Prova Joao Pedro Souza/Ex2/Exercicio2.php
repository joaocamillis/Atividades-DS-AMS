
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
<form method="POST" >
        <label for="mq">digite quantos metros quadrados da area a ser pintada:</label>  <br>
         <input type="number" name="mq" step=".01" id="mq" required>
 
         <input type="submit" name="enviar" value="calcular" id="">
     </form>
 

<?php

if ($_SERVER["REQUEST_METHOD"] == "POST") {
$mq = $_POST['mq'];
$lm = 18; 
$ga = 3.6;
   
$cbt = $mq / 6;

$qntlm = $cbt / $lm;
$qntga = $cbt / $ga;
$precolm = $qntlm * 80;
$precoga = $qntga * 25;


echo "Quantidade de tinta necessária: " . round($cbt, 2) . " litros <br>";
echo  "Se você comprar em latas de 18, tera que comprar: " .round ($qntlm,2) . " latas que saira por: " . round ($precolm,2) . "<br>" . 
"E se comprar em galões de 3,6 tera que comprar " . round($qntga,2) . " de galões que saira por: " . round ($precoga,2) ; 

if($cbt >= $lm){
echo" <br> Compre apenas latas de 18 litros";
}

if($cbt < $lm){
    echo" Compre apenas galões de 3,6 litros";
    }
    
}

else{
    echo "Preencha os campos";
}
    
    ?>
</body>
</html>