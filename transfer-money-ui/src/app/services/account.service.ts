import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Router} from '@angular/router';
import {Account} from '../model/Account';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private baseUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient, private router: Router) {
  }

  userLogin(username: string, password: string): Observable<any> {
    const headers = {
      headers: new HttpHeaders({
        Authorization: 'Basic ' + btoa('app:passApp')
      })
    };
    return this.http.post(`http://localhost:8080/oauth/token?grant_type=password&username=${username}&password=${password}`,
      {},
      headers);
  }

  getClientsList(): Observable<any> {
    return this.http.get(`${this.baseUrl}/clients/all-clients`);
  }

  getClientById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/clients/${id}`);
  }

  deleteClient(clientId: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/clients/delete-client/${clientId}`, {responseType: 'text'});
  }

  createClient(client: Account): Observable<object> {
    return this.http.post(`${this.baseUrl}/clients/create-client`, client);
  }

  updateClient(client: Account, id: number): Observable<object> {
    return this.http.put(`${this.baseUrl}/clients/update-client/${id}`, client);
  }

  getClientByUsername(username: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/profile/${username}`);
  }

  transferMoney(sender: number, receiver: number, amount: number): Observable<any> {
    return this.http.post(`${this.baseUrl}/transfer/${sender}/${receiver}/${amount}`, amount);
  }

  userLogout() {
    this.router.navigate(['/login']);
    return localStorage.clear();
  }

  loggedIn() {
    return !!localStorage.getItem('access_token');
  }

  getToken() {
    return localStorage.getItem('access_token');
  }
}

