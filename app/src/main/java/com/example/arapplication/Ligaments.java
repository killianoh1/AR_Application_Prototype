package com.example.arapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.PixelCopy;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arapplication.common.helpers.SnackbarHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.ar.core.AugmentedImage;
import com.google.ar.core.Frame;
import com.google.ar.core.TrackingState;
import com.google.ar.sceneform.ArSceneView;
import com.google.ar.sceneform.FrameTime;
import com.google.ar.sceneform.rendering.Color;
import com.google.ar.sceneform.rendering.Light;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/** Title: Hand Model
 * Author:  Poly Google
 * Date: 2017
 * Availability: https://poly.google.com/view/btWmPNVSKUc
 */

public class Ligaments extends AppCompatActivity {

    /** Title: Get started with Google Analytics
     * Author: Firebase
     * Date: 2020
     * Version: 2.0
     * Availability: https://firebase.google.com/docs/analytics/get-started?platform=android
     */

    private FirebaseAnalytics mFirebaseAnalytics;

    /** Title: ARCore Augmented Images
     * Author: Google Code labs
     * Date: 2019
     * Code version: 2.0
     * Availability: https://github.com/googlecodelabs/arcore-augmentedimage-intro
     */
    private ArFragment arFragment;
    private ImageView fitToScanView;

    private TextView index;

    private TextView middle;



    private TextView thumb;
    private Toolbar mainToolbar;

    /** Title: Introduction to Sceneform
     * Author: Google Code labs
     * Date: 2019
     * Code version: -
     * Availability: https://codelabs.developers.google.com/codelabs/sceneform-intro/index.html?index=..%2F..io2018#15
     */

    private FloatingActionButton fab;

    /** Title: ARCore Augmented Images
     * Author: Google Code labs
     * Date: 2019
     * Code version: 2.0
     * Availability: https://github.com/googlecodelabs/arcore-augmentedimage-intro
     */

    // Augmented image and its associated center pose anchor, keyed by the augmented image in
    // the database.
    private final Map augmentedImageMap = new HashMap<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ligaments);

        /** Title: Get started with Google Analytics
         * Author: Firebase
         * Date: 2020
         * Version: 2.0
         * Availability: https://firebase.google.com/docs/analytics/get-started?platform=android
         */

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        /** Title: ARCore Augmented Images
         * Author: Google Code labs
         * Date: 2019
         * Code version: 2.0
         * Availability: https://github.com/googlecodelabs/arcore-augmentedimage-intro
         */

        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ux_fragment);
        fitToScanView = findViewById(R.id.image_view_fit_to_scan);

        index = findViewById(R.id.index);

        middle = findViewById(R.id.middle);

        thumb = findViewById(R.id.thumb);

        /** Title: Android Blog App 2018 - Android Studio Firebase Tutorials - Part 7
         * Author: Akshaye JH
         * Date: 2018
         * Availability: https://www.youtube.com/watch?v=sDf7NKROoDM&list=PLGCjwl1RrtcR4ptHvrc_PQIxDBB5MGiJA&index=7
         */


        mainToolbar = findViewById(R.id.main_toolbar);

        setSupportActionBar(mainToolbar);

        getSupportActionBar().setTitle("Anatomy Insight - Ligaments");

        /** Title: Introduction to Sceneform
         * Author: Google Code labs
         * Date: 2019
         * Code version: -
         * Availability: https://codelabs.developers.google.com/codelabs/sceneform-intro/index.html?index=..%2F..io2018#15
         */


        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                takePhoto();

            }
        });


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

        index.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebViewL1Activity();
            }
        });



        /** Title: ARCore Augmented Images
         * Author: Google Code labs
         * Date: 2019
         * Code version: 2.0
         * Availability: https://github.com/googlecodelabs/arcore-augmentedimage-intro
         */

        arFragment.getArSceneView().getScene().addOnUpdateListener(this::onUpdateFrame);



    }


    /** Title: Introduction to Sceneform
     * Author: Google Code labs
     * Date: 2019
     * Code version: -
     * Availability: https://codelabs.developers.google.com/codelabs/sceneform-intro/index.html?index=..%2F..io2018#15
     */

    private String generateFilename() {
        String date =
                new SimpleDateFormat("yyyyMMddHHmmss", java.util.Locale.getDefault()).format(new Date());
        return Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES) + File.separator + "Sceneform/" + date + "_screenshot.jpg";
    }

    /** Title: Introduction to Sceneform
     * Author: Google Code labs
     * Date: 2019
     * Code version: -
     * Availability: https://codelabs.developers.google.com/codelabs/sceneform-intro/index.html?index=..%2F..io2018#15
     */

    private void saveBitmapToDisk(Bitmap bitmap, String filename) throws IOException {

        File out = new File(filename);
        if (!out.getParentFile().exists()) {
            out.getParentFile().mkdirs();
        }
        try (FileOutputStream outputStream = new FileOutputStream(filename);
             ByteArrayOutputStream outputData = new ByteArrayOutputStream()) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputData);
            outputData.writeTo(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException ex) {
            throw new IOException("Failed to save bitmap to disk", ex);
        }
    }

    /** Title: Introduction to Sceneform
     * Author: Google Code labs
     * Date: 2019
     * Code version: -
     * Availability: https://codelabs.developers.google.com/codelabs/sceneform-intro/index.html?index=..%2F..io2018#15
     */


    private void takePhoto() {
        final String filename = generateFilename();
        ArSceneView view = arFragment.getArSceneView();


        // Create a bitmap the size of the scene view.
        final Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),
                Bitmap.Config.ARGB_8888);

        // Create a handler thread to offload the processing of the image.
        final HandlerThread handlerThread = new HandlerThread("PixelCopier");
        handlerThread.start();
        // Make the request to copy.
        PixelCopy.request(view, bitmap, (copyResult) -> {
            if (copyResult == PixelCopy.SUCCESS) {
                try {
                    saveBitmapToDisk(bitmap, filename);
                } catch (IOException e) {
                    Toast toast = Toast.makeText(Ligaments.this, e.toString(),
                            Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }
                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                        "Photo saved", Snackbar.LENGTH_LONG);
                snackbar.setAction("Open in Photos", v -> {
                    File photoFile = new File(filename);

                    Uri photoURI = FileProvider.getUriForFile(Ligaments.this,
                            Ligaments.this.getPackageName() + ".ar.codelab.name.provider",
                            photoFile);
                    Intent intent = new Intent(Intent.ACTION_VIEW, photoURI);
                    intent.setDataAndType(photoURI, "image/*");
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    startActivity(intent);

                });
                snackbar.show();
            } else {
                Toast toast = Toast.makeText(Ligaments.this,
                        "Failed to copyPixels: " + copyResult, Toast.LENGTH_LONG);
                toast.show();
            }
            handlerThread.quitSafely();
        }, new Handler(handlerThread.getLooper()));
    }

    /** Title: WebView - Android Studio Tutorial
     * Author: Coding in Flow
     * Date: 2017
     * Code version: -
     * Availability: https://www.youtube.com/watch?v=TUXui5ItBkM
     */

    private void openWebViewL1Activity() {

        Intent loginIntent = new Intent(Ligaments.this, webview_L1.class);
        startActivity(loginIntent);
        finish();

        Toast.makeText(Ligaments.this, "Label has been selected", Toast.LENGTH_LONG).show();

    }

    /** Title: WebView - Android Studio Tutorial
     * Author: Coding in Flow
     * Date: 2017
     * Code version: -
     * Availability: https://www.youtube.com/watch?v=TUXui5ItBkM
     */

    private void openWebViewL2Activity() {

        Intent loginIntent = new Intent(Ligaments.this, webview_L2.class);
        startActivity(loginIntent);
        finish();

        Toast.makeText(Ligaments.this, "Label has been selected", Toast.LENGTH_LONG).show();

    }


    /** Title: WebView - Android Studio Tutorial
     * Author: Coding in Flow
     * Date: 2017
     * Code version: -
     * Availability: https://www.youtube.com/watch?v=TUXui5ItBkM
     */

    private void openWebViewL3Activity() {

        Intent loginIntent = new Intent(Ligaments.this, webview_L3.class);
        startActivity(loginIntent);
        finish();

        Toast.makeText(Ligaments.this, "Label has been selected", Toast.LENGTH_LONG).show();
    }

    /** Title: ARCore Augmented Images
     * Author: Google Code labs
     * Date: 2019
     * Code version: 2.0
     * Availability: https://github.com/googlecodelabs/arcore-augmentedimage-intro
     */

    @Override
    protected void onResume() {
        super.onResume();
        if (augmentedImageMap.isEmpty()) {
            fitToScanView.setVisibility(View.VISIBLE);

        }

        /** Title: Get started with Google Analytics
         * Author: Firebase
         * Date: 2020
         * Version: 2.0
         * Availability: https://firebase.google.com/docs/analytics/get-started?platform=android
         */

        mFirebaseAnalytics.setCurrentScreen(this, getClass().getSimpleName(), "Ligaments Screen");

    }



    /**
     * Registered with the Sceneform Scene object, this method is called at the start of each frame.
     *
     * @param frameTime - time since last frame.
     */

    /** Title: ARCore Augmented Images
     * Author: Google Code labs
     * Date: 2019
     * Code version: 2.0
     * Availability: https://github.com/googlecodelabs/arcore-augmentedimage-intro
     */
    @SuppressLint("RestrictedApi")
    private void onUpdateFrame(FrameTime frameTime) {
        Frame frame = arFragment.getArSceneView().getArFrame();

        // If there is no frame or ARCore is not tracking yet, just return.
        if (frame == null || frame.getCamera().getTrackingState() != TrackingState.TRACKING) {

            fitToScanView.setVisibility(View.VISIBLE);
            index.setVisibility(View.INVISIBLE);
            middle.setVisibility(View.INVISIBLE);
            thumb.setVisibility(View.INVISIBLE);
            fab.setVisibility(View.INVISIBLE);

            return;
        }

        /** Title: ARCore Augmented Images
         * Author: Google Code labs
         * Date: 2019
         * Code version: 2.0
         * Availability: https://github.com/googlecodelabs/arcore-augmentedimage-intro
         */

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
                    fab.setVisibility(View.VISIBLE);




                    // Create a new anchor for newly found images.
                    if (!augmentedImageMap.containsKey(augmentedImage))
                    {
                        MyARNodeL node = new MyARNodeL(this,R.raw.hand);
                        node.setImage(augmentedImage);

                        /** Title: LightingActivity
                         * Author: Google Code labs
                         * Date: 2018
                         * Code version: 2.0
                         * Availability: https://www.codota.com/web/assistant/code/rs/5c7c4ba12ef5570001db46ab#L209
                         */
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
        Intent loginIntent = new Intent(Ligaments.this, MenuApp.class);
        startActivity(loginIntent);
        finish();
        super.onBackPressed();

    }
}

