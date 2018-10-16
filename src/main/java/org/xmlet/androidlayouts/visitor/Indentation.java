package org.xmlet.androidlayouts.visitor;

class Indentation {

    private static final int MAX_TABS = 1000;
    private static String[] tabs = createTabs(MAX_TABS);

    private Indentation(){ }

    private static String[] createTabs(int tabsMax){
        String[] tabs = new String[tabsMax];

        for (int i = 0; i < tabsMax; i++) {
            char[] newTab = new char[i];

            tabs[i] = new String(newTab).replace('\0', '\t');
        }

        return tabs;
    }

    static String tabs(int depth){
        return tabs[depth];
    }
}
