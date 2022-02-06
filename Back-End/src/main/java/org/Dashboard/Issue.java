package org.Dashboard;

import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Issue {
    private String issueStatus;
    private int storyPoints;
    private int ID;

    public Issue(String issueStatus, int storyPoints){
        this.issueStatus = issueStatus;
        this.storyPoints = storyPoints;
    }

    public Issue(){

    }

    public List<Issue> getAllIssues()
    {
        SendRequest sendRequest = new SendRequest();
        String query = "https://huteam01.atlassian.net/rest/api/2/search?jql=ORDER+BY+status+ASC";
        JSONObject resObj = sendRequest.sendRequest(query);
        JSONArray issues = resObj.getJSONArray("issues");
        List<Issue> parsedIssueList = deserialize(issues);
        return parsedIssueList;
    }

    private static List<Issue> deserialize(JSONArray issues)
    {   int totalUserstoriePoints=0;
        List<Issue> issueList =new ArrayList<Issue>();

        for(int i=0; i<issues.length();i++) {
            Issue issue = new Issue();
            // parsing of json response to get information
            JSONObject jsonObjectIssue = issues.getJSONObject(i);

            JSONObject fieldsObject = jsonObjectIssue.getJSONObject("fields");

            String status = fieldsObject.getJSONObject("status").getString("name");
            issue.setIssueStatus(status);

            int ID2 = Integer.parseInt(jsonObjectIssue.getString("id"));
            int ID = Integer.parseInt(fieldsObject.getJSONObject("status").getString("id"));
            issue.setID(ID2);

            //Story point saved to customfield_10016
            if (notNullObject(fieldsObject, "customfield_10016")) {
                Integer sp = fieldsObject.getInt("customfield_10016");
                totalUserstoriePoints += sp;
                issue.setStoryPoints(sp);
//                System.out.println("Issue nummer : " + i + " - Aantal storypoint zijn: " + sp);
            }
            //add issue in a list
            issueList.add(issue);

        }
//        System.out.println("Totaal user storie punten: "+totalUserstoriePoints);
        return issueList;
    }

    private static boolean notNullObject(JSONObject obj, String key)
    {
        if ( !(obj.isNull(key)))
            return true;

        else
            return false;
    }

    // Getters and Setters
    public String getIssueStatus() {
        return issueStatus;
    }

    public void setIssueStatus(String issueStatus) {
        this.issueStatus = issueStatus;
    }

    public int getID(){
        return ID;
    }

    public void setID(int ID){
        this.ID = ID;
    }

    public int getStoryPoints() {
        return storyPoints;
    }


    public void setStoryPoints(int storyPoints) {
        this.storyPoints = storyPoints;
    }


}
