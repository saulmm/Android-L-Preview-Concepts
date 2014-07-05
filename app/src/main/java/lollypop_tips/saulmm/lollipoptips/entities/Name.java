
package lollypop_tips.saulmm.lollipoptips.entities;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class Name implements Serializable {

    @Expose
    private String title;
    @Expose
    private String first;
    @Expose
    private String last;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }
}
