import { Request, Response } from 'express';
import Usuario from '../models/usuarios';

export const getUsuarios = async (req: Request, res: Response) => {
    try {
        const usuarios = await Usuario.findAll();
        res.json(usuarios);
    } catch (error) {
        res.status(500).json({ message: 'Error al obtener usuarios', error });
    }
};

export const createUsuario = async (req: Request, res: Response) => {
   
        const { nombre, email, telefono } = req.body;
        const nuevoUsuario = await Usuario.create({ nombre, email, telefono });

        res.status(201).json(nuevoUsuario);
    
};


export const getUsuarioById = async (req: Request, res: Response) => {
    try {
        const usuario = await Usuario.findByPk(req.params.id);
        if (usuario) {
            res.json(usuario);
        } else {
            res.status(404).json({ message: 'Usuario no encontrado' });
        }
    } catch (error) {
        res.status(500).json({ message: 'Error en el servidor', error });
    }
};