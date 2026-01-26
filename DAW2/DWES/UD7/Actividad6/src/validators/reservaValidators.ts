import { body, param } from "express-validator";
export const validarIdReservaParam = [
    param("id").isInt({ min: 1 }).withMessage("El id debe ser un entero >= 1"),
];
export const validarCrearReserva = [
    body("pista_id")
        .isInt({ min: 1 })
        .withMessage("pista_id debe ser entero >= 1"),
    body("fecha")
        .isISO8601()
        .withMessage("fecha debe tener formato YYYY-MM-DD"),
    body("hora_inicio")
        .matches(/^\d{2}:\d{2}:\d{2}$/)
        .withMessage("hora_inicio debe ser HH:MM:SS"),
    body("hora_fin")
        .matches(/^\d{2}:\d{2}:\d{2}$/)
        .withMessage("hora_fin debe ser HH:MM:SS"),
    // Regla lógica: hora_fin > hora_inicio (además del CHECK en SQL)
    body("hora_fin").custom((horaFin, { req }) => {
        const inicio = req.body.hora_inicio;
        if (inicio && horaFin <= inicio) {
            throw new Error("hora_fin debe ser mayor que hora_inicio");
        }
        return true;
    }),
];