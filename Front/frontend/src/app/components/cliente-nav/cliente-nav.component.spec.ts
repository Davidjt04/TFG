import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClienteNavComponent } from './cliente-nav.component';

describe('ClienteNavComponent', () => {
  let component: ClienteNavComponent;
  let fixture: ComponentFixture<ClienteNavComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ClienteNavComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ClienteNavComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
