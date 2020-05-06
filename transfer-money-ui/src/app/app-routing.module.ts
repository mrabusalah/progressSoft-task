import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './login/login.component';
import {AdminPanelComponent} from './admin-panel/admin-panel.component';
import {CreateClientComponent} from './create-client/create-client.component';
import {ClientProfileComponent} from './client-profile/client-profile.component';
import {UserAuthGuard} from './guards/user-auth.guard';
import {HomeComponent} from './home/home.component';
import {ClientPanelComponent} from './client-panel/client-panel.component';
import {ClientListComponent} from './client-list/client-list.component';
import {TransactionsListComponent} from './transactions-list/transactions-list.component';
import {NotFound404Component} from "./not-found404/not-found404.component";


const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'transactions-list', redirectTo: '/transactions-list/page/1', pathMatch: 'full', canActivate: [UserAuthGuard]},
  {path: 'login', component: LoginComponent},
  {path: 'admin', component: AdminPanelComponent, canActivate: [UserAuthGuard]},
  {path: 'add-client', component: CreateClientComponent},
  {path: 'profile/:username', component: ClientProfileComponent},
  {path: 'clients', component: ClientPanelComponent, canActivate: [UserAuthGuard]},
  {path: 'clients-list', component: ClientListComponent, canActivate: [UserAuthGuard]},
  {path: 'transactions-list/page/:pageId', component: TransactionsListComponent, canActivate: [UserAuthGuard]},
  {path: 'home', component: HomeComponent, canActivate: [UserAuthGuard]},
  {path: '**', pathMatch: 'full', redirectTo: '/404'},
  {path: '404', component: NotFound404Component}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}



