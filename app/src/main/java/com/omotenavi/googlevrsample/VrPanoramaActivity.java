package com.omotenavi.googlevrsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.vr.sdk.widgets.common.VrWidgetView;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;

public class VrPanoramaActivity extends AppCompatActivity {

    private VrPanoramaView panoWidgetView;
    private ImageLoaderTask backgroundImageLoaderTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vr_panorama);

        Integer tagId = getIntent().getIntExtra("tagId", -1);

        panoWidgetView = (VrPanoramaView) findViewById(R.id.vr_view);
        panoWidgetView.setDisplayMode(VrWidgetView.DisplayMode.FULLSCREEN_MONO);
        panoWidgetView.setInfoButtonEnabled(false); // false:infoボタン非表示
        panoWidgetView.setStereoModeButtonEnabled(false); // false:Stereoモード切り替えボタン非表示
        panoWidgetView.setFullscreenButtonEnabled(true); // true:フルスクリーン表示

        panoWidgetView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(VrPanoramaActivity.this, "Touched: " + view.getPivotX() + "," + view.getPivotY()
                        + "," + view.getHeight() + "," + view.getWidth()
                        + "," + view.getX() + "," + view.getY()
                        , Toast.LENGTH_LONG).show();
            }
        });

        loadPanoImage(tagId);
    }

    @Override
    protected void onResume() {
        panoWidgetView.resumeRendering();
        super.onResume();
    }

    @Override
    protected void onPause() {
        panoWidgetView.pauseRendering();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        panoWidgetView.shutdown(); // Destroy the widget and free memory.
        super.onDestroy();
    }

    private synchronized void loadPanoImage(Integer tagId) {
        ImageLoaderTask task = backgroundImageLoaderTask;
        if(task != null && !task.isCancelled()) {
            // Cancel any task from a previous loading.
            task.cancel(true);
        }

        // pass in the name of the image to load from assets.
        VrPanoramaView.Options viewOptions = new VrPanoramaView.Options();
        viewOptions.inputType = VrPanoramaView.Options.TYPE_MONO;

        String panoImageName = "";
        if(0 == tagId) {
            panoImageName = "panoimage0.jpg";
        } else if(1 == tagId) {
            panoImageName = "panoimage1.jpg";
        } else if(2 == tagId) {
            panoImageName = "panoimage2.jpg";
        }
        if(panoImageName != null && panoImageName.length() > 0) {
            task = new ImageLoaderTask(panoWidgetView, viewOptions, panoImageName);
            task.execute(getAssets());
            backgroundImageLoaderTask = task;
        }

    }
}
