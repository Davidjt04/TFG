import { User } from "./User";
import { WorkerCut } from "./WorkerCut";

export class WorkerDetails {
    idDetalle_Trabajador!: number;
    Horario_Trabajador!: Date;
    Especializacion!: String;
    Ausencias!:any
    Corte_Trabajador_idCorte_Trabajador!:WorkerCut[];
    Usuario_idUsuario!:User;
}   