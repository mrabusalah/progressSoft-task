import {Component, OnInit} from '@angular/core';
import {Transaction} from '../model/Transaction';
import {TransactionService} from '../services/transaction.service';
import Swal from 'sweetalert2';
import {AccountService} from '../services/account.service';
import {Account} from '../model/Account';

@Component({
    selector: 'app-transactions-list',
    templateUrl: './transactions-list.component.html',
    styleUrls: ['./transactions-list.component.css']
})
export class TransactionsListComponent implements OnInit {

    transactions: Transaction[];
    client: Account;

    constructor(private transactionService: TransactionService, private accountService: AccountService) {
    }

    ngOnInit(): void {
        this.transactionService.getAllTransactions().subscribe(res => {
            this.transactions = res;
            this.transactions.sort((leftSide, rightSide): number => {
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
