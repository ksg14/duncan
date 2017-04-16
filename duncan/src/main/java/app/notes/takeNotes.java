// package notes;

// import java.util.Date;
// import java.io.FileWriter;
// import java.io.IOException;
// import org.json.simple.JSONArray;
// import org.json.simple.JSONObject;

// public class takeNotes {
//   public static void writeUserNotes (String userNote) {
//     Date sysDate = new Date ();

//     JSONObject note = new JSONObject();
// 		note.put("Note", userNote);
// 		note.put("Date", ""+sysDate.getTime());

// 		JSONArray notesArray = new JSONArray();
// 		notesArray.add(note);

// 		try (FileWriter file = new FileWriter("/home/duncan/Documents/notes.txt")) {
// 			file.write(notesArray.toJSONString());
// 			System.out.println("Successfully Copied JSON Object to File...");
// 			System.out.println("\nJSON Array: " + notesArray);

//     }
//   }
//   public static void getUserNotes () {

//   }
//   public static void main(String [] args) {
//     writeUserNotes ("Hey");
//   }
// }
