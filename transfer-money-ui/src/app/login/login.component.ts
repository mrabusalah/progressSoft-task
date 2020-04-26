import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {AccountService} from '../services/account.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  username: string;
  password: string;

  constructor(private route: ActivatedRoute,
              private accountService: AccountService,
              private router: Router,
              private fb: FormBuilder
  ) {

  }

  ngOnInit(): void {
    localStorage.clear();
    this.loginForm = this.fb.group({
      username: new FormControl(null, Validators.required),
      password: new FormControl(null, Validators.required)
    });
  }

  onSubmit() {
    this.accountService.userLogin(
      this.loginForm.value.username,
      this.loginForm.value.password
    ).subscribe(
      res => {

        localStorage.setItem('access_token', res.access_token);
        localStorage.setItem('refresh_token', res.refresh_token);
        localStorage.setItem('id', res.userId);
        localStorage.setItem('username', res.username);

        Swal.fire({
          position: 'center',
          icon: 'success',
          title: 'Your are login now ',
          footer: 'Welcome Back ' + localStorage.getItem('username'),
          showConfirmButton: false,
          timer: 2000
        });
        this.router.navigate([`/home`]);
      },
      error => {
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'Something went wrong!'
        });
        console.error(error);
      }
    );
  }
}
