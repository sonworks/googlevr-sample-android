package com.omotenavi.googlevrsample;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.vr.sdk.widgets.common.VrWidgetView;
import com.google.vr.sdk.widgets.video.VrVideoEventListener;
import com.google.vr.sdk.widgets.video.VrVideoView;

import java.io.IOException;

public class VrVideoActivity extends AppCompatActivity {

    private VrVideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vr_video);

        videoView = (VrVideoView) findViewById(R.id.vr_video_view);
        videoView.setInfoButtonEnabled(false);
        videoView.setEventListener(new VideoEventListener());
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
        videoView.resumeRendering();
        super.onResume();
    }

    @Override
    protected void onPause() {
        videoView.pauseRendering();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
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
