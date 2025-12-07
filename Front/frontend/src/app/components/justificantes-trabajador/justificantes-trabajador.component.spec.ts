import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JustificantesTrabajadorComponent } from './justificantes-trabajador.component';

describe('JustificantesTrabajadorComponent', () => {
  let component: JustificantesTrabajadorComponent;
  let fixture: ComponentFixture<JustificantesTrabajadorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [JustificantesTrabajadorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(JustificantesTrabajadorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
