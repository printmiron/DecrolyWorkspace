import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HeroViewComponent } from './hero-view.component';

describe('HeroViewComponent', () => {
  let component: HeroViewComponent;
  let fixture: ComponentFixture<HeroViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HeroViewComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HeroViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
