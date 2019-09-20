package com.edwardjones.avengers.community.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.edwardjones.avengers.community.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    ListView feed;
    ArrayList<Feed> feeds = new ArrayList<>();
    RequestQueue requestQueue;
    String url;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        feed = (ListView)
                view.findViewById(R.id.feed);

        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        getFeed();

        FeedAdapter feedAdapter = new FeedAdapter(getContext(),R.layout.feed_row,feeds);
        feed.setAdapter(feedAdapter);
        return view;
    }

    private void getFeed () {
        this.url = "http://ads19-feed-ws.codefest.ads-avengers.com/feeddata/feed/";

        JsonArrayRequest arrReq = new JsonArrayRequest(Request.Method.GET, url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response.length() > 0) {
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject jsonObj = response.getJSONObject(i);
                                    Feed tempFeed = new Feed();
                                    tempFeed.setAuthor(jsonObj.get("author").toString());
                                    tempFeed.setContent(jsonObj.get("content").toString());
                                    feeds.add(tempFeed);
                                } catch (JSONException e) {
                                    // If there is an error then output this to the logs.
                                    Log.e("request", "Invalid JSON Object.");
                                }

                            }
                        } else {
                            // TODO
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("request", error.toString());
                    }
                }
        );
        requestQueue.add(arrReq);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
