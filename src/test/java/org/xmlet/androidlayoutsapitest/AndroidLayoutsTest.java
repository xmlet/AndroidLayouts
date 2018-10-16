package org.xmlet.androidlayoutsapitest;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.xmlet.androidlayouts.Android;
import org.xmlet.androidlayoutsapi.*;

import java.io.IOException;

@SuppressWarnings("Duplicates")
public class AndroidLayoutsTest {

    @Test
    public void testAndroidHierarchy(){
        Android android = new Android(androidObj ->
                androidObj.layout()
                    .relativeLayout()
                        .attrGravity(EnumGravityRelativeLayout.CENTER)                        /* Method from RelativeLayout */
                        .attrAddStatesFromChildren(EnumAddStatesFromChildrenViewGroup.TRUE)   /* Method from ViewGroup */
                        .attrLayoutX("")                                                      /* Method from View */
                    .__());

        boolean implementsView = false;
        boolean implementsViewGroup = false;

        for (Class<?> aClass : RelativeLayout.class.getInterfaces()) {
            if (aClass.equals(ViewGroupHierarchyInterface.class)){
                implementsViewGroup = true;
            }
        }

        for (Class<?> aClass : ViewGroupHierarchyInterface.class.getInterfaces()) {
            if (aClass.equals(ViewHierarchyInterface.class)){
                implementsView = true;
            }
        }

        String result = android.getLayout();

        Assert.assertEquals(readResourceFile("androidHierarchy.txt"), result);
        Assert.assertTrue(implementsView);
        Assert.assertTrue(implementsViewGroup);
    }

    /**
     * <LinearLayout
     *      android:orientation="vertical"
     *      android:width="match_parent"
     *      android:height="wrap_content">
     *      <LinearLayout
     *          android:orientation="horizontal"
     *          android:width="match_parent"
     *          android:height="wrap_content">
     *          <ImageView
     *              android:width="wrap_content"
     *              android:height="wrap_content"/>
     *          <TextView
     *              android:width="match_parent"
     *              android:height="weight_content"
     *              android:lines="2"/>
     *      </LinearLayout>
     * </LinearLayout>
     */
    @Test
    public void testSimpleAndroidLayout(){
        Android android = new Android(androidObj ->
                androidObj.layout()
                    .linearLayout()
                        .attrOrientation(EnumOrientationLinearLayout.VERTICAL)
                        .attrLayoutWidth("match_parent")
                        .attrLayoutHeight("wrap_content")
                        .linearLayout()
                            .attrOrientation(EnumOrientationLinearLayout.HORIZONTAL)
                            .attrLayoutWidth("match_parent")
                            .attrLayoutHeight("wrap_content")
                            .imageView()
                                .attrLayoutWidth("wrap_content")
                                .attrLayoutHeight("wrap_content").__()
                            .textView()
                                .attrWidth("match_parent")
                                .attrHeight("weight_content")
                                .attrLines("2").__()
                        .__()
                    .__());

        String result = android.getLayout();
        Assert.assertEquals(readResourceFile("simpleAndroidLayout.txt"), result);
    }

    private String readResourceFile(String fileName){
        try {
            return IOUtils.toString(getClass().getClassLoader().getResourceAsStream(fileName)).replaceAll("\r", "");
        } catch (IOException e) {
            throw new RuntimeException("Resource file " + fileName + " is missing.");
        }
    }
}
