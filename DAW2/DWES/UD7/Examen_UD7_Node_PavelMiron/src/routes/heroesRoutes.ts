import { Router } from 'express';
import { actualizarHeroePorId, crearHeroe, eliminarHeroePorId, obtenerHeroePorId, obtenerHeroes, obtenerHeroesPorEquipo } from '../controllers/heroesController';


const router = Router();
router.get('/heroes', obtenerHeroes);
router.get('/heroes/:id', obtenerHeroePorId);
router.post('/heroes', crearHeroe);
router.put('/heroes/:id', actualizarHeroePorId);
router.delete('/heroes/:id', eliminarHeroePorId);
router.get('/heroes/equipo_id/:id', obtenerHeroesPorEquipo);

export default router;