package git.luliu.excel;

import java.io.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * The <code>TestImportMap</code>
 */
public class TestImportMap {
    @SuppressWarnings("rawtypes")
    public static void main(String[] args) throws FileNotFoundException {
        /*File f = new File("C:\\Users\\Administrator\\Desktop\\export-test.xls");
        InputStream inputStream = new FileInputStream(f);

        ExcelLogs logs = new ExcelLogs();
        Collection<Map> importExcel = ExcelUtil.importExcel(Map.class, inputStream, "yyyy/MM/dd HH:mm:ss", logs, 0);

        Iterator<Map> iterator = importExcel.iterator();
        if (iterator.next().size() != 2) {
            System.out.println("Excel file against the rules.");
            System.exit(1);
        } else {
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        }
        System.out.println(Arrays.toString(importExcel.toArray()));

        for (Map m : importExcel) {
            System.out.println(m);
        }*/

String path =TestImportMap.class.getClassLoader().getResource("run.bat").getPath();
        path = path.substring(1);
        String parentDir = path.substring(0, path.lastIndexOf("/"));
        System.out.println(parentDir);
        System.out.println(path);
        try {
            String[] arr = new String[4];
            arr[0] = " C:\\Users\\Administrator\\Desktop\\iflytek.wav";
            arr[1] = " C:\\Users\\Administrator\\Desktop\\bgm.mp3";
            arr[2] = " C:\\Users\\Administrator\\Desktop\\iflytek.mp3";
            arr[3] =" C:\\Users\\Administrator\\Desktop\\syn\\run.bat";
            String dir = "C:\\Users\\Administrator\\Desktop\\syn";
            Runtime.getRuntime().exec("cmd /c start" + path + arr[0] + arr[1] + arr[2], null, new File(parentDir));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
