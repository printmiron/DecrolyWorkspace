console.log('¡El archivo JavaScript está funcionando!');

const cambiarTexto = document.getElementById('boton1');
const cambiarFondo = document.getElementById('boton2');

cambiarTexto.addEventListener('click', () => {
    mensaje.textContent = 'Hola Mundo';
});

cambiarFondo.addEventListener('click', () => {
    document.body.style.backgroundColor = 'red';
});

