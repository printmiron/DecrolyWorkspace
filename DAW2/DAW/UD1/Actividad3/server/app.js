import express from "express";
import bodyParser from "body-parser";

const app = express();
const port = 3000;

app.use(bodyParser.urlencoded({ extended: true }));

app.post("/inscribirse", (req, res) => {
  const { nombre, email, curso } = req.body;
  console.log(`Nueva inscripción: ${nombre}, ${email}, ${curso}`);
  res.send(`<h1>Gracias ${nombre}</h1><p>Te has inscrito al curso de ${curso}. Te contactaremos a ${email}</p>`);
});

app.listen(port, () => {
  console.log(`Servidor Node.js corriendo en http://localhost:${port}`);
});
