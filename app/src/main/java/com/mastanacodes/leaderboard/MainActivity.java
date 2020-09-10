package com.mastanacodes.leaderboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.mastanacodes.leaderboard.Adapters.ViewPagerAdapter;
import com.mastanacodes.leaderboard.Fragments.LearningLeadersFragment;
import com.mastanacodes.leaderboard.Fragments.SkillsIQLeadersFragment;

public class MainActivity extends AppCompatActivity {

    private AppBarLayout appBarLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appBarLayout = findViewById(R.id.appBarLayout);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.view_pager);
        submitButton = findViewById(R.id.submit_btn);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new LearningLeadersFragment(), "Learning Leaders");
        adapter.addFragment(new SkillsIQLeadersFragment(), "Skill IQ Leaders");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent submitIntent = new Intent(MainActivity.this, SubmitActivity.class);
                startActivity(submitIntent);
            }
        });
    }
}
