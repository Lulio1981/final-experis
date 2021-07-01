import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Validadores } from 'src/utils/validadores';
import { Producto } from '../../models/producto';
import { ProductoService } from '../../services/producto.service';
import swal from 'sweetalert2';


@Component({
  selector: 'app-producto',
  templateUrl: './producto.component.html',
  styleUrls: ['./producto.component.css']
})
export class ProductoComponent implements OnInit {


  listaProductos: Producto[] = [];
  validadores: Validadores = new Validadores();
  parametro: string = 'I';
  excelIcon: string = '../../../assets/img/excel.png';
  formGroup: FormGroup;
  producto: Producto = new Producto();
  btnSumbit: string = 'Registrar';



  formProducto: FormGroup = this.fb.group({
    nombre: ['', [Validators.required, Validators.maxLength(50)]],
    precio: ['', [Validators.required,Validators.maxLength(70)]],
  });

  constructor(
    private productoService: ProductoService,
    private fb: FormBuilder,
  ) { }

  ngOnInit(): void {
    this.getProductos();
  }


  getProductos() {
    this.productoService
      .listarProductos()
      .subscribe((data) => {
        console.log(data);
        this.listaProductos = data;
      });
  }

  validaCampos(campo: string) {
    let casoError = this.formProducto.controls[campo].errors;
    return this.formProducto.controls[campo].errors && this.formProducto.controls[campo].touched
  }

  limpiarCampos() {
    this.formProducto.reset();
  }

  tipoRegistroProducto(valor: string) {
    this.parametro = valor;
    this.limpiarCampos();
  }

  verificarDatos() {
    console.log(this.btnSumbit);
    if (this.btnSumbit == 'Registrar') {
      this.producto.cod_producto=null;
      this.registrar();
    } else if (this.btnSumbit == 'Actualizar') {
      this.actualizar();
    }
  }


  obtenerSeleccion(id: number) {
    this.btnSumbit = 'Actualizar';
    this.parametro = 'I';
    this.buscarProductoXId(id);
  }

  obtenerValorOpcion(event: Event) {
    this.btnSumbit = (<HTMLInputElement>event.target).value;
    this.parametro = 'I';
  }


  // eliminarProducto(id: number) {
    // console.log(id);
    // this.productoService.obtenerProducto(id)
      // .subscribe((data) => {
        // this.producto=data;
        // this.eliminar();
      // });
  // }

  eliminarProducto(producto:Producto) {
    this.producto=producto;
        this.eliminar();
  }

  registrar(): void {
    this.productoService.registrarProducto(this.producto).subscribe((response: any) => {
      this.getProductos();
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
        this.productoService.actualizarProducto(this.producto).subscribe((response: any) => {
          this.getProductos();
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
        this.productoService.eliminarProducto(this.producto).subscribe((response: any) => {
          this.getProductos();
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

  buscarProductoXId(idProducto: number) {
    this.productoService.obtenerProducto(idProducto).subscribe((response) => {
      this.producto = response;
    });
  }

}
