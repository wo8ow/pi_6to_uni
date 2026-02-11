export interface LoginRequest {
  correo: string;
  clave: string;
}

export interface LoginResponse {
  usuarioId: string;
  nombreCompleto: string;
  correo: string;
  token: string;
  roles: string[];
}