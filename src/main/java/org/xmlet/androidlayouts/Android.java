package org.xmlet.androidlayouts;

import java.util.function.Consumer;

import org.xmlet.androidlayouts.visitor.AndroidVisitor;
import org.xmlet.androidlayoutsapi.*;

public class Android implements Element<Android, Element> {

    private final AndroidVisitor visitor;
    private final String layout;

    public Android(Consumer<Android> consumer){
        visitor = new AndroidVisitor();
        consumer.accept(this);

        layout = visitor.getLayout();
    }

    public AndroidView<Android> layout(){
        return new AndroidView<>(visitor);
    }

    public String getLayout() {
        return layout;
    }

    @Override
    public Android self() {
        return this;
    }

    @Override
    public ElementVisitor getVisitor() {
        return visitor;
    }

    @Override
    public String getName() {
        return "android";
    }

    @Override
    public Element __() {
        return null;
    }

    @Override
    public Element getParent() {
        return null;
    }
}
