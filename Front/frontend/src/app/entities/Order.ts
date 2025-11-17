import { Purse } from "./Purse";
import { User } from "./User";

export class Order {
    idPedido!: number;
    precio_Total!: number;
    fecha_Realizacion!: Date;
    estado!:String
    usuario_idUsuario!: User;
    monedero_idTransacccion_Monedero!: Purse;
}   