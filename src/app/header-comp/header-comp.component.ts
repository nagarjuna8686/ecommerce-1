import { Component, OnInit, Output, EventEmitter} from '@angular/core';


@Component({
  selector: 'app-header-comp',
  templateUrl: './header-comp.component.html',
  styleUrls: ['./header-comp.component.css']
})
export class HeaderCompComponent implements OnInit {

 

  @Output() termUpdate:EventEmitter<string> = new EventEmitter()

  public term:string;

  constructor() { }

  ngOnInit(): void {}

  emitTermUpdate = (t:string) => {
    this.termUpdate.emit(t);
  } 

}
