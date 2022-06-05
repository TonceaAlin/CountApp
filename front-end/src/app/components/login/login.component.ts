import { Component, OnInit } from '@angular/core';
import {NgForm} from "@angular/forms";
import {Router} from "@angular/router";
import {UserService} from "../../services/user.service";
import {HttpErrorResponse} from "@angular/common/http";
import jwtDecode from 'jwt-decode'
import {LoginResponseDecoded} from "../../domain/responses/LoginResponseDecoded";
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  constructor(private router: Router, private userService: UserService) { }

  userFound: boolean = false;
  errorMessage: string = "";
  ngOnInit(): void {
  }

  gotoRegister(){
    this.router.navigate(['register']).then(r => {});
  }

  onSubmit(form: NgForm){
    let username = form.value.Username;
    let password = form.value.Password;
    this.userService.authenticate(username, password).
    subscribe(
      (response) => {
        this.userFound = true;
        const decodedToken = jwtDecode<LoginResponseDecoded>(response.token);
        localStorage.setItem("Token", response.token);
        localStorage.setItem("Username", decodedToken.username);
        localStorage.setItem("Email", decodedToken.email);
        localStorage.setItem("Id", JSON.stringify(decodedToken.id));

        let currentUser = new LoginResponseDecoded(decodedToken.username, decodedToken.email, decodedToken.id);
        this.router.navigate(['home'], {state: {user: currentUser}});
      },
      (error: HttpErrorResponse) => {
        this.userFound = false;
        if(error.status === 404)
          this.errorMessage = "User not found";
        else if(error.status === 401)
          this.errorMessage = "This user is not validated!";
        else
          this.errorMessage = "Oops, something went wrong on our side :)";
      }
    )
  }
}
