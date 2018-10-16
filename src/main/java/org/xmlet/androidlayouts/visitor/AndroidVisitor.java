package org.xmlet.androidlayouts.visitor;

import org.xmlet.androidlayoutsapi.AndroidView;
import org.xmlet.androidlayoutsapi.Element;
import org.xmlet.androidlayoutsapi.ElementVisitor;
import org.xmlet.androidlayoutsapi.Text;

import static org.xmlet.androidlayouts.visitor.Indentation.tabs;

@SuppressWarnings("Duplicates")
public class AndroidVisitor extends ElementVisitor {

    private boolean isClosed = true;
    private int depth = 0;
    private StringBuilder stringBuilder = new StringBuilder();

    @Override
    public final void visitElement(Element element) {
        if (!isClosed){
            stringBuilder.append('>');
        }

        if (stringBuilder.length() != 0){
            stringBuilder.append('\n');
        }

        stringBuilder.append(tabs(depth)).append('<').append(firstToUpper(element));
        ++depth;

        isClosed = false;
    }

    @Override
    public final void visitParent(Element element) {
        depth--;

        if (elementHasNoChildren()){
            stringBuilder.append("/>");
            isClosed = true;
            return;
        }

        if (!isClosed) {
            stringBuilder.append('>');
        }

        stringBuilder.append('\n').append(tabs(depth)).append("</").append(firstToUpper(element)).append('>');
        isClosed = true;
    }

    @Override
    public final void visitAttribute(String attributeName, String attributeValue) {
        stringBuilder.append('\n').append(tabs(depth)).append("android:").append(attributeName).append("=\"").append(attributeValue).append('\"');
    }

    /**
     * An optimized version of Text without binder.
     */
    @Override
    public final <R> void visitText(Text<? extends Element, R> text) {
        if (!isClosed){
            depth++;
        }

        stringBuilder.append(tabs(depth)).append(text.getValue());
    }

    @Override
    public final <R> void visitComment(Text<? extends Element, R> comment) {
        if (!isClosed){
            depth++;
        }

        stringBuilder.append(tabs(depth)).append("<!-- ").append(comment.getValue()).append(" -->");
    }

    /* ************************* */
    /*     Auxiliary Methods     */
    /* ************************* */

    private boolean elementHasNoChildren() {
        return stringBuilder.charAt(stringBuilder.length() - 1) == '\"';
    }

    public String getLayout(){
        return stringBuilder.toString();
    }

    private String firstToUpper(Element element){
        String name = element.getName();
        return name.substring(0, 1).toUpperCase().concat(name.substring(1));
    }

    /* ************************* */
    /*      Special Elements     */
    /* ************************* */

    @Override
    public <Z extends Element> void visitElementAndroidView(AndroidView<Z> var1) { }

    @Override
    public <Z extends Element> void visitParentAndroidView(AndroidView<Z> var1) { }
}