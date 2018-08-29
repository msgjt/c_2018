import { TestBed, async, inject } from '@angular/core/testing';

import { BugCloseGuard } from './bug-close.guard';

describe('BugCloseGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [BugCloseGuard]
    });
  });

  it('should ...', inject([BugCloseGuard], (guard: BugCloseGuard) => {
    expect(guard).toBeTruthy();
  }));
});
