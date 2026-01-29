import { Router } from "express";
import { createUsuario, getUsuarioById, getUsuarios } from "../controllers/usuariosController";

const router = Router();
router.post('/usuarios', createUsuario);
router.get('/usuarios', getUsuarios);
router.get('/usuarios/:id', getUsuarioById);
export default router;