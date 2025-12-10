import { Article } from './Article';

export interface CartHasArticleResponseDTO {
  cantidad: number;
  article: Article | null;
  idCarrito: number;
}