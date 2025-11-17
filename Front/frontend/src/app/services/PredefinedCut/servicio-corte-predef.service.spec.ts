import { TestBed } from '@angular/core/testing';

import { ServicioCortePredefService } from './servicio-corte-predef.service';

describe('ServicioCortePredefService', () => {
  let service: ServicioCortePredefService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServicioCortePredefService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
