import { Request, Response } from 'express';
import Villanos from '../models/villanos';

export const obtenerVillanos = async (req: Request, res: Response) => {
    const villanos = await Villanos.findAll();

    res.json(villanos);
};

export const obtenerVillanoPorId = async (req: Request, res: Response) => {
    const id = Number(req.params.id);

    try {
        const villano = await Villanos.findByPk(id);
        if (villano) {
            res.json(villano);
        }
    } catch (error) {
        res.status(404).json({
            msg: `No existe una villano con el id ${id}`
        });

    };
}

export const crearVillano = async (req: Request, res: Response) => {
    const { nombre, antiequipo_id } = req.body;
    const nuevoVillano = await Villanos.create({ nombre, antiequipo_id });

    res.status(201).json(nuevoVillano);
};

export const actualizarVillanoPorId = async (req: Request, res: Response) => {
    const id = Number(req.params.id);
    const { body } = req;

    const villano = await Villanos.findByPk(id);
    await villano?.update(body);

    res.json(villano);
}


export const eliminarVillanoPorId = async (req: Request, res: Response) => {
    const id = Number(req.params.id);

    const villano = await Villanos.findByPk(id);
    await villano?.destroy();
}


