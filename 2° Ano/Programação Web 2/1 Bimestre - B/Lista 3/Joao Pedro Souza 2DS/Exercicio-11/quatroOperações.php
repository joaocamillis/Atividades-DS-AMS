<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Soma dos numeros impares</title>
    <link rel="stylesheet" href="quatroOperações.css">
</head>
<body class="b1">
    <?php
    $n1 = $_POST['n1'];
    $n2 = $_POST['n2'];
    $opera = $_POST['op'];
    $result = null;

    switch ($opera) {
        case 'sub':
           $result = $n1 - $n2;
            break;

            case 'add':
                $result = $n1 + $n2;
                break;

                case 'mult':
                    $result = $n1 * $n2;
                    break;

                    case 'div':
                       
                        if($n2 != 0){

                            $result = $n1 / $n2;
                        }

                        else{
                            echo "Erro: Divisão por zero não permitida! <br>";
                        }

                        break;

                        default:
                        $result = "Erro: operação não selecionada!";
    }

    echo "O resultado de acordo com a operação escolhida é: " .$result;

    ?>
    
</body>
</html>