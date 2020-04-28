import {Component, OnInit} from '@angular/core';
import {Transaction} from '../model/Transaction';
import {TransactionService} from '../services/transaction.service';
import {Account} from '../model/Account';
import Swal from 'sweetalert2';
import {ActivatedRoute, ParamMap} from "@angular/router";

@Component({
  selector: 'app-transactions-list',
  templateUrl: './transactions-list.component.html',
  styleUrls: ['./transactions-list.component.css']
})
export class TransactionsListComponent implements OnInit {

  transactions: Transaction[];
  client: Account;
  numbers: number[];
  pageNumber: number;

  constructor(private activatedRoute: ActivatedRoute, private transactionService: TransactionService) {
  }


  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe((param: ParamMap) => {
      this.pageNumber = +param.get('page');
      console.log(+param.get('problemId'));
      this.getTransactions(this.pageNumber);
    });
  }

  getTransactions(pageNumber: number) {
    this.transactionService.getAllTransactionsPage(pageNumber).subscribe(res => {
      this.transactions = res;
      console.log(this.transactions);
      this.numbers = Array(+(this.transactions['totalPages']));
      console.log(this.numbers);
      this.transactions['content'].sort((leftSide, rightSide): number => {
        if (leftSide.id > rightSide.id) {
          return -1;
        }
        if (leftSide.id < rightSide.id) {
          return 1;
        }
        return 0;
      });
    }, error => {
      console.log(error);
      Swal.fire(
        'Error',
        'Error while getting transactions :(',
        'error'
      );
    });
  }
}


