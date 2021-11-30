import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { Book } from './book';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor(private http: HttpClient) { }

  findBooks(): Observable<Book[]> {
    return this.http.get<Book[]>('http://localhost:8080/api/books');
  }
}
