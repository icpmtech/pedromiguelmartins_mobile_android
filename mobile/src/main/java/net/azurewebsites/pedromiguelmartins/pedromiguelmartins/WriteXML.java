package net.azurewebsites.pedromiguelmartins.pedromiguelmartins;

import android.content.Context;
import android.util.Xml;

import org.xmlpull.v1.XmlSerializer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by migue_000 on 09/09/2016.
 */
public class WriteXML {
    public static void WriteXML(Context context) {
        final String xmlFile = "projects";

        try {
            FileOutputStream fos = new FileOutputStream("userData.xml");
            FileOutputStream fileos = context.openFileOutput(xmlFile, Context.MODE_PRIVATE);
            XmlSerializer xmlSerializer = Xml.newSerializer();
            StringWriter writer = new StringWriter();
            xmlSerializer.setOutput(writer);
            xmlSerializer.startDocument("UTF-8", true);
            xmlSerializer.startTag(null, "project");
            xmlSerializer.startTag(null, "entry");

            xmlSerializer.startTag(null, "id");
            xmlSerializer.text("1");
            xmlSerializer.endTag(null, "id");
            xmlSerializer.endTag(null, "entry");
            xmlSerializer.endTag(null, "project");
            xmlSerializer.endDocument();
            xmlSerializer.flush();
            String dataWrite = writer.toString();
            fileos.write(dataWrite.getBytes());
            fileos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
