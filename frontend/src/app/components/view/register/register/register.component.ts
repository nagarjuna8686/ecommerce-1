import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { MessageService } from 'src/app/services/message.service';

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
  }

  constructor(
    public authService: AuthService,
    public messageService: MessageService
  ) {
    this.show = false;
   }

  ngOnInit(): void {
  }

  addUser = () => {
    this.authService.createUser(
      this.registerUser.name,
      this.registerUser.surname,
      this.registerUser.birthdate,
      this.registerUser.email,
      this.registerUser.phone,
      this.registerUser.password
      ).subscribe(
      response => {
        this.messageService.success('SUCCESSO', 'Utente creato con successo');
        console.log('UTENTE REGISTRATO CON SUCCESSO (SPERO) ');
      },
      error => {
        this.messageService.error('ERRORE', 'Errore: ' + JSON.stringify(error));
        console.log('ERRORE REGISTRAZIONE',this.registerUser.name, error);
      }
    )
  }

  passwordView = () => { //metodo per far in modo che se è checkata la checkbox allora mi visualizza la password oscurata
    this.show = !this.show;
}


}
