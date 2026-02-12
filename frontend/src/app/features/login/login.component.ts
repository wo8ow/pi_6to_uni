import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../core/services/auth.service';
import { InputTextModule } from 'primeng/inputtext';
import { PasswordModule } from 'primeng/password';
import { ButtonModule } from 'primeng/button';
import { CheckboxModule } from 'primeng/checkbox';


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, InputTextModule, PasswordModule, ButtonModule, CheckboxModule],
  template: `
    <div class="flex align-items-center justify-content-center min-h-screen bg-blue-50">
      
      <div class="surface-card p-4 shadow-4 border-round w-full lg:w-4">
        
        <div class="text-center mb-5">
          <div class="text-900 text-3xl font-medium mb-3">Bienvenido a SmartFin</div>
          <span class="text-600 font-medium line-height-3">Ingresa tus credenciales para continuar</span>
        </div>

        <form (ngSubmit)="onSubmit()">
          
          <div class="mb-3">
            <label for="email" class="block text-900 font-medium mb-2">Correo Electrónico</label>
            <input 
              id="email" 
              type="text" 
              pInputText 
              [(ngModel)]="email" 
              name="email" 
              placeholder="ej. admin@smartfin.com" 
              class="w-full"
              required>
          </div>

          <div class="mb-3">
            <label for="password" class="block text-900 font-medium mb-2">Contraseña</label>
            <p-password 
              id="password" 
              [(ngModel)]="password" 
              name="password" 
              [toggleMask]="true" 
              [feedback]="false"
              placeholder="••••••••"
              styleClass="w-full"
              [inputStyle]="{'width':'100%'}"
              required>
            </p-password>
          </div>

          <div class="flex align-items-center justify-content-between mb-5">
            <div class="flex align-items-center">
              <p-checkbox id="rememberme" [binary]="true" styleClass="mr-2"></p-checkbox>
              <label for="rememberme" class="text-900">Recordarme</label>
            </div>
            <a class="font-medium no-underline ml-2 text-blue-500 text-right cursor-pointer">¿Olvidaste tu clave?</a>
          </div>

          <p-button 
            pButton 
            pRipple 
            label="Iniciar Sesión" 
            icon="pi pi-user" 
            styleClass="w-full"
            [loading]="cargando"
            type="submit">
          </p-button>

        </form>
      </div>
    </div>
  `,
  styles: [`
    /* Ajuste para que el fondo cubra toda la pantalla */
    :host {
        display: block;
    }
  `]
})
export class LoginComponent {
  email = 'admin@smartfin.com';
  password = '';
  cargando = false; // Para mostrar spinner en el botón

  authService = inject(AuthService);
  router = inject(Router);

  onSubmit() {
    this.cargando = true; // Activar animación de carga
    
    this.authService.login({ correo: this.email, clave: this.password }).subscribe({
      next: () => {
        this.router.navigate(['/app/dashboard']);
        this.cargando = false;
      },
      error: (err) => {
        const mensaje = err.error?.message || 'Credenciales incorrectas o error de servidor';
        alert('Error: ' + mensaje); // Idealmente usaríamos p-toast aquí luego
        this.cargando = false;
      }
    });
  }
}