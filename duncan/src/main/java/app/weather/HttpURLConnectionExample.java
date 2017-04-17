
// import java.io.BufferedReader;
// import java.io.DataOutputStream;
// import java.io.InputStreamReader;
// import java.net.HttpURLConnection;
// import java.net.URL;

// import javax.net.ssl.HttpsURLConnection;

// public class HttpURLConnectionExample {

// 	private final String USER_AGENT = "Mozilla/5.0";

// 	public static void main(String[] args) throws Exception {

// 		HttpURLConnectionExample http = new HttpURLConnectionExample();

// 		System.out.println("Testing 1 - Send Http GET request");
// 		http.sendGet();

// 	}

// 	// HTTP GET request
// 	private void sendGet() throws Exception {

// 		String url = "http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b1b15e88fa797225412429c1c50c122a1";

// 		URL obj = new URL(url);
// 		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

// 		// optional default is GET
// 		con.setRequestMethod("GET");

// 		//add request header
// 		con.setRequestProperty("User-Agent", USER_AGENT);

// 		int responseCode = con.getResponseCode();
// 		System.out.println("\nSending 'GET' request to URL : " + url);
// 		System.out.println(responseCode);
// 		//System.out.println("Response Code : " + responseCode);

// 		BufferedReader in = new BufferedReader(
// 		        new InputStreamReader(con.getInputStream()));
// 		String inputLine;
// 		StringBuffer response = new StringBuffer();

// 		while ((inputLine = in.readLine()) != null) {
// 			response.append(inputLine);
// 		}
// 		//JSONObject jsonObj = new JSONObject(jsonString);
// 		in.close();

// 		//print result
// 		//JSONObject jsonObj = new JSONObject(jsonString.toString());
// 		System.out.println(jsonobj);

// 	}
// }