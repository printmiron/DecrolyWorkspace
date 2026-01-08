import { DataTypes, Model } from 'sequelize';
import sequelize from '../config/database';
class Usuario extends Model {
    public id!: number;
    public nombre!: string;
    public email!: string;
}
Usuario.init(
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
        email: {
            type: DataTypes.STRING,
            allowNull: false,
            unique: true,
        },
    },
    {
        sequelize,
        tableName: 'usuarios',
    }
);
export default Usuario;