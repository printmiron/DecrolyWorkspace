import { Request, Response } from 'express';
import { Contact } from '../models/contactModel';
// Simulamos una base de datos en memoria (puedes sustituirlo por una base
//de datos real)
const contacts: Contact[] = [];
// Obtener todos los contactos
export const getContacts = (req: Request, res: Response) => {
    res.json(contacts);
};
// Agregar un nuevo contacto
export const addContact = (req: Request, res: Response) => {
    const { name, phone, email } = req.body;
    const newContact: Contact = { id: contacts.length + 1, name, phone, email };
    contacts.push(newContact);
    res.status(201).json(newContact);
};
// Actualizar un contacto
export const updateContact = (req: Request, res: Response) => {
    const contactId = parseInt(req.params.id);
    const { name, phone, email } = req.body;
    const contact = contacts.find(c => c.id === contactId);
    if (!contact) {
        return res.status(404).json({ message: 'Contacto no encontrado' });
    }

    contact.name = name || contact.name;
    contact.phone = phone || contact.phone;
    contact.email = email || contact.email;
    res.json(contact);
};
// Eliminar un contacto
export const deleteContact = (req: Request, res: Response) => {
    const contactId = parseInt(req.params.id);
    const index = contacts.findIndex(c => c.id === contactId);
    if (index === -1) {
        return res.status(404).json({ message: 'Contacto no encontrado' });
    }
    contacts.splice(index, 1);
    res.status(204).send();
};
