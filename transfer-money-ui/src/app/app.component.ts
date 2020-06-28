import {Component, OnInit} from '@angular/core';
import {AccountService} from './services/account.service';
import Swal from 'sweetalert2';
import {Router} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'transfer money';

  constructor(public accountService: AccountService, private router: Router) {
  }

  onLogout() {
    Swal.fire({
      title: 'Are you sure?',
      text: 'You won\'t be able to revert this!',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, Logout!'
    }).then((result) => {
      if (result.value) {
        this.accountService.userLogout();
        Swal.fire(
          'Logged Out!',
          'Your session has been ended.',
          'success'
        );
      }
    });
  }

  ngOnInit(): void {
  }

  gotoProfile() {
    console.log('hi abusalah');
    this.router.navigate(['/profile', localStorage.getItem('username')]);
  }

  gotoLogin() {
    this.router.navigate(['login']);
  }

  gotoSignUp() {
    this.router.navigate(['add-client']);
  }

  gotoSettings() {
    this.router.navigate(['settings']);
  }

  gotoChangePassword() {
    this.router.navigate(['change-password']);
  }
}
