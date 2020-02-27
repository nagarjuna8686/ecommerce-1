import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductThumbnailCompComponent } from './product-thumbnail-comp.component';

describe('ProductThumbnailCompComponent', () => {
  let component: ProductThumbnailCompComponent;
  let fixture: ComponentFixture<ProductThumbnailCompComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProductThumbnailCompComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductThumbnailCompComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
