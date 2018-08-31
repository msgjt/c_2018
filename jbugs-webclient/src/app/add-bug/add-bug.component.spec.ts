import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddBugComponentComponent } from './add-bug.component';

describe('AddBugComponentComponent', () => {
  let component: AddBugComponentComponent;
  let fixture: ComponentFixture<AddBugComponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddBugComponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddBugComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
