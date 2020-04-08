import {Component, OnInit} from '@angular/core';
import {AccountService} from "../services/account.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.css']
})
export class ClientListComponent implements OnInit {

  clients: Account[];

  constructor(private accountService: AccountService) {
  }

  ngOnInit(): void {
    this.reloadData();
  }

  reloadData() {
    this.accountService.getClientsList().subscribe(res => {
      this.clients = res;
    }, error => {
      console.log(error);
      Swal.fire(
        'Error',
        'Error while getting clients :(',
        'error'
      )
    });
  }
}
