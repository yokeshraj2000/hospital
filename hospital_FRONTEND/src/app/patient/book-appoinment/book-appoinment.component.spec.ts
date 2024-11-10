import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookAppoinmentComponent } from './book-appoinment.component';

describe('BookAppoinmentComponent', () => {
  let component: BookAppoinmentComponent;
  let fixture: ComponentFixture<BookAppoinmentComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BookAppoinmentComponent]
    });
    fixture = TestBed.createComponent(BookAppoinmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
