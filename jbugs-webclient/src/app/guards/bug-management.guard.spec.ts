import { TestBed, async, inject } from '@angular/core/testing';

import { BugManagementGuard } from './bug-management.guard';

describe('BugManagementGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [BugManagementGuard]
    });
  });

  it('should ...', inject([BugManagementGuard], (guard: BugManagementGuard) => {
    expect(guard).toBeTruthy();
  }));
});
