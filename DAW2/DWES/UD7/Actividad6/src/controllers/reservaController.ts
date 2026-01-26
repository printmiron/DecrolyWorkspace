import { Request, Response } from "express";
import { validationResult } from "express-validator";
import { Op } from "sequelize";
import { Reserva } from "../models/reservas";
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
function getQueryString(value: unknown): string | undefined {
  return typeof value === "string" ? value : undefined;
}
function getQueryInt(value: unknown): number | undefined {
  if (typeof value !== "string") return undefined;
  const n = Number(value);
  return Number.isInteger(n) && n >= 1 ? n : undefined;
}
export async function crearReserva(req: Request, res: Response) {
  if (devolverErroresValidacion(req, res)) return;
 

  const pista_id = Number(req.body.pista_id);
  const fecha = String(req.body.fecha);
  const hora_inicio = String(req.body.hora_inicio);
  const hora_fin = String(req.body.hora_fin);
 

  const pista = await Pista.findByPk(pista_id);
  if (!pista) return res.status(404).json({ message: "La pista no existe" });
 

  const solape = await Reserva.findOne({
    where: {
      pista_id,
      fecha,
      hora_inicio: { [Op.lt]: hora_fin },
      hora_fin: { [Op.gt]: hora_inicio },
    },
  });
  if (solape) {
    return res.status(409).json({
      message: "Reserva inválida: hay solape con otra reserva",
      conflicto: solape,
    });
  }
 

  try {
    const reserva = await Reserva.create({ pista_id, fecha, hora_inicio, hora_fin });
    res.status(201).json(reserva);
  } catch (e: any) {
    res.status(400).json({ message: "Error creando reserva", detail: e.message });
  }
}
export async function listarReservas(req: Request, res: Response) {
  // filtros opcionales
  const fecha = getQueryString(req.query.fecha);
  const pista_id = getQueryInt(req.query.pista_id);
  const where: any = {};
  if (fecha) where.fecha = fecha;
  if (pista_id) where.pista_id = pista_id;
  const reservas = await Reserva.findAll({ where });
  res.json(reservas);
}
export async function eliminarReserva(req: Request, res: Response) {
  if (devolverErroresValidacion(req, res)) return;
  const id = getIdParam(req, res);
  if (id === null) return;
  const reserva = await Reserva.findByPk(id);
  if (!reserva) return res.status(404).json({ message: "Reserva no encontrada" });
  await reserva.destroy();
  res.json({ message: "Reserva eliminada" });
}