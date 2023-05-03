package CafeMangementSystem.com.utils;

import com.google.common.base.Strings;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Slf4j
public class CafeUtils {


    //define custom exception
    private CafeUtils(){

    }

    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus){
        return new ResponseEntity<String>("{\"message\":\"" +responseMessage + "\"}",httpStatus);
    }

    // it will give us unique value.. means it will generate one qunique value
    public static String getUUID() {
        Date date = new Date();
        long time= date.getTime();
        return "BILL-" +time;
    }

    // convert String to JSON Array
    public static JSONArray getJsonArrayFromString(String data)throws JSONException
    {
        JSONArray jsonArray= new JSONArray(data);
        return  jsonArray;
    }

    //If String is empty it will return HashMap.... JSON  data convert String and Object
    //we will get string data basically JSON convert Map<String, Object> from
    public static Map<String, Object> getMapFromJson(String data){
        if(!Strings.isNullOrEmpty(data))
            return new Gson().fromJson(data, new TypeToken<Map<String, Object>>(){
        }.getType());
        return new HashMap<>();
    }


    //if file actully exist memory or not
    public static boolean isFileExist(String path){
        log.info("Inside isFileExist {}", path);
        try{
            File file= new File(path);
            return (file != null && file.exists()) ? Boolean.TRUE : Boolean.FALSE;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return  false;
    }
}
