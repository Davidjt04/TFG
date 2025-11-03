import { Article } from "./Article";

export class OrderDetails {
    idDetalle_Pedido!: number;
    Precio_Unitario!: number;
    Cantidad_Unitaria!: Date;
    Articulo_idArticulo !: Article[];
    Pedido_idPedido !: number;
}   