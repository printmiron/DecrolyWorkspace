import express from "express";
import cors from "cors";
import dotenv from "dotenv";
import { testDbConnection } from "./config/database";
import pistasRoutes from "./routes/pistasRoutes";
import reservasRoutes from "./routes/reservasRoutes";
dotenv.config();
const app = express();
const port = process.env.PORT || 3000;
/**
* MIDDLEWARE 1: express.json()
* - Permite leer JSON en req.body
* - Imprescindible para POST/PUT con body
*/
app.use(express.json());
/**
* MIDDLEWARE 2: cors()
* - Cuando conectemos un front (React/Vue), el navegador bloquea si el origen es distinto.
* - Aquí permitimos SOLO el origen definido en FRONT_ORIGIN.
*/
app.use(
    cors({
        origin: process.env.FRONT_ORIGIN, // ej: http://localhost:5173
        methods: ["GET", "POST", "PUT", "DELETE"],
        allowedHeaders: ["Content-Type", "Authorization"],
    })
);
// Rutas
app.use("/pistas", pistasRoutes);
app.use("/reservas", reservasRoutes);
// 404 básico
app.use((_req, res) => {
    res.status(404).json({ message: "Ruta no encontrada" });
});
async function start() {
    await testDbConnection();
    app.listen(port, () => console.log(` API escuchando en puerto ${port}`));
}
start();