<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> Pagina de dadosalunos.php</title>
</head>
<body>
    <?php
    echo"Segue abaixo as informações digitadas na pagina anterior" . '<br>';
    echo"Nome digitado...:".$_POST['txtnome']. '<br>'; 
    echo"Telefone...:".$_POST['txttelefone']. '<br>'; 
    echo"Curso...:".$_POST['cbocursos']. '<br>';     
    echo"RG...:".$_POST['txtRG']. '<br>';    
    echo"Modulo...".$_POST['txtmodulo']. '<br>';      
    ?>
    
</body>
</html>