import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class UserService {
  private userUrl = 'http://localhost:8080/usuarios';

  constructor(private http: HttpClient) {}

  getUsers() {
    return this.http.get<any[]>(this.userUrl);
  }

  updateUser(id: number, user: any) {
    return this.http.put(`${this.userUrl}/${id}`, user);
  }

  deleteUser(id: number) {
    return this.http.delete(`${this.userUrl}/${id}`);
  }
}

