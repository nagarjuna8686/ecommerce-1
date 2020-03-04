import { Injectable } from '@angular/core';
import { User } from '../classes/User';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { map } from 'rxjs/operators'

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  public loggedUser: User;
  public token: string;

  constructor(
    public httpClient: HttpClient
  ) { }

  createUser = () => {

  }

  login = (email:string, pwd:string) => {

    return this.httpClient.post(
      environment.apiEndpoint + 'users/login',
      {email: email, password: pwd},
      {headers: {'Content Type': 'appplication/json'}}
    ).pipe(
      map( response => {
        this.token = response['token']
      })
    )

  }
}
