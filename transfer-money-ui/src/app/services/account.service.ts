import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {Account} from "../model/Account";

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private baseUrl = "http://localhost:8080/api";
  public currentUser: Account;
  username: string;


  constructor(private http: HttpClient, private router: Router) {
  }

  userLogin(user: any): Observable<any> {
    return this.http.post<any>(`http://localhost:8080/oauth/token`, user);
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

  getClientByUsername(username: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/profile/${username}`);
  }

  transferMoney(sender: number, receiver: number, amount: number): Observable<any> {
    return this.http.post(`${this.baseUrl}/transfer/${sender}/${receiver}/${amount}`, amount);
  }

  userLogout() {
    this.router.navigate(["/login"]);
    return localStorage.clear();
  }

  loggedIn() {
    return !!localStorage.getItem("token");
  }

  getToken() {
    return localStorage.getItem("token");
  }
}

