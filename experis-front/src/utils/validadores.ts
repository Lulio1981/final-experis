export class Validadores {

    //public telefonos : string ='([0-9]{9,15})';
    //public numericos : string ='\d*';
    constructor(
        public telefonos : string ='([0-9]{9,15})',
        public numericos : string ='\d*',
    ){

    }
validarRUC(){

}

getTelefonos(){
    return this.telefonos;
}

}
