// script.js
function appendToDisplay(value) {
    document.getElementById("display").value += value;
}

function clearDisplay() {
    document.getElementById("display").value = "";
}

function operate(operator) {
    document.getElementById("display").value += ` ${operator} `;
}

function calculate() {
    try {
        let display = document.getElementById("display").value;
        let result = eval(display);
        document.getElementById("display").value = result;
    } catch (error) {
        document.getElementById("display").value = "Erro";
    }
}
