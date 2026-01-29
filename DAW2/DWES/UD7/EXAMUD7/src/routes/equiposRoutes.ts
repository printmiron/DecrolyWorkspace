import { Router } from 'express';
import { actualizarEquiposPorId, crearEquipo, eliminarEquipoPorId, obtenerEquipoPorId, obtenerEquipos } from '../controllers/equiposController';

const router = Router();
router.get('/equipos', obtenerEquipos);
router.get('/equipos/:id', obtenerEquipoPorId);
router.post('/equipos', crearEquipo);
router.put('/equipos/:id', actualizarEquiposPorId);
router.delete('/equipos/:id', eliminarEquipoPorId);

export default router;