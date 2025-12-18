import { Router } from 'express';
import { listarPistas, agregarPista, reservarPista, cancelarReserva } from
    '../controllers/pistaController.ts';

export const router = Router();
router.get('/pistas', listarPistas);
router.post('/pistas/agregar', agregarPista);
router.post('/pistas/reservar/:id', reservarPista);
router.post('/pistas/cancelar/:id', cancelarReserva);