import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UserService } from '../user';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './profile.html',
  styleUrls: ['./profile.css']
})
export class ProfileComponent implements OnInit {
  id: number = 0;
  username: string = '';
  password: string = '';
  role: string = '';

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    const user = JSON.parse(localStorage.getItem('user') || '{}');
    this.id = user.id;
    this.username = user.username;
    this.password = user.password;
    this.role = user.role;
  }

  updateProfile() {
    const updatedUser = {
      username: this.username,
      password: this.password,
      role: this.role
    };

    this.userService.updateUser(this.id, updatedUser).subscribe(() => {
      alert('Perfil actualizado correctamente');
      const userWithId = { ...updatedUser, id: this.id };
      localStorage.setItem('user', JSON.stringify(userWithId));
    });
  }
}
