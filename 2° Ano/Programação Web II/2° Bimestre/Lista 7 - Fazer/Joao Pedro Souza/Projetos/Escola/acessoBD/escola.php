<?php
include_once 'conectar.php';

class escola
{
    private $mat;
    private $nome;
    private $endereco;
    private $cidade;
    private $codcurso;
    private $conn;



    public function getMat() {
     return $this->mat;
    }
    public function setMat($imat) {
     return $this->mat = $imat;
    }
    public function getNome() {
     return $this->nome;
    }
    public function setNome($name) {
     return $this->nome = $name;
    }
    public function getEndereco() {
     return $this->endereco;
    }
    public function setEndereco($endereco) {
     return $this->endereco = $endereco;
    }
    public function getCidade() {
     return $this->cidade;
    }
    public function setCidade($cidade) {
     return $this->cidade = $cidade;
    }
    public function getCodcurso() {
     return $this->codcurso;
    }
    public function setCodcurso($codcurso) {
     return $this->codcurso = $codcurso;
    }
    
    function listar()
    {
        try{
            $this-> conn = new Conectar();
            $sql = $this -> conn -> query("select * from produtos order by matricula");
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