import {Component, OnInit} from '@angular/core';
import {Account} from '../model/Account';
import {AccountService} from '../services/account.service';
import {Router} from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {
  client: Account = new Account();
  newPassword: string;

  constructor(private accountService: AccountService, private router: Router) {
    this.accountService.getClientById(+localStorage.getItem('id')).subscribe(res => {
      this.client = res;
    });
  }

  ngOnInit(): void {
    this.newPassword = '';
  }

  save() {
    this.accountService.updatePassword(this.client.id, this.newPassword)
      .subscribe(data => {
        this.gotoHome();
        Swal.fire({
          icon: 'success',
          title: 'Done...',
          text: 'Password Updated successfully!',
        });
      }, error => {
        Swal.fire({
          icon: 'error',
          title: 'Opps...',
          text: 'There is an issue with updating Password!',
        });
        console.log(error);
      });
    this.client = new Account();
  }

  gotoHome() {
    this.router.navigate(['/profile', localStorage.getItem('username')]);
  }
}
