import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrabajadoresAdminComponent } from './trabajadores-admin.component';

describe('TrabajadoresAdminComponent', () => {
  let component: TrabajadoresAdminComponent;
  let fixture: ComponentFixture<TrabajadoresAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TrabajadoresAdminComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TrabajadoresAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
