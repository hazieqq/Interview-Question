/*
 * 
 * Intel Interview Question
 * 
 */
package iv;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author MUHAMMAD HAZIEQ BIN HANNAN
 */ 
public class Iv {

    /**
     *  Intel IV Question
     */
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("I used two methods. First method is I use split java library and the other method I check from behind for each character(hardcode).");
        System.out.println("Enter 1 for method 1 and 2 for method 2");
        int method = input.nextInt();
        while(true){
            if(method==1){
                method1();
                break;
            } else
                if(method==2){
                    method2();
                    break;
                } else {
                    System.out.println("Enter correct number");
                    method = input.nextInt();
                }
        }
    }
    
    public static void method1() {                                                          // check using split method (coma and double quotes)
          try{  
            File file = new File("text.txt");                                               // locate text file
            Scanner reader = new Scanner(file);                                             
            
            int index=0;
           
            while(reader.hasNextLine()) {                                                   // read text file from local file
                String data = reader.nextLine();                                 
                index++;                                                                    // check how many lines are there
            }
            reader = new Scanner(file);                                                         
            LinkedList<String> arr1 = new LinkedList();                                     // to store each line from the text file into linkedlist
            
            
            for(int i=0;i<index;i++){
                String data = reader.nextLine();            
                if(data.isBlank()){                                                         // check if there is an empty line, if yes, then ignore
                    continue;
                }
                arr1.add(data);                                                             // add each line to linkedlist without empty lines
            }
            String arr[] = new String[arr1.size()];                                         // to store each line without empty lines
            System.out.println("----------------------------------------------------------");
            for(int i=0;i<arr1.size();i++){
                arr[i] = arr1.get(i).replaceAll(" ", "");                                   // replace all white space/empty string to no space.
                System.out.println(arr[i]);
            }
            System.out.println("----------------------------------------------------------");
            
            LinkedList<Integer> newAr = new LinkedList();                                   // store age of each student/employee
            String[] split;                                                                 // store array after split double quote
            String[] split1;                                                                // store array after split coma(,)
            for(int i=0;i<arr.length;i++){
                    
                split = arr[i].split("\"");                                                 // split lines which has double quote                     
                
                for(int k=0;k<split.length;k++){
                   
                    if(k==0 || k==2){                                                       
                        split1 = split[k].split(",");
                        for (int j=0;j<split1.length;j++){
                            try {                                                            // method to found age
                                int s = Integer.parseInt(split1[j]);                        // if can convert to integer then it will not throw an exception flow
                                newAr.add(s);                                               //  add age to linked list
                                break;                                                      // if already found age then break so that it will not read other integer value
                            } catch(NumberFormatException e){
                                // print nothing so that user doesn't know if it found string or any data types other than integer.
                            }
                        }
                    }
                }
                
            }
            
            
            int num= 0;
            int check=0;
            for(int i=0;i<newAr.size();i++){
                if(num <= newAr.get(i)) {                                                   // to find the eldest age //
                    num = newAr.get(i); 
                    check=i;
                }
            }
            newAr.remove(check);                                                            // remove eldest in the array because, i want to check if got other member has the same age or not.
            System.out.print("Eldest = ");
            boolean flag=false;
            
            // print  eldest
            for(int i=0;i<newAr.size();i++) {                                                           
                if(num == newAr.get(i)) {                                                    // check if got same eldest age 
                    split = arr[i+1].split(",");
                    System.out.print(split[0]+" ");
                    flag=true;
                }
            }
            if(!flag) {                                                                       // if not true means that no members has the same eldest age
                split = arr[check+1].split(",");
                System.out.println(split[0]);
            } else{                                                                         // print the eldest that i remove just now.
                split = arr[check+1].split(",");
                System.out.print(split[0]);
            }
            
        } catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }

    private static void method2() {                                                         // method 2 - check from behind
       try{
            File file = new File("text.txt");                                               // locate text file
            Scanner reader = new Scanner(file);
            
            int index=0;
           
            while(reader.hasNextLine()) {                                                   // read data from local file
                String data = reader.nextLine();                                 
                index++;                                                                    // check how many lines are there
            }
            reader = new Scanner(file);                                                         
            LinkedList<String> arr1 = new LinkedList();                                     // to store each line from the text file into linkedlist
            
            
            for(int i=0;i<index;i++){
                String data = reader.nextLine();            
                if(data.isBlank()){                                                         // check if there is an empty line
                    continue;
                }
                arr1.add(data);                                                             // add line to linkedlist without added empty lines
            }
            String arr[] = new String[arr1.size()];                                         // to store each line without empty lines
            System.out.println("----------------------------------------------------------");
            for(int i=0;i<arr1.size();i++){
                arr[i] = arr1.get(i).replaceAll(" ", "");                                   // replace all white space/empty string to no space.
                System.out.println(arr[i]);             
            }
             System.out.println("----------------------------------------------------------");
            
            LinkedList<Integer> newAr = new LinkedList();                                   // store age of each student/employee
            Stack<String> stack = new Stack();                                              // add each character age to stack
            for(int i=1;i<arr.length;i++){
                for(int j=arr[i].length()-1;j>=0;j--){
                    try{                                                                                            // method to found age using hardcode 
                        int s = Integer.parseInt(Character.toString(arr[i].charAt(j)));                             // if can convert to integer then it will not throw an exception flow
                        stack.add(s+"");                                                                            // add character age to stack.
                        if(arr[i].charAt(j-1) == ',' && stack.size()==1){                                           // if found coma and stack is not empty it will break so that it will not check other integer value which is other than age.
                            break;
                        } else {
                            int s1 = Integer.parseInt(arr[i].charAt(j-1)+"");                                       // else if not found coma means that the age is 2 digit. so it will put another character age to the  stack.
                            stack.add(s1+"");
                            if(arr[i].charAt(j-2) == ',' && stack.size()==2){                                       //if found coma and stack is not empty it will break so that it will not check other integer value which is other than age.
                                break;
                            } else {
                                int s2 = Integer.parseInt(arr[i].charAt(j-2)+"");                                   // else if not found coma means that the age is 3 digit. so it will put another character age to the  stack.
                                stack.add(s2+"");
                                break;                                                                              
                            }
                        }
                        
                        
                    } catch(NumberFormatException e){
                       // print nothing so that user doesn't know if it found string or any data types other than integer.
                    }
                }
                String str="";
                while(!stack.isEmpty()){
                    str += stack.pop();                                                      // pop the stack since i read from behind so stack is first in last out. example age = 13 . stack will store 3 first then 1. So I pop 1(last in) then 3(first in) so it will become 13.
                }
                newAr.add(Integer.parseInt(str));                                           // add to linkedlist because after pop stack will become empty
            }
            
            int num= 0;
            int check=0;
            for(int i=0;i<newAr.size();i++){                                            // find which is the eldest
                if(num <= newAr.get(i)) {
                    num = newAr.get(i);
                    check=i;
                }
            }
            
            newAr.remove(check);                                                        // remove eldest in the array because, I want to check if got other member has the same age or not.
            System.out.println();
            System.out.print("Eldest = ");
            boolean flag=false;
            
            //print eldest
            for(int i=0;i<newAr.size();i++) {
                if(num == newAr.get(i)) {                                                // check if got same eldest age 
                    System.out.print(arr[i+1]+"  ");
                    flag=true;
                }
            }
            if(!flag) {                                                                 // if not true means that no members has the same eldest age
                System.out.println(arr[check+1]);
            } else{
                System.out.println(arr[check+1]);                                       // print the eldest that had been  remove just now.
            }
            
        } catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }
    
}
