package net.azurewebsites.pedromiguelmartins.pedromiguelmartins;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.view.View;

import org.jetbrains.annotations.Contract;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Scanner;

import static net.azurewebsites.pedromiguelmartins.pedromiguelmartins.R.drawable;

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
    public static Integer[] GetResumeListImages()
    {
        return  new Integer  [] {
                drawable.logo_altran,
                drawable.nls_logo,
                drawable.logo_sparkle,
                drawable.affinity_logo,
                drawable.quantico_white_logo,
                drawable.nos_logo,
                drawable.logo_novabase,
                drawable.logo_publico,
                drawable.logo_kcs_it,
                drawable.exago_logo,
                drawable.logo_ludite,
                drawable.bto_logo,
                drawable.logo_adidas,
                drawable.ic_launcher,
                drawable.logo_sibs



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
    public static Integer[] GetToolsListImages()
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
                drawable.ic_windows_store,
                drawable.ic_visualstudio,
                drawable.ic_androidstudio,
                drawable.ic_bootstrap,
                drawable.ic_bootstrapui,
                drawable.ic_jquery,
                drawable.ic_jquerymobile,
                drawable.ic_azure,
                drawable.ic_jenkins,
                drawable.ic_cordoba,
                drawable.ic_wcf,
                drawable.ic_git,
                drawable.ic_grunt,
                drawable.ic_json,
                drawable.ic_bower,
                drawable.ic_hibernate,
                drawable.ic_entity,
                drawable.logo_github,
                drawable.logo_tfs

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
                drawable.ic_windows_store,
                drawable.ic_visualstudio,
                drawable.ic_androidstudio,
                drawable.ic_bootstrap,
                drawable.ic_bootstrapui,
                drawable.ic_jquery,
                drawable.ic_jquerymobile,
                drawable.ic_azure,
                drawable.ic_jenkins,
                drawable.ic_cordoba,
                drawable.ic_wcf,
                drawable.ic_git,
                drawable.ic_grunt,
                drawable.ic_json,
                drawable.ic_bower,
                drawable.ic_hibernate,
                drawable.ic_entity

        };
    }

    /**
     * @return
     */
    @org.jetbrains.annotations.Contract(" -> !null")
    public static Integer[] GetContactsListImages()
    {
        return  new Integer  [] {
                drawable.ic_gmail,
                drawable.ic_hotmail,
                drawable.ic_github,
                drawable.ic_linkedin,
                drawable.ic_facebook,





        };
    }
    // Use AsyncTask execute Method To Prevent ANR Problem
    // Class with extends AsyncTask class

    public static JSONArray requestWebService(String serviceUrl) {
        disableConnectionReuseIfNecessary();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        HttpURLConnection urlConnection = null;
        try {
            // create connection
            URL urlToRequest = new URL(serviceUrl);
            urlConnection = (HttpURLConnection)
                    urlToRequest.openConnection();


            // handle issues
            int statusCode = urlConnection.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
                // handle unauthorized (if service requires user login)
            } else if (statusCode != HttpURLConnection.HTTP_OK) {
                // handle any other errors, like 404, 500,..
            }

            // create JSON object from content
            InputStream in = new BufferedInputStream(
                    urlConnection.getInputStream());
            return new JSONArray(getResponseText(in));

        } catch (MalformedURLException e) {
            // URL is invalid
        } catch (SocketTimeoutException e) {
            // data retrieval or connection timed out
        } catch (IOException e) {
            // could not read response body
            // (could not create input stream)
        } catch (JSONException e) {
            // response body is no valid JSON string
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return null;
    }
    public static void launchRingDialog(View view, Context context) {
        final ProgressDialog ringProgressDialog = ProgressDialog.show(context, "Please wait ...",	"Downloading Articles ...", true);
        ringProgressDialog.setCancelable(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Here you should write your time consuming task...
                    // Let the progress ring for 10 seconds...
                    Thread.sleep(10000);
                } catch (Exception e) {

                }
                ringProgressDialog.dismiss();
            }
        }).start();
    }
    private static String getResponseText(InputStream inStream) {
        // very nice trick from
        // http://weblogs.java.net/blog/pat/archive/2004/10/stupid_scanner_1.html
        return new Scanner(inStream).useDelimiter("\\A").next();
    }

    /**
     * required in order to prevent issues in earlier Android version.
     */
    private static void disableConnectionReuseIfNecessary() {
        // see HttpURLConnection API doc
        if (Integer.parseInt(Build.VERSION.SDK)
                < Build.VERSION_CODES.M) {
            System.setProperty("http.keepAlive", "false");
        }
    }

    public static Integer[] GetArticleListImages() {
        return new Integer[]{
                drawable.ic_article_portofolio,
                drawable.ic_article_sistemas,
                drawable.ic_article_innovation,
                drawable.ic_article_coaching,
                drawable.ic_article_think,
                drawable.ic_article_tech,
                drawable.ic_article_view,
                drawable.ic_article_consulting,


        };
    }
}
