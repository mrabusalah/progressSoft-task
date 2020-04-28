import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {LoginComponent} from './login/login.component';
import {SweetAlert2Module} from '@sweetalert2/ngx-sweetalert2';
import {RouterModule} from '@angular/router';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from '@angular/common/http';
import {AdminPanelComponent} from './admin-panel/admin-panel.component';
import {CreateClientComponent} from './create-client/create-client.component';
import {ClientProfileComponent} from './client-profile/client-profile.component';
import {ClientPanelComponent} from './client-panel/client-panel.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MaterialModule} from './shared/material/material.module';
import {HomeComponent} from './home/home.component';
import {ClientListComponent} from './client-list/client-list.component';
import {TransactionsListComponent} from './transactions-list/transactions-list.component';
import {TokenInterceptorService} from './services/token-interceptor.service';

@NgModule({
    declarations: [
        AppComponent,
        LoginComponent,
        AdminPanelComponent,
        CreateClientComponent,
        ClientProfileComponent,
        ClientPanelComponent,
        HomeComponent,
        ClientListComponent,
        TransactionsListComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        SweetAlert2Module,
        RouterModule,
        FormsModule,
        ReactiveFormsModule,
        HttpClientModule,
        BrowserAnimationsModule,
        MaterialModule
    ],
    providers: [HttpClient,
        {provide: HTTP_INTERCEPTORS, useClass: TokenInterceptorService, multi: true}
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
