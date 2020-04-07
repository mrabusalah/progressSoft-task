import {Component, OnInit} from '@angular/core';
import Swal from "sweetalert2";
import {Account} from "../model/Account";
import {AccountService} from "../services/account.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-create-client',
  templateUrl: './create-client.component.html',
  styleUrls: ['./create-client.component.css']
})
export class CreateClientComponent implements OnInit {

  client: Account = new Account();
  submitted = false;

  constructor(private accountService: AccountService, private router: Router) {
  }

  ngOnInit(): void {
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
      }, error => {
        Swal.fire({
          icon: 'error',
          title: 'Opps...',
          text: 'There is an issue with adding Client!',
        });
        console.log(error)
      });
    this.client = new Account();
    this.gotoList();
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }

  gotoList() {
    this.router.navigate(['']);
  }

}
