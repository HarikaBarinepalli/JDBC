package hms;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		while(true) {

			System.out.println("Select Option");
			System.out.println("1) insert patient details");
			System.out.println("2) Update disease by id");
			System.out.println("3) update age by phonenumber");
			System.out.println("4) Display All Accounts");
			System.out.println("5) view patient by phoneNumber");
			System.out.println("6) search patients by disease");
			System.out.println("7) search patient by name");
			System.out.println("8) Delete patient Record by phone number and name");
			System.out.println("9)exist");
			
			switch (new Scanner(System.in).nextInt()) {
			case 1:
				insert(); 
				break; 
			case 2:
				updateDiseaseById();
				break;
			case 3:
				updateAgebyPhoneNo();
				break;
			case 4:
				viewAll();
				break;
			case 5:
				viewPatientByPhoneNo();
				break;
			case 6:
				searchByDisease();
				break;
			case 7:
				searchByName();
				break;
			case 8:
				deleteByPhoneAndName();
				break;
			case 9:
				System.exit(0);
				break;

			default:System.out.println("Invalid Choice");
				break;
			}

		}
	}
	

		
		static void insert() {
	 try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","Root");
		PreparedStatement ps = con.prepareStatement("insert into patient values (?,?,?,?,?,?)");
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter Id : ");
		int ID = sc.nextInt();
		System.out.println("Enter Name:");
		String name = sc.next();
		System.out.println("Enter Age:");
		int age= sc.nextInt();
		System.out.println("Enter Phoneno:");
		long phone=sc.nextLong();
		System.out.println("Enter Disease:");
		String disease=sc.next();
		System.out.println("Enter Admisiion_Date:");
		String admition_date=sc.next();
		
		ps.setInt(1, ID);
		ps.setString(2, name);
		ps.setInt(3, age);
		ps.setLong(4, phone);
		ps.setString(5, disease);
		ps.setString(6, admition_date);
		
		 int row= ps.executeUpdate();
		 System.out.println(row+"row affected......");
		 ps.close();
		 con.close();

		
	} catch (ClassNotFoundException | SQLException e) {
		
		e.printStackTrace();
	}
	}
	 static void updateDiseaseById() {
		 try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","Root");
				PreparedStatement ps = con.prepareStatement("update patient set disease=? where ID = ?");
				Scanner sc= new Scanner(System.in);
				System.out.println("Enter updated disesae : ");
				String disease= sc.next();
				System.out.println("Enter ID:");
				int ID=sc.nextInt();
				ps.setString(1, disease);
				ps.setInt(2, ID);
				 int row= ps.executeUpdate();
				 System.out.println(row+"row affected......");
				 ps.close();
				 con.close();

				
			} catch (ClassNotFoundException | SQLException e) {
				
				e.printStackTrace();
			}
		 
		 
	 }
	 
	 static void updateAgebyPhoneNo() {
		 try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","Root");
				PreparedStatement ps = con.prepareStatement("update patient set age=? where phone = ?");
				Scanner sc= new Scanner(System.in);
				System.out.println("Enter updated Age : ");
				int age= sc.nextInt();
				System.out.println("Enter phoneNo:");
				long phone=sc.nextLong();
				ps.setInt(1, age);
				ps.setLong(2, phone);
				 int row= ps.executeUpdate();
				 System.out.println(row+"row affected......");
				 ps.close();
				 con.close();

				
			} catch (ClassNotFoundException | SQLException e) {
				
				e.printStackTrace();
			}
		 
		 
	 }
	 
	 static void viewAll() {
		 try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "Root");
				java.sql.Statement s = con.createStatement();
				boolean b = s.execute("select * from patient");
				if (b == true) {
					ResultSet rs = s.getResultSet();
					
					while (rs.next()) {
						System.out.print(rs.getInt(1) + "\t");
						System.out.print(rs.getString(2) + "\t");
						System.out.print(rs.getInt(3) + "\t");
						System.out.print(rs.getLong(4)+"\t");
						System.out.print(rs.getString(5)+"\t");
						System.out.println(rs.getString(6)+"\t");
					}
					rs.close();
				}
				s.close();
				con.close();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
	 }
	 static void viewPatientByPhoneNo() {
		    try {
		        Class.forName("com.mysql.cj.jdbc.Driver");
		        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "Root");

		        
		        PreparedStatement ps = con.prepareStatement("SELECT * FROM patient WHERE phone = ?");
		        Scanner sc = new Scanner(System.in);
		        System.out.println("Enter phone number:");
		        long phone = sc.nextLong();

		      
		        ps.setLong(1, phone);

		      
		        ResultSet rs = ps.executeQuery();

		       
		        if (rs.next()) {
		            System.out.println("Patient Details:");
		            System.out.println("ID: " + rs.getInt(1));
		            System.out.println("Name: " + rs.getString(2));
		            System.out.println("Age: " + rs.getInt(3));
		            System.out.println("Phone: " + rs.getLong(4));
		            System.out.println("Disease: " + rs.getString(5));
		            System.out.println("Admission Date: " + rs.getString(6));
		        } else {
		            System.out.println("No patient found with the given phone number.");
		        }

		       
		        rs.close();
		        ps.close();
		        con.close();
		    } catch (ClassNotFoundException | SQLException e) {
		        e.printStackTrace();
		    }
		}
	 static void searchByDisease() {
		    try {
		        Class.forName("com.mysql.cj.jdbc.Driver");
		        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "Root");

		       
		        PreparedStatement ps = con.prepareStatement("SELECT * FROM patient WHERE disease = ?");
		        Scanner sc = new Scanner(System.in);
		        System.out.println("Enter disease:");
		        String disease = sc.next();

		        
		        ps.setString(1, disease);

		        
		        ResultSet rs = ps.executeQuery();

		        
		        boolean patientFound = false;
		        System.out.println("Patients with disease: " + disease);
		        while (rs.next()) {
		            System.out.println("ID: " + rs.getInt(1));
		            System.out.println("Name: " + rs.getString(2));
		            System.out.println("Age: " + rs.getInt(3));
		            System.out.println("Phone: " + rs.getLong(4));
		            System.out.println("Admission Date: " + rs.getString(6));
		            System.out.println("-----------------------");
		            patientFound = true;
		        }

		        if (!patientFound) {
		            System.out.println("No patients found with the disease: " + disease);
		        }

		        
		        rs.close();
		        ps.close();
		        con.close();
		    } catch (ClassNotFoundException | SQLException e) {
		        e.printStackTrace();
		    }
		}
	 static void searchByName() {
		    try {
		        Class.forName("com.mysql.cj.jdbc.Driver");
		        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "Root");

		        
		        PreparedStatement ps = con.prepareStatement("SELECT * FROM patient WHERE name = ?");
		        Scanner sc = new Scanner(System.in);
		        System.out.println("Enter patient name:");
		        String name = sc.next();

		       
		        ps.setString(1, name);

		        
		        ResultSet rs = ps.executeQuery();

		       
		        boolean patientFound = false;
		        System.out.println("Patients with name: " + name);
		        while (rs.next()) {
		            System.out.println("ID: " + rs.getInt(1));
		            System.out.println("Name: " + rs.getString(2));
		            System.out.println("Age: " + rs.getInt(3));
		            System.out.println("Phone: " + rs.getLong(4));
		            System.out.println("Disease: " + rs.getString(5));
		            System.out.println("Admission Date: " + rs.getString(6));
		            System.out.println("-----------------------");
		            patientFound = true;
		        }

		        if (!patientFound) {
		            System.out.println("No patients found with the name: " + name);
		        }

		       
		        rs.close();
		        ps.close();
		        con.close();
		    } catch (ClassNotFoundException | SQLException e) {
		        e.printStackTrace();
		    }
		}
	 static void deleteByPhoneAndName() {
		    try {
		        Class.forName("com.mysql.cj.jdbc.Driver");
		        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "Root");

		        
		        PreparedStatement ps = con.prepareStatement("DELETE FROM patient WHERE phone = ? AND name = ?");
		        Scanner sc = new Scanner(System.in);
		        
		        
		        System.out.println("Enter phone number:");
		        long phone = sc.nextLong();
		        System.out.println("Enter name:");
		        String name = sc.next();

		        
		        ps.setLong(1, phone);
		        ps.setString(2, name);

		       
		        int rowsAffected = ps.executeUpdate();

		        
		        if (rowsAffected > 0) {
		            System.out.println("Patient record deleted successfully.");
		        } else {
		            System.out.println("No patient found with the given phone number and name.");
		        }

		      
		        ps.close();
		        con.close();
		    } catch (ClassNotFoundException | SQLException e) {
		        e.printStackTrace();
		    }
		}

	 



	}


