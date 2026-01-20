import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NuevoHeroComponent } from './nuevo-hero.component';

describe('NuevoHeroComponent', () => {
  let component: NuevoHeroComponent;
  let fixture: ComponentFixture<NuevoHeroComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NuevoHeroComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NuevoHeroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
