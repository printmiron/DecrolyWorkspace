//
const Ejercicio1 = document.getElementById('ej1');

Ejercicio1.addEventListener('click', () => {

    let grados = (prompt("Introduce los grados celcios"));

    


    
    
});

const Ejercicio2 = document.getElementById('ej2');

Ejercicio2.addEventListener('click', () => {

    let N = (prompt("Introduce un numero N"));
    let res = "";
    for (let i = 1; i <= N; i++) {
        res += i + " ";
    }

    texto2.textContent = res;
    
});


const Ejercicio3 = document.getElementById('ej3');

Ejercicio3.addEventListener('click', () => {

    let num = (prompt("Introduce un numero para calcular su factorial"));
    let factorial = 1;
    for (let i = num; i > 1; i--) {
        factorial *= i;
    }

    texto3.textContent = "El factorial de " + num + " es " + factorial;
   
});

const Ejercicio4 = document.getElementById('ej4');

Ejercicio4.addEventListener('click', () => {

    let precio = (prompt("Introduce el precio del producto"));
    let cantidad = (prompt("Introduce su cantidad"));


    total = precio * cantidad

    if (precio > 100) {
        totalDescuento = precio * 0.10
    }
    
    descuento = totalDescuento * cantidad 

    texto4_1.textContent = "Total sin descuento: " + total ;
    texto4_2.textContent = "Total con descuento: " + descuento;
   
});

const Ejercicio5 = document.getElementById('ej5');

Ejercicio5.addEventListener('click', () => {

    let numIntroducido = (prompt("Introduce el numero"));

    if (numIntroducido == 17) {
        texto5.textContent = "Cierto" ;
    } else if (numIntroducido < 17) {
         texto5.textContent = "Mayor"
    } else if (numIntroducido > 17) {
        texto5.textContent = "Menor"
    }
   

   
});

const Ejercicio6 = document.getElementById('ej6');

Ejercicio6.addEventListener('click', () => {

   
    let sueldo = (prompt("Introduce el sueldo"));
    let ventas = (prompt("Introduce su ventas del mes"));

   if (ventas > 1000) {
        salario = sueldo / 0.10
   }

   texto6.textContent = "Sueldo del mes con 10% es " + salario;
   

});

const Ejercicio7 = document.getElementById('ej7');


const Ejercicio8 = document.getElementById('ej8');


