import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/learn";
        String user = "root";
        String password = "root";


        try (Connection conn = DriverManager.getConnection(url, user, password);
             BufferedReader sqlReader = new BufferedReader(new FileReader("src/Bikes.sql"));
             Scanner scr = new Scanner(sqlReader);
             Statement statement = conn.createStatement()) {
            String line = "";
            while (scr.hasNext()) {
                line = scr.nextLine();
                if (line.endsWith(";")) {
                    line = line.substring(0, (line.length() - 1));
                }
                statement.executeUpdate(line);
            }
            ResultSet rs = null;
            try{
                rs = statement.executeQuery("SELECT * FROM Bikes");
                while(rs.next()){
                    System.out.println(rs.getInt(1) + " " + rs.getString(2));
                }
            } catch (SQLException e){e.printStackTrace();}
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
