

```json
1️⃣ PISTAS
URL: POST http://localhost:3000/api/pistas

{
  "nombre": "Pista Central - Indoor",
  "tipo": "INDOOR",
  "precio_hora": 20.00
}



2️⃣ USUARIOS
URL: POST http://localhost:3000/api/usuarios
{
  "nombre": "Juan Palas",
  "email": "juan.palas@email.com",
  "telefono": "600111222"
}




3️⃣ RESERVAS
Nota: Asegúrate de que pista_id y usuario_id existan en tu BD.

URL: POST http://localhost:3000/api/reservas
{
  "pista_id": 1,
  "usuario_id": 1,
  "fecha": "2026-02-10",
  "hora_inicio": "17:00:00",
  "hora_fin": "18:30:00"
}