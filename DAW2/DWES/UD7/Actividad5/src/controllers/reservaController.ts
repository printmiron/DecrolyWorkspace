import { Request, Response } from 'express';
import Reservas from "../models/reservas";


export const crearReservas = async (req: Request, res: Response) => {
    const { pista_id, usuario_id, fecha, hora_inicio, hora_fin } = req.body;
    const nuevoReservas = await Reservas.create({ pista_id, usuario_id, fecha, hora_inicio, hora_fin });

    res.status(201).json(nuevoReservas);
};


export const obtenerReservasPorPista = async (req: Request, res: Response) => {
    const { pista_id } = req.query;

    
    let condicion = {};

    if (pista_id) {
        condicion = { pista_id: Number(pista_id) };
    }

    const reservas = await Reservas.findAll({
        where: condicion
    });

    res.json(reservas);
};

export const eliminarReservaPorId = async (req: Request, res: Response) => {
    const { id } = req.params;

    const reservas = await Reservas.findByPk(id);
    await reservas?.destroy();
}
