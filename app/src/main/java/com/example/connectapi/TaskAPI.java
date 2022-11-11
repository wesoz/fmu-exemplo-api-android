package com.example.connectapi;

import android.content.Context;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


public class TaskAPI extends AsyncTask<Void, Void, GameResult> {
    public interface  AsyncResponse {
        void processFinish(GameResult gameResult);
    }

    public AsyncResponse delegate = null;

    Context context;
    String timea;
    String timeb;
    String queryParams;

    public TaskAPI(Context context, String timea, String timeb, AsyncResponse asyncResponse) {
        this.context = context;
        this.timea = timea;
        this.timeb = timeb;
        this.delegate = asyncResponse;
        queryParams = "?timea=" + timea + "&timeb=" + timeb;
    }

    @Override
    protected GameResult doInBackground(Void... voids) {
        GameResult gameResult = new GameResult();

        try {
            URL apiEndpoint = new URL("https://jogosselecao.wesleyoliveir12.repl.co/resultado" + queryParams);

            HttpsURLConnection urlConnection = (HttpsURLConnection) apiEndpoint.openConnection();

            urlConnection.setRequestMethod("GET");

            InputStream responseBody = urlConnection.getInputStream();

            InputStreamReader respondeBodyReader = new InputStreamReader(responseBody, "UTF-8");

            JsonReader jsonReader = new JsonReader(respondeBodyReader);

            jsonReader.beginObject();

            while (jsonReader.hasNext()) {
                String key = jsonReader.nextName();
                switch (key) {
                    case "Draw":
                        gameResult.setDraw(jsonReader.nextDouble());
                        break;
                    case "Games":
                        gameResult.setGames(jsonReader.nextInt());
                        break;
                    case "Loose":
                        gameResult.setLoose(jsonReader.nextDouble());
                        break;
                    case "Win":
                        gameResult.setWin(jsonReader.nextDouble());
                        break;
                    default:
                        break;
                }
            }
            jsonReader.close();
            urlConnection.disconnect();
            System.out.println("!!!!!!!!!!!!!!!");
            System.out.println(gameResult.getGames());
            System.out.println("!!!!!!!!!!!!!!!");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return gameResult;
    }

    @Override
    protected void onPostExecute(GameResult result) {
        delegate.processFinish(result);
        Toast.makeText(this.context, result.toString(), Toast.LENGTH_LONG).show();
    }
}
