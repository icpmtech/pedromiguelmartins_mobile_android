package net.azurewebsites.pedromiguelmartins.pedromiguelmartins.contact;

import net.azurewebsites.pedromiguelmartins.pedromiguelmartins.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class ContactContent {

    /**
     * An array of sample (contact) items.
     */
    public static final List<ContactItem> ITEMS = new ArrayList<ContactItem>();

    /**
     * A map of sample (contact) items, by ID.
     */
    public static final Map<String, ContactItem> ITEM_MAP = new HashMap<String, ContactItem>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        initResume();
    }
    private static void initResume() {
        ContactItem temp_1 = new ContactItem("Mourao.martins@gmail.com", "Mourao.martins@gmail.com", "Mourao.martins@gmail.com", "Mourao.martins@gmail.com", Utils.GetContactsListImages()[0],"0");

        addItem(temp_1);
        ContactItem temp_2 = new ContactItem("miguelrox@msn.com", "miguelrox@msn.com", "miguelrox@msn.com", "miguelrox@msn.com", Utils.GetContactsListImages()[1], "1");

        addItem(temp_2);
        ContactItem temp_3 = new ContactItem("https://github.com/icpmtech/", "https://github.com/icpmtech/", "https://github.com/icpmtech/", "https://github.com/icpmtech/", Utils.GetContactsListImages()[2], "2");

        addItem(temp_3);
        ContactItem temp_4 = new ContactItem("https://pt.linkedin.com/in/pedromiguelmouraomartins", "https://pt.linkedin.com/in/pedromiguelmouraomartins", "https://pt.linkedin.com/in/pedromiguelmouraomartins", "https://github.com/icpmtech/", Utils.GetContactsListImages()[3], "3");

        addItem(temp_4);
        ContactItem temp_5 = new ContactItem("https://www.facebook.com/pedrorox", "https://www.facebook.com/pedrorox", "https://www.facebook.com/pedrorox", "https://www.facebook.com/pedrorox", Utils.GetContactsListImages()[4], "4");

        addItem(temp_5);
    }
    private static void addItem(ContactItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }



    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A contact item representing a piece of content.
     */
    /**
     * A resume item representing a piece of content.
     */
    public static class ContactItem {
        public final String id;
        public final String title;
        public final String content;
        public final String details;
        public final Integer thumbnail;
        public final String summary;

        public ContactItem(String title, String content, String details, String summary, Integer thumbnail, String id) {
            this.title = title;
            this.content = content;
            this.summary = summary;
            this.details = details;
            this.thumbnail=thumbnail;
            this.id = id;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
