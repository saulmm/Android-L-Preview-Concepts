
package lollypop_tips.saulmm.lollipoptips.entities;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class Result implements Serializable {

    @Expose
    private User user;
    @Expose
    private String seed;
    @Expose
    private String version;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
