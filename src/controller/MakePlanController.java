package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.Element;

import makeplan.ReCallTourApi;
import search.TourInfo;

/**
 * Servlet implementation class MakePlanController
 */
@WebServlet("/MakePlan")
public class MakePlanController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ReCallTourApi ReCallTourApi;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MakePlanController() {
        super();
        ReCallTourApi = new ReCallTourApi();

        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url="";
		String contentId=""; //db에 저장된거 가져오기//////////////////////////////
		
		
		url = ReCallTourApi.getReCallApiUrl(contentId);
		
		List<TourInfo> reCallList = new ArrayList<TourInfo>();
		try {
			Element root = ReCallTourApi.RequestTourApiRoot(url);
			reCallList = ReCallTourApi.getTourList(root);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//TourInfo tourInfo = reCallList.get(0);
		//System.out.println(tourInfo.getTitle());
		//System.out.println(tourInfo.getAddr1());
		//System.out.println(tourInfo.getImageUrl());
		//System.out.println(url);
		request.getRequestDispatcher("makeplan.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
