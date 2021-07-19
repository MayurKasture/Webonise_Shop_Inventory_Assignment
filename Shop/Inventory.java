
import java.util.Scanner;
import java.io.*; 
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException; 
import java.util.StringTokenizer;

//shopkeeper
class Shopkeeper
{
    String Id, record, record2, name, price, stock, company;

    // shopkeeper menu
    public void smenu() {

        boolean isCheck = false;

        do{

            System.out.println();
            System.out.println("press 1 : Product List");
            System.out.println("press 2 : Search product ");
            System.out.println("press 3 : Add Product ");
            System.out.println("press 4 : Edit Product ");
            System.out.println("press 5 : Delete product ");
            System.out.println("press 6 : Exit ");
            System.out.print("Select option : ");

            Scanner sc = new Scanner(System.in);
            String a = sc.next();
            try {

                int b = Integer.parseInt(a); 
                switch(b) {

                    case 1 :
                        
                            System.out.println("\n\n***************** ALL Product List **********************\n");
                            product_list();
                            break;
                    case 2 :
                            
                            System.out.println("\n************************************************");
                            product_search();
                            break;
                    
                    case 3 :
                            System.out.println("\n************************************************");
                            product_add();
                            break;

                    case 4 :
                            System.out.println();
                            System.out.println("\n************************************************");
                            product_edit();
                          
                            break;
                    case 5 :
                            System.out.println();
                            System.out.println("\n************************************************");
                            product_delete();
                           
                            break;

                    case 6 :
                            System.out.println();
                            System.out.println("******* Thank you for visit !  *********");
                            isCheck = false;
                            break;

                    default :
                            System.out.println("Please select valid option ");
                            isCheck = true;

                }

            }catch (NumberFormatException  e)  
            { 
                System.out.println("Please enter valid option : ");
                isCheck = true;
            
            } 

        }while(isCheck);
     
    }

    //product list
    public void product_list() {

          try{
                BufferedReader br = new BufferedReader( new FileReader("inventory") );
               
                System.out.println("-------------------------------------------------------------------------------------------------");
                System.out.println("|   ID\tName\t\t\tPrice\t\t\tTotal_stock\t\tCompany_name\t|");
                System.out.println("-------------------------------------------------------------------------------------------------");
                
              
                while( ( this.record = br.readLine() ) != null ) {
                    
                    StringTokenizer st = new StringTokenizer(this.record,",");
                    System.out.println("|   "+st.nextToken()+"\t"+st.nextToken()+"\t\t\t"+st.nextToken()+"\t\t\t"+st.nextToken()+"\t\t\t"+st.nextToken()+"\t\t|");
                }

                System.out.println("-------------------------------------------------------------------------------------------------");

                br.close();
                smenu();  	
          }
          catch (IOException except)
        {
            except.printStackTrace();
        }  

    }

    //search product 
    public void product_search() {

           boolean isExist = false;

           try{

                Scanner strInput = new Scanner(System.in);
                
                BufferedReader br = new BufferedReader( new FileReader("inventory") );
                
                System.out.print("\t\t Search Product Record\n\n");
            
                System.out.print("Enter the Product Name:");
                this.name =strInput.nextLine();
                
                System.out.println(" ----------------------------------------------------------------- ");
                System.out.println("|   ID   Name          Price     Total_stock     Company_name      |");
                System.out.println(" ----------------------------------------------------------------- ");
                
                while( ( this.record = br.readLine() ) != null ) {

                    StringTokenizer st = new StringTokenizer(this.record,",");
                    if( this.record.contains(this.name) ) {
                            System.out.println("|   "+st.nextToken()+"   "+st.nextToken()+"          "+st.nextToken()+"     "+st.nextToken()+"              "+st.nextToken()+"              |");
                            isExist = true;
                    }

                }

                 if(!isExist) {

                    System.out.println("\n ------------------ Product not availble ----------------\n\n");    		

                 }
                
                br.close();
                smenu();

            }
            catch (IOException except)
            {
                except.printStackTrace();
            }  

    }

    //add product
    public void product_add(){
        
        boolean isExist = false;

         try
            {
                File db = new File("inventory");
                BufferedReader br = new BufferedReader( new FileReader(db) );
                BufferedWriter bw = new BufferedWriter(new FileWriter("inventory",true) );
                Scanner strInput = new Scanner(System.in);
                
                System.out.print("Enter the product ID   : P");
                this.Id ='P'+strInput.nextLine();

                //check product already exit or not
                while( ( this.record = br.readLine() ) != null ) {
                
                    StringTokenizer st = new StringTokenizer(this.record,",");
                    if( this.record.contains(this.Id) ) {
                        isExist = true;
                    }
                }

                if(isExist) {

                    System.out.println("\n ------------------ Product already exist ----------------\n\n");    		

                }else{

                    System.out.print("Enter the product name  : ");
                    this.name = strInput.nextLine();
                    System.out.print("Enter the product price : ");
                    this.price = strInput.nextLine();
                    System.out.print("Enter the stock item : ");
                    this.stock = strInput.nextLine(); 
                    System.out.print("Enter the company name : ");
                    this.company = strInput.nextLine();    		

                    bw.write(this.Id + "," + this.name + "," + this.price + "," + this.stock + "," + this.company);
                    bw.flush();
                    bw.newLine();
                    bw.close();
                    System.out.println("\n ------------------ Product Added Successfully ----------------\n\n");
                }

                smenu();
            }
            catch (IOException except)
            {
                except.printStackTrace();
            }
    }

    //edit product
    public void product_edit() {

        boolean isEdit = false;

        try
            {
                File db = new File("inventory");
                File tempDB = new File("inventory1");
                
                BufferedReader br = new BufferedReader( new FileReader(db) );
                BufferedWriter bw = new BufferedWriter( new FileWriter(tempDB) );
                            
                Scanner strInput = new Scanner(System.in);
                System.out.println("\t\t Update Product Record\n\n");  
                
                /**/	
	
                System.out.print("Enter the product ID: P");
                this.Id = 'P'+strInput.nextLine();	    		
                System.out.println(" ----------------------------------------------------------------- ");
                System.out.println("|   ID   Name          Price     Total_stock     Company_name      |");
                System.out.println(" ----------------------------------------------------------------- ");
                    
                while( ( this.record = br.readLine() ) != null ) {
                    
                    StringTokenizer st = new StringTokenizer(this.record,",");
                    if( this.record.contains(this.Id) ) {
                            System.out.println("|   "+st.nextToken()+"   "+st.nextToken()+"          "+st.nextToken()+"     "+st.nextToken()+"              "+st.nextToken()+"              |");
                            isEdit = true;
                    }
                }

                br.close();

                if(isEdit) {

                    System.out.println("\n");
                    System.out.println("Edit product id is   : " + this.Id);
                    System.out.print("Enter the product name  : ");
                    this.name = strInput.nextLine();
                    System.out.print("Enter the product price : ");
                    this.price = strInput.nextLine();
                    System.out.print("Enter the stock item : ");
                    this.stock = strInput.nextLine(); 
                    System.out.print("Enter the company name : ");
                    this.company = strInput.nextLine();    		
                                
                    BufferedReader br2 = new BufferedReader( new FileReader(db) );
                    while( (this.record2 = br2.readLine() ) != null ) {    			
                        if(this.record2.contains(this.Id)) {
                            bw.write(this.Id + "," + this.name + "," + this.price + "," + this.stock + "," + this.company);
                        } else {
                        
                            bw.write(this.record2);	
                        }  

                        bw.flush();
                        bw.newLine();
                    }
                    
                    bw.close();
                    br2.close();    		
                    db.delete();    		
                    boolean success = tempDB.renameTo(db);
                    System.out.println("\n ------------------ Product Updated Successfully ----------------\n\n");    		
                    smenu();
                    
                }else{

                    System.out.println("\n ---------------------- Product not Avilable --------------------\n\n");
                    bw.close();
                  	tempDB.delete();   
                    smenu();

                }	    		
                
            }
            catch (IOException except)
            {
                except.printStackTrace();
            }
            
    }

    //delete product
    public void product_delete() {

        boolean isDelete = false;

        try
            {

            Scanner strInput =  new Scanner(System.in);
    		
    		File tempDB = new File("inventory1");
    		File db = new File("inventory");
    		
    		
    		BufferedReader br = new BufferedReader( new FileReader( db ) );
    		BufferedWriter bw = new BufferedWriter( new FileWriter( tempDB ) );
    		
    		System.out.print("Enter the product ID : P");
    		this.Id ='P'+strInput.nextLine();
    		
    	
    		while( ( this.record = br.readLine() ) != null ) {
                
    			if( record.contains(this.Id) ) {
                    isDelete = true;
    				continue;
                }
                    
                
    			bw.write(this.record);
    			bw.flush();
    			bw.newLine();
 
    		}

            if(isDelete){
                System.out.println("\n ------ Product deleted Successfully ------------\n\n");    		
            }else{
                System.out.println("\n ------ Product id not available --------------\n\n");    		
            }
    		
    		br.close();
    		bw.close();
    		
    		db.delete();
    		
    		tempDB.renameTo(db);
            smenu();
        }
        catch (IOException except)
        {
            except.printStackTrace();
        }
    }

}



// customer
class Customer{

    String record,record2,Id,card_n,card_cvv,name,price,stock,company;
     // customer menu
    public void cmenu() {

        boolean isCheck = false;

        do{

            System.out.println();
            System.out.println("press 1 : Product List");
            System.out.println("press 2 : Search product ");
            System.out.println("press 3 : Buy Product ");
            System.out.println("press 4 : Exit ");
            System.out.print("Select option : ");

            Scanner sc = new Scanner(System.in);
            String a = sc.next();
            try {

                int b = Integer.parseInt(a); 
                switch(b) {

                    case 1 :
                        
                            System.out.println("\n****************** ALL Product List *********************");
                            product_list();
                            break;
                    case 2 :
                            
                            System.out.println("\n************************************************");
                            product_search();
                            break;
                    
                    case 3 :
                            
                            System.out.println("\n************************************************");
                            product_buy();
                            break;
                    case 4 :
                            System.out.println();
                            System.out.println("******* Thank you for visit !  *********");
                            isCheck = false;
                            break;

                    default :
                            System.out.println("Please select valid option ");
                            isCheck = true;

                }

            }catch (NumberFormatException  e)  
            { 
                System.out.println("Please enter valid option : ");
                isCheck = true;
            
            } 

        }while(isCheck);
     
    }

    //product list 
    public void product_list() {

           try{
                BufferedReader br = new BufferedReader( new FileReader("inventory") );
               
                System.out.println("-------------------------------------------------------------------------------------------------");
                System.out.println("|   ID\tName\t\t\tPrice\t\t\tTotal_stock\t\tCompany_name\t|");
                System.out.println("-------------------------------------------------------------------------------------------------");
                    
                while( ( this.record = br.readLine() ) != null ) {
                    
                    StringTokenizer st = new StringTokenizer(this.record,",");
                    System.out.println("|   "+st.nextToken()+"\t"+st.nextToken()+"\t\t\t"+st.nextToken()+"\t\t\t"+st.nextToken()+"\t\t\t"+st.nextToken()+"\t\t|");
                }
             
                br.close();
                cmenu();  	
          }
          catch (IOException except)
         {
            except.printStackTrace();
         }  	
    }

    // //product search
    public void product_search() {

        boolean isExist = false;

           try{

                Scanner strInput = new Scanner(System.in);
                
                BufferedReader br = new BufferedReader( new FileReader("inventory") );
                
                System.out.print("\t\t Search Product Record\n\n");
            
                System.out.print("Enter the Product ID: P");
                this.Id ='P'+strInput.nextLine();
                
                System.out.println("-------------------------------------------------------------------------------------------------");
                System.out.println("|   ID\tName\t\t\tPrice\t\t\tTotal_stock\t\tCompany_name\t|");
                System.out.println("-------------------------------------------------------------------------------------------------");
                
                while( ( this.record = br.readLine() ) != null ) {

                    StringTokenizer st = new StringTokenizer(this.record,",");
                    if( this.record.contains(this.Id) ) {
                            System.out.println("|   "+st.nextToken()+"\t"+st.nextToken()+"\t\t\t"+st.nextToken()+"\t\t\t"+st.nextToken()+"\t\t\t"+st.nextToken()+"\t\t|");
                            isExist = true;
                    }

                }

                 if(!isExist) {

                    System.out.println("\n ------------------ Product not availble ----------------\n\n");    		

                 }
                
                br.close();
                cmenu();

            }
            catch (IOException except)
            {
                except.printStackTrace();
            }  

    }

    //product buy
    public void product_buy() {
        boolean isExist = false;
        try{

            Scanner strInput = new Scanner(System.in);
            
            BufferedReader br = new BufferedReader( new FileReader("inventory") );
            
            System.out.print("\t\t Buy Product Record\n\n");
        
            System.out.print("Enter the Product ID: P");
            this.Id ='P'+strInput.nextLine();
                
          
            String newstring="";
            String getdetails="";
            while( ( this.record = br.readLine() ) != null ) {
                
                StringTokenizer st = new StringTokenizer(this.record,",");
                if( this.record.contains(this.Id) ) {
                        newstring = this.record;
                        isExist = true;
                }
            }

            boolean isAvailable=false;

            if(!isExist) {

                System.out.println("\n --------------------------------- Product not availble ------------------------------\n\n");    		
                cmenu();
            }else{

                String[] getprod = newstring.split(",");  // use for get product name

                isAvailable  = check_stock(getprod[3]);

                if(isAvailable) {

                    getdetails = get_customer_details(this.Id,getprod[1],getprod[2]);

                    // order - save in order file
                    try
                    {
                        BufferedWriter bw = new BufferedWriter( new FileWriter("orders",true) );
                        bw.write(getdetails);
                        bw.flush();
                        bw.newLine();
                        bw.close();
                        System.out.println("\n --------------------------------- Ordered Successfully ------------------------------\n\n");    		
                        cmenu();
                    }
                    catch (IOException except)
                    {
                        except.printStackTrace();
                    }

                }else {
                        System.out.println("\n --------------------------------- Out of Stock ------------------------------\n\n");    		
                        cmenu();
                }


            }  

            br.close();
            order_product_update(this.Id,isAvailable);
        }
        catch (IOException except)
        {
            except.printStackTrace();
        }  
    }

    //update inventory file
    public void order_product_update(String id,boolean isAvailable) {

        if(isAvailable) {
            
            try{
            
                File db = new File("inventory");
                File tempDB = new File("inventory1");
                    
                BufferedReader br = new BufferedReader( new FileReader(db) );
                BufferedWriter bw = new BufferedWriter( new FileWriter(tempDB) );

                while( ( this.record = br.readLine() ) != null ) {
                        
                    StringTokenizer st = new StringTokenizer(this.record,",");
                    if( this.record.contains(id) ) {

                        this.Id = st.nextToken();
                        this.name = st.nextToken();
                        this.price = st.nextToken();
                        this.stock = st.nextToken();
                        this.company = st.nextToken();

                    }
                }

                br.close();

                BufferedReader br2 = new BufferedReader( new FileReader(db) );

                while( (this.record2 = br2.readLine() ) != null ) {   

                    if(this.record2.contains(this.Id)) {

                        // = this.stock - 1; // stock decrease
                        int a = Integer.parseInt(this.stock);
                        a = a - 1;

                        bw.write(this.Id + "," + this.name + "," + this.price + "," + a + "," + this.company);

                    } else {

                        bw.write(this.record2);	
                    }   

                    bw.flush();
                    bw.newLine();
                }
                
                bw.close();
                br2.close();    		
                db.delete();
                boolean success = tempDB.renameTo(db);
                    
            } 
            catch (IOException except)
            {
                except.printStackTrace();
            } 

        }

    }

    // get customer details
    public String get_customer_details(String id, String name, String price) {

        Scanner strInput = new Scanner(System.in);
        System.out.println("Product Name  : " + name);
        System.out.println("Product Price  : " + price);
        //get card details
        System.out.println("\nEnter your card details : \n");
        System.out.print("Enter card number : ");
        this.card_n = strInput.nextLine(); 
        System.out.print("Enter CVV : ");
        this.card_cvv = strInput.nextLine();

        return id + "," + name + "," + price + "," + this.card_n + "," + this.card_cvv;
    }

    public boolean check_stock(String stock){

        int a = Integer.parseInt(stock);
        return a == 0 ? false : true;
    }

}




class Inventory 
{
    public static void main (String[] args)
    {
       boolean isCheck=false;
       int op = 0;

        do{ 

            System.out.println("press 1  : you are customer  ");
            System.out.println("press 2  : you are shopkeeper  ");
            System.out.println("press 3  : exit ");
            System.out.print("Select option : ");

            Scanner sc = new Scanner(System.in);
            String a = sc.next();

            try 
            { 
                int b = Integer.parseInt(a); 
                switch(b) {

                    case 1 : 
                            System.out.println();
                            System.out.println("************************************************");
                            Customer c = new Customer(); // create obj for customer
                            c.cmenu(); 
                            break;

                    case 2 : 
                            System.out.println();
                            System.out.println("*************** WELCOME TO SHOPKEEPER *******************");
                            Shopkeeper s = new Shopkeeper(); // create obj for shopkeeper
                            s.smenu(); 
                            break;

                    case 3 : 
                            System.out.println();
                            System.out.println("******* Thank you for visit ! *********");
                            isCheck = false;
                            break;

                    default :
                            System.out.println("Please select valid option ");
                            isCheck = true;
                }
            }  
            catch (NumberFormatException  e)  
            { 
                System.out.println("Please enter valid option  ");
                isCheck = true;
               
            } 

        }while(isCheck);

    }
}


