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
  default: any;

  constructor(private accountService: AccountService, private router: Router) {
  }

  ngOnInit(): void {
    this.default = 'abusalah';
  }

  save() {
    this.accountService.createClient(this.client)
      .subscribe(data => {
        Swal.fire({
          icon: 'success',
          title: 'Done...',
          text: 'Client added successfully!',
        });
        console.log(data);
        this.gotoHome();
      }, error => {
        Swal.fire({
          icon: 'error',
          title: 'Opps...',
          text: 'There is an issue with adding Client!',
        });
        console.log(error);
      });
    this.client = new Account();
  }

  onSubmit() {
    this.save();
  }


  gotoHome() {
    this.router.navigate(['']);
  }
}
