import { Purse } from "./Purse";
import { User } from "./User";

export class Order {
    idPedido!: number;
    Precio_Total!: number;
    Fecha_Realizacion!: Date;
    Estado!:String
    Usuario_idUsuario!: User;
    Monedero_idTransacccion_Monedero!: Purse;
}   