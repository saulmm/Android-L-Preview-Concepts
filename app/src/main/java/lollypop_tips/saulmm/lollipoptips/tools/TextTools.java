package lollypop_tips.saulmm.lollipoptips.tools;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

/**
 * Class created to manage different textTools
 */
public class TextTools {

    /**
     * Gets a string with the content of a file
     * @param resourceReference, the resource to give the content
     * @param context, the context of the resources
     * @return the content of the file as a string
     */
    public static String getFileContent(int resourceReference, Context context) {
        InputStream is = context.getResources().openRawResource(resourceReference);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];

        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;

            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                is.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return writer.toString();
    }


}
