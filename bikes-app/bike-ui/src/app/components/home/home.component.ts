import { Component, OnInit } from '@angular/core';
import { BikeService } from 'src/app/services/bike.service';
import { FormGroup, FormControl, Validators } from '@angular/forms'
import { Observable } from 'rxjs';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  models: string[] = [
    'Carbon fiber race series',
    'Full suspension',
    'Time trial blade'
  ];
  bikeform!: FormGroup;
  validMessage: string = '';
  
  constructor(private bikeService: BikeService) { }

  ngOnInit(): void {
    this.bikeform = new FormGroup({
      name: new FormControl('', Validators.required),
      email: new FormControl('', Validators.required),
      phone: new FormControl('', Validators.required),
      model: new FormControl('', Validators.required),
      serialNumber: new FormControl('', Validators.required),
      purchasePrice: new FormControl('', Validators.required),
      purchaseDate: new FormControl('', Validators.required),
      contact: new FormControl()
    }); 
  }

  submitRegistration() {
    if(this.bikeform.valid) {
      this.validMessage = "Your bike registration has been submitted. Thank you!";
      this.bikeService.createBikeRegistration(this.bikeform.value).subscribe({
        next: data => {
          this.bikeform.reset();
          return true;
        },
        error: error => {
            return new Error(error);
        },
        complete: () => this.validMessage = "Please fill out the form before submitting!"
      })
    }
  }

}
