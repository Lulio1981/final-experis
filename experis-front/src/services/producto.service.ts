import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { URLENDPOINT } from 'src/app/config/app.config';
import { Producto } from 'src/models/producto';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  private httpHeaders = new HttpHeaders({
    "Content-Type": "application/json",
  });


  constructor(
    private httpclient : HttpClient
  ) { }

  listarProductos():Observable<Producto[]>{
    return this.httpclient.get<Producto[]>(`${URLENDPOINT}/productosg`)
  }
  
  registrarProducto(producto:Producto):Observable<Producto>{
    return this.httpclient.post<Producto>(`${URLENDPOINT}/productos`,producto,{headers:this.httpHeaders})
  }

  obtenerProducto(cod_producto:number):Observable<Producto>{
    return this.httpclient.get<Producto>(`${URLENDPOINT}/productosi/${cod_producto}`)
  }

  actualizarProducto(producto:Producto):Observable<Producto>{
    return this.httpclient.put<Producto>(`${URLENDPOINT}/productosa`,producto,{headers:this.httpHeaders})
  }

  eliminarProducto(producto:Producto):Observable<Producto>{
    return this.httpclient.delete<Producto>(`${URLENDPOINT}/productose/${producto.cod_producto}`,{headers:this.httpHeaders})
  }
}
