//1
const Ejercicio1 = document.getElementById('ej1');

Ejercicio1.addEventListener('click', () => {
    texto1.textContent = 'Buenos Dias';
});

//2
const Ejercicio2 = document.getElementById('ej2');

let lado = 7;

let area = lado * lado;

Ejercicio2.addEventListener('click', () => {
    texto2.textContent = area;
});


//3
const Ejercicio3 = document.getElementById('ej3');

Ejercicio3.addEventListener('click', () => {

    let lado1 = (prompt('Introduce el valor del lado'));

    let area1 = lado1 * lado1;

    texto3.textContent = area1;
});

//4
const Ejercicio4 = document.getElementById('ej4');

Ejercicio4.addEventListener('click', () => {

    let num1 = (prompt('Introduce primer valor'));
    let num2 = (prompt('Introduce segundo valor valor'));

    let suma = num1 + num2;
    let resta = num1 - num2;
    let producto = num1 * num2;
    let division = num1 / num2;

    texto4.textContent = 'Suma = ' + suma +
                         ' Resta = ' + resta +
                         ' Producto = ' + producto +
                         ' Division = ' + division;
});

//5
const Ejercicio5 = document.getElementById('ej5');

Ejercicio5.addEventListener('click', () => {

    function calcularMedias(radio){

        const pi = Math.PI;

        const longitudCircunferencia = 2 * pi * radio;

        const areaCirculo = pi * Math.pow(radio, 2);

        const volumenEsfera = (4 / 3) * pi * Math.pow(radio, 3);

        texto5.textContent = 'Longitud Circunferencia = ' + longitudCircunferencia +
                             ' Area Circulo = ' + areaCirculo +
                             ' Volumen Esfera = ' + volumenEsfera;
    }

    let radio = (prompt('Introduce el radio'));

    calcularMedias(radio);

});

//6
const Ejercicio6 = document.getElementById('ej6');

Ejercicio6.addEventListener('click', () => {

    function calcularDescuento(pOriginal, pVenta){
        
        const descuento = ((pOriginal - pVenta) / pOriginal) * 100;

        texto6.textContent = 'El procentaje de descuento es = ' + descuento;

    }

    let pOriginal = (prompt('Introduce el precio original'));
    let pVenta = (prompt('Introduce el precio de venta'));

    calcularDescuento(pOriginal, pVenta);

});

//7
const Ejercicio7 = document.getElementById('ej7');

Ejercicio7.addEventListener('click', () => {

    function convertirMetros(millas){
       
    const metroPorMilla = 1852;

    const distanciaEnMetros = millas * metroPorMilla;
        
    texto7.textContent = millas + ' millas marinas son equivalentes a ' + distanciaEnMetros + ' metros';

    }

    let millas = (prompt('Cuantos millas quieres convertir en metros'));

    convertirMetros(millas);

});

//8
const Ejercicio8 = document.getElementById('ej8');

Ejercicio8.addEventListener('click', () => {

    let num1 = (prompt('Introduce numero 1'));
    let num2 = (prompt('Introduce numero 2'));

    if (num1 < num2) {
        texto8.textContent = 'El orden ascendente es: ' + num1 + '-' + num2;
    }else{
        texto8.textContent = 'El orden ascendente es: ' + num2 + '-' + num1;
    }
});

//9
const Ejercicio9 = document.getElementById('ej9');

Ejercicio9.addEventListener('click', () => {

    let num1 = (prompt('Introduce numero 1'));
    let num2 = (prompt('Introduce numero 2'));

    if (num1 > num2) {
        texto9.textContent = ("El numero mayor es: " + num1);
    } else if (num1 < num2) {
        texto9.textContent = ("El numero mayor es: " + num2);
    } else {
        texto9.textContent = ("Los dos numeros son iguales");
    }
});

//10
const Ejercicio10 = document.getElementById('ej10');

Ejercicio10.addEventListener('click', () => {

    let num1 = (prompt('Introduce numero 1'));
    let num2 = (prompt('Introduce numero 2'));
    let num3 = (prompt('Introduce numero 3'));

    if (num1 > num2 && num1 > num3) {
        texto10.textContent = ("El numero mayor es: " + num1);
    } else if (num2 > num1 && num2 > num3) {
        texto10.textContent = ("El numero mayor es: " + num2);
    } else {
        texto10.textContent = ("El numero mayor es: " + num3);
    }
    
});


//11
const Ejercicio11 = document.getElementById('ej11');

Ejercicio11.addEventListener('click', () => {

    let num1 = (prompt('Introduce primer valor'));
    let num2 = (prompt('Introduce segundo valor valor'));

    let suma = num1 + num2;
    let resta = num1 - num2;
    let producto = num1 * num2;
    let division = num1 / num2;

    if (num2 !== 0) {
        division = num1 / num2;
        texto11.textContent = ("La division es: " + division);
    } else {
        texto11.textContent = ('No se puede dividir por cero');
    }

    texto11.textContent = 'Suma = ' + suma +
                         ' Resta = ' + resta +
                         ' Producto = ' + producto +
                         ' Division = ' + division;
});


//12
const Ejercicio12 = document.getElementById('ej12');

Ejercicio12.addEventListener('click', () => {

    let num1 = (prompt('Introduce primer valor'));
    let num2 = (prompt('Introduce segundo valor valor'));

    if (num1 > num2) {
        texto12.textContent = ("El numero mayor es: " + num1);
    } else if (num1 < num2) {
        texto12.textContent = ("El numero mayor es: " + num2);
    }

});

//13
const Ejercicio13 = document.getElementById('ej13');

Ejercicio13.addEventListener('click', () => {

    let num = (prompt('Introduce un valor'));
    
    if (num < 0) {
        texto13.textContent = ("El numero es negativo");
    }else if (num >= 0){
        texto13.textContent = ("El numero es positivo");
    }
    

});