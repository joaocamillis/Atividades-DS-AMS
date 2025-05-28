<?php
include_once 'conectar.php';

class Produto
{
    private $id;
    private $nome;
    private $estoque;
    private $conn;



    public function getId() {
     return $this->id;
    }
    public function setId($iid) {
     return $this->id = $iid;
    }
    public function getNome() {
     return $this->nome;
    }
    public function setNome($name) {
     return $this->nome = $name;
    }
    public function getEstoque() {
     return $this->estoque;
    }
    public function setEstoque($estoqui) {
     return $this->estoque = $estoqui;
    }

    function listar()
    {
        try{
            $this-> conn = new Conectar();
            $sql = $this -> conn -> query("select * from produtos order by nome");
            $sql -> execute();
            return $sql -> fetchAll();
            $this -> conn = null;
        }
        catch(PDOException $exc){
         echo "Erro ao executar consulta. " . $exc->getMessage();
        }
    }
}
?>