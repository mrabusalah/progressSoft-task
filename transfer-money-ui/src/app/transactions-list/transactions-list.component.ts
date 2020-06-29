import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import {TransactionService} from '../services/transaction.service';
import {Transaction} from '../model/Transaction';
import {MatSort} from '@angular/material/sort';
import * as moment from 'moment';

@Component({
  selector: 'app-transactions-list',
  templateUrl: './transactions-list.component.html',
  styleUrls: ['./transactions-list.component.css']
})
export class TransactionsListComponent implements OnInit {
  displayedColumns: string[] = ['id', 'from', 'to', 'amount', 'date'];
  dataSource: any;
  moment;

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;

  constructor(private transactionService: TransactionService) {
  }

  ngOnInit() {
    this.transactionService.getAllTransactions().subscribe(res => {
      this.dataSource = new MatTableDataSource<Transaction>(res);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    }, error => {
      console.log(error);
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}
