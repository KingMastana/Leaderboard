package com.mastanacodes.leaderboard.Fragments;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mastanacodes.leaderboard.Adapters.SkillsAdapter;
import com.mastanacodes.leaderboard.ApiClient;
import com.mastanacodes.leaderboard.Interface.JsonPlaceHolderApi;
import com.mastanacodes.leaderboard.R;
import com.mastanacodes.leaderboard.SkillsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class SkillsIQLeadersFragment extends Fragment {


    public SkillsIQLeadersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getTopSkillIQResponse();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_skills_iqleaders, container, false);
    }

    private void getTopSkillIQResponse() {
        JsonPlaceHolderApi apiService = ApiClient.getClient().create(JsonPlaceHolderApi.class);
        Call<List<SkillsModel>> call = apiService.getSkillsList();
        call.enqueue(new Callback<List<SkillsModel>>() {
            @Override
            public void onResponse(Call<List<SkillsModel>> call, Response<List<SkillsModel>> response) {
                List<SkillsModel> skillsModelList = response.body();
                RecyclerView recyclerView = getView().findViewById(R.id.skills_recycler_view);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                layoutManager.setOrientation(RecyclerView.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);
                SkillsAdapter adapter = new SkillsAdapter(getContext(), skillsModelList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<SkillsModel>> call, Throwable t) {

            }
        });
    }

}
