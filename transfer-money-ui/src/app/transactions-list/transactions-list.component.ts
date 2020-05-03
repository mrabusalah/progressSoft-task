import {Component, OnInit} from '@angular/core';
import {TransactionService} from '../services/transaction.service';
import {Transaction} from '../model/Transaction';
import {ActivatedRoute, ParamMap, Router} from '@angular/router';

@Component({
  selector: 'app-transactions-list',
  templateUrl: './transactions-list.component.html',
  styleUrls: ['./transactions-list.component.css']
})
export class TransactionsListComponent implements OnInit {

  data: Transaction[];
  numberOfPages: number;
  currentPage: number;

  constructor(private transactionService: TransactionService, private route: ActivatedRoute, private router: Router) {
    this.route.paramMap.subscribe((param: ParamMap) => {
      this.currentPage = +param.get('pageId');
    });
  }


  ngOnInit(): void {
    this.loadData();
  }

  loadData() {
    this.transactionService.getAllTransactionsPage(this.currentPage , 1).subscribe(res => {
      this.data = res.content;
      this.numberOfPages = res.totalPages;
      console.log(this.numberOfPages);
      console.log(this.data);
    }, error => {
      console.log(error);
    });
  }

  allowedToShowNext(): boolean {
    return (this.currentPage !== this.numberOfPages);
  }

  allowedToShowPrevious() {
    return (this.currentPage !== 1);
  }

  counter(numberOfPages: number) {
    return new Array(numberOfPages);
  }

  gotoPage(pageNumber: number) {
    this.router.navigate(['/transactions-list/page', pageNumber]);
  }
}
