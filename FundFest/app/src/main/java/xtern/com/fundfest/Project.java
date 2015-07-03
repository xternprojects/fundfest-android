package xtern.com.fundfest;

import android.util.Pair;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sean.phillips on 7/3/15.
 * Data object for a Project
 */
public class Project {

    List<Pair<Integer,String>> backers;
    List<String> owners;
    DateTime endDate; //TODO : needs to be explained further
    DateTime estimatedDelivery; //TODO : Same as above
    int funded; //TODO : fuck all of this need to be explained
    int pledged;
    String projectDescription;
    String projectName;
    String projectID; //TODO objectID?
    DateTime createdAt;
    DateTime updatedAt;

    //TODO : make a json stream reader (?) possibly to be done later

    /**
     * Reads in a Json Object straight from the server and makes
     * a native Project data object
     */
    public static Project newInstance(JSONObject object) throws JSONException{
        Project project = new Project();
        DateTimeFormatter dateParser = ISODateTimeFormat.dateTimeNoMillis();

        JSONArray backers = object.getJSONArray("backers");
        for(int i = 0; i < backers.length(); i++){
            JSONObject pledger = backers.getJSONObject(i);
            Integer amount = pledger.getInt("amountPledged");
            String userID = pledger.getString("userID");
            project.backers.add(new Pair<>(amount,userID));
        }

        String endDate = object.getJSONObject("endDate").getString("iso");
        project.endDate = dateParser.parseDateTime(endDate);
        String estDelivery = object.getJSONObject("estimatedDelivery").getString("iso");
        project.estimatedDelivery = dateParser.parseDateTime(estDelivery);

        project.funded = object.getInt("funded");
        JSONArray owners = object.getJSONArray("owners");
        for(int i = 0; i < owners.length(); i++){
            String owner = backers.getJSONObject(i).getString("name");
            project.owners.add(owner);
        }

        project.pledged = object.getInt("pledged");
        project.projectDescription = object.getString("projectDescription");
        project.projectName = object.getString("projectName");
        project.projectID = object.getString("objectId");
        project.createdAt = dateParser.parseDateTime(object.getString("createdAt"));
        project.updatedAt = dateParser.parseDateTime(object.getString("updatedAt"));

        return project;
    }

    public static Project newInstance(String jsonString) throws JSONException{
        return Project.newInstance(new JSONObject(jsonString));
    }

    public static Project newInstance(JSONArray jsonArray) throws JSONException{
        return Project.newInstance(jsonArray.getJSONObject(0));
    }

    public Project(){
        backers = new ArrayList<>();
        owners = new ArrayList<>();
    }

}
