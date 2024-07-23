package com.example.githubprofileviewer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private Button searchButton;
    private ImageView avatarImageView;
    private TextView nameTextView;
    private TextView bioTextView;
    private TextView followersTextView;
    private TextView followingTextView;
    private TextView locationTextView;
    private RecyclerView reposRecyclerView;

    private GitHubApiService apiService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.username);
        searchButton = findViewById(R.id.search);
        avatarImageView = findViewById(R.id.avatar);
        nameTextView = findViewById(R.id.name);
        bioTextView = findViewById(R.id.bio);
        followersTextView = findViewById(R.id.followers);
        followingTextView = findViewById(R.id.following);
        locationTextView = findViewById(R.id.location);
        reposRecyclerView = findViewById(R.id.repos_list);

        apiService = RetrofitClient.getRetrofitInstance().create(GitHubApiService.class);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchUserProfile();
            }
        });
    }

    private void fetchUserProfile() {
        String username = usernameEditText.getText().toString().trim();
        if (username.isEmpty()) {
            Toast.makeText(this, "Lütfen bir kullanıcı adı girin.", Toast.LENGTH_SHORT).show();
            return;
        }

        Call<User> userCall = apiService.getUserProfile(username);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    displayUserProfile(user);
                } else {
                    Toast.makeText(MainActivity.this, "Profil bilgileri bulunamadı.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Bir hata oluştu.", Toast.LENGTH_SHORT).show();
            }
        });

        Call<List<Repo>> reposCall = apiService.getUserRepos(username);
        reposCall.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                if (response.isSuccessful()) {
                    List<Repo> repos = response.body();
                    displayUserRepos(repos);
                } else {
                    Toast.makeText(MainActivity.this, "Projeler bulunamadı.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Repo
