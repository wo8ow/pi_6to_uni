import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistroBalance } from './registro-balance';

describe('RegistroBalance', () => {
  let component: RegistroBalance;
  let fixture: ComponentFixture<RegistroBalance>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegistroBalance]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegistroBalance);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
