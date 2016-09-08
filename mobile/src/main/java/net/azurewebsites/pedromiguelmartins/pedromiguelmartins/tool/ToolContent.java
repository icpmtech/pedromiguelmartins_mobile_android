package net.azurewebsites.pedromiguelmartins.pedromiguelmartins.tool;

import android.content.Context;

import net.azurewebsites.pedromiguelmartins.pedromiguelmartins.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class ToolContent {

    /**
     * An array of sample (resume) items.
     */
    public static final List<ToolItem> ITEMS = new ArrayList<ToolItem>();
    /**
     * A map of sample (resume) items, by ID.
     */
    public static final Map<String, ToolItem> ITEM_MAP = new HashMap<String, ToolItem>();
    private static final int COUNT = 25;
    public static Context resume_context = null;

    static {
        // Add some sample items.
        initResume();


    }

    private static void initResume() {
        ToolItem temp_1 = new ToolItem(" .NET SENIOR CONSULTANT & ARCHITECT", "", "New Link Solutions, Lisbon (Portugal)", "Company: New Link Solutions Client:Hmr, Lisbon (Portugal) Sector: Health Project Scope: Create Orchestrator of process.To run other applications in workflow process. Project Contribution; ▪	Solution Architect for .net applications; ▪	Design the UX/UI to mobile applications and web responsive solutions. ▪	Responsible for technologies choices to the solutions; ▪ Development on Agile Methodologies (SCRUM). Function: .Net Solution Architect and Innovation Manager Technologies used: ASP.NET,MVC4.5, MVC5, SQL SERVER 2014, C#, Web Services,WCF,Json Javascript,Html5,Asp.net Web Forms,Web Api,Entity Framework,Windows Services", Utils.GetResumeListImages()[0], "0");

        addItem(temp_1);
        ToolItem temp_2 = new ToolItem(".NET SOLUTION ARCHITECT & INNOVATION MANAGER", "", "Sparkle IT SA, Lisbon (Portugal)", "Company: Sparkle IT SA Client: Sparkle IT SA, Lisbon (Portugal) Sector: Construction Project Scope: Innovation manager of the solutions in construction market. Creator of one ERP to civil construction to the Euromib SA. Mobile applications to the construction markets. Project Contribution; ▪\tInterviews and selection of team members; ▪\tCoordinator of the teams. ▪\tSolution Architect for .net applications; ▪\tDesign the UX/UI to mobile applications and web responsive solutions. ▪ Responsible for technologies choices to the solutions; ▪\tCoordination of Development teams on Agile Methodologies (SCRUM). Function: .Net Solution Architect and Innovation Manager Technologies used: ASP.NET,MVC4.5, MVC5, SQL SERVER 2012, C#, Web Services,WCF,Json Javascript,Html5,DevExpress Framework,Asp.net Web Forms,Web Api,Entity Framework,Cordova,Windows Mobile,Windowns Store", Utils.GetResumeListImages()[1], "1");

        addItem(temp_2);
        ToolItem temp_3 = new ToolItem("SENIOR CONSULTANT", "Affinity, Lisbon (Portugal)", "", "Company: Affinity Client: Affinity Sector: Services Project Scope: Former of Asp.net 4.5 course and Azure Integration Project Contribution; ▪\tTrain junior consultants with the ASP.net; ▪\tTeaching the juniors with the technologies of the course. Function: Former Technologies used: ASP.NET,MVC4.5, MVC5, SQL SERVER 2012, C#, Web Services,WCF,Json Javascript,Html5,Azure Plataform,Asp.net Web Forms,Web Api,Entity", Utils.GetResumeListImages()[2], "2");

        addItem(temp_3);
    }

    private static void addItem(ToolItem item) {

        ITEMS.add(item);
        ITEM_MAP.put(item.title, item);
    }

    private static ToolItem createDummyItem(int position) {
        return new ToolItem(String.valueOf(position), "Item " + position, makeDetails(position), makeDetails(position), Utils.GetProjectsListImages()[0], "0");
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
    public static class ToolItem {
        public final String title;
        public final String id;
        public final String content;
        public final String details;
        public final Integer thumbnail;
        public final String summary;

        public ToolItem(String title, String content, String details, String summary, Integer thumbnail, String id) {
            this.title = title;
            this.content = content;
            this.summary = summary;
            this.details = details;
            this.thumbnail = thumbnail;
            this.id = id;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
