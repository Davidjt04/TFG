import { TestBed } from '@angular/core/testing';

import { ClienteStateServiceService } from './services/cliente-state-service.service';

describe('ClienteStateServiceService', () => {
  let service: ClienteStateServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ClienteStateServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
