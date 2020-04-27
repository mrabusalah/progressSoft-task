import {Injectable, Injector} from '@angular/core';
import {HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {AccountService} from './account.service';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (this.accountService.loggedIn()) {
      const authReq = req.clone({
        headers: new HttpHeaders({
          'Content-Type': 'application/x-www-form-urlencoded',
          Authorization: `Basic ` + btoa('app' + ':' + 'passApp')
        })
      });
      return next.handle(authReq);
    } else {
      return next.handle(req);
    }
  }

  constructor(private injector: Injector, private accountService: AccountService) {
  }
}
