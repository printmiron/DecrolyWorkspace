//1
const Ejercicio1 = document.getElementById('ej1');

Ejercicio1.addEventListener('click', () => {

    let edad = (prompt('Introduce su edad'));
    if (edad >= 18) {
        texto1.textContent = 'Eres mayor de edad';
    }

});

//2
const Ejercicio2 = document.getElementById('ej2');


Ejercicio2.addEventListener('click', () => {
    let edad = (prompt('Introduce su edad'));
    if (edad >= 18) {
        texto2.textContent = "Eres mayor de edad";
    }else{
        texto2.textContent = "Eres menor de edad";
    }

});


//3
const Ejercicio3 = document.getElementById('ej3');

Ejercicio3.addEventListener('click', () => {

    let res = "";
    for (let i = 1; i < 20; i++) {
        res += i + " ";
    }
    texto3.textContent = res;

});

//4
const Ejercicio4 = document.getElementById('ej4');

Ejercicio4.addEventListener('click', () => {

    let res = "";
    for (let i = 2; i <= 200; i += 2) {
        res += i + " ";
    }

    texto4.textContent = res;

});

//5
const Ejercicio5 = document.getElementById('ej5');

Ejercicio5.addEventListener('click', () => {

    let res = "";
    for (let i = 1; i <= 200; i++) {
        if (i % 2 === 0) {
            res += i + " ";
        }
    }

    texto5.textContent = res;
    
});

//6
const Ejercicio6 = document.getElementById('ej6');

Ejercicio6.addEventListener('click', () => {

    let N = (prompt("Introduce un numero N"));
    let res = "";
    for (let i = 1; i <= N; i++) {
        res += i + " ";
    }

    texto6.textContent = res;
    
});

//7
const Ejercicio7 = document.getElementById('ej7');

Ejercicio7.addEventListener('click', () => {

    let calificacion = (prompt("Introduce la calificacion (0-10)"));
    let res = calificacion < 3 ? "muy deficiente" :
                calificacion < 5 ? "insuficiente" :
                calificacion < 6 ? "bien" :
                calificacion < 9 ? "notable" : "sobresaliente";
    
    texto7.textContent = res;
});

//8
const Ejercicio8 = document.getElementById('ej8');

Ejercicio8.addEventListener('click', () => {

    let num = (prompt("Introduce un numero para calcular su factorial"));
    let factorial = 1;
    for (let i = num; i > 1; i--) {
        factorial *= i;
    }

    texto8.textContent = "El factorial de " + num + " es " + factorial;
   
});

//9
const Ejercicio9 = document.getElementById('ej9');

Ejercicio9.addEventListener('click', () => {

    let horas = (prompt("Introduce las horas"));
    let minutos = (prompt("Introduce los minutos"));
    let segundos = (prompt("Introduce los segundos"));
    segundos++;
    if (segundos === 60) {
        segundos = 0;
        minutos++;
        if (minutos === 60) {
            minutos = 0;
            horas = (horas + 1) % 24;
        }
    }
    
    texto9.textContent = (`Hora despues de un segundo: ${horas}:${minutos}:${segundos}`);
    
});

//10
const Ejercicio10 = document.getElementById('ej10');

Ejercicio10.addEventListener('click', () => {

    let hayNegativo = false;
    for (let i = 0; i < 10; i++) {
        let numero = (prompt("Introduce un numero:"));
        if (numero < 0) {
            hayNegativo = true;
        }
    }
    
    texto10.textContent = (hayNegativo ? "Se introdujo al menos un numero negativo" : "No se introdujeron numeros negativos");
    
    
});


//11
const Ejercicio11 = document.getElementById('ej11');

Ejercicio11.addEventListener('click', () => {

    let positivos = 0, negativos = 0;
    for (let i = 0; i < 10; i++) {
        let numero = (prompt("Introduce un numero:"));
        if (numero > 0) 
            positivos++;
        else if (numero < 0) 
            negativos++;
    }

    texto11.textContent = (`Positivos: ${positivos}, Negativos: ${negativos}`);
});


//12
const Ejercicio12 = document.getElementById('ej12');

Ejercicio12.addEventListener('click', () => {

    let positivos = 0, negativos = 0;
    do {
        let numero = (prompt("Introduce numero (0 para terminar)"));
        if (numero > 0) positivos++;
        else if (numero < 0) negativos++;
        else break;
    } while (true);

        texto12.textContent = (`Positivos: ${positivos}, Negativos: ${negativos}`);
    

});

//13
const Ejercicio13 = document.getElementById('ej13');

Ejercicio13.addEventListener('click', () => {

    let suma = 0, producto = 1;
    for (let i = 1; i <= 10; i++) {
        suma += i;
        producto *= i;
    }

    texto13.textContent = (`Suma: ${suma}, Producto: ${producto}`);
    
    

});

//14
const Ejercicio14 = document.getElementById('ej14');

Ejercicio14.addEventListener('click', () => {

    let nombre = prompt("Introduce tu nombre:");
    let horasTrabajadas = (prompt("Introduce las horas trabajadas:"));
    let tarifaHora = (prompt("Introduce la tarifa por hora:"));
    
    let salarioBruto = horasTrabajadas <= 35 ? horasTrabajadas * tarifaHora : (35 * tarifaHora) + ((horasTrabajadas - 35) * tarifaHora * 1.5);
    let impuestos = salarioBruto > 900 ? (salarioBruto - 900) * 0.45 + 400 * 0.25 : salarioBruto > 500 ? (salarioBruto - 500) * 0.25 : 0;
    let salarioNeto = salarioBruto - impuestos;

    texto14.textContent = (`Nombre: ${nombre}, Salario Bruto: ${salarioBruto}, Impuestos: ${impuestos}, Salario Neto: ${salarioNeto}`);
    
    

});