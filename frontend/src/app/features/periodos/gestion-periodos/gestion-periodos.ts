import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
// Importamos PrimeNG
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { ToolbarModule } from 'primeng/toolbar';
import { CardModule } from 'primeng/card';

@Component({
  selector: 'app-gestion-periodos',
  standalone: true,
  imports: [CommonModule, TableModule, ButtonModule, ToolbarModule, CardModule],
  template: `
    <div class="p-4">
      <p-card header="Gestión de Periodos Contables">
        
        <p-toolbar styleClass="mb-4">
            <ng-template pTemplate="left">
                <p-button label="Nuevo Periodo" icon="pi pi-plus" class="mr-2"></p-button>
                <p-button label="Eliminar" icon="pi pi-trash" severity="danger"></p-button>
            </ng-template>
            <ng-template pTemplate="right">
                <p-button label="Exportar" icon="pi pi-upload" styleClass="p-button-help"></p-button>
            </ng-template>
        </p-toolbar>

        <p-table [value]="periodos" [tableStyle]="{ 'min-width': '50rem' }">
            <ng-template pTemplate="header">
                <tr>
                    <th>Código</th>
                    <th>Inicio</th>
                    <th>Fin</th>
                    <th>Tipo</th>
                    <th>Estado</th>
                    <th>Acciones</th>
                </tr>
            </ng-template>
            <ng-template pTemplate="body" let-periodo>
                <tr>
                    <td>{{ periodo.codigo }}</td>
                    <td>{{ periodo.inicio }}</td>
                    <td>{{ periodo.fin }}</td>
                    <td>{{ periodo.tipo }}</td>
                    <td>
                        <span [class]="'px-2 py-1 border-round ' + (periodo.activo ? 'bg-green-100 text-green-800' : 'bg-red-100 text-red-800')">
                            {{ periodo.activo ? 'ABIERTO' : 'CERRADO' }}
                        </span>
                    </td>
                    <td>
                        <p-button icon="pi pi-pencil" [rounded]="true" [text]="true"></p-button>
                    </td>
                </tr>
            </ng-template>
        </p-table>
      </p-card>
    </div>
  `
})
export class GestionPeriodosComponent {
  periodos = [
    { codigo: '2026-01', inicio: '2026-01-01', fin: '2026-01-31', tipo: 'MENSUAL', activo: true },
    { codigo: '2025-ANUAL', inicio: '2025-01-01', fin: '2025-12-31', tipo: 'ANUAL', activo: false }
  ];
}