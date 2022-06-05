import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {FilePickerComponent} from "./components/file-picker/file-picker.component";
import {HomeComponent} from "./components/home/home.component";

const routes: Routes = [
  {path: "upload-photo", component:FilePickerComponent},
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  {path: 'home', component: HomeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
