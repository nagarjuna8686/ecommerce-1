import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-navbar-comp',
  templateUrl: './navbar-comp.component.html',
  styleUrls: ['./navbar-comp.component.css']
})
export class NavbarCompComponent implements OnInit {

  @Output() sortUpdate:EventEmitter <string> = new EventEmitter()

  @Input() sortOptions : any[] ;

  public selected:string;

  constructor() { }

  ngOnInit(): void {
    this.selected = this.sortOptions[0].value
  }

  emitSortUpdate = (s:string) => {
    this.sortUpdate.emit(s);
  } 

}
