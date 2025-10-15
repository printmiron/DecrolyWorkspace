<!DOCTYPE html>
<html>

<body>

    <?php

    echo "Hola, esto es el juego de piedra, papel o tijera, entre dos jugadores.<br>";
    echo "Van a ser 10 rondas donde cada jugador de manera aleatoria elegirá una de las tres opciones, y al final se suman quien ha ganado mas rondas.<br><br>";
    $opciones = [piedra, papel, tijera];

    $resultados = [
        "Jugador 1" => 0,
        "Jugador 2" => 0,
        "Empates" => 0
    ];

    $partidas = 10;

    for ($i = 1; $i <= $partidas; $i++) {

        // eleccion aleatoria para cada jugador
        $jugador1 = $opciones[array_rand($opciones)];
        $jugador2 = $opciones[array_rand($opciones)];

        // mostrar elecciones de los jugadores en cada ronda
        echo "Ronda $i: <br>";
        echo "Jugador 1: $jugador1 <br>";
        echo "Jugador 2: $jugador2 <br>";

        // determinar el ganador de la ronda
        if ($jugador1 == $jugador2) {
            echo "Empate!<br><br>";
            $resultados["Empates"]++;
        } else if (
            $jugador1 == "piedra" && $jugador2 == "tijera" ||
            $jugador1 == "papel" && $jugador2 == "piedra" ||
            $jugador1 == "tijera" && $jugador2 == "papel"
        ) {
            echo "Jugador 1 gana la ronda!<br><br>";
            $resultados["Jugador 1"]++;
        } else {
            echo "Jugador 2 gana la ronda!<br><br>";
            $resultados["Jugador 2"]++;

        }
    }

    echo "Resultados jugador1: " . $resultados["Jugador 1"] . ".<br>";
    echo "Resultados jugador2: " . $resultados["Jugador 2"] . ".<br>.<br>";

    // mostrar resultados finales
    if ($resultados["Jugador 1"] > $resultados["Jugador 2"]) {
        echo "El ganador final es el Jugador 1 .<br>";
    } else if ($resultados["Jugador 2"] > $resultados["Jugador 1"]) {
        echo "El ganador final es el Jugador 2 .<br>";
    } else {
        echo "El juego termina en empate .<br>";

    }



    ?>

</body>

</html>