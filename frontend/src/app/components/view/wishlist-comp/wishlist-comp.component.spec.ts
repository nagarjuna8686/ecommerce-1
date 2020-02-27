import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WishlistCompComponent } from './wishlist-comp.component';

describe('WishlistCompComponent', () => {
  let component: WishlistCompComponent;
  let fixture: ComponentFixture<WishlistCompComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WishlistCompComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WishlistCompComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
