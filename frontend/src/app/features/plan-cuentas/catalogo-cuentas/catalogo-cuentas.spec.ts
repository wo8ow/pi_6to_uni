import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CatalogoCuentas } from './catalogo-cuentas';

describe('CatalogoCuentas', () => {
  let component: CatalogoCuentas;
  let fixture: ComponentFixture<CatalogoCuentas>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CatalogoCuentas]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CatalogoCuentas);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
