<!DOCTYPE html>
<html>
<body>

<?php
// 1 hago un array con nombres
$nombres = ["Pavel", "Alex", "Ana", "Juan", "Pedro"];

// 2 funcion para contar todos los caracteres
function contarCaracteresTotales($nombres) {
    $total = 0;
    foreach ($nombres as $nombre) {
        $total += strlen($nombre);
    }
    return $total;
}

echo "Total de caracteres: " . contarCaracteresTotales($nombres) . "<br>";

// 3 funcion para contar cuantas veces aparece un caracter
function contarOcurrenciasDelCaracter($nombres, $cracter) {
    $total = 0;
    foreach ($nombres as $nombre) {
        $total += substr_count(strtolower($nombre), strtolower($cracter));
    }
    return $total;
}

$cracter = "a";
echo "El caracter '$cracter' aparece: " . contarOcurrenciasDelCaracter($nombres, $cracter) . "<br>";

// 4 funcion para contar vocales y consonantes
function contarVocalesYConsonantes($nombres) {
    $vocales = "aeiou";
    $v = 0;
    $c = 0;

    foreach ($nombres as $nombre) {
        $nombre = strtolower($nombre);
        for ($i = 0; $i < strlen($nombre); $i++) {
            $letra = $nombre[$i];
            if (ctype_alpha($letra)) {
                if (strpos($vocales, $letra) !== false) {
                    $v++;
                } else {
                    $c++;
                }
            }
        }
    }
    return ["vocales" => $v, "consonantes" => $c];
}

$res = contarVocalesYConsonantes($nombres);
echo "Vocales: " . $res["vocales"] . "<br>";
echo "Consonantes: " . $res["consonantes"] . "<br>";

// 5 funcion para encontrar nombre mas largo y mas corto
function encontrarNombreMasLargoYCorto($nombres) {
    $largo = $nombres[0];
    $corto = $nombres[0];

    foreach ($nombres as $nombre) {
        if (strlen($nombre) > strlen($largo)) {
            $largo = $nombre;
        }
        if (strlen($nombre) < strlen($corto)) {
            $corto = $nombre;
        }
    }
    return ["largo" => $largo, "corto" => $corto];
}

$res2 = encontrarNombreMasLargoYCorto($nombres);
echo "Nombre mas largo: " . $res2["largo"] . "<br>";
echo "Nombre mas corto: " . $res2["corto"] . "<br>";




?>

</body>
</html>