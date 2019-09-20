package com.edwardjones.avengers.community.ui.region;

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

public class RegionFragment extends Fragment {

    ListView region;
    ArrayList<Region> regions = new ArrayList<>();
    OkHttpClient client;
    /*    RequestQueue requestQueue;*/
    String url;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_region, container, false);

        client = new OkHttpClient();

        region = (ListView)
                view.findViewById(R.id.regionPeople);

/*        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getContext());
        }*/
        //requestQueue = Volley.newRequestQueue(getContext());
        getRegion();

        RegionAdapter regionAdapter = new RegionAdapter(getContext(), R.layout.region_row, regions);
        region.setAdapter(regionAdapter);
        /*        requestQueue.stop();*/
        return view;
    }

    private void getRegion() {
        this.url = "http://ads19-person-ws.codefest.ads-avengers.com/person/region?regionId=77";

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("REGION", "Error", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);

                    Headers responseHeaders = response.headers();
                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        Log.i("REGION", responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }

                    ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
//                    mapper.rea
                    Region[] jsonFeeds = mapper.readValue(responseBody.string(), Region[].class);
                    for (Region region : jsonFeeds) {
                        regions.add(region);
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