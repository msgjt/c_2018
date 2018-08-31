import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FixedBugsChartComponent } from './fixed-bugs-chart.component';

describe('FixedBugsChartComponent', () => {
  let component: FixedBugsChartComponent;
  let fixture: ComponentFixture<FixedBugsChartComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FixedBugsChartComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FixedBugsChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
