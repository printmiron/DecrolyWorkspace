import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CardHeroComponent } from './card-hero.component';

describe('CardHeroComponent', () => {
  let component: CardHeroComponent;
  let fixture: ComponentFixture<CardHeroComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CardHeroComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CardHeroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
