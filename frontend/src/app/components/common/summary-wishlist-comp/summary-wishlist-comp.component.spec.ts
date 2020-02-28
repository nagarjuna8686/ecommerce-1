import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SummaryWishlistCompComponent } from './summary-wishlist-comp.component';

describe('SummaryWishlistCompComponent', () => {
  let component: SummaryWishlistCompComponent;
  let fixture: ComponentFixture<SummaryWishlistCompComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SummaryWishlistCompComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SummaryWishlistCompComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
