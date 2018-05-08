package kr.or.dgit.it.datapersistenceapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class WidgetActivity extends AppCompatActivity {
    ProgressBar mProgBar;
    ProgressBar mProgCircle;

    SeekBar mSeekBar;
    TextView mVolume;

    RatingBar mRating;
    TextView mRateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget);
        setTitle(getIntent().getStringExtra("title"));

        //progressBar
        mProgBar = findViewById(R.id.progress);
        mProgCircle = findViewById(R.id.progcircle);


        //SeekBar
        mSeekBar = findViewById(R.id.seek);
        mVolume = findViewById(R.id.volume);

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mVolume.setText("Now Valume : " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
        //RatingBar

        mRating = findViewById(R.id.ratingbar01);
        mRateText = findViewById(R.id.ratetext);

        mRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                mRateText.setText("Now Rate : " + rating);
            }
        });
    }

    public void mDecFirstClick(View view) {
        mProgBar.incrementProgressBy(-2);
    }

    public void mIncFirstClick(View view) {
        mProgBar.incrementProgressBy(2);
    }

    public void mDecSecondClick(View view) {
        mProgBar.incrementSecondaryProgressBy(-2);
    }

    public void mIncSecondClick(View view) {
        mProgBar.incrementSecondaryProgressBy(2);
    }

    public void mStartClick(View view) {
        mProgCircle.setVisibility(view.VISIBLE);
    }

    public void mStopClick(View view) {
        mProgCircle.setVisibility(view.INVISIBLE);
    }

}
