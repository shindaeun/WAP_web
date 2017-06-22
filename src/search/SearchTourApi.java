package search;

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

public class SearchTourApi {
	private static String serviceKey = "rScdNggADyKJei0eJUP%2B3A09KzNddoEGygB5xCDqlyYJbzQTZORNEOp0%2FtxF9e5Mwd8%2F5bJFmGzJY0RjTzwHGw%3D%3D";
	private static String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchKeyword?MobileOS=ETC&MobileApp=WAP&numOfRows=10&ServiceKey=";
	int totalCount;
	int pageNo;
	
	
	public SearchTourApi() {
		url += serviceKey;
		url += "&keyword=";
	}

	public String getTourApiUrl(String keyword, Integer pageNo) {
		String tourUrl = "";
		try {
			// this.pageNo=pageNo;
			tourUrl = url + URLEncoder.encode(keyword, "UTF-8");
			tourUrl += "&pageNo=" + pageNo;

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

	public int getTotalCount(Element root) {
		String totalCount = getTextContent(root.getLastChild(), "totalCount");
		if(totalCount.length() == 0)
			return 0;
		return Integer.parseInt(totalCount);

	}

	public int getpageNo() {
		return pageNo;
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
			tourInfo.setContentid(getTextContent(item, "contentid"));
			tourList.add(tourInfo);
		}
		return tourList;
	}
}