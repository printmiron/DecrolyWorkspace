import { DataTypes, Model } from "sequelize";
import sequelize from "../config/database";

class Villanos extends Model {

    public id!: number;
    public nombre!: string;
    public antiequipo_id!: number;

}

Villanos.init(
    {
        id: {
            type: DataTypes.INTEGER,
            autoIncrement: true,
            primaryKey: true,
        },
        nombre: {
            type: DataTypes.STRING,
            allowNull: false,
        },
        antiequipo_id: {
            type: DataTypes.INTEGER,
            allowNull: false,
            field: 'antiequipo_id'
        },
    },
    {
        sequelize,
        tableName: 'villanos',
        timestamps: false
    }
);


export default Villanos;