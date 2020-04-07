import {Component, OnInit} from '@angular/core';
import {AccountService} from "./services/account.service";
import Swal from "sweetalert2";
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements  OnInit{
  title = 'transfer money';
  // username = this.accountService.currentUser['username'];

  constructor(public accountService: AccountService, private router: Router) {
  }

  onLogout() {
    const swalWithBootstrapButtons = Swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success',
        cancelButton: 'btn btn-danger'
      },
      buttonsStyling: false
    });

    swalWithBootstrapButtons.fire({
      title: 'Are you sure?',
      text: "You won't be able to revert this!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes, logout!',
      cancelButtonText: 'No, cancel!',
      reverseButtons: true
    }).then((result) => {
      if (result.value) {
        swalWithBootstrapButtons.fire(
          'logged out!',
          'Your session has been ended.',
          'success'
        )
        this.accountService.userLogout();
      } else if (
        /* Read more about handling dismissals below */
        result.dismiss === Swal.DismissReason.cancel
      ) {
        swalWithBootstrapButtons.fire(
          'Cancelled',
          'Your session is safe :)',
          'error'
        )
      }
    });
  }

  ngOnInit(): void {
  }

  gotoProfile() {
    // this.router.navigate(['/profile', this.username]);
  }
}
