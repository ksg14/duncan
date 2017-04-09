import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class browserAPI 
{
     
    public static void browserScript (String url)
    {

        //String url = "http://www.google.com";

        if(Desktop.isDesktopSupported())
        {
        	Desktop desktop = Desktop.getDesktop();
            
        	try 
            {
                desktop.browse(new URI(url));
            }
            
            catch (IOException | URISyntaxException e) 
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        else
        {
            Runtime runtime = Runtime.getRuntime();
            
            try 
            {
                runtime.exec("xdg-open " + url);
            }
            
            catch (IOException e) 
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}