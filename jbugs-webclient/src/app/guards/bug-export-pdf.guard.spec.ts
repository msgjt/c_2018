import { TestBed, async, inject } from '@angular/core/testing';

import { BugExportPdfGuard } from './bug-export-pdf.guard';

describe('BugExportPdfGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [BugExportPdfGuard]
    });
  });

  it('should ...', inject([BugExportPdfGuard], (guard: BugExportPdfGuard) => {
    expect(guard).toBeTruthy();
  }));
});
