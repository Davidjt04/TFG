import { PredefinedCut } from "./PredefinedCut";
import { Service } from "./Service";
import { WorkerDetails } from "./WorkerDetails";

export class WorkerCut {
  idCorte_Trabajador!: number;
  duracion!: string; // LocalDateTime se recibe como string ISO
  precio!: number;
  workerDetail!: WorkerDetails; // relación @OneToOne
  predefinedCut!: PredefinedCut; // relación @ManyToOne
  services!: Service[]; // relación N:M
}
