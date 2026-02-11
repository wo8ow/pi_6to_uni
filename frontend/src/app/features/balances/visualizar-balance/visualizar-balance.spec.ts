import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VisualizarBalance } from './visualizar-balance';

describe('VisualizarBalance', () => {
  let component: VisualizarBalance;
  let fixture: ComponentFixture<VisualizarBalance>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VisualizarBalance]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VisualizarBalance);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
