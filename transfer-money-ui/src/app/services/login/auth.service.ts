import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {map} from 'rxjs/operators';
import {Observable} from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class AuthService {

    // BASE_PATH: 'http://localhost:8080'
    USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser';

    public username: string;
    public password: string;
    private user: string;

    constructor(private http: HttpClient) {
    }


    authenticationService(username: string, password: string): Observable<any> {
        return this.http.post(`http://localhost:8080/oauth/token`,
            {headers: {authorization: this.createBasicAuthToken()}}).pipe(map((res) => {
            console.log('hi dania');
            this.username = username;
            this.password = password;
            // this.registerSuccessfulLogin(username, password);
        }));
    }

    createBasicAuthToken(): string {
        return 'Basic Auth' + btoa('app:passApp');
    }
}
