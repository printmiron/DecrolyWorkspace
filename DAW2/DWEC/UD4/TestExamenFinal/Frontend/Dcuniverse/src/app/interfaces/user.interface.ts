export interface User {
    username: string,
    password: string,
    expiresInMins?: number;
}

// export interface AuthResponse {
//   accessToken: string;
//   refreshToken: string;
//   username: string;
//   // Agrega otros si el profe los pide, pero estos son los vitales
// }