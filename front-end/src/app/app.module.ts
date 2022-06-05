import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FilePickerComponent } from './components/file-picker/file-picker.component';
import {MatIconModule} from "@angular/material/icon";
import {MatButtonModule} from "@angular/material/button";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {FileService} from "./services/file.service";
import {MatProgressBarModule} from "@angular/material/progress-bar";
import {MatCardModule} from "@angular/material/card";
import { TopBarComponent } from './components/top-bar/top-bar.component';
import {MatToolbarModule} from "@angular/material/toolbar";
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatListModule} from "@angular/material/list";
import {FormsModule} from "@angular/forms";
import { RegisterComponent } from './components/register/register.component';
import {MatInputModule} from "@angular/material/input";
import {UserService} from "./services/user.service";
import {DatePipe} from "@angular/common";


@NgModule({
  declarations: [
    AppComponent,
    FilePickerComponent,
    TopBarComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        MatIconModule,
        MatButtonModule,
        HttpClientModule,
        MatProgressBarModule,
        MatCardModule,
        MatToolbarModule,
        MatFormFieldModule,
        MatListModule,
        FormsModule,
        MatInputModule,
    ],
  providers: [FileService, UserService, DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
