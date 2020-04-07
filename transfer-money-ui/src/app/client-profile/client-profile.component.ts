import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, ParamMap, Router} from "@angular/router";
import {AccountService} from "../services/account.service";
import {Account} from "../model/Account";

@Component({
  selector: 'app-client-profile',
  templateUrl: './client-profile.component.html',
  styleUrls: ['./client-profile.component.css']
})
export class ClientProfileComponent implements OnInit {


  username: string;
  client: Account;
  submitted = false;

  // change id to username
  constructor(private route: ActivatedRoute, private router: Router, private accountService: AccountService) {
    this.route.paramMap.subscribe((param: ParamMap) => {
      this.username = param.get('username');
      console.log(this.username);
      this.accountService.getClientByUsername(this.username)
        .subscribe(data => {
          console.log(data);
          this.client = data;
        }, error => console.log(error));
    })
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
