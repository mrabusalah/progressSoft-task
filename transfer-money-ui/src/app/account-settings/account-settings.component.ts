import {Component, OnInit} from '@angular/core';
import Swal from 'sweetalert2';
import {Account} from '../model/Account';
import {AccountService} from '../services/account.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-account-settings',
  templateUrl: './account-settings.component.html',
  styleUrls: ['./account-settings.component.css']
})
export class AccountSettingsComponent implements OnInit {
  client: Account = new Account();

  constructor(private accountService: AccountService, private router: Router) {
    this.accountService.getClientById(+localStorage.getItem('id')).subscribe(res => {
      this.client = res;
    });
  }


  ngOnInit(): void {
    this.client = new Account();
  }

  save() {
    this.accountService.updateClient(this.client, this.client.id)
      .subscribe(data => {
        this.gotoHome();
        Swal.fire({
          icon: 'success',
          title: 'Done...',
          text: 'Client Updated successfully!',
        });
      }, error => {
        Swal.fire({
          icon: 'error',
          title: 'Opps...',
          text: 'There is an issue with updating Client information!',
        });
        console.log(error);
      });
  }

  gotoHome() {
    this.router.navigate(['/profile', localStorage.getItem('username')]);
  }
}
