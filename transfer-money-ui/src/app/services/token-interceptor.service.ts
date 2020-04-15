import {Injectable, Injector} from '@angular/core';
import {HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {AccountService} from "./account.service";

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const authService = this.injector.get(AccountService);
    const tokenizedReq = req.clone({
      headers: new HttpHeaders({
        'Authorization': `Bearer ${authService.getToken()}`
      })
    });
    return next.handle(tokenizedReq);
  }

  constructor(private injector: Injector) {
  }
}
