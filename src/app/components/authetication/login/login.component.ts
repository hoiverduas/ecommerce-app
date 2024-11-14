import { Component, OnInit } from '@angular/core';
import { AutheticationService } from '../../../services/authetication.service';
import { Logindto } from '../../../common/logindto';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {

  username:string='';
  password:string='';

  constructor(private authentication:AutheticationService){}
  
  ngOnInit(): void {
    
  }

  login(){
    

    let loginDto = new Logindto(this.username,this.password); 
     this.authentication.login(loginDto).subscribe(
      token => { console.log(token); }
     );
   

  }

}
