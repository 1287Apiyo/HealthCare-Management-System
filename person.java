public class person {
    int nationalId;
   
    String  fName;
    String  sName;
    String address;
    String gender;
    //constructor in our body and has the same name as the default constructor 
    person(int id,String name1,String name2){
     nationalId=id;
     fName=name1;
     sName=name2;
   
         
   }
   void  printDetails(){
    System.out.println(nationalId);
    System.out.println(fName);
    System.out.println(sName);
    System.out.println(address);
    System.out.println(gender);
   
       
   }
}

