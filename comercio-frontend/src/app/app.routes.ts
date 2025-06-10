import { Routes } from '@angular/router';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', loadComponent: () => import('./login/login').then(m => m.LoginComponent) },
  { path: 'register', loadComponent: () => import('./register/register').then(m => m.RegisterComponent) },
  { path: 'dashboard', loadComponent: () => import('./dashboard/dashboard').then(m => m.DashboardComponent) },
  { path: 'profile', loadComponent: () => import('./profile/profile').then(m => m.ProfileComponent) },
  { path: 'recover-password', loadComponent: () => import('./recover-password/recover-password').then(m => m.RecoverPasswordComponent) },
  { path: 'admin', loadComponent: () => import('./admin-panel/admin-panel').then(m => m.AdminPanelComponent) }
];
