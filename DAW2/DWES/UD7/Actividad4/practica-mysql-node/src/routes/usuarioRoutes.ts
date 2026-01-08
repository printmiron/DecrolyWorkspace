import { Router } from 'express';
import { obtenerUsuarios, crearUsuario } from '../controllers/usuarioController';
const router = Router();
router.get('/usuarios', obtenerUsuarios);
router.post('/usuarios', crearUsuario);
export default router;