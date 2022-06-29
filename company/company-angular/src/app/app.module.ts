import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { EmployeeComponent } from './employee/employee.component';
import { EmployeeService } from './employee/employee.service';
import { DirectorateComponent } from './directorate/directorate.component';
import { DirectorateService } from './directorate/directorate.service';
import { DepartmentComponent } from './department/department.component';
import { DepartmentService } from './department/department.service';

@NgModule({
  declarations: [
    AppComponent,
    EmployeeComponent,
    DirectorateComponent,
    DepartmentComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [EmployeeService, 
    DirectorateService,
    DepartmentService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
