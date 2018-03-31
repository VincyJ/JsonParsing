package example.com.jsonparsing.interfaces;

import java.util.List;

import example.com.jsonparsing.model.Movie;

/**
 * Created by compaq on 3/30/2018.
 */

/**
 * ApiCallback is an interface to send
 * success and failure callback to the ui
 */
public interface ApiCallback {

    // It recieves data and send it to ui when the api call is success
    void succsessCallback(List<Movie> response);

    // It is to inform the user that the api call is failed.
    void failureCallback();
}
