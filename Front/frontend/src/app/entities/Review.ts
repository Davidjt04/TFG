import { User } from "./User";

export class Review {
  idResenia!: number;
  estrellas!: number;
  hora!: Date;
  resenia!: string;
  user!: User;
}