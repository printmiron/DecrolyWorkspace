import express from "express";
import bodyParser from "body-parser";

const app = express();
const port = 3000;

app.use(bodyParser.urlencoded({ extended: true }));

app.get("/", (req, res) => {
  res.send("<h1>Servidor Node.js activo</h1><p>Usa el formulario en Apache para inscribirte.</p>");
});

// Ruta que recibe el formulario
app.post("/inscribirse", (req, res) => {
  const { nombre, email, curso } = req.body;
  console.log("Nueva inscripcion recibida:");
  console.log(`Nombre: ${nombre}`);
  console.log(`Email: ${email}`);
  console.log(`Curso: ${curso}`);

  res.send(`
    <h1>Gracias ${nombre}!</h1>
    <p>Te has inscrito al curso de ${curso}.</p>
    <p>Te contactaremos en ${email}.</p>
    <a href="http://localhost/web/index.html">Volver al inicio</a>
  `);
});

app.listen(port, () => {
  console.log(`Servidor Node.js corriendo en http://localhost:${port}`);
});

