import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, ParamMap, Router} from "@angular/router";
import {AccountService} from "../services/account.service";
import {Account} from "../model/Account";
import {Transaction} from "../model/Transaction";
import {TransactionService} from "../services/transaction.service";

@Component({
  selector: 'app-client-profile',
  templateUrl: './client-profile.component.html',
  styleUrls: ['./client-profile.component.css']
})
export class ClientProfileComponent implements OnInit {


  transactions: Transaction[];
  transactionsSent: Transaction[];
  transactionsReceived: Transaction[];

  username: string;
  client: Account;
  submitted = false;


  constructor(private route: ActivatedRoute, private router: Router, private accountService: AccountService, private transactionService: TransactionService) {
    this.route.paramMap.subscribe((param: ParamMap) => {
      this.username = param.get('username');
      console.log(this.username);
      this.accountService.getClientByUsername(this.username)
        .subscribe(data => {
          console.log(data);
          this.client = data;
          this.transactionService.getAllTransactionsById(this.client['id']).subscribe(res => {
            this.transactions = res;
          }, error => {
            console.log(error);
          });
          this.transactionService.getAllTransactionsByReceiverId(this.client['id']).subscribe(res => {
            this.transactionsReceived = res;
          }, error => {
            console.log(error);
          });
          this.transactionService.getAllTransactionsBySenderId(this.client['id']).subscribe(res => {
            this.transactionsSent = res;
          }, error => {
            console.log(error);
          });
        }, error => console.log(error));
    });
  }

  ngOnInit() {
    this.client = new Account();
  }

  gotoList() {
    this.router.navigate(['/home']);
  }

  gotoProfile(username: string) {
    this.router.navigate(['/profile', username]);
  }

}
