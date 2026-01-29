import { Request, Response } from 'express';
import Equipos from '../models/equipos';

export const obtenerEquipos = async (req: Request, res: Response) => {
    const pistas = await Equipos.findAll();

    res.json(pistas);
};

export const obtenerEquipoPorId = async (req: Request, res: Response) => {
    const id = Number(req.params.id);

    try {
        
        const equipo = await Equipos.findByPk(id);
        if (equipo) {
            res.json(equipo);
        }
    } catch (error) {
        res.status(404).json({
            msg: `No existe una equipo con el id ${id}`
        });

    };
}

export const crearEquipo = async (req: Request, res: Response) => {
    const { nombre, sede } = req.body;
    const nuevoEquipo = await Equipos.create({ nombre, sede });

    res.status(201).json(nuevoEquipo);
};

export const actualizarEquiposPorId = async (req: Request, res: Response) => {
    const id = Number(req.params.id);
    const { body } = req;

    const equipo = await Equipos.findByPk(id);
    await equipo?.update(body);

    res.json(equipo);
}


export const eliminarEquipoPorId = async (req: Request, res: Response) => {
    const id = Number(req.params.id);

    const equipo = await Equipos.findByPk(id);
    await equipo?.destroy();
}




