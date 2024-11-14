import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../common/user';
import { Observable } from 'rxjs';
import { Logindto } from '../common/logindto';
import { Jwtclient } from '../common/jwtclient';

@Injectable({
  providedIn: 'root'
})
export class AutheticationService {

private apiUrl : string = 'http://localhost:8080/api/v1/users'
private apiUrlAuth : string = 'http://localhost:8080/api/v1/auth'

  constructor(private httpClient:HttpClient) { }

  register(user:User):Observable<User>{
    return this.httpClient.post<User>(this.apiUrl+'/register',user);
    
  }

  login(loginDto:Logindto):Observable<Jwtclient>{
    return this.httpClient.post<Jwtclient>(this.apiUrl+'/login',loginDto);
  }

}
