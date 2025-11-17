import { User } from "./User";
import { WorkerCut } from "./WorkerCut";

export class WorkerDetails {
    idDetalle_Trabajador!: number;
    horario_Trabajador!: Date;
    especializacion!: String;
    ausencias!:any
    imagen !: String;
    corte_Trabajador_idCorte_Trabajador!:WorkerCut[];
    usuario_idUsuario!:User;

}   