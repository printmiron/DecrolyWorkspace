import { Request, Response } from 'express';
import Usuario from '../models/usuario';
export const obtenerUsuarios = async (req: Request, res: Response) => {
    const usuarios = await Usuario.findAll();
    res.json(usuarios);
};
export const crearUsuario = async (req: Request, res: Response) => {

    const { nombre, email } = req.body;
    const nuevoUsuario = await Usuario.create({ nombre, email });
    res.status(201).json(nuevoUsuario);
};
