import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { Book } from '../book/book';

@Injectable({
  providedIn: 'root'
})
export class RecommendationService {

  constructor(private http: HttpClient) { }

  recommendSimilarBooks(uuid: string): Observable<Book[]> {
    return this.http.get<Book[]>("http://localhost:8080/api/books/" + uuid + "/similar");
  }
}
