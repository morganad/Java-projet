package projectDangMorelNadeau.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import projectDangMorelNadeau.db.daos.DataSourceFactory;

public class ContactOverviewController {
	
	public static void backup() throws SQLException, IOException {
		try(Connection connection = DataSourceFactory.getDataSource().getConnection()){
			try(Statement statement = connection.createStatement()){
				try (ResultSet resultSet = statement.executeQuery("SELECT * FROM person")){
					
					File f=new File("contact.vcf");
					FileOutputStream fop = new FileOutputStream(f);
					
				    if(f.exists()){
						while(resultSet.next()) {
							String lastname = resultSet.getString("lastname");
							String firstname = resultSet.getString("firstname");			
							Date birth_date = resultSet.getDate("birth_date");
								
							String str="BEGIN:VCARD\n" +
						               "VERSION:4.0\n" +
						               "N:"+lastname+";"+firstname+";;;\n" +
						               "FN:"+firstname+" "+lastname+"\n"+
						               "TEL;TYPE=home,voice;VALUE=uri:tel:"+resultSet.getString("phone_number")+"\n"+
						               "BDAY:"+birth_date+"\n"+
						               "EMAIL:"+resultSet.getString("email_address")+"\n"+
						               "END:VCARD";
							fop.write(str.getBytes());
					        //Now read the content of the vCard after writing data into it
					        BufferedReader br = null;
					        String sCurrentLine;
				            br = new BufferedReader(new FileReader("contact.vcf"));
				            while ((sCurrentLine = br.readLine()) != null) {
				            	System.out.println(sCurrentLine);
					        }
						}
					fop.flush();
			        fop.close();
			        System.out.println("The data has been written");
			        }else System.out.println("This file does not exist");
				    
				} catch (SQLException e) {
					System.out.println("BAD STATEMENT");
					e.printStackTrace();
				}
			}catch (SQLException e) {
				System.out.println("CANNOT CREATE STATEMENT");
				e.printStackTrace();
			}
		}catch (SQLException e) {
			System.out.println("NO DATASOURCE FOUND");
			e.printStackTrace();
		}
	}
}
