import { Request, Response } from "express";
import { validationResult } from "express-validator";
import { Pista } from "../models/pistas";
function devolverErroresValidacion(req: Request, res: Response) {
    const errors = validationResult(req);
    if (!errors.isEmpty()) {
        res.status(400).json({ errors: errors.array() });
        return true;
    }
    return false;
}

function getIdParam(req: Request, res: Response): number | null {
    const id = Number(req.params.id);
    if (!Number.isInteger(id) || id < 1) {
        res.status(400).json({ message: "ID inválido" });
        return null;
    }
    return id;
}
export async function crearPista(req: Request, res: Response) {
    if (devolverErroresValidacion(req, res)) return;
    try {
        const pista = await Pista.create(req.body);
        res.status(201).json(pista);
    } catch (e: any) {
        res.status(400).json({ message: "Error creando pista", detail: e.message });
    }
}
export async function listarPistas(_req: Request, res: Response) {
    const pistas = await Pista.findAll();
    res.json(pistas);
}
export async function obtenerPista(req: Request, res: Response) {
    if (devolverErroresValidacion(req, res)) return;
    const id = getIdParam(req, res);
    if (id === null) return;
    const pista = await Pista.findByPk(id);
    if (!pista) return res.status(404).json({ message: "Pista no encontrada" });
    res.json(pista);
}
export async function actualizarPista(req: Request, res: Response) {
    if (devolverErroresValidacion(req, res)) return;
    const id = getIdParam(req, res);
    if (id === null) return;
    const pista = await Pista.findByPk(id);
    if (!pista) return res.status(404).json({ message: "Pista no encontrada" });
    await pista.update(req.body);
    res.json(pista);
}
export async function eliminarPista(req: Request, res: Response) {
    if (devolverErroresValidacion(req, res)) return;
    const id = getIdParam(req, res);
    if (id === null) return;
    const pista = await Pista.findByPk(id);
    if (!pista) return res.status(404).json({ message: "Pista no encontrada" });
    try {
        await pista.destroy();
        res.json({ message: "Pista eliminada" });
    } catch (e: any) {
        res.status(409).json({
            message: "No se puede borrar la pista: tiene reservas asociadas",
            detail: e.message,
        });
    }
}