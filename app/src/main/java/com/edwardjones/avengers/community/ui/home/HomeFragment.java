package com.edwardjones.avengers.community.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.edwardjones.avengers.community.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class HomeFragment extends Fragment {

    ListView feed;
    ArrayList<Feed> feeds = new ArrayList<>();
    OkHttpClient client;
    String url;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        feed = (ListView)
                view.findViewById(R.id.feed);

        client = new OkHttpClient();

        getFeed();

        FeedAdapter feedAdapter = new FeedAdapter(getContext(),R.layout.feed_row,feeds);
        feed.setAdapter(feedAdapter);
        return view;
    }

    private void getFeed () {
        this.url = "http://ads19-feed-ws.codefest.ads-avengers.com/feeddata/feed/";

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                Log.e("FEED", "Error", e);
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                    Headers responseHeaders = response.headers();
                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        Log.i("FEED", responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }

                    ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
                    Feed[] jsonFeeds = mapper.readValue(responseBody.string(), Feed[].class);
                    for (Feed feed : jsonFeeds) {
                        feeds.add(feed);
                    }
                }
            }
        });
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
