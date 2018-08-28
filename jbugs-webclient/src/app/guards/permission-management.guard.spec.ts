import { TestBed, async, inject } from '@angular/core/testing';

import { PermissionManagementGuard } from './permission-management.guard';

describe('PermissionManagementGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PermissionManagementGuard]
    });
  });

  it('should ...', inject([PermissionManagementGuard], (guard: PermissionManagementGuard) => {
    expect(guard).toBeTruthy();
  }));
});
