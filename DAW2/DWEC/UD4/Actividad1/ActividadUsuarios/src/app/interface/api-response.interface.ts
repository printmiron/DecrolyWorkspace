import { UsuarioI } from "./usuario.interface";

export interface ApiResponse {
    page: number;
    per_page: number;
    total: number;
    total_pages: number;
    results: UsuarioI[];
}
