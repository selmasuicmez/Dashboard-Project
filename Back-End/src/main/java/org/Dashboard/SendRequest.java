package org.Dashboard;

import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

public class SendRequest {

    public SendRequest(){

    }

    public kong.unirest.json.JSONObject sendRequest(String query)
    {
        Authentication Authentication = new Authentication();
        kong.unirest.HttpResponse<JsonNode> response = Unirest.get(query)
                .basicAuth(Authentication.getUserEmail(), Authentication.getToken())
                .header("Accept", "application/json")
                .asJson();

        JSONObject responseObject = response.getBody().getObject();
        return responseObject;
    }
}
