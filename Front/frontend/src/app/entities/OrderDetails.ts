import { Article } from "./Article";

export class OrderDetails {
    idDetalle_Pedido!: number;
    precio_Unitario!: number;
    cantidad_Unitaria!: Date;
    articulo_idArticulo !: Article[];
    pedido_idPedido !: number;
}   