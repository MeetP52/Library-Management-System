package dev.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class UserCatalogItem {
    private User user;
    private AddInfo addInfo;

    public UserCatalogItem() {};

    @JsonCreator
    public UserCatalogItem(@JsonProperty("user") User user,
                           @JsonProperty("addInfo") AddInfo addInfo) {
        this.user = user;
        this.addInfo = addInfo;
    }

    protected UserCatalogItem(UserCatalogItem userCatalogItem) {
        this.user = userCatalogItem.user.copy("deep");
        this.addInfo = userCatalogItem.addInfo.copy("deep");
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AddInfo getAddInfo() {
        return addInfo;
    }

    public void setAddInfo(AddInfo addInfo) {
        this.addInfo = addInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCatalogItem userCatalogItem = (UserCatalogItem) o;
        return Objects.equals(user, userCatalogItem.user) && Objects.equals(addInfo, userCatalogItem.addInfo);
    }

    @Override
    public int hashCode() {
        return getUser().hashCode();
    }

    public UserCatalogItem copy(String type)  {
        UserCatalogItem userCatalogItem;
        if(type.equals("deep")) {
            return userCatalogItem = new UserCatalogItem(this);
        }
        return userCatalogItem = this;
    }

    @Override
    public String toString() {
        return "UserInfo { " +
                "\n\t user = " + user +
                ",\n\t addInfo=" + addInfo + "\n\t" +
                '}';
    }
}
