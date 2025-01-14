// Este evento se dispara cuando todo el contenido HTML ha sido completamente cargado. 
// Todo el código dentro de esta función se ejecutará una vez que la página esté lista para ser manipulada.
document.addEventListener('DOMContentLoaded', function () {     

    // Aquí se obtienen referencias a varios elementos HTML utilizando sus identificadores. 
    // Estas referencias se utilizarán más adelante para interactuar con esos elementos.
    const cuerpo = document.getElementById('cuerpo');
    const botonRestaurar = document.getElementById('botonRestaurar');
    const enlacesMenu = document.querySelectorAll('nav a');
    const parrafoInteractivo = document.getElementById('parrafoInteractivo');
    const imagenInteractiva = document.getElementById('imagenInteractiva');


    // Estilos iniciales
    // Se definen dos objetos que contienen los estilos iniciales y actuales de la página. 
    // Estos estilos incluyen el color de fondo, el tamaño del texto y el tamaño de la imagen. 
    // El objeto estilosActuales se inicializa con los mismos valores que estilosIniciales.
    const estilosIniciales = {
        colorFondo: '#fff',
        tamanoTexto: '24px',
        tamanoImagen: '50%'
    };

    // Estilos actuales (inicialmente iguales a los iniciales)
    let estilosActuales = { ...estilosIniciales };

    // Dimensiones específicas para restaurar la imagen
    // Se definen las dimensiones específicas a las que se restaurará la imagen al hacer clic en el botón "Restaurar Estilos".
    const dimensionesRestaurar = {
        ancho: '580px',
        alto: '410px'
    };

    // Función para cambiar el estilo del cuerpo al hacer clic en el párrafo interactivo
    // Esta función cambia dinámicamente el color de fondo y el color del texto del cuerpo al hacer clic en el párrafo interactivo. 
    // Se selecciona un color aleatorio de la lista definida y se actualizan los estilos actuales.
    function cambiarEstilo() {
        const colores = ['#f39c12', '#e74c3c', '#27ae60', '#3498db'];
        const colorAleatorio = colores[Math.floor(Math.random() * colores.length)];
        cuerpo.style.backgroundColor = colorAleatorio;
        cuerpo.style.color = obtenerColorContraste(colorAleatorio);
        // Actualizar los estilos actuales
        estilosActuales.colorFondo = colorAleatorio;
        estilosActuales.colorTexto = obtenerColorContraste(colorAleatorio);
    }

    // Función para cambiar el tamaño del texto al hacer clic en los enlaces del menú
    // Esta función cambia el tamaño del texto del cuerpo cuando se hace clic en los enlaces del menú de navegación. 
    // El tamaño se obtiene del atributo data-tamano del enlace clicado y se actualizan los estilos actuales.
    function cambiarTamañoTexto() {
        const tamanoTexto = this.getAttribute('data-tamano');
        cuerpo.style.fontSize = tamanoTexto;
        // Actualizar los estilos actuales
        estilosActuales.tamanoTexto = tamanoTexto;
    }

    // Función para restaurar los estilos a los valores iniciales
    // Esta función restaura todos los estilos a sus valores iniciales. 
    // Restaura el fondo, el tamaño del texto y el color del cuerpo, y también restaura el tamaño y la rotación de la imagen. 
    // Finalmente, actualiza los estilos actuales.
    function restaurarEstilos() {
        cuerpo.style.backgroundColor = estilosIniciales.colorFondo;
        cuerpo.style.fontSize = estilosIniciales.tamanoTexto;
        cuerpo.style.color = estilosIniciales.colorTexto;

        // Restaurar el tamaño de la imagen y la rotación
        imagenInteractiva.style.width = dimensionesRestaurar.ancho;
        imagenInteractiva.style.height = dimensionesRestaurar.alto;
        imagenInteractiva.style.transform = 'rotate(0deg)';

        // Restaurar los estilos actuales
        estilosActuales = { ...estilosIniciales };
    }

    // Función para obtener un color de texto contrastante
    // Esta función toma un color en formato hexadecimal y devuelve un color de texto contrastante 
    // (negro o blanco) basado en la luminosidad del color de fondo.
    function obtenerColorContraste(color) {
        const luminosidad = (0.299 * parseInt(color.substring(1, 3), 16)) +
                            (0.587 * parseInt(color.substring(3, 5), 16)) +
                            (0.114 * parseInt(color.substring(5, 7), 16));

        return luminosidad > 128 ? '#000' : '#fff';
    }

    // Función para cambiar el estilo de la imagen al hacer clic
    function cambiarEstiloImagen() {
        // Reducir el tamaño y rotar
        // Esta función reduce el tamaño y rota la imagen al hacer clic en ella. 
        // Luego, actualiza los estilos actuales con el nuevo tamaño de la imagen.
        imagenInteractiva.style.width = estilosIniciales.tamanoImagen;
        imagenInteractiva.style.height = estilosIniciales.tamanoImagen;
        imagenInteractiva.style.transform = 'rotate(180deg)';
        // Actualizar los estilos actuales
        estilosActuales.tamanoImagen = estilosIniciales.tamanoImagen;
    }

    // Asociar funciones a eventos
    // Estas líneas de código asocian las funciones creadas anteriormente a eventos específicos. 
    //  Por ejemplo, la función cambiarEstilo se ejecuta cuando se hace clic en el párrafo interactivo, y así sucesivamente.
    parrafoInteractivo.addEventListener('click', cambiarEstilo);
    enlacesMenu.forEach(enlace => enlace.addEventListener('click', cambiarTamañoTexto));
    botonRestaurar.addEventListener('click', restaurarEstilos);
    imagenInteractiva.addEventListener('click', cambiarEstiloImagen);
});
