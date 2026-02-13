export interface Empresa {
  empresaId: string;
  identificacion: string;
  nombre: string;
  sector: string;
  monedaCodigo: string;
  activo: boolean;
}

export interface EmpresaResponse extends Empresa {}

export interface EmpresaCrearRequest {
  identificacion: string;
  nombre: string;
  sector: string;
  monedaCodigo: string;
}

export interface EmpresaActualizarRequest {
  nombre: string;
  sector: string;
  monedaCodigo: string;
  activo: boolean;
}
