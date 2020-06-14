import {Component, OnInit} from '@angular/core';
import {AccountService} from '../services/account.service';
import Swal from 'sweetalert2';
import {Router} from '@angular/router';

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.css']
})
export class ClientListComponent implements OnInit {

  clients: Account[];

  constructor(private accountService: AccountService,
              private router: Router) {
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
      );
    });
  }

  deleteUser(id: number) {
    Swal.fire({
      title: 'Are you sure?',
      text: 'You won\'t be able to revert this!',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
      if (result.value) {
        this.accountService.deleteClient(id).subscribe(res => {
          Swal.fire(
            'Deleted!',
            'Your file has been deleted.',
            'success'
          );
          this.gotoPage();
        }, error => {
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Something went wrong!',
          });
          console.log(error);
        });
      }
    });
  }

  gotoPage() {
    this.router.navigate(['/clients-list']);
  }
}
