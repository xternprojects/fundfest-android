package xtern.com.fundfest.Parsers;

import android.util.JsonReader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProjectParser {

    JsonReader reader;

    public ProjectParser(InputStream stream) throws Exception{
        reader = new JsonReader(new BufferedReader(new InputStreamReader(stream, "UTF-8"), 8));
    }
}
