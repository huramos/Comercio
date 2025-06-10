import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UserService } from '../user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-recover-password',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './recover-password.html',
  styleUrls: ['./recover-password.css']
})
export class RecoverPasswordComponent {
  username = '';
  newPassword = '';
  foundUser: any = null;

  constructor(private userService: UserService,  private router: Router) {}
  

  searchUser() {
    this.userService.getUsers().subscribe(users => {
      const user = users.find((u: any) => u.username === this.username);
      if (user) {
        this.foundUser = user;
      } else {
        alert('Usuario no encontrado');
        this.foundUser = null;
      }
    });
  }

  resetPassword() {
    if (!this.newPassword) {
      alert('La nueva contraseña no puede estar vacía');
      return;
    }

    const updatedUser = {
      username: this.foundUser.username,
      password: this.newPassword,
      role: this.foundUser.role
    };

    this.userService.updateUser(this.foundUser.id, updatedUser).subscribe(() => {
      alert('Contraseña actualizada correctamente');
      this.username = '';
      this.newPassword = '';
      this.foundUser = null;
    });
  }
  goTo(path: string) {
    this.router.navigate([`/${path}`]);
  }
}
