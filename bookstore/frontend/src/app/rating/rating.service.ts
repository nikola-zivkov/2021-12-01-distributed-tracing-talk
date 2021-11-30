import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RatingService {

  constructor(private http: HttpClient) { }

  canRateBook(): Observable<void> {
    return this.http.get<void>('http://localhost:8080/actuator/rate-book-enabled');
  }

  rateBook(uuid: string, rating: number): void {
    this.http.put('http://localhost:8080/api/books/' + uuid + '/rating', { 'rating': rating }).subscribe();
  }
}
