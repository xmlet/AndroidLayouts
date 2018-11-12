[![Maven Central](https://img.shields.io/maven-central/v/com.github.xmlet/androidLayouts.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.github.xmlet%22%20AND%20a:%22androidLayouts%22)

# AndroidLayouts

<div align="justify"> 
    The AndroidLayouts is a Java library that uses a Java DSL, <a href="https://github.com/xmlet/AndroidLayoutsApi">AndroidLayoutsApi</a>, 
    to write valid Android visual layouts. The elements and rules of this language are defined in a XML Schema Definition file, 
    i.e. XSD file, and those language rules were used to create a Java DSL using <a href="https://github.com/xmlet/XsdAsmFaster">XsdAsmFaster</a>. 
</div>

## Installation

<div align="justify"> 
    First, in order to include it to your Maven project, simply add this dependency:
    <br />
    <br />
</div>

```xml
<dependency>
    <groupId>com.github.xmlet</groupId>
    <artifactId>androidLayouts</artifactId>
    <version>1.0.1</version>
</dependency>
``` 

## How does AndroidLayouts works?

<div align="justify"> 
    This library consists of three classes:
    <br />
    <ul>
        <li>
            Android - Receives a function that will create the visual layout.
        </li>
        <li>
            AndroidVisitor - Provides an implementation of the <i>ElementVisitor</i> class. This implementation writes
            and indents XML based on the classes and attributes that are present in the element tree.
        </li>
        <li>
            Indentation - Helper class used for perfomance.
        </li>
    </ul>
</div>

### Usage

<div align="justify"> 
    In the following code snippet we have the required code to generate a layout, which is shown below the code.
</div>

```java
class Example{
    void example(){
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
    }
}
```

```xml
<LinearLayout
	android:orientation="vertical"
	android:layout_width="match_parent"
	android:layout_height="wrap_content">
	<LinearLayout
		android:orientation="horizontal"
		android:layout_width="match_parent"
		android:layout_height="wrap_content">
		<ImageView
			android:layout_width="wrap_content"
			android:layout_height="wrap_content"/>
		<TextView
			android:width="match_parent"
			android:height="weight_content"
			android:lines="2"/>
	</LinearLayout>
</LinearLayout>
```

<div align="justify"> 
    As we can see from the previous example, this library provides a very similar way to write valid layouts, the flow of the
    creation of the layout being very similar to the flow of writing XML, while guaranteeing that the rules of the creation
    of Android layouts are verified.
</div>
