import {Component, OnInit, TemplateRef} from '@angular/core';
import {AccountService} from '../services/account.service';
import {Account} from '../model/Account';
import {Router} from '@angular/router';
import {MatDialog} from '@angular/material/dialog';
import Swal from 'sweetalert2';
import {TransactionService} from '../services/transaction.service';
import {Transaction} from '../model/Transaction';

@Component({
  selector: 'app-client-panel',
  templateUrl: './client-panel.component.html',
  styleUrls: ['./client-panel.component.css']
})
export class ClientPanelComponent implements OnInit {

  clients: Account[];
  currentClient: Account;
  value: number;
  amount: number;
  transaction: Transaction;

  constructor(private accountService: AccountService,
              private router: Router,
              private dialog: MatDialog,
              private transactionService: TransactionService) {
    this.reloadData();
  }

  ngOnInit() {
    this.reloadData();
    this.accountService.getClientByUsername(localStorage.getItem('username')).subscribe(res => {
      this.currentClient = res;
    }, error => {
      console.log(error);
    });
  }

  private reloadData() {
    this.accountService.getClientsList().subscribe(res => {
      this.clients = res;
    }, error => console.log(error));
  }

  transferMoney(id: number, amount: number) {
    if (id === this.currentClient.id) {
      Swal.fire(
        'Cancelled',
        'You cannot transfer money for your self',
        'error'
      );
    } else {
      this.accountService.transferMoney(this.currentClient.id, id, this.amount)
        .subscribe(res => {
          let timerInterval;
          Swal.fire({
            title: 'wait until finishing the process !',
            html: 'I will close in <b></b> milliseconds.',
            timer: 2000,
            timerProgressBar: true,
            onBeforeOpen: () => {
              Swal.showLoading();
              timerInterval = setInterval(() => {
                const content = Swal.getContent();
                if (content) {
                  const b = content.querySelector('b');
                  if (b) {
                    b.textContent = String(Swal.getTimerLeft());
                  }
                }
              }, 100);
            },
            onClose: () => {
              clearInterval(timerInterval);
              {
                Swal.fire(
                  'Converted!',
                  'Your Money has been converted.',
                  'success'
                );
              }
            }
          });
          // save the operation in the history of transactions
          console.log("request arrived here");
          this.transactionService.addNewTransaction(this.currentClient.id, id, amount).subscribe(req => {
            console.log(req);
          }, error => {
            console.log(error);
          });
        }, error => {
          console.log(error);
          Swal.fire(
            'Cancelled',
            'Error while transfer money :(',
            'error'
          );
        });
    }
  }


  hide(): void {
    this.dialog.closeAll();
  }

  openDialog(template: TemplateRef<any>) {
    this.dialog.open(template);
  }

  notME(client: Account): boolean {
    return (client.clientUsername !== localStorage.getItem('username'));
  }
}
