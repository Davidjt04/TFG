import { TestBed } from '@angular/core/testing';

import { FinalCiteService } from './final-cite.service';

describe('FinalCiteService', () => {
  let service: FinalCiteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FinalCiteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
