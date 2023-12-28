import { HttpClientModule } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import { CarService } from '../services/car.service';
import { ClientService } from '../services/client.service';
import { Client } from '../../models/client.model';

@Component({
  selector: 'app-car-add-edit',
  standalone: true,
  imports: [MatDialogModule,MatButtonModule,MatFormFieldModule,MatInputModule,MatSelectModule,ReactiveFormsModule,HttpClientModule],
  templateUrl: './car-add-edit.component.html',
  styleUrl: './car-add-edit.component.css'
})
export class CarAddEditComponent implements OnInit{
  carForm!: FormGroup;


  clients : Client[]=[]

  constructor(private _fb: FormBuilder,
    private _carService:CarService,
    private _clientservice:ClientService,
    private _dialogRef:MatDialogRef<CarAddEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data:any
    ){
    this.carForm = this._fb.group({
      brand:'',
      model:'',
      matricue:'',
      client:''
    })
  }
  ngOnInit(): void {
    this.getClients()
    this.carForm.patchValue(this.data)
  }

  onFormSubmit(){
    if(this.carForm.valid){
      if(this.data){
        console.log(this.carForm.value);
        this._carService.updateCar(this.data.id,this.carForm.value).subscribe({
          next:(res)=>{
            alert("Car Updated successfully")
            this.carForm.reset()
            this._dialogRef.close(true)
          },
          error:(err)=>{
            console.log(err);
          }
        })
      }else{
        this._carService.addCar(this.carForm.value).subscribe({
          next:(res)=>{
            alert("Car added successfully")
            this.carForm.reset()
            this._dialogRef.close(true)
          },
          error:(err)=>{
            console.log(err);
          }
        })
      }
    }
  }

  getClients(){
    this._clientservice.getClients().subscribe({
      next:(res)=>{
        this.clients=res
      },
      error:(err)=>{
        console.log(err);
      }
    });
  }
}
