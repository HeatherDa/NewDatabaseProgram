package com.Heather;

        import java.sql.*;

public class Main {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";        //Configure the driver needed
    static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/vet";     //Connection string – where's the database?
    static final String USER = "heather";   //TODO replace with your username
    static final String PASSWORD = "password";   //TODO replace with your password

    public static void main(String[] args) {

        try {

            Class.forName(JDBC_DRIVER);

        } catch (ClassNotFoundException cnfe) {
            System.out.println("Can't instantiate driver class; check you have drives and classpath configured correctly?");
            cnfe.printStackTrace();
            System.exit(-1);  //No driver? Need to fix before anything else will work. So quit the program
        }

        Statement statement = null;
        Connection conn = null;

        /** New code here **/

        ResultSet rs = null;

        /** End of new code **/

        try {

            //You should have already created a database via terminal/command prompt OR MySQL Workbench

            conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);
            statement = conn.createStatement();

            //Create a table in the database, if it does not exist already
            String createTableSQL = "CREATE TABLE  if not exists Dogs (Name varchar(30), Age int)";
            statement.executeUpdate(createTableSQL);
            System.out.println("Created Dogs table");

            //Add some data
            String addDataSQL = "INSERT INTO Dogs VALUES ('Poppy', 3)";
            statement.executeUpdate(addDataSQL);

            addDataSQL = "INSERT INTO Dogs VALUES ('Zelda', 5)";
            statement.executeUpdate(addDataSQL);
            System.out.println("Added two rows of data");

            /** More new code here **/

            addDataSQL = "INSERT INTO Dogs VALUES ('Fred', 7)";
            statement.executeUpdate(addDataSQL);

            addDataSQL = "INSERT INTO Dogs VALUES ('Lassie', 12)";
            statement.executeUpdate(addDataSQL);

            String fetchAllDataSQL = "SELECT * FROM Dogs";
            rs = statement.executeQuery(fetchAllDataSQL);
            while (rs.next()) {
                String dogName = rs.getString("Name");
                int dogAge = rs.getInt("Age");
                System.out.println("Dog name = " + dogName + " dog age = " + dogAge);
            }

            /** End of new code here **/


        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //A finally block runs whether an exception is thrown or not. Close resources and tidy up whether this code worked or not.
            try {
                if (statement != null) {
                    statement.close();
                    System.out.println("Statement closed");
                }
            } catch (SQLException se){
                //Closing the connection could throw an exception too
                se.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();  //Close connection to database
                    System.out.println("Database connection closed");
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("End of program");
    }
}

