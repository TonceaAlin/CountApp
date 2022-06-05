import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {HttpClient, HttpEventType, HttpResponse} from "@angular/common/http";
import {tap} from "rxjs/operators";
import {Prediction} from "../../../model/prediction";
import {any} from "codelyzer/util/function";
import {FileService} from "../../services/file.service";
import {Detection} from "../../domain/detection";
import {DomSanitizer, SafeResourceUrl} from "@angular/platform-browser";

@Component({
  selector: 'app-file-picker',
  templateUrl: './file-picker.component.html',
  styleUrls: ['./file-picker.component.scss']
})
export class FilePickerComponent{

  @Output() setCounts = new EventEmitter<Prediction[]>()
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
  constructor(private service : FileService, private sanitizer: DomSanitizer) {
  }

  onFileSelected(event: any) {

    for (let i = 0 ; i < event.target.files.length; i++){
      this.selectedFiles.push(event.target.files[i])
      this.file = event.target.files[i];
      console.log(event.target.files[i])
    }


    if (event.target.files.length > 0) {
      console.log(event.target.files.length)
      this.selectedFile = this.file;
      this.fileName = this.file.name;
      const formData = new FormData();
      this.images = []
      this.numberOfApples = []
      for (let j = 0 ; j  < this.selectedFiles.length; j++){
        formData.append("files[]", this.selectedFiles[j]);
      }

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
        },
        (err : any) => {
          console.log(err);
          this.progress = 0;
        }
      )

      // upload$.pipe(
      //   tap((result: any) => console.log(result)
      //   )).subscribe();
    }
  }

}
