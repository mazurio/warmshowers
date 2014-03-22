package io.mazur.warmshowers.Navigation;

/**
 * Created by Mazur on 22/03/2014.
 */
public class Item {
    public String title;

    public Item() {
        this.title = "Title";
    }

    public Item(String title) {
        super();
        this.title = title;
    }

    public String toString() {
        return this.title;
    }
}
