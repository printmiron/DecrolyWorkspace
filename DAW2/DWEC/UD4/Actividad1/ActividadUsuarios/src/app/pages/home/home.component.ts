import { Component } from '@angular/core';
import { ListUserComponent } from "../../components/list-user/list-user.component";

@Component({
  selector: 'app-home',
  imports: [ListUserComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
})
export class HomeComponent {

}
