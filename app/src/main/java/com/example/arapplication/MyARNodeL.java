package com.example.arapplication;

import android.content.Context;

import com.google.ar.core.AugmentedImage;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;

import java.util.concurrent.CompletableFuture;

/** Title: ARCore Augmented Images
 * Author: Google Code labs
 * Date: 2019
 * Code version: 2.0
 * Availability: https://github.com/googlecodelabs/arcore-augmentedimage-intro
 */

public class MyARNodeL extends AnchorNode {

    /** Title: ARCore Augmented Images
     * Author: Google Code labs
     * Date: 2019
     * Code version: 2.0
     * Availability: https://github.com/googlecodelabs/arcore-augmentedimage-intro
     */

    private AugmentedImage image;
    private static CompletableFuture<ModelRenderable> modelRenderableCompletableFuture;


    private float hand_scale = 0.0f;

    /** Title: ARCore Augmented Images
     * Author: Google Code labs
     * Date: 2019
     * Code version: 2.0
     * Availability: https://github.com/googlecodelabs/arcore-augmentedimage-intro
     */


    public MyARNodeL(Context context, int modelId)
    {

        if (modelRenderableCompletableFuture == null) {


            modelRenderableCompletableFuture = ModelRenderable.builder()
                    .setRegistryId("my_model")
                    .setSource(context, modelId)
                    .build();



        }



    }

    /** Title: ARCore Augmented Images
     * Author: Google Code labs
     * Date: 2019
     * Code version: 2.0
     * Availability: https://github.com/googlecodelabs/arcore-augmentedimage-intro
     */

    public void setImage(AugmentedImage image) {
        this.image = image;

        /** Title: ARCore Augmented Images
         * Author: Google Code labs
         * Date: 2019
         * Code version: 2.0
         * Availability: https://github.com/googlecodelabs/arcore-augmentedimage-intro
         */
        if (!modelRenderableCompletableFuture.isDone())
        {
            CompletableFuture.allOf(modelRenderableCompletableFuture)
                    .thenAccept((Void aVoid)-> {
                        setImage(image);
                    }).exceptionally(throwable -> {
                return null;
            });
        }

        setAnchor(image.createAnchor(image.getCenterPose()));


        Node node = new Node();
        node.setParent(this);
        node.setRenderable(modelRenderableCompletableFuture.getNow(null));


        final float maze_edge_size = 5f;
        final float max_image_edge = Math.max(image.getExtentX(), image.getExtentZ());
        hand_scale = max_image_edge / maze_edge_size;


        // Scale Y an extra 10 times to lower the maze wall.
        node.setLocalScale(new Vector3(hand_scale, hand_scale * 0.1f, hand_scale));

    }



    /** Title: ARCore Augmented Images
     * Author: Google Code labs
     * Date: 2019
     * Code version: 2.0
     * Availability: https://github.com/googlecodelabs/arcore-augmentedimage-intro
     */


    public AugmentedImage getImage() {
        return image;
    }
}
