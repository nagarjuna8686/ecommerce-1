import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  show: boolean;

  public registerUser = {
    name: '',
    surname: '',
    birthdate: '',
    email: '',
    phone: '',
    password: ''
  };

  constructor( public authService: AuthService ) {
    this.show = false;
  }

  ngOnInit(): void {
  }

  addUser = () => {

    const mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

    if (this.registerUser.email.match(mailformat)) {
    this.authService.createUser(
      this.registerUser.name,
      this.registerUser.surname,
      this.registerUser.birthdate,
      this.registerUser.email,
      this.registerUser.phone,
      this.registerUser.password
      ).subscribe(
      response => {
        console.log('UTENTE REGISTRATO CON SUCCESSO (SPERO)');
      },
      error => {
        console.log('ERRORE REGISTRAZIONE',this.registerUser.name, error);
      }
    )
    }else{
      console.log('formato mail sbagliata');
    }
  }

  passwordView = () => { //metodo per far in modo che se è checkata la checkbox allora mi visualizza la password oscurata
    this.show = !this.show;
}

  //metodo per controllo password


}
