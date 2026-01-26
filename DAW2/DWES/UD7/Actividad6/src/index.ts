import dotenv from 'dotenv';
dotenv.config();

import express from 'express';
import {sequelize} from './config/database';
import pistasRoutes from './routes/pistasRoutes';
import reservasRoutes from './routes/reservasRoutes';


const app = express();
const PORT = 3000;
app.use(express.json());
app.get('/', (req, res) => {

    res.send('Servidor funcionando');
});
// Probar conexión a la base de datos
sequelize
    .authenticate()
    .then(() => console.log('Conexión a la base de datos exitosa'))
    .catch((error) => console.error('Error al conectar la base de datos:', error));
app.listen(PORT, () => console.log(`Servidor corriendo en http://localhost:${PORT}`));

app.use('/api', pistasRoutes, reservasRoutes);
