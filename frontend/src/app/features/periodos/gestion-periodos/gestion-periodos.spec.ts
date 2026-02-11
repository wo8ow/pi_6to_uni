import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionPeriodos } from './gestion-periodos';

describe('GestionPeriodos', () => {
  let component: GestionPeriodos;
  let fixture: ComponentFixture<GestionPeriodos>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GestionPeriodos]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GestionPeriodos);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
