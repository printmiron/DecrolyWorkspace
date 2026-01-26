import { Router } from "express";
import {
    crearReserva,
    listarReservas,
    eliminarReserva,
} from "../controllers/reservaController";
import {
    validarCrearReserva,
    validarIdReservaParam,
} from "../validators/reservaValidators";
const router = Router();
// Endpoints del enunciado :contentReference[oaicite:8]{index=8}
router.post("/", validarCrearReserva, crearReserva);
router.get("/", listarReservas);
router.delete("/:id", validarIdReservaParam, eliminarReserva);
export default router;