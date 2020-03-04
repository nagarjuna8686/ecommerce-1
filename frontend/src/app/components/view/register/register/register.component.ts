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

  confirmedPassword: string = '';

  public registerUser = {
    name: '',
    surname: '',
    birthdate: '',
    email: '',
    phone: '',
    password: ''
  };

  constructor(
    public authService: AuthService,
    public messageService: MessageService
  ) {
    this.show = false;
  }

  ngOnInit(): void {
  }

  addUser = () => {

    const mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

    if (this.registerUser.email.match(mailformat) && this.registerUser.password === this.confirmedPassword) {

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
        console.log('UTENTE REGISTRATO CON SUCCESSO (SPERO)');
      },
      error => {
        this.messageService.error('ERRORE', 'Errore: ' + JSON.stringify(error));
        console.log('ERRORE REGISTRAZIONE',this.registerUser.name, error);
      }
    )
    }else{
      this.messageService.success('MI DISPIACE', 'La tua password/email è sbagliata');
      console.log('formato mail sbagliata');
      console.log('password non uguale')
    }
  }

  passwordView = () => { //metodo per far in modo che se è checkata la checkbox allora mi visualizza la password oscurata
    this.show = !this.show;
}

  //metodo per controllo password


}
