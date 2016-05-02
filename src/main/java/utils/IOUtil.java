package utils;

import constants.Constants;

import java.io.*;

/**
 * Created by paranoidq on 16/4/26.
 */
public class IOUtil {

    /**
     * Get BufferedReader for reading.
     * @param path
     * @return
     * @throws IOException
     */
    public static BufferedReader getReader(String path) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(path),
                Constants.UTF8));
        return br;
    }

    /**
     * Get BufferedWriter for writing.
     * @param path
     * @return
     * @throws IOException
     */
    public static BufferedWriter getWriter(String path) throws IOException {
        File file = new File(path);
        if (!file.exists() && !file.isDirectory()) {
            file.getParentFile().mkdirs();
        }
        BufferedWriter br = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(path),
                Constants.UTF8));
        return br;
    }
}
