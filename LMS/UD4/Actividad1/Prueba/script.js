document.addEventListener('DOMContentLoaded', function () {
    // Cambiar tamano de texto en toda la pagina
    const enlacesTamano = document.querySelectorAll('nav a');
    const contenedor = document.getElementById('contenedor');

    enlacesTamano.forEach(enlace => {
        enlace.addEventListener('click', function (event) {
            event.preventDefault();
            const tamano = enlace.getAttribute('data-tamano');
            contenedor.style.fontSize = tamano;
        });
    });

    // Cambiar fondo del main al pulsar el botón
    const botonInteractivo = document.getElementById('botonInteractivo');
    const main = document.querySelector('main');

    botonInteractivo.addEventListener('click', function () {
        main.style.backgroundColor = '#d3f4ff'; // Cambia el color de fondo
    });

    // efecto de ampliacion y giro al pulsar imágenes
    const imagenesInteractivas = document.querySelectorAll('#imagenInteractiva');

    imagenesInteractivas.forEach(imagen => {
        imagen.addEventListener('click', function () {
            imagen.style.transition = 'transform 1s ease';
            imagen.style.transform = 'scale(1.2) rotate(360deg)';
            setTimeout(() => {
                imagen.style.transform = 'scale(1)'; // Restaurar tamaño original
            }, 1000);
        });
    });

    // restaurar estilos al estado inicial
    const botonRestaurar = document.getElementById('botonRestaurar');

    botonRestaurar.addEventListener('click', function () {
        // Restaurar tamaño de fuente
        contenedor.style.fontSize = '16px';

        // Restaurar fondo del main
        main.style.backgroundColor = '#ffffff51';

        // Restaurar imágenes (opcional, por si quedan efectos activos)
        imagenesInteractivas.forEach(imagen => {
            imagen.style.transform = 'scale(1)';
        });
    });
});
