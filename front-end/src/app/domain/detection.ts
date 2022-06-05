export class Detection{
  noDetectedApples : string;
  imageBytes : string

  constructor(number: string, img: string){
    this.noDetectedApples = number;
    this.imageBytes = img;
  }
}

