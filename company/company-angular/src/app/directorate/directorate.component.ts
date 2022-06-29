import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Directorate } from './directorate';
import { DirectorateService } from './directorate.service';

@Component({
  selector: 'app-directorate',
  templateUrl: './directorate.component.html',
  styleUrls: ['./directorate.component.css']
})
export class DirectorateComponent implements OnInit {
  public directorate: Directorate | undefined;
  public directorates: Directorate[] | undefined;
  public editDirectorate: Directorate | undefined;
  public deleteDirectorate: Directorate | undefined;

  constructor(private directorateService: DirectorateService) { }


  ngOnInit(): void {
    this.getDirectorates();
  }

  public getDirectorates(): void {
    this.directorateService.getDirectorates().subscribe(
      (response: Directorate[]) => {
        this.directorates = response;
      },
      (error: HttpErrorResponse) => alert(error.message));
  }

  public onAddDirectorate(addForm: NgForm): void {
    document.getElementById('add-directorate-form')!.click();
    this.directorateService.addDirectorate(addForm.value).subscribe(
      (response: Directorate) => {
        console.log(response);
        this.getDirectorates();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
  }

  public searchDirectorate(key: string): void {
    console.log(key);
    const results: Directorate[] = [];
    for (const employee of this.directorates!) {
      if (this.directorate?.name.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || this.directorate?.description.toLowerCase().indexOf(key.toLowerCase()) !== -1) {
        results.push(employee);
      }
    }
    this.directorates = results;
    if (results.length === 0 || !key) {
      this.getDirectorates();
    }
  }

  public onUpdateDirectorate(directorate: Directorate): void {
    this.directorateService.updateDirectorate(directorate).subscribe(
      (response: Directorate) => {
        console.log(response);
        this.getDirectorates();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onDeleteDirectorate(directorateId: number): void {
    this.directorateService.deleteDirectorate(directorateId).subscribe(
      (response: void) => {
        console.log(response);
        this.getDirectorates();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onOpenModal(directorate: Directorate | null, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');

    if(mode === 'add') {
      button.setAttribute('data-target', '#addDirectorateModal');
    }
    if(mode === 'edit') {
      button.setAttribute('data-target', '#updateDirectorateModal');
    }
    if(mode === 'delete') {
      button.setAttribute('data-target', '#deleteDirectorateModal');
    }

    container?.appendChild(button);
    button.click();
  }
}
