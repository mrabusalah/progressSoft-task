import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  private baseUrl = 'http://localhost:8080/api';


  constructor(private http: HttpClient, private router: Router) {
  }

  getAllTransactions(): Observable<any> {
    return this.http.get(`${this.baseUrl}/transactions`);
  }

  getAllTransactionsPage(pageNumber: number, numberOfItems: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/transactions/page?page=${pageNumber - 1}&size=${numberOfItems}`);
  }


  getAllTransactionsById(clientId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/transactions/${clientId}`);
  }

  getAllTransactionsByReceiverId(clientId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/transactions/receiver/${clientId}`);
  }

  getAllTransactionsBySenderId(clientId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/transactions/sender/${clientId}`);
  }

  addNewTransaction(sender: number, receiver: number, amount: number): Observable<any> {
    return this.http.post(`${this.baseUrl}/transactions/${sender}/${receiver}/${amount}`, {
      sender,
      receiver,
      amount
    });
  }
}
