package util;

import util.models.PayMap;

import com.squareup.moshi.*;
import okio.Buffer;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

public class JSONParser {

    public static String SFJSONToProperJSON (String SFJson) {
        return SFJson.substring(SFJson.indexOf('['), SFJson.indexOf(']')+1);
    }

    public static void parseJSON(String input) {
        String json;
        try {
            Moshi moshi = new Moshi.Builder().build();
            //json = Files.readString(Path.of("input/json2.txt"));
            json = SFJSONToProperJSON(input);
            System.out.println(json);
            Type type = Types.newParameterizedType(List.class, PayMap.class);
            JsonAdapter<List<PayMap>> adapter = moshi.adapter(type);
            List<PayMap> maps = adapter.fromJson(json);
            Buffer buffer = new Buffer();
            buffer.writeUtf8(json);

            for (PayMap map : maps) {
                System.out.println(map);
            }



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
