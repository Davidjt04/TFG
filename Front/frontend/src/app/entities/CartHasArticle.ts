  
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

