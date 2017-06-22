package controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.Element;

import search.SearchTourApi;
import search.TourInfo;


/**
 * Servlet implementation class SearchController
 */
@WebServlet("/Search")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static SearchTourApi searchTourApi;
	
	private Connection con;
	private Statement st;
	private ResultSet rs;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchController() {
		super();
		searchTourApi = new SearchTourApi();
		
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");

		String keyword = request.getParameter("kw");
		System.out.println(keyword);
		if (keyword == null)
			keyword = "";

		int pageNo = 1;
		if (request.getParameter("pageNo") != null)
			pageNo = Integer.parseInt(request.getParameter("pageNo"));

		List<TourInfo> tourList = new ArrayList<TourInfo>();
		int totalCount = 0;

		String url = searchTourApi.getTourApiUrl(keyword, pageNo);
		try {
			Element root = searchTourApi.RequestTourApiRoot(url);
			totalCount = searchTourApi.getTotalCount(root);
			tourList = searchTourApi.getTourList(root);
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("kw", keyword);
		request.setAttribute("url", url);
		request.setAttribute("tourList", tourList);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("totalCount", totalCount);
		actionDo(request, response);
	
		request.getRequestDispatcher("search.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("EUC-KR");
		String kw = request.getParameter("kw");

		// String SQL = "SELECT * FROM ADMIN WHERE adminID = " + adminID + "and
		// adminPassword = " + adminPassword;
		String SQL = "SELECT * FROM searchlog WHERE keyword = '" + kw + "'";

		//System.out.println(SQL);

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/webproject?autoReconnect=true&useSSL=false&useTimezone=true&serverTimezone=GMT",
					"root", "0410");

			st = con.createStatement();
			rs = st.executeQuery(SQL);

			if (rs.next()) { // 검색되는 데이터가 있으면
				String keyword = rs.getString("keyword");
				int cnt = rs.getInt("cnt");

				st.executeUpdate("UPDATE searchlog SET cnt =" + (cnt + 1) + " WHERE keyword = '" + kw + "';");

			} else { // 검색되는 데이터가 없으면!
				st.executeUpdate("INSERT into searchlog values ('" + kw + "', '1');");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("데이터베이스 오류 : " + e.getMessage());
		}
	}
}
