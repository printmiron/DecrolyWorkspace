

document.addEventListener("DOMContentLoaded", function () {

const cantidad = document.getElementById("cant");
const butonMas = document.getElementById("mas-cant");
const butonMenos = document.getElementById("menos-cant");

const price = document.getElementById("price");
const priceTotalProd = document.getElementById("price-total-prod");

var value = cantidad.value;
var value = price.textContent;

butonMas.addEventListener("click", function(){ 
    cantidad.value = Number(cantidad.value) + 1;
    priceTotalProd = Number(price.textContent) * Number(cantidad.value);
});

butonMenos.addEventListener("click", function(){ 
    if(cantidad.value > 0){
        cantidad.value = Number(cantidad.value) - 1;
    }
});



});

 

