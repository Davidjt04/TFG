// export class CartHasArticle {
//     articulo_idArticulo!: number;
//     cantidad!: number;
//     fecha_agrega!: Date;
//     imagen!:String
//     precio!:number
//     categorta!:String
// }   
import { IDCartHasArticle } from '../interfaces/IDCartHasArticle';
import { Article } from './Article';
import { Cart } from './Cart';

export interface CartHasArticle {
  id: IDCartHasArticle;
  cantidad: number;
  cart: Cart;
  article: Article;
  fecha_agrega?: Date;
}

