package org.Dashboard;

public class ProgressData extends Issue {

    public ProgressData(){
        for (int i = 0; i < getAllIssues().size(); i++){
            System.out.println(" ID: " + getAllIssues().get(i).getID() + "\n" + " Storypoints: "
                    + getAllIssues().get(i).getStoryPoints() + "\n" +
                    " Status: " + getAllIssues().get(i).getIssueStatus()
                    + "\n" + "-----------------------------------------");

        }
    }
}
