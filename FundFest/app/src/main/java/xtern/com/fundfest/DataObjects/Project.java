package xtern.com.fundfest.DataObjects;

import android.location.Location;
import android.util.Pair;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sean.phillips on 7/3/15.
 * Data object for a Project
 */
public class Project {

    public String title;
    public String description;
    public String[] pics;
    public String[] vids;
    public int voteCount;
    public String kickStarter;
    public String facebook;
    public String twitter;
    public String categoryId;
    public String id;
    public Location location;

//    public List<Pair<Integer,String>> backers;
//    public List<String> owners;
//    public DateTime endDate; //TODO : needs to be explained further
//    public DateTime estimatedDelivery; //TODO : Same as above
//    public int funded; //TODO : fuck all of this need to be explained
//    public int pledged;
//    public String projectDescription;
//    public String projectName;
//    public String projectID; //TODO objectID?
//    public DateTime createdAt;
//    public DateTime updatedAt;
//
//    //TODO : make a json stream reader (?) possibly to be done later
//
//    /**
//     * Reads in a Json Object straight from the server and makes
//     * a native Project data object
//     */
//    public static Project newInstance(JSONObject object) throws JSONException{
//        Project project = new Project();
//        DateTimeFormatter dateParser = ISODateTimeFormat.dateTimeNoMillis();
//
//        JSONArray backers = object.getJSONArray("backers");
//        for(int i = 0; i < backers.length(); i++){
//            JSONObject pledger = backers.getJSONObject(i);
//            Integer amount = pledger.getInt("amountPledged");
//            String userID = pledger.getString("userID");
//            project.backers.add(new Pair<>(amount,userID));
//        }
//
//        String endDate = object.getJSONObject("endDate").getString("iso");
//        //project.endDate = dateParser.parseDateTime(endDate);
//        String estDelivery = object.getJSONObject("estimatedDelivery").getString("iso");
//        //project.estimatedDelivery = dateParser.parseDateTime(estDelivery);
//
//        project.funded = object.getInt("funded");
//        JSONArray owners = object.getJSONArray("owners");
//        for(int i = 0; i < owners.length(); i++){
//            String owner = owners.getJSONObject(i).getString("name");
//            project.owners.add(owner);
//        }
//
//        project.pledged = object.getInt("pledged");
//        project.projectDescription = object.getString("projectDescription");
//        project.projectName = object.getString("projectName");
//        project.projectID = object.getString("objectId");
////        project.createdAt = dateParser.parseDateTime(object.getString("createdAt"));
////        project.updatedAt = dateParser.parseDateTime(object.getString("updatedAt"));
//
//        return project;
//    }
//
//    public static ArrayList<Project> newList(JSONArray jsonArray) throws JSONException{
//        ArrayList<Project> list = new ArrayList<>();
//        for(int i = 0; i < jsonArray.length(); i++){
//            list.add(newInstance(jsonArray.getJSONObject(i)));
//        }
//        return list;
//    }
//
//    public static ArrayList<Project> newList(String jsonString) throws JSONException{
//        return newList(new JSONArray(jsonString));
//    }
//
//
//
//    public Project(){
//        backers = new ArrayList<>();
//        owners = new ArrayList<>();
//    }

}
