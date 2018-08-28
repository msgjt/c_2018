import { TestBed, async, inject } from '@angular/core/testing';

import { ReportManagementGuard } from './report-management.guard';

describe('ReportManagementGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ReportManagementGuard]
    });
  });

  it('should ...', inject([ReportManagementGuard], (guard: ReportManagementGuard) => {
    expect(guard).toBeTruthy();
  }));
});
