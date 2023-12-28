import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  constructor(private _http: HttpClient) { }


  getClients():Observable<any>{
    return this._http.get("http://localhost:8888/CLIENT-SERVICE/api/client")
  }
}
