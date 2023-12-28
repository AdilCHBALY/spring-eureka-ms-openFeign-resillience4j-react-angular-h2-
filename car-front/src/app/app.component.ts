import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { CommonModule, DOCUMENT } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatDialog, MatDialogModule} from "@angular/material/dialog"
import { CarAddEditComponent } from './car-add-edit/car-add-edit.component';
import {HttpClientModule } from '@angular/common/http';
import {MatPaginator, MatPaginatorModule} from '@angular/material/paginator';
import {MatSort, MatSortModule} from '@angular/material/sort';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import { CarService } from './services/car.service';
import {MatFormFieldModule} from '@angular/material/form-field';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet,MatIconModule,MatButtonModule,MatToolbarModule,MatDialogModule,HttpClientModule,MatTableModule,MatPaginatorModule,MatSortModule,MatFormFieldModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit{


  displayedColumns: string[] = ['id', 'brand', 'model', 'matricue','client','action'];
  dataSource!: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  title = 'car-front';

  constructor(private _dialog: MatDialog,
    private _carService:CarService,
    @Inject(DOCUMENT) private document : Document){}

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  ngOnInit(): void {
    this.getCars()
  }

  getCars(){
    this._carService.getCars().subscribe({
      next:(res)=>{
        this.dataSource=new MatTableDataSource(res)
        this.dataSource.sort = this.sort;
        this.dataSource.paginator=this.paginator
      },
      error:(err)=>{
        console.log(err);
      }
    })
  }
  
  deleteCar(id:number){
    this._carService.deleteCar(id).subscribe({
      next:(res)=>{
        alert('Car Deleted !')
        this.getCars()
      },  
      error:(err)=>{
        console.log(err);
      }
    })
  }

  openAddEditCarForm(){
    const dialogRef = this._dialog.open(CarAddEditComponent)
    dialogRef.afterClosed().subscribe({
      next:(res)=>{
        if(res){
          this.getCars()
        }
      },
      error:(err)=>{
        console.log(err);
      }
    })
  }
  goToUrl():void{
    this.document.location.href = "http://localhost:3000"
  }

  openEditForm(data:any){
    const dialogRef = this._dialog.open(CarAddEditComponent,{
      data
    })

    dialogRef.afterClosed().subscribe({
      next:(res)=>{
        if(res){
          this.getCars()
        }
      },
      error:(err)=>{
        console.log(err);
      }
    })
  }
}
