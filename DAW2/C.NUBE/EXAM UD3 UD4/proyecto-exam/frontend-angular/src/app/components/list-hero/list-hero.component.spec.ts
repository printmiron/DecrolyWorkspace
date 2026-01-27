import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListHeroComponent } from './list-hero.component';

describe('ListHeroComponent', () => {
  let component: ListHeroComponent;
  let fixture: ComponentFixture<ListHeroComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListHeroComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListHeroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
