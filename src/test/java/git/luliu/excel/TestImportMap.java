package git.luliu.excel;

import git.luliu.excel.model.Speaker;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * The <code>TestImportMap</code>
 */
public class TestImportMap {
    @SuppressWarnings("rawtypes")
    public static void main(String[] args) throws FileNotFoundException {
        /*File f = new File("C:\\Users\\Administrator\\Desktop\\test.xls");
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

        File f = new File("C:\\Users\\Administrator\\Desktop\\speakers.xls");
        InputStream inputStream = new FileInputStream(f);

        ExcelLogs logs = new ExcelLogs();
        Collection<Map> importExcel = ExcelUtil.importExcel(Map.class, inputStream, "yyyy/MM/dd HH:mm:ss", logs, 0);

        List<List<Speaker>> speakersGroupByCategory = new ArrayList<>();
        List<List<Speaker>> speakersGroupBySpeaker = new ArrayList<>();
        List<Speaker> speakerList = new ArrayList<>();

        HashSet<Integer> categorySet = new HashSet<>();
        HashSet<Integer> speakerSet = new HashSet<>();

        for (Map m : importExcel) {

            System.out.println(categorySet);
            System.out.println(speakerSet);

            Speaker speaker = new Speaker();
            speaker.setWorkId(Integer.valueOf((String) m.get("workId")));
            speaker.setSpeakerId(Integer.valueOf((String) m.get("speakerId")));
            speaker.setCategoryId(Integer.valueOf((String) m.get("categoryId")));
            speaker.setSpeakerName((String) m.get("speakerName"));
            speaker.setSpeakerGender((String) m.get("speakerGender"));
            speaker.setSpeakerCategory((String) m.get("speakerCategory"));
            speaker.setSpeakerStyle((String) m.get("speakerStyle"));
            speaker.setWorkName((String) m.get("workName"));
            speaker.setWorkUrl("http://yspt.oss-cn-hangzhou.aliyuncs.com/speaker/" + m.get("workId") + ".mp3");
            //speaker.setWorkUrl((String) m.get("workUrl"));


            // group by category
            int categoryId = speaker.getCategoryId();
            // 初始化
            if (speakersGroupByCategory.size() < categoryId) {

                int gap = categoryId - speakersGroupByCategory.size();
                for (int i = 0; i < gap; i++) {
                    speakersGroupByCategory.add(new ArrayList<Speaker>());
                }
            }

            if (categorySet.contains(categoryId)) {
                speakersGroupByCategory.get(categoryId - 1).add(speaker);
            } else {
                categorySet.add(categoryId);
                speakersGroupByCategory.get(categoryId - 1).add(speaker);
            }

            // group by speaker
            int speakerId = speaker.getSpeakerId();
            if (speakersGroupBySpeaker.size() < speakerId) {

                int gap = speakerId - speakersGroupBySpeaker.size();
                for (int i = 0; i < gap; i++) {
                    speakersGroupBySpeaker.add(new ArrayList<Speaker>());
                }
            }

            if (speakerSet.contains(speakerId)) {
                speakersGroupBySpeaker.get(speakerId - 1).add(speaker);
            } else {
                speakerSet.add(speakerId);
                speakersGroupBySpeaker.get(speakerId - 1).add(speaker);
            }


        }

        System.out.println(speakersGroupByCategory);
        System.out.println(speakersGroupBySpeaker);

        /*System.out.println(getSpeakersGroup(speakerList, "getCategoryId"));
        System.out.println(getSpeakersGroup(speakerList, "getSpeakerId"));*/


        try {
            File categoryInfoFile = new File("C:\\Users\\Administrator\\Desktop\\category-info.js");
            FileOutputStream categoryInfoOut = new FileOutputStream(categoryInfoFile);
            for (int index : categorySet) {
                List<Speaker> list = speakersGroupByCategory.get(index - 1);
                int id = list.get(0).getCategoryId();
                String str = "var category_info_" + id + "= { \"data\": " + list + "};";
                categoryInfoOut.write(str.getBytes("UTF-8"));

                System.out.println(str);
            }
            categoryInfoOut.close();


            File speakerInfoFile = new File("C:\\Users\\Administrator\\Desktop\\speaker-info.js");
            FileOutputStream speakerInfoOut = new FileOutputStream(speakerInfoFile);
            for (int index : speakerSet) {
                List<Speaker> list = speakersGroupBySpeaker.get(index - 1);
                int id = list.get(0).getSpeakerId();
                String str = "var speaker_info_" + id + "= { \"data\": " + list + "};";
                speakerInfoOut.write(str.getBytes("UTF-8"));

                System.out.println(str);
            }
            speakerInfoOut.close();


//            speakerList = sort(speakerSet, speakersGroupBySpeaker);

            /*System.out.println(speakerSet);
            System.out.println(speakersGroupBySpeaker);

            sortByWorkSize(speakersGroupBySpeaker);

            for (List<Speaker> list : speakersGroupBySpeaker) {
                if (list.size() != 0) {
                    System.out.println(list.size() + ":" + list);
                    speakerList.add(list.get(0));
                } else {
                    break;
                }
            }

            File speakerFile = new File("C:\\Users\\Administrator\\Desktop\\speaker.js");
            FileOutputStream speakerOut = new FileOutputStream(speakerFile);
            String str = "var speaker = { \"data\": " + speakerList.toString() + "};";
            speakerOut.write(str.getBytes("UTF-8"));
            speakerOut.close();*/

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static List<List<Speaker>> getSpeakersGroup(List<Speaker> speakerList, String method) {
        List<List<Speaker>> speakersGroup = new ArrayList<>();
        if (speakerList.size() != 0) {
            HashSet<Integer> set = new HashSet<>();
            int id = 0;
            try {
                Class c = Class.forName("git.luliu.excel.model.Speaker");
                Object o = c.newInstance();
                Method m = c.getDeclaredMethod(method);
                id = (Integer) m.invoke(o);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            for (Speaker speaker : speakerList) {
                // group by category
//                int categoryId = speaker.getCategoryId();
                // 初始化
                if (speakersGroup.size() < id) {

                    int gap = id - speakersGroup.size();
                    for (int i = 0; i < gap; i++) {
                        speakersGroup.add(new ArrayList<Speaker>());
                    }
                }

                if (set.contains(id)) {
                    speakersGroup.get(id - 1).add(speaker);
                } else {
                    set.add(id);
                    speakersGroup.get(id - 1).add(speaker);
                }
            }
        }
        return speakersGroup;
    }

    // 按主播作品数倒序
    public static void sortByWorkSize(List<List<Speaker>> speakersGroupBySpeaker) {
        Collections.sort(speakersGroupBySpeaker, new Comparator<List<Speaker>>() {
            @Override
            public int compare(List<Speaker> o1, List<Speaker> o2) {
                return o2.size() - o1.size();
            }
        });
    }
}
