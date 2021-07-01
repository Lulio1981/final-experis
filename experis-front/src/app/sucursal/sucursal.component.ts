import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Validadores } from 'src/utils/validadores';
import { Sucursal } from '../../models/sucursal';
import swal from 'sweetalert2';
import { SucursalService } from '../../services/sucursal.service';


@Component({
  selector: 'app-sucursal',
  templateUrl: './sucursal.component.html',
  styleUrls: ['./sucursal.component.css']
})
export class SucursalComponent implements OnInit {

  listaSucursal: Sucursal[] = [];
  validadores: Validadores = new Validadores();
  parametro: string = 'I';
  excelIcon: string = '../../../assets/img/excel.png';
  formGroup: FormGroup;
  sucursal: Sucursal = new Sucursal();
  btnSumbit: string = 'Registrar';
  formSucursal: FormGroup = this.fb.group({
    codigo: [''],
    nombre: ['', [Validators.required, Validators.maxLength(70)]],
  });

  constructor(
    private sucursalService: SucursalService,
    private fb: FormBuilder,
  ) { }

  ngOnInit(): void {
    this.getSucursal();
  }

  getSucursal() {
    this.sucursalService
      .listarSucursales()
      .subscribe((data) => {
        console.log(data);
        this.listaSucursal = data;
      });
  }

  validaCampos(campo: string) {
    let casoError = this.formSucursal.controls[campo].errors;
    return this.formSucursal.controls[campo].errors && this.formSucursal.controls[campo].touched
  }

  limpiarCampos() {
    this.formSucursal.reset();
  }

  tipoRegistroSucursal(valor: string) {
    this.parametro = valor;
    this.limpiarCampos();
  }

  verificarDatos() {
    console.log(this.btnSumbit);
    if (this.btnSumbit == 'Registrar') {
      this.sucursal.cod_sucursal=null;
      this.registrar();
    } else if (this.btnSumbit == 'Actualizar') {
      this.actualizar();
    }
  }


  obtenerSeleccion(id: number) {
    this.btnSumbit = 'Actualizar';
    this.parametro = 'I';
    this.buscarSucursalXId(id);
  }

  obtenerValorOpcion(event: Event) {
    this.btnSumbit = (<HTMLInputElement>event.target).value;
    this.parametro = 'I';
  }

  eliminarSucursal(sucursal:Sucursal) {
    this.sucursal=sucursal;
        this.eliminar();
  }

  registrar(): void {
    this.sucursalService.registrarSucursal(this.sucursal).subscribe((response: any) => {
      this.getSucursal();
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
        this.sucursalService.actualizarSucursal(this.sucursal).subscribe((response: any) => {
          this.getSucursal();
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
        this.sucursalService.eliminarSucursal(this.sucursal,).subscribe((response: any) => {
          this.getSucursal();
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

  buscarSucursalXId(idProducto: number) {
    this.sucursalService.obtenerSucursal(idProducto).subscribe((response) => {
      this.sucursal = response;
    });
  }
}
