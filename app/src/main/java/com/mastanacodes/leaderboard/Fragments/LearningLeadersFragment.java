package com.mastanacodes.leaderboard.Fragments;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mastanacodes.leaderboard.Adapters.HoursAdapter;
import com.mastanacodes.leaderboard.ApiClient;
import com.mastanacodes.leaderboard.HoursModel;
import com.mastanacodes.leaderboard.Interface.JsonPlaceHolderApi;
import com.mastanacodes.leaderboard.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LearningLeadersFragment extends Fragment {

    public LearningLeadersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getTopLearnersResponse();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_learning_leaders, container, false);

        return view;
    }

    private void getTopLearnersResponse() {
        JsonPlaceHolderApi apiService = ApiClient.getClient().create(JsonPlaceHolderApi.class);
        Call<List<HoursModel>> call = apiService.getHoursList();
        call.enqueue(new Callback<List<HoursModel>>() {
            @Override
            public void onResponse(Call<List<HoursModel>> call, Response<List<HoursModel>> response) {
                List<HoursModel> hoursModelList = response.body();
                RecyclerView recyclerView = getView().findViewById(R.id.hours_recycler_view);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                layoutManager.setOrientation(RecyclerView.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);
                HoursAdapter adapter = new HoursAdapter(getContext(),hoursModelList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<HoursModel>> call, Throwable t) {

            }
        });
    }

}
