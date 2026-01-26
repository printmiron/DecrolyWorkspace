import { body, param } from "express-validator";
export const validarIdParam = [
    param("id").isInt({ min: 1 }).withMessage("El id debe ser un entero >= 1"),
];
export const validarCrearPista = [
    body("nombre")
        .isString().withMessage("nombre debe ser texto")
        .notEmpty().withMessage("nombre es obligatorio")
        .isLength({ max: 60 }).withMessage("nombre máximo 60 caracteres"),
    body("tipo")
        .isIn(["INDOOR", "OUTDOOR"])
        .withMessage("tipo debe ser INDOOR u OUTDOOR"),
    body("precio_hora")
        .isFloat({ min: 0 })
        .withMessage("precio_hora debe ser un número >= 0"),
];
export const validarActualizarPista = [
    ...validarIdParam,
    body("nombre").optional().isString().notEmpty().isLength({ max: 60 }),
    body("tipo").optional().isIn(["INDOOR", "OUTDOOR"]),
    body("precio_hora").optional().isFloat({ min: 0 }),
];
