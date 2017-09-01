package com.omotenavi.googlevrsample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.vr.sdk.widgets.common.VrWidgetView;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;
import com.google.vr.sdk.widgets.video.VrVideoEventListener;
import com.google.vr.sdk.widgets.video.VrVideoView;

import java.io.IOException;

public class MainActivity extends Activity {


    private VrPanoramaView panoWidgetView1;
    private VrPanoramaView panoWidgetView2;
    private VrPanoramaView panoWidgetView3;
    private VrPanoramaView panoWidgetView4;
    private ImageLoaderTask backgroundImageLoaderTask1;
    private ImageLoaderTask backgroundImageLoaderTask2;
    private ImageLoaderTask backgroundImageLoaderTask3;
    private ImageLoaderTask backgroundImageLoaderTask4;

    private VrVideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        LinearLayout cardLinear = (LinearLayout) findViewById(R.id.cardLinear);
//        cardLinear.removeAllViews();
//
//        final int MAX_CNT = 4;
//        for (int i = 0; i < MAX_CNT; i++) {
//            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
//            LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.custom_card_view, null);
//            CardView cardView = (CardView) linearLayout.findViewById(R.id.cardView);
//            TextView textBox = (TextView) linearLayout.findViewById(R.id.textBox);
//            textBox.setText("Panorama View " + i);
//            cardView.setTag(i);
//            cardView.setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View view) {
//                    Integer tagId = Integer.valueOf(view.getTag().toString());
//                    if(tagId >= 0 && tagId < 3) {
//                        Intent intent = new Intent(MainActivity.this, VrPanoramaActivity.class);
//                        intent.putExtra("tagId", tagId);
//                        startActivity(intent);
//                    } if(tagId >= 3) {
//                        Intent intent = new Intent(MainActivity.this, VrVideoActivity.class);
//                        intent.putExtra("tagId", tagId);
//                        startActivity(intent);
//                    }
//                }
//            });
//            cardLinear.addView(linearLayout, i);
//        }


        panoWidgetView1 = (VrPanoramaView) findViewById(R.id.vr_view1);
        //panoWidgetView1.setDisplayMode(VrWidgetView.DisplayMode.FULLSCREEN_MONO);
        panoWidgetView1.setInfoButtonEnabled(false); // false:infoボタン非表示
        panoWidgetView1.setStereoModeButtonEnabled(false); // false:Stereoモード切り替えボタン非表示
        panoWidgetView1.setFullscreenButtonEnabled(true); // true:フルスクリーン表示
        panoWidgetView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(MainActivity.this, "Touched: " + view.getPivotX() + "," + view.getPivotY()
                                + "," + view.getHeight() + "," + view.getWidth()
                                + "," + view.getX() + "," + view.getY()
                        , Toast.LENGTH_LONG).show();
            }
        });

        panoWidgetView2 = (VrPanoramaView) findViewById(R.id.vr_view2);
        //panoWidgetView2.setDisplayMode(VrWidgetView.DisplayMode.FULLSCREEN_MONO);
        panoWidgetView2.setInfoButtonEnabled(false); // false:infoボタン非表示
        panoWidgetView2.setStereoModeButtonEnabled(false); // false:Stereoモード切り替えボタン非表示
        panoWidgetView2.setFullscreenButtonEnabled(true); // true:フルスクリーン表示
        panoWidgetView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(MainActivity.this, "Touched: " + view.getPivotX() + "," + view.getPivotY()
                                + "," + view.getHeight() + "," + view.getWidth()
                                + "," + view.getX() + "," + view.getY()
                        , Toast.LENGTH_LONG).show();
            }
        });

        panoWidgetView3 = (VrPanoramaView) findViewById(R.id.vr_view3);
        //panoWidgetView3.setDisplayMode(VrWidgetView.DisplayMode.FULLSCREEN_MONO);
        panoWidgetView3.setInfoButtonEnabled(false); // false:infoボタン非表示
        panoWidgetView3.setStereoModeButtonEnabled(false); // false:Stereoモード切り替えボタン非表示
        panoWidgetView3.setFullscreenButtonEnabled(true); // true:フルスクリーン表示
        panoWidgetView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(MainActivity.this, "Touched: " + view.getPivotX() + "," + view.getPivotY()
                                + "," + view.getHeight() + "," + view.getWidth()
                                + "," + view.getX() + "," + view.getY()
                        , Toast.LENGTH_LONG).show();
            }
        });

        panoWidgetView4 = (VrPanoramaView) findViewById(R.id.vr_view4);
        //panoWidgetView4.setDisplayMode(VrWidgetView.DisplayMode.FULLSCREEN_MONO);
        panoWidgetView4.setInfoButtonEnabled(false); // false:infoボタン非表示
        //panoWidgetView4.setStereoModeButtonEnabled(false); // false:Stereoモード切り替えボタン非表示
        panoWidgetView4.setFullscreenButtonEnabled(true); // true:フルスクリーン表示
        panoWidgetView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(MainActivity.this, "Touched: " + view.getPivotX() + "," + view.getPivotY()
                                + "," + view.getHeight() + "," + view.getWidth()
                                + "," + view.getX() + "," + view.getY()
                        , Toast.LENGTH_LONG).show();
            }
        });

//        loadPanoImage(tagId);

        ImageLoaderTask task1 = backgroundImageLoaderTask1;
        if (task1 != null && !task1.isCancelled()) {
            // Cancel any task from a previous loading.
            task1.cancel(true);
        }
        // pass in the name of the image to load from assets.
        VrPanoramaView.Options viewOptions1 = new VrPanoramaView.Options();
        viewOptions1.inputType = VrPanoramaView.Options.TYPE_MONO;
        task1 = new ImageLoaderTask(panoWidgetView1, viewOptions1, "panoimage0.jpg");
        task1.execute(getAssets());
        backgroundImageLoaderTask1 = task1;

        ImageLoaderTask task2 = backgroundImageLoaderTask2;
        if (task2 != null && !task2.isCancelled()) {
            // Cancel any task from a previous loading.
            task2.cancel(true);
        }
        // pass in the name of the image to load from assets.
        VrPanoramaView.Options viewOptions2 = new VrPanoramaView.Options();
        viewOptions2.inputType = VrPanoramaView.Options.TYPE_MONO;
        task2 = new ImageLoaderTask(panoWidgetView2, viewOptions2, "panoimage1.jpg");
        task2.execute(getAssets());
        backgroundImageLoaderTask2 = task2;

        ImageLoaderTask task3 = backgroundImageLoaderTask3;
        if (task3 != null && !task3.isCancelled()) {
            // Cancel any task from a previous loading.
            task3.cancel(true);
        }
        // pass in the name of the image to load from assets.
        VrPanoramaView.Options viewOptions3 = new VrPanoramaView.Options();
        viewOptions3.inputType = VrPanoramaView.Options.TYPE_MONO;
        task3 = new ImageLoaderTask(panoWidgetView3, viewOptions3, "panoimage2.jpg");
        task3.execute(getAssets());
        backgroundImageLoaderTask3 = task3;

        ImageLoaderTask task4 = backgroundImageLoaderTask4;
        if (task4 != null && !task4.isCancelled()) {
            // Cancel any task from a previous loading.
            task4.cancel(true);
        }
        // pass in the name of the image to load from assets.
        VrPanoramaView.Options viewOptions4 = new VrPanoramaView.Options();
        viewOptions4.inputType = VrPanoramaView.Options.TYPE_STEREO_OVER_UNDER;
        task4 = new ImageLoaderTask(panoWidgetView4, viewOptions4, "andes.jpg");
        task4.execute(getAssets());
        backgroundImageLoaderTask4 = task4;


        videoView = (VrVideoView) findViewById(R.id.vr_video_view);
        videoView.setInfoButtonEnabled(false);
        videoView.setEventListener(new MainActivity.VideoEventListener());
        try {
            VrVideoView.Options videoOptions = new VrVideoView.Options();
            videoOptions.inputType = VrVideoView.Options.TYPE_STEREO_OVER_UNDER;//.TYPE_MONO;

            // HLS 配信の場合は、inputFormat に FORMAT_HLS を指定する。
//            videoOptions.inputFormat = VrVideoView.Options.FORMAT_HLS;
//            Uri uri = Uri.parse("https://vr.cloudfront.net/sample.m3u8");
//            videoView.loadVideo(uri, videoOptions);

            // HLS 配信の場合は、inputFormat に FORMAT_HLS を指定する。
            videoOptions.inputFormat = VrVideoView.Options.FORMAT_DEFAULT;
            videoView.loadVideoFromAsset("congo.mp4", videoOptions);

//            // HLS 配信以外は FORMAT_DEFAULT を指定する。
//            videoOptions.inputFormat = VrVideoView.Options.FORMAT_DEFAULT;
//            videoView.loadVideoFromAsset("testRoom1_1920Mono.mp4", videoOptions);
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("VR", "Video Load Error.");
        }
    }

    @Override
    protected void onResume() {
        panoWidgetView1.resumeRendering();
        panoWidgetView2.resumeRendering();
        panoWidgetView3.resumeRendering();
        panoWidgetView4.resumeRendering();
        videoView.resumeRendering();
        super.onResume();
    }

    @Override
    protected void onPause() {
        panoWidgetView1.pauseRendering();
        panoWidgetView2.pauseRendering();
        panoWidgetView3.pauseRendering();
        panoWidgetView4.pauseRendering();
        videoView.pauseRendering();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        panoWidgetView1.shutdown();
        panoWidgetView2.shutdown();
        panoWidgetView3.shutdown();
        panoWidgetView4.shutdown();
        videoView.shutdown();
        super.onDestroy();
    }

    private class VideoEventListener extends VrVideoEventListener {
        @Override
        public void onLoadSuccess() {
            // コンテンツの読み込みに成功。
            super.onLoadSuccess();
            Log.d("VR", "Content Load Success.");
        }

        @Override
        public void onLoadError(String errorMessage) {
            // コンテンツの読み込みに失敗。
            super.onLoadError(errorMessage);
            Log.d("VR", errorMessage);
        }

        @Override
        public void onClick() {
            // View がタップされた時に呼ばれる。
            super.onClick();
            Log.d("VR", "Click");
        }

        @Override
        public void onDisplayModeChanged(int newDisplayMode) {
            // 表示モードが切り替わった時に呼ばれる。
            super.onDisplayModeChanged(newDisplayMode);
            String displayModeName;
            switch (newDisplayMode) {
                case VrWidgetView.DisplayMode.EMBEDDED:
                    displayModeName = "EMBEDDED";
                    break;
                case VrWidgetView.DisplayMode.FULLSCREEN_MONO:
                    displayModeName = "FULLSCREEN_MONO";
                    break;
//                case VrWidgetView.DisplayMode.FULLSCREEN_VR:
//                    displayModeName = "FULLSCREEN_VR";
//                    break;
                default:
                    displayModeName = "UNKNOWN";
                    break;
            }
            Log.d("VR", "Display Mode = " + displayModeName);
        }

        @Override
        public void onNewFrame() {
            // 動画再生位置を取得
            super.onNewFrame();
            Log.d("VR", "Position : " + videoView.getCurrentPosition());
        }

        @Override
        public void onCompletion() {
            // 動画再生が完了
            super.onCompletion();
            Log.d("VR", "onCompletion()");
            videoView.seekTo(0);
            videoView.playVideo();
        }
    }
}
