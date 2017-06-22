package makeplan;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import search.TourInfo;
public class ReCallTourApi {

		private static String serviceKey = "rScdNggADyKJei0eJUP%2B3A09KzNddoEGygB5xCDqlyYJbzQTZORNEOp0%2FtxF9e5Mwd8%2F5bJFmGzJY0RjTzwHGw%3D%3D";
		private static String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?firstImageYN=Y&defaultYN=Y&addrinfoYN=Y&MobileOS=ETC&MobileApp=AppTesting&ServiceKey=";
		
		public ReCallTourApi() {
			url += serviceKey;
			url += "&contentId=";
		}

		public String getReCallApiUrl(String contentid) {
			String tourUrl = "";
			try {
				
				tourUrl = url + contentid;

			} catch (Exception e) {
				e.printStackTrace();
			}
			return tourUrl;
		}

		public Element RequestTourApiRoot(String url) throws Exception {
			Element root = null;
			try {
				DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
				DocumentBuilder parser = f.newDocumentBuilder();
				Document xmlDoc = null;
				xmlDoc = parser.parse(url);
				root = xmlDoc.getDocumentElement();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return root;
		}

		private String getTextContent(Node itemNode, String nodeName) {
			Node item = ((Element)itemNode).getElementsByTagName(nodeName).item(0);
			if (item == null)
				return "";
			return item.getTextContent();
		}


	
		public List<TourInfo> getTourList(Element root) {
			List<TourInfo> tourList = new ArrayList<TourInfo>();
			NodeList itemList = root.getElementsByTagName("item");
			for (int i = 0; i < itemList.getLength(); i++) {
				Node item = itemList.item(i);
				TourInfo tourInfo = new TourInfo();
				tourInfo.setAddr1(getTextContent(item, "addr1"));
				tourInfo.setTitle(getTextContent(item,"title"));
				tourInfo.setImageUrl(getTextContent(item, "firstimage"));
				tourList.add(tourInfo);
			}
			return tourList;
		}
	
}
