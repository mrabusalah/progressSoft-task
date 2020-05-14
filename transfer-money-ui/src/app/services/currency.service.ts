import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CurrencyService {
  private baseUrl = 'assets/Common-Currency.json';

  constructor(private http: HttpClient) {
  }

  getCurrencyList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
}
