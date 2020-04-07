import {Component, OnInit, TemplateRef} from '@angular/core';
import {AccountService} from "../services/account.service";
import {Account} from "../model/Account";
import {Router} from "@angular/router";
import {MatDialog} from "@angular/material/dialog";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-client-panel',
  templateUrl: './client-panel.component.html',
  styleUrls: ['./client-panel.component.css']
})
export class ClientPanelComponent implements OnInit {

  clients: Account[];
  currentClient: Account;
  value: number;
  amount: number;

  constructor(private accountService: AccountService, private router: Router, private dialog: MatDialog, private snackBar: MatSnackBar) {
    this.reloadData()
  }

  //TODO security issue
  ngOnInit() {
    this.reloadData();
    this.accountService.getClientByUsername(localStorage.getItem("username")).subscribe(res => {
      this.currentClient = res;
    }, error => {
      console.log(error);
    });
  }

  private reloadData() {
    this.accountService.getClientsList().subscribe(res => {
      this.clients = res;
    })
  }

  transferMoney(id: number) {
    console.log(id);
    console.log(this.currentClient);

    this.accountService.transferMoney(this.currentClient['id'], id, this.amount)
      .subscribe(res => {
        this.snackBar.open('Transfer Completed Successfully!', 'success', {
          duration: 2000
        });
      }, error => {
        console.log(error)
      });
  }


  hide(): void {
    this.dialog.closeAll();
  }

  openDialog(template: TemplateRef<any>) {
    this.dialog.open(template);
  }


  // viewClient(username: string) {
  //   this.router.navigate(['/profile', username]);
  // }


  // deleteAccount(id: number) {
  //   Swal.fire({
  //     title: 'Are you sure?',
  //     text: "You won't be able to revert this!",
  //     icon: 'warning',
  //     showCancelButton: true,
  //     confirmButtonColor: '#3085d6',
  //     cancelButtonColor: '#d33',
  //     confirmButtonText: 'Yes, delete it!'
  //   }).then((result) => {
  //     if (result.value) {
  //       this.accountService.deleteClient(id)
  //         .subscribe(
  //           data => {
  //             console.log(data);
  //             this.reloadData();
  //           },
  //           error => {
  //             Swal.fire({
  //               icon: 'error',
  //               title: 'Oops...',
  //               text: 'Something went wrong!',
  //               footer: 'can\'t delete the client'
  //             })
  //             console.log(error);
  //           });
  //       Swal.fire(
  //         'Deleted!',
  //         'The client has been deleted.',
  //         'success'
  //       )
  //     }
  //   });
  // }
  //

  // async onClientClick(receiverId: number) {
  //   console.log(this.accountService.currentUser)
  //   const {value: money} = await Swal.fire({
  //     title: 'Transfer Money',
  //     input: 'number',
  //     showCloseButton: true,
  //     inputPlaceholder: 'Enter Amount'
  //   });
  //   if (money) {
  //     const swalWithBootstrapButtons = Swal.mixin({
  //       customClass: {
  //         confirmButton: 'btn btn-success',
  //         cancelButton: 'btn btn-danger'
  //       },
  //       buttonsStyling: false
  //     });
  //
  //     swalWithBootstrapButtons.fire({
  //       title: 'Are you sure?',
  //       text: "You won't be able to revert this!",
  //       icon: 'warning',
  //       showCancelButton: true,
  //       confirmButtonText: 'Yes, transfer it!',
  //       cancelButtonText: 'No, cancel!',
  //       reverseButtons: true
  //     }).then((result) => {
  //       if (result.value) {
  //
  //             this.accountService.transferMoney(this.accountService.currentUser.id, receiverId, money).subscribe(data => {
  //               let timerInterval;
  //               Swal.fire({
  //                 title: 'wait until finishing the process !',
  //                 html: 'I will close in <b></b> milliseconds.',
  //                 timer: 2000,
  //                 timerProgressBar: true,
  //                 onBeforeOpen: () => {
  //                   Swal.showLoading();
  //                   timerInterval = setInterval(() => {
  //                     const content = Swal.getContent();
  //                     if (content) {
  //                       const b = content.querySelector('b');
  //                       if (b) {
  //                         b.textContent = String(Swal.getTimerLeft())
  //                       }
  //                     }
  //                   }, 100)
  //                 },
  //                 onClose: () => {
  //                   clearInterval(timerInterval);
  //                   {
  //                     swalWithBootstrapButtons.fire(
  //                       'Converted!',
  //                       'Your Money has been converted.',
  //                       'success'
  //                     )
  //                   }
  //                 }
  //               });
  //             }, error => {
  //               console.log("transferMoney");
  //               swalWithBootstrapButtons.fire(
  //                 'Cancelled',
  //                 'Error while transfer money :(',
  //                 'error'
  //               )
  //               console.log(error);
  //             });
  //
  //
  //
  //
  //
  //
  //       } else if (result.dismiss === Swal.DismissReason.cancel) {
  //         swalWithBootstrapButtons.fire(
  //           'Cancelled',
  //           'Your Money is safe :)',
  //           'error'
  //         )
  //       }
  //     });
  //   }
  // }


}
