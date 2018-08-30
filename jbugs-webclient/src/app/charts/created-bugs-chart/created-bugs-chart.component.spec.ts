import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatedBugsChartComponent } from './created-bugs-chart.component';

describe('CreatedBugsChartComponent', () => {
  let component: CreatedBugsChartComponent;
  let fixture: ComponentFixture<CreatedBugsChartComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreatedBugsChartComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreatedBugsChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
