import express from 'express';
import contactRoutes from './routes/contactRoutes';
const app = express();
const port = 3000;
app.use(express.json()); // Para poder recibir JSON en las peticiones
app.use('/api/contacts', contactRoutes);
app.listen(port, () => {
    console.log(`Servidor corriendo en http://localhost:${port}`);
});
