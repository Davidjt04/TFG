import { User } from "./User";

export class Review {
  idResenia!: number;
  estrellas!: number;
  hora!: Date;
  usuario_idUsuario!: User;
}