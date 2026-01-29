import { DataTypes, Model } from 'sequelize';
import sequelize from '../config/database'; // Ajusta la ruta a tu config

class Usuario extends Model {
    //!exacto como en bd
    public id!: number;
    public nombre!: string;
    public email!: string;
    public telefono?: string;
}

Usuario.init({
    id: {
        type: DataTypes.INTEGER,
        autoIncrement: true,
        primaryKey: true,
    },
    nombre: {
        type: DataTypes.STRING(100),
        allowNull: false,
    },
    email: {
        type: DataTypes.STRING(100),
        allowNull: false,
        unique: true,
    },
    telefono: {
        type: DataTypes.STRING(20),
        allowNull: true,
    }
}, {
    sequelize,
    tableName: 'usuarios',
    timestamps: false 
});

export default Usuario;