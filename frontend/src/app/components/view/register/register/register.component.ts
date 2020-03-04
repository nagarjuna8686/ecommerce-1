import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  show: boolean;

  constructor() {
    this.show = false;
   }

  ngOnInit(): void {
  }

  passwordView() { //metodo per far in modo che se è checkata la checkbox allora mi visualizza la password oscurata
    this.show = !this.show;
}


}
