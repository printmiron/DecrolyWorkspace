import { Request, Response } from 'express';
import Antiequipos from '../models/antiequipos';


export const obtenerAntiequipos = async (req: Request, res: Response) => {
    const antiequipos = await Antiequipos.findAll();

    res.json(antiequipos);
};

export const obtenerAntiequipoPorId = async (req: Request, res: Response) => {
    const id = Number(req.params.id);

    try {
        const antiequipo = await Antiequipos.findByPk(id);
        if (antiequipo) {
            res.json(antiequipo);
        }
    } catch (error) {
        res.status(404).json({
            msg: `No existe una antiequipo con el id ${id}`
        });

    };
}

export const crearAntiequipo = async (req: Request, res: Response) => {
    const { nombre, sede } = req.body;
    const nuevoAntiequipo = await Antiequipos.create({ nombre, sede });

    res.status(201).json(nuevoAntiequipo);
};

export const actualizarAntiequiposPorId = async (req: Request, res: Response) => {
    const id = Number(req.params.id);
    const { body } = req;

    const antiequipo = await Antiequipos.findByPk(id);
    await antiequipo?.update(body);

    res.json(antiequipo);
}


export const eliminarAntiequiposPorId = async (req: Request, res: Response) => {
    const id = Number(req.params.id);

    const antiequipo = await Antiequipos.findByPk(id);
    await antiequipo?.destroy();
}


