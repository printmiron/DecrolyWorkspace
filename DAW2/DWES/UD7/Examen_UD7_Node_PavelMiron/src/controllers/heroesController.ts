import { Request, Response } from 'express';
import Heroes from '../models/heroes';

export const obtenerHeroes = async (req: Request, res: Response) => {
    const heroe = await Heroes.findAll();

    res.json(heroe);
};

export const obtenerHeroePorId = async (req: Request, res: Response) => {
    const id = Number(req.params.id);

    try {
        const heroe = await Heroes.findByPk(id);
        if (heroe) {
            res.json(heroe);
        }
    } catch (error) {
        res.status(404).json({
            msg: `No existe una heroe con el id ${id}`
        });

    };
}

export const crearHeroe = async (req: Request, res: Response) => {
    const { nombre, equipo_id } = req.body;
    const nuevoHeroe = await Heroes.create({ nombre, equipo_id });

    res.status(201).json(nuevoHeroe);
};

export const actualizarHeroePorId = async (req: Request, res: Response) => {
    const id = Number(req.params.id);
    const { body } = req;

    const heroe = await Heroes.findByPk(id);
    await heroe?.update(body);

    res.json(heroe);
}


export const eliminarHeroePorId = async (req: Request, res: Response) => {
    const id = Number(req.params.id);

    const heroe = await Heroes.findByPk(id);
    await heroe?.destroy();
}


export const obtenerHeroesPorEquipo = async (req: Request, res: Response) => {
    const { equipo_id } = req.query;

    
    let condicion = {};

    if (equipo_id) {
        condicion = { equipo_id: Number(equipo_id) };
    }

    const heroes = await Heroes.findAll({
        where: condicion
    });

    res.json(heroes);
};


