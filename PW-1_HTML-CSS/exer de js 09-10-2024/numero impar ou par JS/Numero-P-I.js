function verificarParImpar(){
    var numero = document.getElementById("numero").value;
    var resultado = document.getElementById("resultado");

    if(numero === ""){
        resultado.textContent = "por favor, insira um numero.";
        return;

    }

    numero = parseInt(numero);

    if(isNaN(numero)){
        resultado.textContent = "por favor, insira um numero valido.";
    }

    else if (numero % 2 === 0){
        resultado.textContent = "O numero " + numero + " é par.";
    }

    else{
        resultado.textContent = "O numero " + numero + " é impar.";
    }
}