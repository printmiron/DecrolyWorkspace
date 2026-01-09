import { Request, Response } from 'express';
import Pista from '../models/pistas';

export const obtenerPistas = async (req: Request, res: Response) => {
    const pistas = await Pista.findAll();

    res.json(pistas);
};

export const crearPista = async (req: Request, res: Response) => {
    const { nombre, tipo, precio_hora } = req.body;
    const nuevoPista = await Pista.create({ nombre, tipo, precio_hora });

    res.status(201).json(nuevoPista);
};

export const obtenerPistaPorId = async (req: Request, res: Response) => {
    const { id } = req.params;
    const pista = await Pista.findByPk(id);

    if (pista) {
        res.json(pista);
    } else {
        res.status(404).json({
            msg: `No existe una pista con el id ${id}`
        });
    }
};

export const actualizarPistaPorId = async (req: Request, res: Response) => {
    const { id } = req.params;
    const { body } = req;

    const pista = await Pista.findByPk(id);
    await pista?.update(body);

    res.json(pista);
}

export const eliminarPistaPorId = async (req: Request, res: Response) => {
    const { id } = req.params;

    const pista = await Pista.findByPk(id);
    await pista?.destroy();
}

