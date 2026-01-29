import { DataTypes, Model } from "sequelize";
import sequelize from "../config/database";

class Equipos extends Model {

    public id!: number;
    public nombre!: string;
    public sede!: string;

}

Equipos.init(
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
        sede: {
            type: DataTypes.STRING,
            allowNull: false,
        }
        
    },
    {
        
        sequelize,
        tableName: 'equipos',
        timestamps: false
        
    }
);


export default Equipos;