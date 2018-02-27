package com.javapoint;

import org.assertj.core.internal.IterableElementComparisonStrategy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class Item{
	
	private int no;
	private String itemName;

	Item(){
		
	}
	Item(int no, String name){
		this.no = no;
		this.itemName = name;
	}
	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


	@Override
	public String toString(){
		return ("no:" + no + " item:" + itemName);
	}
}

/* commented to replace for 
 * Dependency injection through contructor.
class Store{
	Item item;
	
	Store(){
		item = new Item(1,"computer");
	}
	
	void showStoreItem() {
		System.out.println(item);
	}
}

public class SpringDependencyInjectionConstructor {

	public static void main(String args[]) {
		
		new Store().showStoreItem();
	}
}


**/


class Store{

	private Item item;
	
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	
	Store(){
		
	}
	Store(Item item){
		this.item = item;
	}
	
	public void showStoreItem() {
		System.out.println(item);
	}
}


public  class SpringDependencyInjectionConstructor{
	
	public static void main(String args[]) {
		
		ApplicationContext context =  new ClassPathXmlApplicationContext("SpringDependencyInjectionConstructor.xml");
		Store store = (Store) context.getBean("store");
		store.showStoreItem();
	}
}