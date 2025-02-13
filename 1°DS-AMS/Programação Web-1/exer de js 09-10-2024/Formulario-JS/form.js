function calcularIdade() {
    const dataNascimento = new Date(document.getElementById("dataNascimento").value);
    const hoje = new Date();
    let idade = hoje.getFullYear() - dataNascimento.getFullYear();
    const mes = hoje.getMonth() - dataNascimento.getMonth();
    
    if (mes < 0 || (mes === 0 && hoje.getDate() < dataNascimento.getDate())) {
        idade--;
    }
    
    document.getElementById("resultado").innerText = `Você tem ${idade} anos.`;
}