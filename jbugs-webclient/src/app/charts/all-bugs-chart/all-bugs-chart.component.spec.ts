import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AllBugsChartComponent } from './all-bugs-chart.component';

describe('AllBugsChartComponent', () => {
  let component: AllBugsChartComponent;
  let fixture: ComponentFixture<AllBugsChartComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AllBugsChartComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AllBugsChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
