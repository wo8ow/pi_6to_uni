import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { Empresa } from '../models/empresa.model';
import { Observable } from 'rxjs';

// I will define these interfaces in the model file in the next step,
// but for now I can use them here or wait.
// To avoid compilation errors if I were running it, I'll import them.
// Since they don't exist yet, I'll add them to the model file first in the next step.
// But to write the file content now, I will assume they are exported from the model file.
import { EmpresaCrearRequest, EmpresaActualizarRequest, EmpresaResponse } from '../models/empresa.model';

@Injectable({ providedIn: 'root' })
export class EmpresaService {
  private apiUrl = `${environment.apiUrl}/empresas`;

  constructor(private http: HttpClient) {}

  crear(empresa: EmpresaCrearRequest): Observable<EmpresaResponse> {
    return this.http.post<EmpresaResponse>(this.apiUrl, empresa);
  }

  listar(): Observable<EmpresaResponse[]> {
    return this.http.get<EmpresaResponse[]>(this.apiUrl);
  }

  obtener(id: string): Observable<EmpresaResponse> {
    return this.http.get<EmpresaResponse>(`${this.apiUrl}/${id}`);
  }

  actualizar(id: string, empresa: EmpresaActualizarRequest): Observable<EmpresaResponse> {
    return this.http.put<EmpresaResponse>(`${this.apiUrl}/${id}`, empresa);
  }

  eliminar(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
