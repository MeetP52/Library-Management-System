package dev.user;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UserCatalog {
    private Map<Integer, UserCatalogItem> users;
    private static UserCatalog catalog;

    private UserCatalog() {
        users = new HashMap<>();
        generateUserData();
    }

    public static UserCatalog getUserCatalog() {
        return (catalog == null) ? (catalog = new UserCatalog()) : catalog;
    }

    private void generateUserData() {
        String absFilePath = "E:\\Code\\Intellij\\User\\src\\main\\java\\docs\\user\\data.txt";

        try(FileReader reader = new FileReader(absFilePath)) {
            BufferedReader bufferedReader = new BufferedReader(reader);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(bufferedReader);

            Iterator<Map.Entry<String, JsonNode>> fields = rootNode.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                // int userID = Integer.parseInt(entry.getKey());
                UserCatalogItem userCatalogItem = mapper.treeToValue(entry.getValue(), UserCatalogItem.class);
                if(users.put(userCatalogItem.hashCode(), userCatalogItem) != null) {
                    System.out.println("Already Exists: " + userCatalogItem.getUser().getEmailAddress());
                }

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Done Creating Users");
        }
    }

    public boolean storeUserCatalogData() {
        String absFilePath = "E:\\Code\\Intellij\\User\\src\\main\\java\\docs\\user\\UserData.txt";

        try(FileWriter writer = new FileWriter(absFilePath)) {
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter jsonWriter = mapper.writerWithDefaultPrettyPrinter();
            jsonWriter.writeValue(writer,users);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public int findUserId(User user) {
        return user.hashCode();
    }

    public UserCatalogItem findUser(int id) {
        return users.get(id);
    }

    public UserCatalogItem findUser(User user) {
        return findUser(findUserId(user));
    }

    public boolean addUser(UserCatalogItem userCatalogItem) {
        if(users.containsKey(userCatalogItem.hashCode())) {
            return false;
        }
        users.put(userCatalogItem.hashCode(), userCatalogItem);
        return true;
    }

    public boolean removeUser(UserCatalogItem userCatalogItem) {
        if(users.containsKey(userCatalogItem.hashCode())) {
            return users.remove(userCatalogItem.hashCode(), userCatalogItem);
        }
        return false;
    }

    @Override
    public String toString() {
        return "UserCatalog {\n" +
                "users = " + users + "\n" +
                '}';
    }
}
