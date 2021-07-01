import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { URLENDPOINT } from 'src/app/config/app.config';
import { Usuario } from 'src/models/usuario';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private httpHeaders = new HttpHeaders({
    "Content-Type": "application/json",
  });

  constructor(
    private httpclient : HttpClient

  ) { }

  listarUsuarios():Observable<Usuario[]>{
    return this.httpclient.get<Usuario[]>(`${URLENDPOINT}/usuariosg`)
  }
  
  registrarUsuario(usuario:Usuario):Observable<Usuario>{
    return this.httpclient.post<Usuario>(`${URLENDPOINT}/usuarios`,usuario,{headers:this.httpHeaders})
  }

  obtenerUsuario(cod_usuario:number):Observable<Usuario>{
    return this.httpclient.get<Usuario>(`${URLENDPOINT}/usuariosi/${cod_usuario}`)
  }

  actualizarUsuario(usuario:Usuario):Observable<Usuario>{
    return this.httpclient.put<Usuario>(`${URLENDPOINT}/usuariosa`,usuario,{headers:this.httpHeaders})
  }
  eliminarUsuario(usuario:Usuario):Observable<Usuario>{
    return this.httpclient.delete<Usuario>(`${URLENDPOINT}/usuariose/${usuario.cod_usuario}`,{headers:this.httpHeaders})
}
}