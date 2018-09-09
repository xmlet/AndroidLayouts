package org.xmlet.androidlayoutsapitest;

import org.xmlet.androidlayoutsapi.*;

import java.util.List;

@SuppressWarnings("Duplicates")
public class CustomVisitor<R> extends ElementVisitor<R> {

    private R model;
    private StringBuilder stringBuilder = new StringBuilder();
    private int tabCount = 0;

    public CustomVisitor(){ }

    public CustomVisitor(R model){
        this.model = model;
    }

    @Override
    public <T extends Element> void sharedVisit(Element<T, ?> element) {
        doTabs();
        print(String.format("<%s", element.getName()));

        element.getAttributes().forEach(attribute -> print(String.format(" %s=\"%s\"", attribute.getName(), attribute.getValue())));

        print(">\n");

        ++tabCount;

        if(element.isBound()) {
            List<Element> children = element.cloneElem().bindTo(model).getChildren();
            children.forEach( child -> child.accept(this));
        } else {
            element.getChildren().forEach(item -> item.accept(this));
        }

        --tabCount;
        doTabs();
        print(String.format("</%s>\n", element.getName()));
    }

    @Override
    public void visit(Text text){
        String textValue = text.getValue();

        if (textValue != null){
            doTabs();
            print(textValue + "\n");
        }
    }

    @Override
    public void visit(Comment comment){
        String textValue = comment.getValue();

        if (textValue != null){
            doTabs();
            print(textValue + "\n");
        }
    }

    @Override
    public <U> void visit(TextFunction<R, U, ?> text){
        if (model == null){
            throw new RuntimeException("Text node is missing the model. Usage of new CustomVisitor(model) is required.");
        }

        doTabs();
        print(text.getValue(model).toString() + "\n");
    }

    private void print(String string){
        stringBuilder.append(string);
    }

    String getResult(){
        return stringBuilder.toString();
    }

    private void doTabs() {
        char[] tabs = new char[tabCount];

        for (int i = 0; i < tabCount; i++) {
            tabs[i] = '\t';
        }

        stringBuilder.append(tabs);
    }
}