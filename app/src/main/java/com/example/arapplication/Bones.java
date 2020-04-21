package com.example.arapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.example.arapplication.common.helpers.SnackbarHelper;
import com.google.ar.core.AugmentedImage;
import com.google.ar.core.Frame;
import com.google.ar.core.TrackingState;
import com.google.ar.sceneform.FrameTime;
import com.google.ar.sceneform.rendering.Color;
import com.google.ar.sceneform.rendering.Light;
import com.google.ar.sceneform.ux.ArFragment;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Bones extends AppCompatActivity {


    private Toolbar mainToolbar;
    private ArFragment arFragment;
    private ImageView fitToScanView;

    private TextView index;

    private TextView middle;

    private TextView thumb;



    // Augmented image and its associated center pose anchor, keyed by the augmented image in
    // the database.
    private final Map augmentedImageMap = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bones);

        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ux_fragment);
        fitToScanView = findViewById(R.id.image_view_fit_to_scan);

        index = findViewById(R.id.index);

        mainToolbar = findViewById(R.id.main_toolbar);

        setSupportActionBar(mainToolbar);

        getSupportActionBar().setTitle("Anatomy Insight - Bones");


        middle = findViewById(R.id.middle);

        thumb = findViewById(R.id.thumb);

        thumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebViewB3Activity();
            }
        });

        middle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebViewB2Activity();
            }
        });

        index.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebViewB1Activity();
            }
        });


        arFragment.getArSceneView().getScene().addOnUpdateListener(this::onUpdateFrame);


    }





    private void openWebViewB1Activity() {

        Intent loginIntent = new Intent(Bones.this, webview_b3.class);
        startActivity(loginIntent);
        finish();

    }

    private void openWebViewB2Activity() {

        Intent loginIntent = new Intent(Bones.this, webview_b2.class);
        startActivity(loginIntent);
        finish();

    }

    private void openWebViewB3Activity() {

        Intent loginIntent = new Intent(Bones.this, webview_b3.class);
        startActivity(loginIntent);
        finish();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (augmentedImageMap.isEmpty()) {
            fitToScanView.setVisibility(View.VISIBLE);
        }


    }


    /**
     * Registered with the Sceneform Scene object, this method is called at the start of each frame.
     *
     * @param frameTime - time since last frame.
     */
    public void onUpdateFrame(FrameTime frameTime) {
        Frame frame = arFragment.getArSceneView().getArFrame();

        // If there is no frame or ARCore is not tracking yet, just return.
        if (frame == null || frame.getCamera().getTrackingState() != TrackingState.TRACKING) {

            fitToScanView.setVisibility(View.VISIBLE);
            index.setVisibility(View.INVISIBLE);
            middle.setVisibility(View.INVISIBLE);
            thumb.setVisibility(View.INVISIBLE);

            return;
        }

        Collection<AugmentedImage> updatedAugmentedImages =
                frame.getUpdatedTrackables(AugmentedImage.class);
        for (AugmentedImage augmentedImage : updatedAugmentedImages) {
            switch (augmentedImage.getTrackingState()) {
                case PAUSED:
                    // When an image is in PAUSED state, but the camera is not PAUSED, it has been detected,
                    // but not yet tracked.
                    String text = "Detected Image " + augmentedImage.getIndex();
                    SnackbarHelper.getInstance().showMessage(this, text);
                    break;

                case TRACKING:
                    // Have to switch to UI Thread to update View.
                    fitToScanView.setVisibility(View.GONE);


                    index.setVisibility(View.VISIBLE);
                    middle.setVisibility(View.VISIBLE);
                    thumb.setVisibility(View.VISIBLE);


                    // Create a new anchor for newly found images.
                    if (!augmentedImageMap.containsKey(augmentedImage)) {
                        MyARNodeB node = new MyARNodeB(this, R.raw.blue_bones);
                        node.setImage(augmentedImage);
                        node.setLight(Light.builder(Light.Type.POINT).setColor(new Color(0,245,244)).build());
                        augmentedImageMap.put(augmentedImage, node);
                        arFragment.getArSceneView().getScene().addChild(node);


                    }

                    break;

                case STOPPED:
                    augmentedImageMap.remove(augmentedImage);


                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent loginIntent = new Intent(Bones.this, MenuApp.class);
        startActivity(loginIntent);
        finish();

        super.onBackPressed();

    }
}
