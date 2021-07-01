import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { URLENDPOINT } from 'src/app/config/app.config';
import { Sucursal } from 'src/models/sucursal';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class SucursalService {

  private httpHeaders = new HttpHeaders({
    "Content-Type": "application/json",
  });

  constructor(
    private httpclient : HttpClient

  ) { }

  listarSucursales():Observable<Sucursal[]>{
    return this.httpclient.get<Sucursal[]>(`${URLENDPOINT}/sucursalesg`)
  }
  
  registrarSucursal(sucursal:Sucursal):Observable<Sucursal>{
    return this.httpclient.post<Sucursal>(`${URLENDPOINT}/sucursales`,sucursal,{headers:this.httpHeaders})
  }

  obtenerSucursal(cod_sucursal:number):Observable<Sucursal>{
    return this.httpclient.get<Sucursal>(`${URLENDPOINT}/sucursalesi/${cod_sucursal}`)
  }

  actualizarSucursal(sucursal:Sucursal):Observable<Sucursal>{
    return this.httpclient.put<Sucursal>(`${URLENDPOINT}/sucursalesa`,sucursal,{headers:this.httpHeaders})
  }

  eliminarSucursal(sucursal:Sucursal):Observable<Sucursal>{
    return this.httpclient.delete<Sucursal>(`${URLENDPOINT}/sucursalese/${sucursal.cod_sucursal}`,{headers:this.httpHeaders})
  }
}

