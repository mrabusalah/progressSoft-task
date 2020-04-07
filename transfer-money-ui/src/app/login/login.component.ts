import {Component, OnInit} from '@angular/core';
import Swal from "sweetalert2";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {AccountService} from "../services/account.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;


  constructor(private route: ActivatedRoute, private accountService: AccountService, private router: Router, private fb: FormBuilder) {

  }

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      username: new FormControl(null, Validators.required),
      password: new FormControl(null, Validators.required)
    })
  }

  onSubmit() {
    this.accountService.userLogin(this.loginForm.value).subscribe(
      res => {
        localStorage.setItem("token", res.token);
        // TODO
        this.accountService.username = this.loginForm.controls['username'].value;

        this.accountService.getClientByUsername(this.loginForm.controls['username'].value)
          .subscribe(res => {
            console.log(res);
            this.accountService.currentUser = res;
          });
        Swal.fire({
          position: 'center',
          icon: 'success',
          title: 'Your are login now ',
          footer: 'Welcome Back ' + localStorage.getItem("username"),
          showConfirmButton: false,
          timer: 2000
        })
        if (this.loginForm.controls['username'].value === "bawa3neh") {
          this.router.navigate([`/admin`]);
        } else {
          this.router.navigate([`/home`]);
        }
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
