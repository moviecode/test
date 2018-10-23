package com.itaiti.soft;

class Invoice {
	public void printInvoice() {
		System.out.println("This is the content of the invoice!");
	}
}

class Decorator extends Invoice {
	protected Invoice ticket;
	public Decorator(Invoice t) {
		ticket = t;
	}
	public void printInvoice() {
		if(ticket != null) {
			ticket.printInvoice();	//	1
		}
	}
}

class HeadDecorator extends Decorator {
	public HeadDecorator(Invoice t) {
		super(t);
	}
	public void printInvoice() {
		System.out.println("This is the header of the invoice!");
		//2
		// ticket.printInvoice();
		super.printInvoice();
	}
}

class FootDecorator extends Decorator {
	public FootDecorator(Invoice t) {
		super(t);
	}
	public void printInvoice() {
		// 3
		// ticket.printInvoice();
		super.printInvoice();
		System.out.println("This is the footnote of the invoice!");
	}
}

public class ExtendTest {
	public static void main(String[] args) {
		Invoice t = new Invoice();
		Invoice ticket;
		// 4
		ticket = new FootDecorator(new HeadDecorator(t));
		ticket.printInvoice();
		System.out.println("------------------------");
		// 5
		ticket = new FootDecorator(new HeadDecorator(null));
		//ticket = new FootDecorator(new HeadDecorator(new Decorator(null)));
		ticket.printInvoice();
	}
}

