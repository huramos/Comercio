import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private userUrl = 'http://localhost:8080/usuarios';

  constructor(private http: HttpClient) {}

  login(username: string, password: string) {
    return this.http.get<any[]>(this.userUrl).pipe(
      map(users => users.find(u => u.username === username && u.password === password))
    );
  }

  register(user: any) {
    return this.http.post(this.userUrl, user);
  }
}