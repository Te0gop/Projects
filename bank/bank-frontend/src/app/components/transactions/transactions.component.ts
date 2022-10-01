import { Component, OnInit } from '@angular/core';
import { Transaction } from 'src/app/models/transaction';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.css']
})
export class TransactionsComponent implements OnInit {

  transactions: Transaction[] = [
    {id: 1, name: 'John Smith', transactionId: '4443-5555-1266-1314'},
    {id: 2, name: 'Mark Lucas', transactionId: '1113-3244-1266-4355'},
    {id: 3, name: 'James Depth', transactionId: '4411-4431-1266-3436'},
    {id: 4, name: 'Luke Harris', transactionId: '3242-5789-1389-3464'},
  ];

  constructor() { }

  ngOnInit(): void {
  }

}
