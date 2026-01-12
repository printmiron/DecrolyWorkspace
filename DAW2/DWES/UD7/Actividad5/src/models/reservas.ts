import { DataTypes, Model } from "sequelize";
import sequelize from '../config/database';

class Reservas extends Model {

    public id!: number;
    public pista_id!: number;
    public fecha!: Date;
    public hora_inicio!: string;
    public hora_fin!: string;
}

Reservas.init(
    {
        id: {
            type: DataTypes.INTEGER,
            autoIncrement: true,
            primaryKey: true,
        },
        pista_id: {
            type: DataTypes.INTEGER,
            allowNull: false,
            field: 'pista_id'
        },
        fecha: {
            type: DataTypes.DATEONLY,
            allowNull: false,
        },
        hora_inicio: {
            type: DataTypes.TIME,
            allowNull: false,
        },
        hora_fin: {
            type: DataTypes.TIME,
            allowNull: false,
        }
    },
    {
        sequelize,
        tableName: 'reservas',
        timestamps: false
    }
);

export default Reservas;
