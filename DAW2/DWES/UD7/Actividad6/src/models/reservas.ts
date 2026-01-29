import { DataTypes } from "sequelize";
import { sequelize } from "../config/database";
import { Pista } from "./pistas";
export const Reserva = sequelize.define(
    "Reserva",
    {
        id: { type: DataTypes.INTEGER, autoIncrement: true, primaryKey: true },
        pista_id: { type: DataTypes.INTEGER, allowNull: false },
        fecha: { type: DataTypes.DATEONLY, allowNull: false },
        hora_inicio: { type: DataTypes.TIME, allowNull: false },
        hora_fin: { type: DataTypes.TIME, allowNull: false },
    },
    {
        tableName: "reservas",
        timestamps: true,
        createdAt: "created_at",
        updatedAt: "updated_at",
    }
);
// Relaciones (FK)
Pista.hasMany(Reserva, { foreignKey: "pista_id" });
Reserva.belongsTo(Pista, { foreignKey: "pista_id" });