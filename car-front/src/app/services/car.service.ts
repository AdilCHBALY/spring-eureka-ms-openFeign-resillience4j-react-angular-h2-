import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CarService {

  constructor(private _http: HttpClient) { }

  addCar(data:any):Observable<any>{
    return this._http.post("http://localhost:8888/CAR-SERVICE/api/car",data)
  }

  getCars():Observable<any>{
    return this._http.get("http://localhost:8888/CAR-SERVICE/api/car")
  }

  updateCar(id: number, data: any): Observable<any> {
    return this._http.put(`http://localhost:8888/CAR-SERVICE/api/car/${id}`, data);
  }

  deleteCar(id: number): Observable<any> {
    return this._http.delete(`http://localhost:8888/CAR-SERVICE/api/car/${id}`);
  }
}
