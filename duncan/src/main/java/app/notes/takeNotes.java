// package notes;
//
// import org.json.simple.JSONArray;
// import org.json.simple.JSONObject;
//
// import java.io.FileWriter;
// import java.io.IOException;
//
// public class takeNotes {
//     final static String notesFilePath = "/home/duncan/duncan_assets/test.json";
//     public static void main(String[] args) {
//         JSONObject obj = new JSONObject();
//         obj.put("name", "mkyong.com");
//         obj.put("age", new Integer(100));
//
//         JSONArray list = new JSONArray();
//         list.add("msg 1");
//         list.add("msg 2");
//         list.add("msg 3");
//
//         obj.put("messages", list);
//
//         try (FileWriter file = new FileWriter(notesFilePath)) {
//             file.write(obj.toJSONString());
//             file.flush();
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//         System.out.print(obj);
//     }
//
// }
