package com.example.ph23;

import java.util.ArrayList;

public class Inventory {
     private String category;
     private ArrayList<Item> items=new ArrayList<>();

     public Inventory(String category) {
          this.category = category;

     }
     public void newItem(String type,int quantity,double price){
          if (findItem(type,false)>=0){ //Check if the item exists
               System.out.println("Item already exists =" +type);
               return;
          }
          Item i = new Item(type);
          i.setPrice(price);
          i.setQuantity(quantity);
          items.add(i);
     }
     public void newItem(String brand,String type,int quantity,double price){
          if (findItem(type,false,brand)>=0){
               System.out.println("Item already exists =" +type);
               return;
          }

          Brand i = new Brand(type,brand);
          i.setQuantity(quantity);
          i.setPrice(price);
          items.add(i) ;

     }
     public void setQuantity(String type,int quantity){
          if (findItem(type,false)>=0){//Check if the item exists
               int i = findItem(type,false);//(if the item is exists , give the index of item from method findItem and set Quantity from it
               items.get(i).setQuantity(quantity);
               System.out.println("the quantity of "+type+" is update !!");
          }
          else {
               System.out.println("the item not found =" +type);
          }

     }
     public void setQuantity(String brand,String type,int quantity){
          if (findItem(type,false,brand)>=0){
               int i = findItem(type,false,brand);
               items.get(i).setPrice(quantity);
               System.out.println("the quantity of "+type+" is update !!");
          }
          else {
               System.out.println("the item not found =" +type);
          }
     }
     public void setPrice(String type,double price){
          if (findItem(type,false)>=0){
               int i = findItem(type,false);
               items.get(i).setPrice(price);
               System.out.println("the Price of "+type+" is update !!");
          }
          else {
               System.out.println("the item not found =" +type);
          }
     }
     public void setPrice(String brand,String type,double price){
          if (findItem(type,false,brand)>=0){
               int i = findItem(type,false,brand);
               items.get(i).setPrice(price);
               System.out.println("the quantity of "+type+" is update !!");
          }
          else {
               System.out.println("the item not found =" +type);
          }
     }

     public int getQuantity(String type){

          if (findItem(type,false)>=0){
               int i =findItem(type,false);
               if(items.get(i) instanceof Brand){
                    System.out.println("there is more than one brand of "+type);
                    return 0;
               }
               return items.get(i).getQuantity();
          }

          System.out.println("the item not found!! =" +type);
          return -1;
     }
     public int getQuantity(String brand,String type){
          if (findItem(type,false,brand)>=0){
               int i =findItem(type,false,brand);
               return items.get(i).getQuantity();
          }

          System.out.println("the item not found!! = "+type+" of " +brand);
          return -1;

     }
     public double getPrice(String type){
          if (findItem(type,false)>=0){
               int i =findItem(type,false);
               if(items.get(i) instanceof Brand){
                    System.out.println("there is more than one brand of "+type);
                    return 0;
               }
               return items.get(i).getPrice();
          }
          System.out.println("the item not found!! =" +type);
          return -1;
     }
     public double getPrice(String brand,String type){
          if (findItem(type,false,brand)>=0){
               int i =findItem(type,false,brand);
               return items.get(i).getPrice();
          }
          System.out.println("the item not found!! = "+type+" of " +brand);
          return -1;
     }
     public void update(String type,int qtyIncrease){
          if (findItem(type,false)>=0){
               int i = findItem(type,false);
               items.get(i).update(qtyIncrease);
               System.out.println("the quantity of "+type+" is update !!");
          }
          else {
               System.out.println("the item not found!! =" +type);
          }
     }
     public void update(String brand,String type,int qtyIncrease){
          if (findItem(type,false,brand)>=0){
               int i = findItem(type,false,brand);
               items.get(i).update(qtyIncrease);
               System.out.println("the quantity of "+type+" is update !!");
          }
          else {
               System.out.println("the item not found!! = "+type+" of " +brand);
          }
     }
     public void update(String type,double adjustmentFactor){
          double x = adjustmentFactor;
          if (findItem(type,false)>=0){
               int i = findItem(type,false);
               if (x<0){
                    x=items.get(i).getPrice()*adjustmentFactor;
                    items.get(i).update(x);
                    return;
               }
               items.get(i).update(adjustmentFactor);
               System.out.println("the Price of "+type+" is update !!");
          }
          else {
               System.out.println("the item not found!! =" +type);
          }
     }
     public void update(String brand,String type,double adjustmentFactor){
          double x = adjustmentFactor;
          if (findItem(type,false,brand)>=0){
               int i = findItem(type,false,brand);
               if (x<1){
                    x=items.get(i).getPrice()*adjustmentFactor;
                    items.get(i).update(x);
                    return;
               }
               items.get(i).update(adjustmentFactor);
               items.get(i).setType(type);
               System.out.println("the Price of "+type+" is update !!");
          }

          else {
               System.out.println("the item not found!! = "+type+" of " +brand);
          }
     }


     public void update1(String type,double adjustmentFactor,int qunt){
          double x = adjustmentFactor;
          if (findItem(type,false)>=0){
               int i = findItem(type,false);
               if (x<0){
                    x=items.get(i).getPrice()*adjustmentFactor;
                    items.get(i).update(x);
                    return;
               }
               items.get(i).update(adjustmentFactor);
               items.get(i).update(qunt);
               System.out.println("the Price of "+type+" is update !!");
          }
          else {
               System.out.println("the item not found!! =" +type);
          }
     }
     public void Delete(String type,double adjustmentFactor,int qunt){
          double x = adjustmentFactor;
          if (findItem(type,false)>=0){
               int i = findItem(type,false);
               items.remove(i);

          }
          else {
               System.out.println("the item not found!! =" +type);
          }
     }
     public void Delete(String Brand,String type,double adjustmentFactor,int qunt){
          double x = adjustmentFactor;
          if (findItem(type,false,Brand)>=0){
               int i = findItem(type,false);
               items.remove(i);

          }
          else {
               System.out.println("the item not found!! =" +type);
          }
     }
     public void update1(String brand,String type,double adjustmentFactor,int qunt){
          double x = adjustmentFactor;
          if (findItem(type,false,brand)>=0){
               int i = findItem(type,false,brand);
               if (x<1){
                    x=items.get(i).getPrice()*adjustmentFactor;
                    items.get(i).update(x);
                    return;
               }
               items.get(i).update(adjustmentFactor);
               items.get(i).update(qunt);
               items.get(i).setType(type);
               System.out.println("the Price of "+type+" is update !!");
          }
          else {
               System.out.println("the item not found!! = "+type+" of " +brand);
          }
     }
     private int findItem(String type,boolean warningIfFound){
          warningIfFound =false;
          for (int i =0;i<items.size();i++){

               if (type.equals(items.get(i).getType())){
                    warningIfFound =true;
                    return i;//return the index of the item to set it or give it or update it
               }
          }
          return -1;
     }
     private int findItem(String type,boolean warningIfFound,String brand){
          warningIfFound =false;
          for (int i =0;i<items.size();i++){
               if (type.equals(items.get(i).getType())
                       &&items.get(i) instanceof Brand && brand.equals(((Brand) items.get(i)).getBrand())){
                    warningIfFound =true;

                    return i;//return the index of the item to set it or give it or update it
               }
          }
          return -1;
     }

     public void stockReport(){
          double sum =0;
          if (items.size()<=0){
               System.out.println("no item !!");
               return;
          } // pass

          for (int i =0;i<items.size();i++){

               if(items.get(i) instanceof Brand){
                    System.out.println(((Brand) items.get(i)).getBrand()+" "+items.get(i).getType()+"- in stock: "+items.get(i).getQuantity()+", price: "+items.get(i).getPrice());
                    sum+=items.get(i).getPrice()*items.get(i).getQuantity();
               } else {
                    System.out.println(items.get(i).getType()+"- in stock: "+items.get(i).getQuantity()+", price: "+items.get(i).getPrice());
                    sum+=items.get(i).getPrice()*items.get(i).getQuantity();
               }

          }

          System.out.println("the Total value : $"+sum);
     }
     public String stockReport1(){
          String s = new String();
          double sum =0;
//          if (items.size()<=0){
//               System.out.println("no item !!");
//               return null;
//          } // pass

          for (int i =0;i<items.size();i++){

               if(items.get(i) instanceof Brand){
                    s+=((Brand) items.get(i)).getBrand()+" "+items.get(i).getType()+"- in stock: "+items.get(i).getQuantity()+", price: "+items.get(i).getPrice()+"\n";
                    sum+=items.get(i).getPrice()*items.get(i).getQuantity();
               } else {
                    s+= items.get(i).getType()+"- in stock: "+items.get(i).getQuantity()+", price: "+items.get(i).getPrice()+"\n";
                    sum+=items.get(i).getPrice()*items.get(i).getQuantity();
               }

          }
          return s;

     }
     public double stocksum(){
          double sum =0;
          for (int i =0;i<items.size();i++){
               sum+=items.get(i).getPrice()*items.get(i).getQuantity();
          }
          return sum;
     }
     public int find(String type,boolean warningIfFound) {
    	 int n = findItem(type, false);
    	 return n;
     }
     public int findbrand(String type,boolean warningIfFound,String brand) {
    	 int n = findItem(type, false, brand);
    	 return n;
    	
     }


}
