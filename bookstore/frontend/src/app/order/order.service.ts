import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) { }

  createOrder(bookUuid: string): Observable<void> {
    return this.http.post<void>('http://localhost:8080/api/orders', { 'bookUuid': bookUuid });
  }
}
