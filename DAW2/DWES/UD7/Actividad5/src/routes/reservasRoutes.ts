import { Router } from "express";
import { crearReservas, eliminarReservaPorId, obtenerReservasPorPista } from "../controllers/reservaController";

const router = Router();
router.post('/reservas', crearReservas);
router.get('/reservas', obtenerReservasPorPista);
router.delete('/reservas/:id', eliminarReservaPorId);
export default router;