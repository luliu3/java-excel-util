package git.luliu.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The <code>TestExportMap</code>
 */
public class TestExportMap {
  public static void main(String[] args) throws IOException {
    List<Map<String,Object>> list = new ArrayList<>();
    Map<String,Object> map =new HashMap<String, Object>();
    map.put("name", "李磊");
    map.put("age", 20);
    Map<String,Object> map2 =new HashMap<String, Object>();
    map2.put("name", "luliu");
    map2.put("age", 24);
    list.add(map);
    list.add(map2);
    
    File f= new File("C:\\Users\\Administrator\\Desktop\\export-test.xls");
    OutputStream out = new FileOutputStream(f);
    ExcelUtil.exportExcel(new String[]{"name","age"},list, out );
    out.close();
  }
}
