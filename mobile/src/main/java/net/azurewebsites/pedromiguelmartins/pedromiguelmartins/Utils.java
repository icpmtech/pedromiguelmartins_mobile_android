package net.azurewebsites.pedromiguelmartins.pedromiguelmartins;

import org.jetbrains.annotations.Contract;

import static net.azurewebsites.pedromiguelmartins.pedromiguelmartins.R.*;

/**
 * Created by migue_000 on 30/08/2016.
 */
public  class  Utils {


    /**
     * @return
     */
   @Contract(" -> !null")
   public static String[] GetContactsList()
    {
      return    new String[] {
                "mourao.martins@gmail.com",

        };
    }
    /**
     * @return
     */
    @org.jetbrains.annotations.Contract(" -> !null")
    public static String[] GetProjectsList()
    {
        return    new String[] {
                "Asp.net",
                "AngularJS",
                "Web Development",
                "Spring",
                "Java EE",
                ".NET",
                "Android",
                "Windows Phone",
                "Windows Store"
        };
    }
    /**
     * @return
     */
    @org.jetbrains.annotations.Contract(" -> !null")
    public static Integer[] GetProjectsListImages()
    {
        return  new Integer  [] {
            drawable.ic_asp_net,
            drawable.ic_angularjs,
            drawable.ic_html_css_javascript,
            drawable.ic_spring,
            drawable.ic_javaee,
            drawable.ic_net,
            drawable.ic_android,
            drawable.ic_windows_phone,
            drawable.ic_windows_store

        };
    }
    /**
     * @return
     */
    @org.jetbrains.annotations.Contract(" -> !null")
    public static String[] GetTechnologiesList()
    {
        return    new String[] {
                "Asp.net",
                "AngularJS",
                "Web Development",
                "Spring",
                "Java EE",
                ".NET",
                "Android",
                "Windows Phone",
                "Windows Store"
        };
    }
    /**
     * @return
     */
    @org.jetbrains.annotations.Contract(" -> !null")
    public static Integer[] GetTechnologiesListImages()
    {
        return  new Integer  [] {
                drawable.ic_asp_net,
                drawable.ic_angularjs,
                drawable.ic_html_css_javascript,
                drawable.ic_spring,
                drawable.ic_javaee,
                drawable.ic_net,
                drawable.ic_android,
                drawable.ic_windows_phone,
                drawable.ic_windows_store

        };
    }

    /**
     * @return
     */
    @org.jetbrains.annotations.Contract(" -> !null")
    public static Integer[] GetContactsListImages()
    {
        return  new Integer  [] {
                android.R.drawable.ic_dialog_email,



        };
    }

}
