import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Department } from './department';

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getDepartments(): Observable<Department[]> {
    return this.http.get<Department[]>(`${this.apiServerUrl}/departments`);
  }

  public addDepartment(department: Department): Observable<Department> {
    return this.http.post<Department>(`${this.apiServerUrl}/departments`, department);
  }

  public updateDepartment(department: Department): Observable<Department> {
    return this.http.put<Department>(`${this.apiServerUrl}/departments`, department);
  }

  public deleteDepartment(departmentId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/departments/${departmentId}`);
  }
}
