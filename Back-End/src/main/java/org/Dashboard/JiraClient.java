package org.Dashboard;


public class JiraClient {
    public static void main(String[] args)
    {
        Sprint sprint= new Sprint().getCurrentprint();
        Issue issue = new Issue();
        DataBaseCon dataBaseCon = new DataBaseCon();
        System.out.println("==============================================================================");
        System.out.println("Startdatum van sprint: "+sprint.getStartDate());
        System.out.println("EindDatum van Sprint: "+sprint.getEndDate());
        System.out.println("Id van Sprint: "+sprint.getJiraId());
        System.out.println("Totaal issues in Sprint Darma :"+ issue.getAllIssues().size());
        System.out.println("==============================================================================");
        ProgressData  progressData = new ProgressData();

    }

}
