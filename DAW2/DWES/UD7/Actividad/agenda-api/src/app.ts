import express, { Request, Response } from "express";
import { contactsRouter } from "./routes/contacts.routes";

const app = express();

// Middleware para poder leer JSON en requests (hoy no lo usamos mucho, pero es estándar)
app.use(express.json());

// Endpoint de salud: útil para comprobar que el servidor vive
app.get("/health", (_req: Request, res: Response) => {
    res.json({ ok: true, status: "up" });
});

// Montamos el router: todas las rutas de contactos cuelgan de /contacts
app.use("/contacts", contactsRouter);

const PORT = Number(process.env.PORT ?? 3000);

app.listen(PORT, () => {
    console.log(`Agenda API escuchando en http://localhost:${PORT}`);
});