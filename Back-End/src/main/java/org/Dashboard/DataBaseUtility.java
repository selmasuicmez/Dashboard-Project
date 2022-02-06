package org.Dashboard;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataBaseUtility {
    static Connection connection = null;
    static Statement statement = null;
    static ResultSetMetaData rand = null;
    static ResultSet resultSet = null;
    static PreparedStatement preparedStatement = null;
    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static Connection connectionDB()
    {
        System.out.println("Connecting to database....." + "\n" + dateFormat.format(new Date()) + ""
                + "\n====================================================================================");
        try
        {
            String url = "jdbc:sqlite:C:\\Users\\hp\\OneDrive\\Bureaublad\\DarmaProject\\dharma\\DarmaNEW\\issues.db";
            connection = DriverManager.getConnection(url);
            System.out.println("Connected to MySQL Database");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static Statement getStatement()
    {
        try
        {
            statement = connection.createStatement();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    public static void updateDataBase(String sqlQuery)
    {
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            int rows = preparedStatement.executeUpdate(sqlQuery);
            System.out.println(rows + "rows updated succesfuly");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertDataBase(String sqlQuery)
    {
        try
        {
            preparedStatement = connection.prepareStatement(sqlQuery);
            int rows = preparedStatement.executeUpdate();
            System.out.println(rows + "rows inserted to database");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet getDataBase(String sqlQuery)
    {
        try
        {
            resultSet = getStatement().executeQuery(sqlQuery);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static void showDataBase(String sqlQuery)
    {
        try
        {
            System.out.println("*********************** Showing data ***************************");
            resultSet = getStatement().executeQuery(sqlQuery);
            rand = resultSet.getMetaData();
            int columNumber = rand.getColumnCount();

            while (resultSet.next())
            {
                for (int i = 1; i <= columNumber; i++)
                {
                    System.out.println(resultSet.getString(i) + "\t");
                }
                System.out.println();//printing next line
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeDB() throws SQLException
    {
        if (connection != null && !connection.isClosed())
        {
            connection.setAutoCommit(false);
            connection.close();
            System.out.println("\n===================================================================================="
                    + "\n" + dateFormat.format(new Date()));
            System.out.println("Dicconnected from MySql database");
        }
    }

    public static void deleteDataBase(String sqlQuery)
    {
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            int rows = preparedStatement.executeUpdate(sqlQuery);
            System.out.println(rows + "rows deleted succesfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

