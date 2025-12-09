import { TestBed } from '@angular/core/testing';

import { TiendaAdminService } from './tienda-admin.service';

describe('TiendaAdminService', () => {
  let service: TiendaAdminService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TiendaAdminService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
