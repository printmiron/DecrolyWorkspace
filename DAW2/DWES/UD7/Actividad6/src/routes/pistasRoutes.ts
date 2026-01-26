import { Router } from "express";
import {
    crearPista,
    listarPistas,
    obtenerPista,
    actualizarPista,
    eliminarPista,
} from "../controllers/pistasController";
import {
    validarCrearPista,
    validarActualizarPista,
    validarIdParam,
} from "../validators/pistaValidators";
const router = Router();
// Endpoints del enunciado :contentReference[oaicite:7]{index=7}
router.post("/", validarCrearPista, crearPista);
router.get("/", listarPistas);
router.get("/:id", validarIdParam, obtenerPista);
router.put("/:id", validarActualizarPista, actualizarPista);
router.delete("/:id", validarIdParam, eliminarPista);
export default router;
