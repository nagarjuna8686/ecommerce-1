import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public loginUser = {
    email: '',
    password: ''
  }

  constructor(
    public authService: AuthService
  ) { }

  ngOnInit(): void {
  }

  login = () => {

    let mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

    if (this.loginUser.email.match(mailformat)) {

      this.authService.login(this.loginUser.email, this.loginUser.password).subscribe(
        response => {
          alert('UTENTE LOGGATO CON SUCCESSO')
        },
        error => {
          alert('ERRORE LOGIN')
        }
      )
    }
    else {
      alert('FORMATO MAIL ERRATO')
    }
  } 

}
