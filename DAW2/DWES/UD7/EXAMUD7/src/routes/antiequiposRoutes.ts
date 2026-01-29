import { Router } from 'express';
import { actualizarAntiequiposPorId, crearAntiequipo, eliminarAntiequiposPorId, obtenerAntiequipoPorId, obtenerAntiequipos } from '../controllers/antiequiposController';

const router = Router();
router.get('/antiequipos', obtenerAntiequipos);
router.get('/antiequipos/:id', obtenerAntiequipoPorId);
router.post('/antiequipos', crearAntiequipo);
router.put('/antiequipos/:id', actualizarAntiequiposPorId);
router.delete('/antiequipos/:id', eliminarAntiequiposPorId);

export default router;