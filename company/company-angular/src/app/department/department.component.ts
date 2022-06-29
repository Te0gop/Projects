import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Department } from './department';
import { DepartmentService } from './department.service';

@Component({
  selector: 'app-department',
  templateUrl: './department.component.html',
  styleUrls: ['./department.component.css']
})
export class DepartmentComponent implements OnInit {

  public department: Department | undefined;
  public departments: Department[] | undefined;
  public editDepartment: Department | undefined;
  public deleteDepartment: Department | undefined;

  constructor(private departmentService: DepartmentService) { }


  ngOnInit(): void {
    this.getDepartments();
  }

  public getDepartments(): void {
    this.departmentService.getDepartments().subscribe(
      (response: Department[]) => {
        this.departments = response;
      },
      (error: HttpErrorResponse) => alert(error.message));
  }

  public onAddDepartment(addForm: NgForm): void {
    document.getElementById('add-department-form')!.click();
    this.departmentService.addDepartment(addForm.value).subscribe(
      (response: Department) => {
        console.log(response);
        this.getDepartments();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
  }

  public searchDepartment(key: string): void {
    console.log(key);
    const results: Department[] = [];
    for (const department of this.departments!) {
      if (this.department?.name.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || this.department?.description.toLowerCase().indexOf(key.toLowerCase()) !== -1) {
        results.push(department);
      }
    }
    this.departments = results;
    if (results.length === 0 || !key) {
      this.getDepartments();
    }
  }

  public onUpdateDepartment(department: Department): void {
    this.departmentService.updateDepartment(department).subscribe(
      (response: Department) => {
        console.log(response);
        this.getDepartments();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onDeleteDepartment(departmentId: number): void {
    this.departmentService.deleteDepartment(departmentId).subscribe(
      (response: void) => {
        console.log(response);
        this.getDepartments();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onOpenModal(department: Department | null, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');

    if(mode === 'add') {
      button.setAttribute('data-target', '#addDepartmentModal');
    }
    if(mode === 'edit') {
      button.setAttribute('data-target', '#updateDepartmentModal');
    }
    if(mode === 'delete') {
      button.setAttribute('data-target', '#deleteDepartmentModal');
    }

    container?.appendChild(button);
    button.click();
  }

}
