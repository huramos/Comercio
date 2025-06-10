import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common'; // ← Necesario para *ngFor, *ngIf
import { UserService } from '../user';

@Component({
  selector: 'app-admin-panel',
  standalone: true, // ← Asegúrate de que esto esté incluido
  imports: [CommonModule], // ← Agrega esta línea
  templateUrl: './admin-panel.html',
  styleUrls: ['./admin-panel.css']
})
export class AdminPanelComponent implements OnInit {
  users: any[] = [];
  posts: any[] = [];

  constructor(
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers() {
    this.userService.getUsers().subscribe(data => this.users = data);
  }

  deleteUser(id: number) {
    if (confirm('¿Eliminar este usuario?')) {
      this.userService.deleteUser(id).subscribe(() => this.loadUsers());
    }
  }

}
