import {Component, OnInit} from '@angular/core';
import Swal from "sweetalert2";
import {AccountService} from "../services/account.service";

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.css']
})
export class AdminPanelComponent implements OnInit {

  jsonRes: string;

  constructor(private accountService: AccountService) {
  }

  ngOnInit(): void {
  }

  // createClient() {
  //   Swal.mixin({
  //     input: 'text',
  //     width: 700,
  //     confirmButtonText: 'Next &rarr;',
  //     showCancelButton: true,
  //     progressSteps: ['1', '2', '3', '4', '5', '6', '7', '8']
  //   }).queue([
  //     {
  //       title: 'Client Full name',
  //       text: 'Adding new client is easy',
  //       inputPlaceholder: 'ex : Abdalah AbuSalah'
  //     },
  //     {
  //       title: 'Client Username',
  //       text: 'Empty spaces',
  //       inputPlaceholder: 'ex : mr_abusalah'
  //     },
  //     {
  //       title: 'Client Password',
  //       text: 'Make it a strong one',
  //       input: 'password',
  //       inputPlaceholder: 'Enter Client Password'
  //     },
  //     {
  //       title: 'Client Balance',
  //       input: 'number',
  //       inputPlaceholder: 'ex : 1000'
  //     },
  //     {
  //       title: 'Client Phone number',
  //       input: 'number',
  //       inputPlaceholder: 'ex : 0791234567'
  //     },
  //     {
  //       title: 'Client Address',
  //       inputPlaceholder: 'ex : Amman'
  //     },
  //     {
  //       title: 'Client Description',
  //       inputPlaceholder: 'ex : just a user'
  //     },
  //     {
  //       title: 'Client Image',
  //       text: 'Just paste the URL of image',
  //       inputPlaceholder: 'www.site.com/profile-pic.png'
  //     }
  //
  //   ]).then((result) => {
  //     if (result.value) {
  //       this.jsonRes = "{"
  //         .concat("\"clientUsername\" : \"")
  //         .concat(result.value[1] + "\",")
  //
  //         .concat("\"clientPassword\" : \"")
  //         .concat(result.value[2] + "\",")
  //
  //         .concat("\"clientBalance\" : \"")
  //         .concat(result.value[3] + "\",")
  //
  //         .concat("\"clientName\" : \"")
  //         .concat(result.value[0] + "\",")
  //
  //         .concat("\"clientPhone\" : \"")
  //         .concat(result.value[4] + "\",")
  //
  //         .concat("\"clientAddress\" : \"")
  //         .concat(result.value[5] + "\",")
  //
  //         .concat("\"clientDescription\" : \"")
  //         .concat(result.value[6] + "\",")
  //
  //         .concat("\"clientProfilePic\" : \"")
  //         .concat(result.value[7] + "\"")
  //         .concat("}");
  //
  //       this.accountService.createClientFromAdmin(this.jsonRes)
  //         .subscribe(data => {
  //             console.log(data)
  //             Swal.fire({
  //               icon: 'success',
  //               title: 'Done...',
  //               text: 'Problem added successfully!',
  //             })
  //           }
  //           , error => {
  //             Swal.fire({
  //               icon: 'error',
  //               title: 'Opps...',
  //               html: `<h3>Problem while adding Client !</h3>
  //                   <pre><code>${this.jsonRes}</code></pre>`,
  //               confirmButtonText: 'Lovely!'
  //             });
  //             console.log(error)
  //           });
  //     }
  //   })
  // }
}
