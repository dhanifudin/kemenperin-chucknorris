package com.dhanifudin.jokes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dhanifudin.jokes.generator.ServiceGenerator;
import com.dhanifudin.jokes.model.Joke;
import com.dhanifudin.jokes.services.ChuckService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

	private static final String TAG = MainActivity.class.getName();

	ImageView imageIcon;
	TextView textJoke;
	ChuckService service;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imageIcon = findViewById(R.id.image_icon);
		textJoke = findViewById(R.id.text_joke);
		Button buttonReload = findViewById(R.id.reload_buttton);

		service = ServiceGenerator.createService(ChuckService.class);
		reloadData(null);
//		buttonReload.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				reloadData();
//			}
//		});
	}

	public void reloadData(View view) {
		Call<Joke> jokeResponse = service.getRandomJoke();
		jokeResponse.enqueue(new Callback<Joke>() {
			@Override
			public void onResponse(Call<Joke> call, Response<Joke> response) {
				Joke joke = response.body();
				Log.i(TAG, joke.toString());
				Picasso.get().load(joke.getIconUrl()).into(imageIcon);
				textJoke.setText(joke.getValue());
			}

			@Override
			public void onFailure(Call<Joke> call, Throwable t) {
				Log.e(TAG, t.toString());
			}
		});
	}
}
