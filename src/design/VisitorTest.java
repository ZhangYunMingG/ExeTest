package design;

import java.util.ArrayList;

//  访问者模式
public class VisitorTest {
	public static void main(String[] args) {
		Book accountBook = new Book();
	    accountBook.add(new InAccept("卖商品", 10000));
	    accountBook.add(new InAccept("卖广告", 12000));
	    accountBook.add(new OutAccept("工资", 1000));
	    accountBook.add(new OutAccept("材料", 2000));
	
	    BookViewer boss = new Boss();
	    accountBook.show(boss);
	    System.out.println(((Boss) boss).inTotalNum); 
	    System.out.println(((Boss) boss).outTotalNum); 
	    
	    
	}
}

interface Accept
{
	public void accept(BookViewer bookViewer);
}

class OutAccept implements Accept
{
	private String name;
	private int number;
	public OutAccept(String _name, int _number) {
		name = _name;
		number = _number;
	}
	public void accept(BookViewer bookViewer) {
		bookViewer.view(this);
	}
	public String getName() {
		return name;
	}
	public int getNumber() {
		return number;
	}
}

class InAccept implements Accept
{
	private String name;
	private int number;
	public InAccept(String _name, int _number) {
		name = _name;
		number = _number;
	}
	public void accept(BookViewer bookViewer) {
		bookViewer.view(this);
	}
	public String getName() {
		return name;
	}
	public int getNumber() {
		return number;
	}
	
}

interface BookViewer
{
	public void view(InAccept inAccept);
	public void view(OutAccept inAccept);
}

class Boss implements BookViewer
{
	public int inTotalNum;
	public int outTotalNum;
	public void view(InAccept inAccept) {
		inTotalNum += inAccept.getNumber();
	}
	public void view(OutAccept inAccept) {
		outTotalNum += inAccept.getNumber();
	}
}

class Account implements BookViewer
{
	public void view(InAccept inAccept) {
//		inTotalNum += inAccept.getNumber();
	}
	public void view(OutAccept inAccept) {
//		outTotalNum += inAccept.getNumber();
	}
}

class Book 
{
	private ArrayList<Accept> accept = new ArrayList<Accept>();
	public void add(Accept a) {
		accept.add(a);
	}
	public void show(BookViewer bookViewer) {
		for (Accept accept2 : accept) {
			accept2.accept(bookViewer);
		}
	}
}