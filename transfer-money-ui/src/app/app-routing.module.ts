import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {AdminPanelComponent} from "./admin-panel/admin-panel.component";
import {CreateClientComponent} from "./create-client/create-client.component";
import {ClientProfileComponent} from "./client-profile/client-profile.component";
import {ClientPanelComponent} from "./client-panel/client-panel.component";
import {UserAuthGuard} from "./guards/user-auth.guard";


const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'login', component: LoginComponent},
  {path: 'admin', component: AdminPanelComponent,  canActivate: [UserAuthGuard]},
  {path: 'add-client', component: CreateClientComponent, canActivate: [UserAuthGuard]},
  {path: 'profile/:username', component: ClientProfileComponent,  canActivate: [UserAuthGuard]},
  {path: 'home', component: ClientPanelComponent,  canActivate: [UserAuthGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}



