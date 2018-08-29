import { TestBed, inject } from '@angular/core/testing';

import { ExportPDFService } from './export-pdf.service';

describe('ExportPDFService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ExportPDFService]
    });
  });

  it('should be created', inject([ExportPDFService], (service: ExportPDFService) => {
    expect(service).toBeTruthy();
  }));
});
