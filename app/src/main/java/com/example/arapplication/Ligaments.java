package com.example.arapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.arapplication.common.helpers.SnackbarHelper;
import com.google.ar.core.AugmentedImage;
import com.google.ar.core.Frame;
import com.google.ar.core.TrackingState;
import com.google.ar.sceneform.FrameTime;
import com.google.ar.sceneform.ux.ArFragment;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Ligaments extends AppCompatActivity {

    private ArFragment arFragment;
    private ImageView fitToScanView;

    private TextView planetInfoCard;

    private TextView middle;

    private TextView thumb;









    // Augmented image and its associated center pose anchor, keyed by the augmented image in
    // the database.
    private final Map augmentedImageMap = new HashMap<>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ligaments);

        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ux_fragment);
        fitToScanView = findViewById(R.id.image_view_fit_to_scan);

        planetInfoCard = findViewById(R.id.index);

        middle = findViewById(R.id.middle);

        thumb = findViewById(R.id.thumb);

        thumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebViewL3Activity();
            }
        });

        middle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebViewL2Activity();
            }
        });

        planetInfoCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebViewL1Activity();
            }
        });





        arFragment.getArSceneView().getScene().addOnUpdateListener(this::onUpdateFrame);



    }

    private void openWebViewL1Activity() {

        Intent loginIntent = new Intent(Ligaments.this, webview_L1.class);
        startActivity(loginIntent);
        finish();

    }

    private void openWebViewL2Activity() {

        Intent loginIntent = new Intent(Ligaments.this, webview_L2.class);
        startActivity(loginIntent);
        finish();

    }

    private void openWebViewL3Activity() {

        Intent loginIntent = new Intent(Ligaments.this, webview_L3.class);
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
    private void onUpdateFrame(FrameTime frameTime) {
        Frame frame = arFragment.getArSceneView().getArFrame();

        // If there is no frame or ARCore is not tracking yet, just return.
        if (frame == null || frame.getCamera().getTrackingState() != TrackingState.TRACKING) {

            fitToScanView.setVisibility(View.VISIBLE);
            planetInfoCard.setVisibility(View.INVISIBLE);
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


                    planetInfoCard.setVisibility(View.VISIBLE);
                    middle.setVisibility(View.VISIBLE);
                    thumb.setVisibility(View.VISIBLE);




                    // Create a new anchor for newly found images.
                    if (!augmentedImageMap.containsKey(augmentedImage))
                    {
                        MyARNode node = new MyARNode(this,R.raw.hand);
                        node.setImage(augmentedImage);
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
        Intent loginIntent = new Intent(Ligaments.this, MenuApp.class);
        startActivity(loginIntent);
        finish();

    }
}

