
let jugador1Pos = 1;
let jugador2Pos = 1;
let jugadorTurno = 1;


const numDadosElement = document.getElementById('numDados');
const botonTirarDados = document.getElementById('butonT');
const mensajeVictoria = document.getElementById('mensajeVictoria');
const mensajeElemento = document.getElementById('mensaje');
const reiniciarBoton = document.getElementById('reiniciarJuego');
const imgJugador1 = document.getElementById('img_jugador1');
const imgJugador2 = document.getElementById('img_jugador2');

function tirarDados() {
    const dado = Math.floor(Math.random() * 6) + 1;
    numDadosElement.textContent = `Número tirado: ${dado}`;

    if (jugadorTurno === 1) {
        jugador1Pos += dado;
        if (jugador1Pos > 39) jugador1Pos = 39;
        moverJugador(jugador1Pos, imgJugador1, 'jugador1');
        if (jugador1Pos === 39) mostrarVictoria('Jugador 1 ha ganado!');
        jugadorTurno = 2;
    } else {
        jugador2Pos += dado;
        if (jugador2Pos > 39) jugador2Pos = 39;
        moverJugador(jugador2Pos, imgJugador2, 'jugador2');
        if (jugador2Pos === 39) mostrarVictoria('Jugador 2 ha ganado!');
        jugadorTurno = 1;
    }
}


function moverJugador(pos, imagen, jugador) {

    document.querySelectorAll('.jugador1, .jugador2').forEach(item => item.classList.remove('jugador1', 'jugador2'));

    const casilla = document.querySelector(`.c${pos}`);

    casilla.appendChild(imagen);

    if (jugador === 'jugador1') {
        imagen.classList.add('jugador1');
    } else {
        imagen.classList.add('jugador2');
    }
}

function mostrarVictoria(mensaje) {
    mensajeElemento.textContent = mensaje;
    mensajeVictoria.style.display = 'block'; // Mostrar el mensaje de victoria
}


function reiniciarJuego() {
    jugador1Pos = 1;
    jugador2Pos = 1;
    jugadorTurno = 1;

    mensajeVictoria.style.display = 'none';

    document.querySelector('.c1').appendChild(imgJugador1);
    document.querySelector('.c1').appendChild(imgJugador2);

    numDadosElement.textContent = '';
}

botonTirarDados.addEventListener('click', tirarDados);
reiniciarBoton.addEventListener('click', reiniciarJuego);
