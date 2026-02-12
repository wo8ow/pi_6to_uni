import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-inicio',
  standalone: true,
  imports: [RouterLink],
  template: `
    <main class="inicio">
      <section class="tarjeta">
        <p class="etiqueta">SmartFin</p>
        <h1>Control financiero para tu empresa</h1>
        <p class="descripcion">
          Reemplazamos la pantalla por defecto de Angular con una portada simple para que puedas
          comenzar a personalizar tu producto desde hoy.
        </p>

        <div class="acciones">
          <a routerLink="/login" class="btn btn-primario">Ir al inicio de sesión</a>
          <a routerLink="/dashboard" class="btn btn-secundario">Ver dashboard</a>
        </div>
      </section>
    </main>
  `,
  styles: `
    :host {
      display: block;
      min-height: 100vh;
    }

    .inicio {
      min-height: 100vh;
      display: grid;
      place-items: center;
      padding: 2rem;
      background: linear-gradient(135deg, #0f172a, #1e293b 55%, #334155);
      color: #e2e8f0;
      font-family: Inter, system-ui, sans-serif;
    }

    .tarjeta {
      width: min(720px, 100%);
      background: rgba(15, 23, 42, 0.75);
      border: 1px solid rgba(148, 163, 184, 0.25);
      border-radius: 16px;
      padding: 2rem;
      box-shadow: 0 12px 30px rgba(15, 23, 42, 0.4);
    }

    .etiqueta {
      display: inline-block;
      margin: 0 0 1rem;
      font-size: 0.75rem;
      letter-spacing: 0.08em;
      text-transform: uppercase;
      color: #93c5fd;
    }

    h1 {
      margin: 0;
      font-size: clamp(1.8rem, 3vw, 2.4rem);
      line-height: 1.2;
      color: #f8fafc;
    }

    .descripcion {
      margin: 1rem 0 0;
      color: #cbd5e1;
      line-height: 1.6;
    }

    .acciones {
      margin-top: 1.5rem;
      display: flex;
      gap: 0.75rem;
      flex-wrap: wrap;
    }

    .btn {
      text-decoration: none;
      border-radius: 999px;
      padding: 0.7rem 1rem;
      font-weight: 600;
      transition: transform 0.2s ease, opacity 0.2s ease;
    }

    .btn:hover {
      transform: translateY(-1px);
      opacity: 0.95;
    }

    .btn-primario {
      background: #2563eb;
      color: white;
    }

    .btn-secundario {
      background: #e2e8f0;
      color: #0f172a;
    }
  `
})
export class InicioComponent {}