package org.Dashboard;

import org.apache.http.HttpRequest;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.auth.BasicScheme;

public class BasicCredentials implements ICredentials{
    private String userName;
    private String token;

    /**
     * Creates new basic HTTP credentials.
     */
    public BasicCredentials(String userName, String token) {
        this.userName = userName;
        this.token = token;
    }
    /**
     * Sets the Authorization header for the given request.
     */
    public void authenticate(HttpRequest req) {
        Credentials creds = new UsernamePasswordCredentials(userName, token);
        req.addHeader(BasicScheme.authenticate(creds, "utf-8", false));
    }
}
