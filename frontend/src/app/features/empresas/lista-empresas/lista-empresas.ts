import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { ToolbarModule } from 'primeng/toolbar';
import { CardModule } from 'primeng/card';
import { EmpresaService } from '../../../core/services/empresa.service';
import { EmpresaResponse } from '../../../core/models/empresa.model';

@Component({
  selector: 'app-lista-empresas',
  standalone: true,
  imports: [CommonModule, TableModule, ButtonModule, ToolbarModule, CardModule],
  templateUrl: './lista-empresas.html',
  styleUrl: './lista-empresas.scss',
})
export class ListaEmpresas implements OnInit {
  empresas = signal<EmpresaResponse[]>([]);

  constructor(private empresaService: EmpresaService, private router: Router) {}

  ngOnInit() {
    this.cargarEmpresas();
  }

  cargarEmpresas() {
    this.empresaService.listar().subscribe((data) => {
      this.empresas.set(data);
    });
  }

  nuevaEmpresa() {
    this.router.navigate(['/app/empresas/crear']);
  }

  editarEmpresa(id: string) {
    this.router.navigate(['/app/empresas/editar', id]);
  }

  eliminarEmpresa(id: string) {
    if (confirm('¿Está seguro de eliminar esta empresa?')) {
      this.empresaService.eliminar(id).subscribe(() => {
        this.cargarEmpresas();
      });
    }
  }
}
