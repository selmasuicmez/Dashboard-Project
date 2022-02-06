package org.Dashboard;

import kong.unirest.json.JSONObject;

public class Sprint {
    private String startDate,endDate;
    private int jiraId;

    public Sprint(String startDate, String endDate, int jiraId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.jiraId = jiraId;
    }

    public Sprint(){

    }

    public Sprint getCurrentprint()
    {
        SendRequest sendRequest = new SendRequest();
        String query = "https://huteam01.atlassian.net/rest/agile/1.0/board/1/sprint?state=active";
        JSONObject resObj = sendRequest.sendRequest(query);
        JSONObject sprintArray = resObj.getJSONArray("values").getJSONObject(0);
        String startDate = sprintArray.getString("startDate").substring(0, 10);
        String endDate = sprintArray.getString("endDate").substring(0, 10);
        int jiraId = sprintArray.getInt("id");
        return new Sprint(startDate, endDate, jiraId);
    }

    public static int getInfo (String projectName, String taskName )
    {
        SendRequest sendRequest = new SendRequest();
        // get info by project name and task status
        String filter="status=%22"+taskName+"%22%20AND%20project=%22"+projectName+"%22";
        String query="https://huteam01.atlassian.net/rest/api/2/search?jql="+filter;

        JSONObject resObj = sendRequest.sendRequest(query);
        int total = resObj.getInt("total");
        return total;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getJiraId() {
        return jiraId;
    }

    public void setJiraId(int jiraId) {
        this.jiraId = jiraId;
    }
}
