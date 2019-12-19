import java.sql.*;
import java.util.Scanner;

public class Main {
	

		public static void main(String[] args) throws SQLException {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/invoicepo", "root", "Elisa3hc*m");
			Connect con = new Connect ();
		
		Scanner scan = new Scanner(System.in);
		

		int choiceentry = -1;

			    while(choiceentry < 1 || choiceentry > 3){

			            System.out.println("Select an option: \n1 - Purchase Order \n2 - Invoice\n3 - Exit");
			            if(scan.hasNextInt())
			            choiceentry = scan.nextInt();

			    }

			     switch(choiceentry){
			        case 1:
			        	
			        	conn.conPO();
			        	scan.close();
			           break;
			        case 2:
			        	
			        	try
			            {
			        	         	
			             String query = "insert into invoice (CustomerName, idItem)"
			                + " values (?, ?)";
			              
			              Statement st = conn.createStatement();
			  			
			  			PreparedStatement preparedStmt = conn.prepareStatement(query);
			              
			              System.out.println("Enter Item:");
			              int item = scan.nextInt();
			              System.out.println("Enter Customer Name:");
			              String customerName = scan.next();
			              
			              preparedStmt.setString (1, customerName);
			              preparedStmt.setInt (2, item);
			             
	              
			              preparedStmt.execute();
			              
			              query = "SELECT * FROM items, invoice WHERE  idItems = idItem";
			              ResultSet rs = st.executeQuery(query);
			              while (rs.next())
			              {
			                int id = rs.getInt("idInvoice");
			                String name = rs.getString("name");
			                double net = rs.getDouble("price");
			                String customer = rs.getString("CustomerName");
			                double hst = net * 0.13;
			                double total = hst + net;
			                			                
			                // print the results
			                System.out.format("\nId PO: %d \nName Customer: %s \nItem: %s \nNet: %.2f \nHST: %.2f \nTotal: %.2f,\n", 
			                		id, customer, name, net, hst, total);
			              }
			              
			              conn.close();
			            }
			            catch (Exception e)
			            {
			              System.err.println("Got an exception!");
			              System.err.println(e.getMessage());
			            }
			        	scan.close();
			           break;
			        case 3:
			        	 // .. exit program
			        	scan.close();
			            break;
			        default:
			            System.out.println("Choice must be a value between 1 and 3.");
			    }
			  
	
		
	
		
	}
}
	

