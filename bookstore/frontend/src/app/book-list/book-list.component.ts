import { Component, OnInit } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzModalService } from 'ng-zorro-antd/modal';
import { Observable } from 'rxjs';

import { Book } from '../book/book';
import { BookService } from '../book/book.service';
import { OrderService } from '../order/order.service';
import { RatingService } from '../rating/rating.service';
import { RecommendationService } from '../recommendation/recommendation.service';

@Component({
  selector: 'book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit {

  books!: Book[];
  selectedBook?: Book;
  similarBooks: Book[] = [];
  canRateBook!: Observable<void>;

  constructor(private bookService: BookService,
    private ratingService: RatingService,
    private recommendationService: RecommendationService,
    private orderService: OrderService,
    private modalService: NzModalService,
    private messageService: NzMessageService) {
  }

  ngOnInit(): void {
    this.bookService.findBooks().subscribe(books => this.books = books);
    this.canRateBook = this.ratingService.canRateBook();
  }

  showBookDetails(uuid: string): void {
    this.selectedBook = this.books.find(book => book.uuid == uuid);
    this.canRateBook = this.ratingService.canRateBook();
    this.recommendationService
      .recommendSimilarBooks(uuid)
      .subscribe(similarBooks => this.similarBooks = similarBooks, () => this.similarBooks = []);
  }

  rateBook(): void {
    this.ratingService.rateBook(this.selectedBook!.uuid!, this.selectedBook!.rating!);
  }

  createOrder(): void {
    this.modalService.confirm({
      nzTitle: 'Create order?',
      nzOnOk: () => this.orderService.createOrder(this.selectedBook!.uuid!)
        .subscribe(() => this.messageService.success('Order created'),
          () => this.messageService.error('Order failed'))
    });
  }
}
