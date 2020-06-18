import com.google.gson.Gson;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static spark.Spark.*;

public class Main {
    static List<Contact> contactList = new ArrayList<>();
    //static Logger logger = org.slf4j.LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        port(8010);
        final Gson gson = new Gson();

        contactList.add(new Contact(UUID.randomUUID().toString(), "Albert", "Einstein", "1111-1111"));
        contactList.add(new Contact(UUID.randomUUID().toString(), "Marie", "Curie", "2222-1111"));

        post("/", (request, response) -> {
            Contact contact = gson.fromJson(request.body(), Contact.class);
            contact.setId(UUID.randomUUID().toString());
            contactList.add(contact);
            response.status(201);
            //response.header("Location", "http://" + request.host() + request.uri() + "/" + contact.getId());
            response.header("Location", "/" + contact.getId());
            return "";
        });

        get("/", (request, response) -> {
            response.status(200);
            response.type("application/json");
            return contactList;
        },gson::toJson);

        get("/:id", (request, response) -> {
            String id = request.params(":id");
            response.status(200);
            response.type("application/json");
            return contactList.stream().filter(contact ->  contact.getId().equals(id)).findFirst();
        },gson::toJson);

        put("/:id", (request, response) -> {
            String id = request.params(":id");
            Contact contact = gson.fromJson(request.body(), Contact.class);

            Optional<Contact> optionalContact = contactList.stream().filter(c ->  c.getId().equals(id)).findFirst();
            if (optionalContact.isEmpty()){
                halt(404);
            }

            Contact currentContact = optionalContact.get();
            currentContact.setFirstName(contact.getFirstName());
            currentContact.setLastName(contact.getLastName());
            currentContact.setPhoneNumber(contact.getPhoneNumber());

            response.status(204);
            return "";
        });

        delete("/:id", (request, response) -> {
            String id = request.params(":id");
            Optional<Contact> optionalContact = contactList.stream().filter(c ->  c.getId().equals(id)).findFirst();
            if (optionalContact.isEmpty()){
                halt(404);
            }

            contactList.remove(optionalContact.get());
            response.status(204);
            return "";
        });
    }
}