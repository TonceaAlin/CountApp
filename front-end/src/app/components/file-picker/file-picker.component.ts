import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {HttpClient, HttpEventType, HttpResponse} from "@angular/common/http";
import {tap} from "rxjs/operators";
import {Prediction} from "../../../model/prediction";
import {any} from "codelyzer/util/function";
import {FileService} from "../../services/file.service";
import {Detection} from "../../domain/detection";
import {DomSanitizer, SafeResourceUrl} from "@angular/platform-browser";
import {NgForm} from "@angular/forms";
import {DatePipe} from "@angular/common";
import {Session} from "../../domain/session";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-file-picker',
  templateUrl: './file-picker.component.html',
  styleUrls: ['./file-picker.component.scss']
})
export class FilePickerComponent{

  @Output() setCounts = new EventEmitter<Prediction[]>()
  sessionStarted: boolean = false;
  fileName = '';
  progress = 0;
  selectedFile? : File;
  detection: undefined;
  image? : SafeResourceUrl;
  images : SafeResourceUrl[] = [];
  numberOfApples : number[] = [];
  displayImage : boolean = false;
  selectedFiles: string[] = [];
  file : any;
  loading: boolean = false;
  constructor(private service : FileService, private sanitizer: DomSanitizer, public datepipe: DatePipe
  ,private snackBar: MatSnackBar) {
  }

  onFileSelected(event: any) {

    for (let i = 0 ; i < event.target.files.length; i++){
      this.selectedFiles.push(event.target.files[i])
      this.file = event.target.files[i];
    }

    const sessionId = localStorage.getItem("SessionId");
    if (event.target.files.length > 0) {
      console.log(event.target.files)
      this.selectedFile = this.file;
      this.fileName = this.file.name;
      const formData = new FormData();
      this.images = []
      this.numberOfApples = []
      for (let j = 0 ; j  < this.selectedFiles.length; j++){
        formData.append("files[]", this.selectedFiles[j]);
      }
      // @ts-ignore
      formData.append("sessionId", sessionId);
      this.loading = true;
      this.service.countApples(formData).subscribe(
        (detect: any) => {
              this.selectedFiles = []
              this.detection = detect;
              console.log(detect);
              detect.forEach( (element : Detection) => {
                  this.images?.push(this.sanitizer.bypassSecurityTrustResourceUrl('data:image/jph;base64,' + element.imageBytes))
                  this.numberOfApples.push(Number(element.noDetectedApples))
                }
              )

              // this.image = this.sanitizer.bypassSecurityTrustResourceUrl('data:image/jpg;base64,' + detect.imageBytes);
              this.displayImage = true;
              this.loading = false;
        },
        (err : any) => {
          console.log(err);
          this.progress = 0;
        }
      )
    }
  }
  startSession(form: NgForm){
    if(form.value.name == ""){
      return
    }
    this.sessionStarted = true;
    let name = form.value.name
    let now = new Date();
    let time = this.datepipe.transform(now, 'medium');
    let user = localStorage.getItem("Id")
    let session = new Session(name, time, user);
    this.service.startSession(session).subscribe((result: any) =>{
      localStorage.setItem("SessionId", String(result))
      this.snackBar.open("session started", "OK", {
        duration: 3000
      })
    });
    form.value.name = ""
  }

  stopSession(){
    this.sessionStarted = false;
    this.snackBar.open("session stoped, results saved", "OK", {
    duration: 3000
  })

  }
}
