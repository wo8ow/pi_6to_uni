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
    <div class="login-page">
      <div class="login-shell">
        <aside class="brand-panel">
          <div class="brand-badge">SF</div>
          <h1>SmartFin</h1>
          <p>
            Centraliza tus balances, periodos y KPIs en un solo lugar.
            Inicia sesión para continuar con tu análisis financiero.
          </p>
          <ul>
            <li><i class="pi pi-chart-line"></i> Tableros de indicadores</li>
            <li><i class="pi pi-building"></i> Gestión de empresas</li>
            <li><i class="pi pi-shield"></i> Acceso seguro con token JWT</li>
          </ul>
        </aside>

        <section class="login-card">
          <div class="title-group">
            <h2>Bienvenido de nuevo</h2>
            <span>Ingresa tus credenciales para acceder al sistema</span>
          </div>

          <div *ngIf="error" class="error-banner">
            <i class="pi pi-exclamation-triangle"></i>
            <span>{{ error }}</span>
          </div>

          <form (ngSubmit)="onSubmit()">
            <div class="field-group">
              <label for="email">Correo electrónico</label>
              <input
                id="email"
                type="email"
                pInputText
                [(ngModel)]="email"
                name="email"
                placeholder="ej. admin@smartfin.com"
                class="w-full"
                required>
            </div>

            <div class="field-group">
              <label for="password">Contraseña</label>
              <p-password
                id="password"
                [(ngModel)]="password"
                name="password"
                [toggleMask]="true"
                [feedback]="false"
                placeholder="••••••••"
                styleClass="w-full"
                [inputStyle]="{
                  'width':'100%', 
                  'padding-right': '3.5rem', 
                  'position': 'relative'
                }"
                required>
              </p-password>
            </div>

            <div class="actions-row">
              <div class="remember-box">
                <p-checkbox id="rememberme" [binary]="true" styleClass="mr-2"></p-checkbox>
                <label for="rememberme">Recordarme</label>
              </div>
              <a class="forgot-link">¿Olvidaste tu clave?</a>
            </div>

            <p-button
              pRipple
              label="Iniciar sesión"
              icon="pi pi-sign-in"
              styleClass="w-full"
              [loading]="cargando"
              [disabled]="!email || !password"
              type="submit">
            </p-button>
          </form>
        </section>
      </div>
    </div>
  `,
  styles: [`
    :host {
      display: block;
    }

    .login-page {
      min-height: 100vh;
      display: flex;
      justify-content: center;
      align-items: center;
      background:
        radial-gradient(circle at 20% 20%, #dbeafe 0%, transparent 35%),
        radial-gradient(circle at 80% 80%, #bfdbfe 0%, transparent 30%),
        #f8fbff;
      padding: 1.5rem;
    }

    .login-shell {
      width: min(980px, 100%);
      background: #ffffff;
      border-radius: 24px;
      box-shadow: 0 22px 50px rgba(15, 23, 42, 0.15);
      overflow: hidden;
      display: grid;
      grid-template-columns: 1fr 1.1fr;
    }

    .brand-panel {
      background: linear-gradient(160deg, #0a205e, #061d4f);
      color: #eff6ff;
      padding: 2.25rem;
      display: flex;
      flex-direction: column;
      gap: 1rem;
    }

    .brand-badge {
      width: 52px;
      height: 52px;
      border-radius: 12px;
      background: rgba(255, 255, 255, 0.2);
      display: grid;
      place-items: center;
      font-weight: 700;
      letter-spacing: 0.03em;
    }

    .brand-panel h1 {
      margin: 0;
      font-size: 1.8rem;
    }

    .brand-panel p {
      margin: 0;
      line-height: 1.5;
      color: #dbeafe;
      max-width: 30ch;
    }

    .brand-panel ul {
      margin: 0.5rem 0 0;
      padding: 0;
      list-style: none;
      display: grid;
      gap: 0.85rem;
    }

    .brand-panel li {
      display: flex;
      align-items: center;
      gap: 0.55rem;
      font-size: 0.95rem;
    }

    .login-card {
      padding: 2.25rem;
      display: flex;
      flex-direction: column;
      justify-content: center;
    }

    .title-group h2 {
      margin: 0;
      color: #0f172a;
      font-size: 1.6rem;
    }

    .title-group span {
      color: #64748b;
      font-size: 0.95rem;
    }

    .field-group {
      margin-top: 1rem;
    }

    .field-group label {
      display: block;
      color: #0f172a;
      margin-bottom: 0.5rem;
      font-weight: 600;
    }

    .actions-row {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin: 1rem 0 1.25rem;
      gap: 0.8rem;
      flex-wrap: wrap;
    }

    .remember-box {
      display: flex;
      align-items: center;
      color: #334155;
      font-size: 0.93rem;
    }

    .forgot-link {
      color: #0c2d75;
      font-weight: 600;
      text-decoration: none;
      cursor: pointer;
    }

    .error-banner {
      margin-top: 1rem;
      border: 1px solid #fecaca;
      color: #b91c1c;
      background: #fef2f2;
      border-radius: 10px;
      padding: 0.7rem 0.9rem;
      display: flex;
      align-items: center;
      gap: 0.6rem;
      font-size: 0.92rem;
    }

    @media (max-width: 860px) {
      .login-shell {
        grid-template-columns: 1fr;
      }

      .brand-panel {
        padding: 1.4rem 1.5rem;
      }

      .login-card {
        padding: 1.6rem 1.5rem;
      }
    }
  `]
})
export class LoginComponent {
  email = 'admin@smartfin.com';
  password = '';
  cargando = false;
  error = '';

  authService = inject(AuthService);
  router = inject(Router);

  onSubmit() {
    this.error = '';
    this.cargando = true;

    this.authService.login({ correo: this.email, clave: this.password }).subscribe({
      next: () => {
        this.router.navigate(['/app/dashboard']);
        this.cargando = false;
      },
      error: (err) => {
        this.error = err.error?.message || 'Credenciales incorrectas o error del servidor';
        this.cargando = false;
      }
    });
  }
}