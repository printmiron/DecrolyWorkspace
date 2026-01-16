import { Router, Request, Response } from "express";
import { pool } from "../db/pool";
 
export const contactsRouter = Router();
 
contactsRouter.get("/", async (_req: Request, res: Response) => {
  try {
    const [rows] = await pool.query(
      "SELECT id, full_name, phone, email, created_at FROM contacts ORDER BY id ASC"
    );
 
    res.json({
      ok: true,
      count: Array.isArray(rows) ? rows.length : 0,
      data: rows
    });
  } catch (err) {
    res.status(500).json({
      ok: false,
      message: "Error leyendo contactos",
      error: err instanceof Error ? err.message : String(err)
    });
  }
});