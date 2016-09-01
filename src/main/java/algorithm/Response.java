package algorithm;

import models.Center;

import java.util.List;

public class Response {

    private final List<Center> centersOut;

    public Response(List<Center> centersOut) {
        this.centersOut = centersOut;
    }

    public List<Center> getCentersOut() {
        return this.centersOut;
    }

}
