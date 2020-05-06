import {Component, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, ParamMap, Router} from '@angular/router';
import {AccountService} from '../services/account.service';
import {Account} from '../model/Account';
import {Transaction} from '../model/Transaction';
import {TransactionService} from '../services/transaction.service';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';

@Component({
  selector: 'app-client-profile',
  templateUrl: './client-profile.component.html',
  styleUrls: ['./client-profile.component.css']
})
export class ClientProfileComponent implements OnInit {

  username: string;
  client: Account;

  displayedColumns: string[] = ['id', 'from', 'to', 'amount', 'date'];

  transactions: any;
  transactionsBySender: any;
  transactionsByReceiver: any;

  @ViewChild(MatPaginator, {static: true}) transactionsPaginator: MatPaginator;
  @ViewChild(MatPaginator, {static: true}) transactionsPaginatorSender: MatPaginator;
  @ViewChild(MatPaginator, {static: true}) transactionsPaginatorReceiver: MatPaginator;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private accountService: AccountService,
              private transactionService: TransactionService) {
    this.route.paramMap.subscribe((param: ParamMap) => {
      this.username = param.get('username');

      this.accountService.getClientByUsername(this.username)
        .subscribe(data => {
          this.client = data;

          this.transactionService.getAllTransactionsById(this.client.id).subscribe(res => {
            this.transactions = new MatTableDataSource<Transaction>(res);
            this.transactions.paginator = this.transactionsPaginator;
          }, error => {
            console.log(error);
          });


          this.transactionService.getAllTransactionsByReceiverId(this.client.id).subscribe(res => {
            this.transactionsByReceiver = new MatTableDataSource<Transaction>(res);
            this.transactionsByReceiver.paginator = this.transactionsPaginatorReceiver;
          }, error => {
            console.log(error);
          });


          this.transactionService.getAllTransactionsBySenderId(this.client.id).subscribe(res => {
            this.transactionsBySender = new MatTableDataSource<Transaction>(res);
            this.transactionsBySender.paginator = this.transactionsPaginatorSender;
          }, error => {
            console.log(error);
          });
        }, error => console.log(error));
    });
  }

  ngOnInit() {
    this.client = new Account();
  }
}
