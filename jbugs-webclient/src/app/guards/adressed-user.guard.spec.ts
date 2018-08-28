import { TestBed, async, inject } from '@angular/core/testing';

import { AdressedUserGuard } from './adressed-user.guard';

describe('AdressedUserGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AdressedUserGuard]
    });
  });

  it('should ...', inject([AdressedUserGuard], (guard: AdressedUserGuard) => {
    expect(guard).toBeTruthy();
  }));
});
