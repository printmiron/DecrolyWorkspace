import { Router } from 'express';
import { actualizarPistaPorId, crearPista, eliminarPistaPorId, obtenerPistaPorId, obtenerPistas } from '../controllers/pistasController';

const router = Router();
router.post('/pistas', crearPista);
router.get('/pistas', obtenerPistas);
router.get('/pistas/:id', obtenerPistaPorId);
router.put('/pistas/:id', actualizarPistaPorId);
router.delete('/pistas/:id', eliminarPistaPorId);
export default router;