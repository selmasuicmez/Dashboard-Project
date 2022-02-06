package org.Dashboard;

import java.sql.*;

public class DataBaseCon {

    // Database credentials
    private final String url = "jdbc:mysql://remotemysql.com:3306/";
    private final String user = "";
    private final String password = "";

    public DataBaseCon(){
        Issue issue = new Issue();
        // Poging verbinding leggen met database
        try {

            Connection myConn = DriverManager.getConnection(url, user, password);
            Statement myStmt = myConn.createStatement();

        } catch (SQLException e){
            e.printStackTrace();
        }

        // Dmv een dynamische integer die in een while-loop elke issue 1 voor 1 afloopt
        int dynamischeInt = 0;
        while(dynamischeInt < issue.getAllIssues().size()){

            // Output in console
            System.out.println("==============================================================================");
            System.out.println("ID pakken van issue: " + issue.getAllIssues().get(dynamischeInt).getID());
            System.out.println("Status pakken van issue: " + issue.getAllIssues().get(dynamischeInt).getIssueStatus());
            System.out.println("Storypoints pakken van issue: " + issue.getAllIssues().get(dynamischeInt).getStoryPoints());

            // Toevoegen aan database
            try {

                // Database verbinding opzetten
                Connection myConn = DriverManager.getConnection(url, user, password);

                // Query voorbereiden
                String SQL = "INSERT INTO gO.issues (ID, status, storypoints) VALUES (?, ?, ?)";
                PreparedStatement myStmt = myConn.prepareStatement(SQL);
                myStmt.setString(1, String.valueOf(issue.getAllIssues().get(dynamischeInt).getID()));
                myStmt.setString(2, issue.getAllIssues().get(dynamischeInt).getIssueStatus());
                myStmt.setString(3, String.valueOf(issue.getAllIssues().get(dynamischeInt).getStoryPoints()));

                // Query uitvoeren
                myStmt.executeUpdate();

            } catch (SQLException e){
                e.printStackTrace();
            }

            dynamischeInt ++;

            System.out.println(dynamischeInt + "De regel is ingevoegd in database!");

        }
    }
}
