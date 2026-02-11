import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TableroKpis } from './tablero-kpis';

describe('TableroKpis', () => {
  let component: TableroKpis;
  let fixture: ComponentFixture<TableroKpis>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TableroKpis]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TableroKpis);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
