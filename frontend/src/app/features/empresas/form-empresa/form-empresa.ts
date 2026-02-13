import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { CheckboxModule } from 'primeng/checkbox';
import { EmpresaService } from '../../../core/services/empresa.service';

@Component({
  selector: 'app-form-empresa',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, InputTextModule, ButtonModule, CardModule, CheckboxModule],
  templateUrl: './form-empresa.html',
  styleUrl: './form-empresa.scss',
})
export class FormEmpresa implements OnInit {
  form: FormGroup;
  empresaId: string | null = null;
  titulo = 'Nueva Empresa';

  constructor(
    private fb: FormBuilder,
    private empresaService: EmpresaService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.form = this.fb.group({
      identificacion: ['', Validators.required],
      nombre: ['', Validators.required],
      sector: ['', Validators.required],
      monedaCodigo: ['', Validators.required],
      activo: [true],
    });
  }

  ngOnInit() {
    this.route.params.subscribe((params) => {
      if (params['id']) {
        this.empresaId = params['id'];
        this.titulo = 'Editar Empresa';
        this.cargarEmpresa();
      }
    });
  }

  cargarEmpresa() {
    if (this.empresaId) {
      this.empresaService.obtener(this.empresaId).subscribe((empresa) => {
        this.form.patchValue(empresa);
        // Deshabilitar identificación en edición si no se puede cambiar
        this.form.get('identificacion')?.disable();
      });
    }
  }

  guardar() {
    if (this.form.invalid) return;

    if (this.empresaId) {
      this.empresaService
        .actualizar(this.empresaId, this.form.getRawValue())
        .subscribe(() => {
          this.router.navigate(['/app/empresas']);
        });
    } else {
      this.empresaService.crear(this.form.value).subscribe(() => {
        this.router.navigate(['/app/empresas']);
      });
    }
  }

  cancelar() {
    this.router.navigate(['/app/empresas']);
  }
}
