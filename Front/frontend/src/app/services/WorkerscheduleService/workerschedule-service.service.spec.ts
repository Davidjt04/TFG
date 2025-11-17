import { TestBed } from '@angular/core/testing';

import { WorkerscheduleServiceService } from '../../workerschedule-service.service';

describe('WorkerscheduleServiceService', () => {
  let service: WorkerscheduleServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WorkerscheduleServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
