import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/user.service";
import {Router} from "@angular/router";
import {NgForm} from "@angular/forms";
import {RegisterModel} from "../../domain/register";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  hide: boolean = true;
  isOn: boolean = true;
  constructor(private service: UserService, private router: Router) { }

  ngOnInit(): void {
    this.isOn= true;
  }

  onSubmit(form: NgForm){
    //check passwords to be the same before sending
    let user = new RegisterModel();
    user.email = form.value['Email'];
    user.password = form.value['Password'];
    user.username = form.value['Username'];

    this.service.register(user).subscribe((response) => console.log(response));
    this.router.navigate(['/login']);
  }

  changeHide() {
    this.hide = !this.hide;
  }

}
