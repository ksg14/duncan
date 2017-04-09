
public static final String[] xmlLoader(){
    String xmlData[] = new String[2];
    try {
        URL googleWeatherXml = new URL("http://www.google.com/ig/api?weather=02110");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();    
        Document doc = db.parse(googleWeatherXml.openStream());

         // normalize text representation
        doc.getDocumentElement ().normalize ();
        NodeList listOfWeek = doc.getElementsByTagName("");

            Node firstWeekNode = listOfWeek.item(dateCounter-1);
            int totalWeeks = listOfWeek.getLength();


            //Break xml file into parts, then break those parts down int an array by passing individual elements to srtings
            if(firstWeekNode.getNodeType() == Node.ELEMENT_NODE){
                Element firstWeekElement = (Element)firstWeekNode;
                //-------
                NodeList dateList = firstWeekElement.getElementsByTagName("date");
                Element dateElement = (Element)dateList.item(0);

                NodeList textDateList = dateElement.getChildNodes();
                xmlData[0]= (((Node)textDateList.item(0)).getNodeValue().trim()).toString();

                //-------
                NodeList riddleList = firstWeekElement.getElementsByTagName("riddle");
                Element riddleElement = (Element)riddleList.item(0);

                NodeList textRiddleList = riddleElement.getChildNodes();
                xmlData[1]= (((Node)textRiddleList.item(0)).getNodeValue().trim()).toString();

                //----
                NodeList lWSList = firstWeekElement.getElementsByTagName("lastWeekSolution");
                Element ageElement = (Element)lWSList.item(0);

                NodeList textLWSList = ageElement.getChildNodes();
                xmlData[2]= (((Node)textLWSList.item(0)).getNodeValue().trim()).toString();

                //------
            }//end of if clause

    }
    catch(MalformedURLException f){System.err.println(f.getMessage()); }
    catch(NullPointerException npe){
        System.out.println("The Weather Data you searched for is incorrect or does not yet exist, try again. ");
        String s[] = {" ", " "};
        main(s);
    }

   catch (SAXParseException err) {
    System.out.println ("** Parsing error" + ", line " 
         + err.getLineNumber () + ", uri " + err.getSystemId ());
    System.out.println(" " + err.getMessage ());

    }
    catch (SAXException e) {
    Exception x = e.getException ();
    ((x == null) ? e : x).printStackTrace ();

    }catch (Throwable t) {
    t.printStackTrace ();
    }
    return xmlData;
}