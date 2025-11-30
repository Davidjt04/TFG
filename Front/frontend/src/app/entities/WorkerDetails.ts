import { User } from "./User";
import { WorkerCut } from "./WorkerCut";

export class WorkerDetails {
  idDetalle_Trabajador!: number;
  horario_Trabajador!: Date;
  especializacion!: string;
  ausencias?: any;
  imagen!: string;
  nombre!: string;
  corte_Trabajador_idCorte_Trabajador!: WorkerCut[];
  usuario_idUsuario!: User;
}
