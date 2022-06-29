import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Directorate } from './directorate';

@Injectable({
  providedIn: 'root'
})
export class DirectorateService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getDirectorates(): Observable<Directorate[]> {
    return this.http.get<Directorate[]>(`${this.apiServerUrl}/directorates`);
  }

  public addDirectorate(directorate: Directorate): Observable<Directorate> {
    return this.http.post<Directorate>(`${this.apiServerUrl}/directorates`, directorate);
  }

  public updateDirectorate(directorate: Directorate): Observable<Directorate> {
    return this.http.put<Directorate>(`${this.apiServerUrl}/directorates`, directorate);
  }

  public deleteDirectorate(directorateId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/directorates/${directorateId}`);
  }
}
