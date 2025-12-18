import express from 'express';
import { pistas } from '../data.ts';

export const listarPistas = (req: express.Request, res: express.Response) => {
    res.status(200).json(pistas);
};



export const agregarPista = (req: express.Request, res: express.Response) => {
    const newPista = req.body;
    pistas.push(newPista);
    res.status(201).json("Pista " + newPista + " añadida!");

};


export const reservarPista = (req: express.Request, res: express.Response) => {
    const id = parseInt(req.params.id);
    const pista = pistas.find(p => p.id === id)
    if (pista) {
        pista.reservada = true;
        res.json("Pista con id (" + id + ") reservada!");
    } else {
        res.status(404).json("Pista no encontrada!");
    }
};


export const cancelarReserva = (req: express.Request, res: express.Response) => {
    const id = parseInt(req.params.id);
    const pista = pistas.find(p => p.id === id)
    if (pista) {
        pista.reservada = false;
        res.json("Pista con id (" + id + ") cancelada!");
    } else {
        res.status(404).json("Pista no encontrada!");
    }
};


