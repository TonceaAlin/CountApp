import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {FilePickerComponent} from "./components/file-picker/file-picker.component";
import {HomeComponent} from "./components/home/home.component";
import {LoginComponent} from "./components/login/login.component";
import {RegisterComponent} from "./components/register/register.component";
import {ResultsComponent} from "./components/results/results.component";

const routes: Routes = [
  {path: "upload-photo", component:FilePickerComponent},
  { path: '', pathMatch: 'full', redirectTo: 'login' },
  {path: 'home', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'results', component: ResultsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
