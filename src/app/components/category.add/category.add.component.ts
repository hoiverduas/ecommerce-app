import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../../services/category.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Category } from '../../common/category';

@Component({
  selector: 'app-category.add',
  templateUrl: './category.add.component.html',
  styleUrl: './category.add.component.css'
})
export class CategoryAddComponent implements OnInit{

  id:number=0;
  name:string='';

  constructor(private categoryService:CategoryService,private router:Router,private activatedRoute:ActivatedRoute){}


  ngOnInit(): void {

    this.getCategoryId();
  }


  addCategory(){
    console.log(this.name);
    let category = new Category(this.id,this.name)
    this.categoryService.createCotegory(category).subscribe(
      res =>{
        this.router.navigate(['admin/category']);
      }
    );
  }


  getCategoryId(){
    this.activatedRoute.params.subscribe(
      category =>{
        let id = category['id']
        if(id){
          console.log('valor de la variable id :'+ id);
          this.categoryService.getCategoryById(id).subscribe(
            data =>{
              this.id = data.id;
              this.name = data.name;
            }
          )
        }
      }
    );
  }




}
