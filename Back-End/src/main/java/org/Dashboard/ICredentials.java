package org.Dashboard;
import org.apache.http.HttpRequest;

public interface ICredentials {

    void authenticate(HttpRequest req);

}