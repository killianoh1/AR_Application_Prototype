package com.example.arapplication;

import com.google.ar.sceneform.ux.ArFragment;

import android.Manifest;

/** Title: Introduction to Sceneform
 * Author: Google Code labs
 * Date: 2019
 * Code version: -
 * Availability: https://codelabs.developers.google.com/codelabs/sceneform-intro/index.html?index=..%2F..io2018#15
 */

public class WritingARFragment extends ArFragment {

    /** Title: Introduction to Sceneform
     * Author: Google Code labs
     * Date: 2019
     * Code version: -
     * Availability: https://codelabs.developers.google.com/codelabs/sceneform-intro/index.html?index=..%2F..io2018#15
     */
    @Override
    public String[] getAdditionalPermissions() {
        String[] additionalPermissions = super.getAdditionalPermissions();
        int permissionLength = additionalPermissions != null ? additionalPermissions.length : 0;
        String[] permissions = new String[permissionLength + 1];
        permissions[0] = Manifest.permission.WRITE_EXTERNAL_STORAGE;

        /** Title: Introduction to Sceneform
         * Author: Google Code labs
         * Date: 2019
         * Code version: -
         * Availability: https://codelabs.developers.google.com/codelabs/sceneform-intro/index.html?index=..%2F..io2018#15
         */
        if (permissionLength > 0) {
            System.arraycopy(additionalPermissions, 0, permissions, 1, additionalPermissions.length);
        }
        return permissions;
    }
}
