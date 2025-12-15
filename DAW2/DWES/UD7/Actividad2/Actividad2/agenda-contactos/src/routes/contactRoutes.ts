import express from 'express';
import { getContacts, addContact, updateContact, deleteContact } from
    '../controllers/contactController';
const router = express.Router();
router.get('/', getContacts); // Obtener todos los contactos
router.post('/', addContact); // Agregar un nuevo contacto
router.put('/:id', updateContact); // Actualizar un contacto
router.delete('/:id', deleteContact); // Eliminar un contacto
export default router;