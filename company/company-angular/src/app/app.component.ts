import { Component, OnInit } from '@angular/core';
import { Employee } from './employee/employee';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { EmployeeService } from './employee/employee.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

  title: any;

  constructor() { }

  ngOnInit() {
  }



}
