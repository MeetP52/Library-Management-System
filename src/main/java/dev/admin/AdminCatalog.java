package dev.admin;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AdminCatalog {
    private final Map<Integer,Admin> admins;
    private static AdminCatalog catalog;

    private AdminCatalog() {
        admins = new HashMap<>();
        generateAdminCatalog();
    }

    public static AdminCatalog getAdminCatalog() {
        return (catalog == null) ? (catalog = new AdminCatalog()) : catalog;
    }

    private void generateAdminCatalog() {
        String absFilePath = "E:\\Code\\Intellij\\User\\src\\main\\java\\docs\\admin\\AdminData.txt";

        try(FileReader reader = new FileReader(absFilePath)) {
            BufferedReader bufferedReader = new BufferedReader(reader);
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModules(new JavaTimeModule());
            JsonNode rootNode = mapper.readTree(bufferedReader);

            Iterator<Map.Entry<String, JsonNode>> fields = rootNode.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                Admin admin = mapper.treeToValue(entry.getValue(), Admin.class);
                if(admins.put(admin.hashCode(), admin) != null) {
                    System.out.println("Already Exists: " + admin.getUser().getEmailAddress());
                }

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Done Creating Admin Users");
        }
    }

    public boolean storeAdminCatalogData() {
        String absFilePath = "E:\\Code\\Intellij\\User\\src\\main\\java\\docs\\admin\\AdminData.txt";

        try(FileWriter writer = new FileWriter(absFilePath)) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModules(new JavaTimeModule());
            ObjectWriter jsonWriter = mapper.writerWithDefaultPrettyPrinter();
            jsonWriter.writeValue(writer,admins);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public int findAdminId(Admin admin) {
        return admin.hashCode();
    }

    public Admin findAdmin(int adminId) {
        return (admins.get(adminId) == null) ? null : admins.get(adminId).copy("deep");
    }

    public Admin findAdmin(Admin admin) {
        return (!admins.containsKey(findAdminId(admin))) ? null : findAdmin(findAdminId(admin));
    }

    public boolean addAdminUser(Admin admin) {
        if(admins.containsKey(admin.hashCode())) {
            return false;
        }
        admins.put(admin.hashCode(),admin);
        return true;
    }

    public boolean removeAdminUser(Admin admin) {
        if(admins.containsKey(admin.hashCode())) {
            return admins.remove(admin.hashCode(),admin);
        }
        return false;
    }

    @Override
    public String toString() {
        return "AdminCatalog {" +
                "\n\tadmins = " + admins + "\n" +
                '}';
    }
}
