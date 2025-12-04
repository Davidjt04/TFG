import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrabajadorNavComponent } from './trabajador-nav.component';

describe('TrabajadorNavComponent', () => {
  let component: TrabajadorNavComponent;
  let fixture: ComponentFixture<TrabajadorNavComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TrabajadorNavComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TrabajadorNavComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
