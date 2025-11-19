import { WorkerDetails } from './WorkerDetails';

export class WorkerSchedule {
  id!: number;
  fecha!: string;           // ISO string "2025-11-19"
  hora!: string;            // ISO string "09:00:00"
  disponible!: boolean;
  detalleTrabajador!: WorkerDetails;
}
