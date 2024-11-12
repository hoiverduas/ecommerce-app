import { Component, OnInit } from '@angular/core';
import { Category } from '../../common/category';
import { CategoryService } from '../../services/category.service';

@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html',
  styleUrl: './category-list.component.css'
})
export class CategoryListComponent implements OnInit{

categories:Category[]=[];

constructor(private categoryService:CategoryService){}
  ngOnInit(): void {
     this.listCategoies();
  }

  listCategoies(){
    this.categoryService.getCategoryList().subscribe(
      data => this.categories = data
    );
  }

  deleteCategoryById(id:number){
    console.log('id de categoria : ' + id);
    this.categoryService.deleteCategoryById(id).subscribe(
      ()=> this.listCategoies()
    );
  }



}
