package es.uca.gofestapppp;

import android.os.StrictMode;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class  HttpRequest {

    private OkHttpClient client = new OkHttpClient();
    private String url = "http://10.0.2.2:8080/assistants/";
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    private User user;
    private ArrayList<User> users;
    private static final MediaType JSON = MediaType.parse("application/json");

    public HttpRequest() {
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }

    public User getUser(String id){
        Request request = new Request.Builder()
                .url(url+id)
                .build();
        try {
            Response res = client.newCall(request).execute();
            JSONObject obj = new JSONObject(res.body().string());
            user = new User(obj.getString("_id"), obj.getString("name"), obj.getString("dni"), obj.getString("phone"), sf.parse(obj.getString("birthdate")), sf.parse(obj.getString("inscriptiondate")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public ArrayList<User> getAll(){
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            users = new ArrayList<>();
            Response res = client.newCall(request).execute();
            JSONArray array = new JSONArray(res.body().string());
            for(int i = 0; i< array.length(); i++){
                JSONObject obj = array.getJSONObject(i);
                users.add(new User(obj.getString("_id"), obj.getString("name"), obj.getString("dni"), obj.getString("phone"), sf.parse(obj.getString("birthdate")), sf.parse(obj.getString("inscriptiondate"))));
            }
        } catch (IOException | JSONException | ParseException e) {
            e.printStackTrace();
        }
        return users;
    }

    public String put(User u) throws IOException, JSONException {
        String json = "{ \"name\": \"" + u.getNombre() + "\", \"dni\" : \"" + u.getDni() + "\", \"phone\" : " + u.getTelefono() + ", \"birthdate\" : \"" + sf.format(u.getNacimiento()) + "\", \"inscriptiondate\" : \"" + sf.format(u.getInscripcion()) + "\"}";
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url+u.getId())
                .put(body)
                .build();
        Response res = client.newCall(request).execute();
        JSONObject mensage = new JSONObject(res.body().string());
        try {
            if (mensage.has("errors")) {
                JSONObject error = new JSONObject(mensage.getString("errors"));
                if (error.has("dni")) {
                    JSONObject errordni = new JSONObject(error.getString("dni"));
                    Log.d("a", errordni.getString("message"));
                    return errordni.getString("message");
                }
                if (error.has("phone")) {
                    JSONObject errorphone = new JSONObject(error.getString("phone"));
                    return errorphone.getString("message");
                }
            }
        }catch (JSONException ignored){

        }
        return mensage.getString("msg");
    }

    public String post(User u) throws IOException, JSONException {
        String json = "{ \"name\": \"" + u.getNombre() + "\", \"dni\" : \"" + u.getDni() + "\", \"phone\" : " + u.getTelefono() + ", \"birthdate\" : \"" + sf.format(u.getNacimiento()) + "\", \"inscriptiondate\" : \"" + sf.format(u.getInscripcion()) + "\"}";
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response res = client.newCall(request).execute();
        JSONObject mensage = new JSONObject(res.body().string());
        try {
            if (mensage.has("errors")) {
                JSONObject error = new JSONObject(mensage.getString("errors"));
                if (error.has("dni")) {
                    JSONObject errordni = new JSONObject(error.getString("dni"));
                    Log.d("a", errordni.getString("message"));
                    return errordni.getString("message");
                }
                if (error.has("phone")) {
                    JSONObject errorphone = new JSONObject(error.getString("phone"));
                    return errorphone.getString("message");
                }
            }
        }catch (JSONException ignored){

        }
        return mensage.getString("msg");
    }

    public String delete(String id) throws IOException, JSONException {
        Request request = new Request.Builder()
                .url(url+id)
                .delete()
                .build();
        Response res = client.newCall(request).execute();
        JSONObject mensage = new JSONObject(res.body().string());
        return mensage.getString("msg");
    }
}
