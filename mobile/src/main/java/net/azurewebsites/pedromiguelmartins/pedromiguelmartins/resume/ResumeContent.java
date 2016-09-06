package net.azurewebsites.pedromiguelmartins.pedromiguelmartins.resume;

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
public class ResumeContent {

    /**
     * An array of sample (resume) items.
     */
    public static final List<ResumeItem> ITEMS = new ArrayList<ResumeItem>();

    /**
     * A map of sample (resume) items, by ID.
     */
    public static final Map<String, ResumeItem> ITEM_MAP = new HashMap<String, ResumeItem>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(ResumeItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static ResumeItem createDummyItem(int position) {
        return new ResumeItem(String.valueOf(position), "Item " + position, makeDetails(position), Utils.GetProjectsListImages()[0]);
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
     * A resume item representing a piece of content.
     */
    public static class ResumeItem {
        public final String id;
        public final String content;
        public final String details;
        public final Integer thumbnail;

        public ResumeItem(String id, String content, String details,Integer thumbnail) {
            this.id = id;
            this.content = content;
            this.details = details;
            this.thumbnail=thumbnail;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
