import {Component, OnInit} from '@angular/core';
import {Transaction} from "../model/Transaction";
import {TransactionService} from "../services/transaction.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-transactions-list',
  templateUrl: './transactions-list.component.html',
  styleUrls: ['./transactions-list.component.css']
})
export class TransactionsListComponent implements OnInit {

  transactions: Transaction[];
  client: Account;

  constructor(private transactionService: TransactionService) {
  }

  ngOnInit(): void {
    this.transactionService.getAllTransactions().subscribe(res => {
      this.transactions = res;
    }, error => {
      console.log(error);
      Swal.fire(
        'Error',
        'Error while getting transactions :(',
        'error'
      )
    });
  }
}
