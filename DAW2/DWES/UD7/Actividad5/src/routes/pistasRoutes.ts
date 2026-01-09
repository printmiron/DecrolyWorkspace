import { Router } from 'express';
import { actualizarPistaPorId, crearPista, eliminarPistaPorId, obtenerPistaPorId, obtenerPistas } from '../controllers/pistasController';

const router = Router();
router.post('/pistas', crearPista);
router.get('/pistas', obtenerPistas);
router.get('/pistas', obtenerPistaPorId);
router.put('/pistas', actualizarPistaPorId);
router.delete('/pistas', eliminarPistaPorId);
export default router;