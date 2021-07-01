import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Validadores } from 'src/utils/validadores';
import { Usuario } from '../../models/usuario';
import swal from 'sweetalert2';
import { UsuarioService } from '../../services/usuario.service';
import { SucursalService } from '../../services/sucursal.service';
import { Sucursal } from 'src/models/sucursal';


@Component({
  selector: 'app-usuario',
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.css']
})
export class UsuarioComponent implements OnInit {

  listaUsuario: Usuario[] = [];
  listaSucursal: Sucursal[] = [];

  validadores: Validadores = new Validadores();
  parametro: string = 'I';
  excelIcon: string = '../../../assets/img/excel.png';
  formGroup: FormGroup;
  usuario: Usuario = new Usuario();
  btnSumbit: string = 'Registrar';
  formUsuario: FormGroup = this.fb.group({
    nombre: ['', [Validators.required, Validators.maxLength(50)]],
    user: ['', [Validators.required, Validators.maxLength(70)]],
    password: ['', [Validators.required, Validators.maxLength(70)]],
    sucursal: ['', [Validators.required, Validators.maxLength(70)]],
  });

  constructor(
    private usuarioService : UsuarioService,
    private sucursalService : SucursalService,
    private fb: FormBuilder,

  ) { }

  ngOnInit(): void {
    this.getSucursales();
    this.getUsuarios();
  }

  getUsuarios() {
    this.usuarioService
      .listarUsuarios()
      .subscribe((data) => {
        console.log(data);
        this.listaUsuario = data;
      });
  }

  getSucursales() {
    this.sucursalService
      .listarSucursales()
      .subscribe((data) => {
        console.log(data);
        this.listaSucursal = data;
      });
  }

  validaCampos(campo: string) {
    let casoError = this.formUsuario.controls[campo].errors;
    return this.formUsuario.controls[campo].errors && this.formUsuario.controls[campo].touched
  }
  limpiarCampos() {
    this.formUsuario.reset();
  }
  tipoRegistroSucursal(valor: string) {
    this.parametro = valor;
    this.limpiarCampos();
  }
  verificarDatos() {
    console.log(this.btnSumbit);
    if (this.btnSumbit == 'Registrar') {
      this.usuario.cod_usuario=null;
     this.registrar();
    } else if (this.btnSumbit == 'Actualizar') {
      this.actualizar();
    }
  }
  obtenerSeleccion(id: number) {
    this.btnSumbit = 'Actualizar';
    this.parametro = 'I';
    this.buscarUsuarioXId(id);
  }
  obtenerValorOpcion(event: Event) {
    this.btnSumbit = (<HTMLInputElement>event.target).value;
    this.parametro = 'I';
  }

  eliminarSucursal(usuario:Usuario) {
    this.usuario=usuario;
        this.eliminar();
  }


  registrar(): void {
    this.usuarioService.registrarUsuario(this.usuario).subscribe((response: any) => {
      this.getUsuarios();
      swal.fire({
        icon: 'success',
        width: '300px',
        text: response.mensaje,
        showConfirmButton: false,
        timer: 3000
      })
      document.getElementById('CerrarI').click();
    }, (err) => {
      swal.fire({
        icon: 'error',
        text: 'Ocurrió un error al registrar',
      })
    });
  }
  actualizar(): void {
    swal.fire({
      title: '¿Esta seguro que desea modificar la Empresa?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si, Guardar cambios'
    }).then((result) => {
      if (result.isConfirmed) {   
          this.usuarioService.actualizarUsuario(this.usuario).subscribe((response: any) => {
          this.getUsuarios();
          swal.fire({
            icon: 'success',
            width: '300px',
            text: response.mensaje,
            showConfirmButton: false,
            timer: 3000
          })
          document.getElementById('CerrarI').click();
        }, (err) => {
          swal.fire({
            icon: 'error',
            text: 'Ocurrió un error al actualizar',
          })
        });
      }
    })
  }
  eliminar(): void {
    swal.fire({
      title: '¿Esta seguro que desea modificar la Empresa?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si, Guardar cambios'
    }).then((result) => {
      if (result.isConfirmed) {       
        this.usuarioService.eliminarUsuario(this.usuario,).subscribe((response: any) => {
          this.getUsuarios();
          swal.fire({
            icon: 'success',
            width: '300px',
            text: response.mensaje,
            showConfirmButton: false,
            timer: 3000
          })
          document.getElementById('CerrarI').click();
        }, (err) => {
          swal.fire({
            icon: 'error',
            text: 'Ocurrió un error al eliminar',
          })
        });
      }
    })
  }
  buscarUsuarioXId(idProducto: number) {
    this.usuarioService.obtenerUsuario(idProducto).subscribe((response) => {
      this.usuario = response;
      console.log(this.usuario.sucursal.nombre);
    });
  }
}
