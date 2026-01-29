import { Router } from 'express';
import { actualizarVillanoPorId, crearVillano, eliminarVillanoPorId, obtenerVillanoPorId, obtenerVillanos } from '../controllers/villanosController';



const router = Router();
router.get('/villanos', obtenerVillanos);
router.get('/villanos/:id', obtenerVillanoPorId);
router.post('/villanos', crearVillano);
router.put('/villanos/:id', actualizarVillanoPorId);
router.delete('/villanos/:id', eliminarVillanoPorId);

export default router;