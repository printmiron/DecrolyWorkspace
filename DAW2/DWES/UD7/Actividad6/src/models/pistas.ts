import { DataTypes } from "sequelize";
import { sequelize } from "../config/database";
export const Pista = sequelize.define(
    "Pista",
    {
        id: { type: DataTypes.INTEGER, autoIncrement: true, primaryKey: true },
        nombre: { type: DataTypes.STRING(60), allowNull: false, unique: true },
        tipo: { type: DataTypes.ENUM("INDOOR", "OUTDOOR"), allowNull: false },
        precio_hora: { type: DataTypes.DECIMAL(7, 2), allowNull: false },
    },
    {
        tableName: "pistas",
        timestamps: true,
        createdAt: "created_at",
        updatedAt: "updated_at",
    }
);