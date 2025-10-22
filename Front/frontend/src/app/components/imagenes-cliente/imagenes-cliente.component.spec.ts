import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ImagenesClienteComponent } from './imagenes-cliente.component';

describe('ImagenesClienteComponent', () => {
  let component: ImagenesClienteComponent;
  let fixture: ComponentFixture<ImagenesClienteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ImagenesClienteComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ImagenesClienteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
