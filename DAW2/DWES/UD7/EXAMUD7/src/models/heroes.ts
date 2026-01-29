import { DataTypes, Model } from "sequelize";
import sequelize from "../config/database";

class Heroes extends Model {

    public id!: number;
    public nombre!: string;
    public equipo_id!: number;

}

Heroes.init(
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
        equipo_id: {
            type: DataTypes.INTEGER,
            allowNull: false,
            field: 'equipo_id'
        },
    },
    {
        sequelize,
        tableName: 'heroes',
        timestamps: false
    }
);


export default Heroes;