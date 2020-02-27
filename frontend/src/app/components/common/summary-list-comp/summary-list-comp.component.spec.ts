import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SummaryListCompComponent } from './summary-list-comp.component';

describe('SummaryListCompComponent', () => {
  let component: SummaryListCompComponent;
  let fixture: ComponentFixture<SummaryListCompComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SummaryListCompComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SummaryListCompComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
