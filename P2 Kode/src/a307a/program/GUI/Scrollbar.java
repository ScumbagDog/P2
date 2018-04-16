package a307a.program.GUI;

import javafx.geometry.Orientation;
import javafx.scene.control.ScrollBar;


public class Scrollbar {

    public static ScrollBar AddScrollbar() {


        //her laves scrollbaren
        ScrollBar scrollbar = new ScrollBar();


        //her gives scrollbaren nogle egenskaber
        scrollbar.setMin(0);
        scrollbar.setOrientation(Orientation.VERTICAL);
        scrollbar.setPrefHeight(180);
        scrollbar.setMax(360);


        return scrollbar;
    }
}