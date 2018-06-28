package org.xmlet.androidlayoutsapitest;

import org.junit.Test;
import org.xmlet.androidlayoutsapi.EnumAndroidOrientation;
import org.xmlet.androidlayoutsapi.LinearLayout;

public class AndroidLayoutsApiTest {

    @Test
    public void testSimpleAndroidLayout() throws Exception{
        new LinearLayout<>()
                .attrAndroidOrientation(EnumAndroidOrientation.VERTICAL)
                .attrAndroidLayoutWidth("match_parent")
                .attrAndroidLayoutHeight("wrap_content")
                    .linearLayout()
                        .attrAndroidOrientation(EnumAndroidOrientation.HORIZONTAL)
                        .attrAndroidLayoutWidth("match_parent")
                        .attrAndroidLayoutHeight("wrap_content")
                            .imageView()
                                .attrAndroidLayoutWidth("wrap_content")
                                .attrAndroidLayoutHeight("wrap_content")
                            .º()
                            .textView()
                                .attrAndroidWidth("match_parent")
                                .attrAndroidHeight("weight_content")
                                .attrAndroidLines("2");
    }
}
