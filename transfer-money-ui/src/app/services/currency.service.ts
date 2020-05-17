import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CurrencyService {
  private baseUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {
  }

  getCurrencyList(from: string, to: string, amount: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/convert-currency/${from}/${to}/${amount}`);
  }
}
