


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kaitorisisystem.DBconect;

/**
 * Servlet implementation class ServLogin
 */
public class ServLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		String ID =request.getParameter("txtLoginID");
		String Password = request.getParameter("txtLoginPassword");
		String strStatement = "SELECT";
		String strSQL = "select count(ID ="+ID+ " and Password = '"+Password+"'or null) as Count  from tbpass";
		int Result = 0;
		DBconect dbconect =null;
		ArrayList<HashMap<String, Object>> resultList = null;
		
		// セッションオブジェクトの生成or取得
	    HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		ArrayList<String> sessResultList = (ArrayList<String>)session.getAttribute("resultList");
        System.out.println(Result);
		if(sessResultList == null) {
			System.out.println("リストない");
			//インスタンスを作成
			dbconect = new DBconect();
			//DB接続コードに値を渡す
			dbconect.processString(strSQL,strStatement);
			//一致したID、Passwordの件数を取得
			Result = dbconect.getCount();
            resultList = new ArrayList<>();
            System.out.println(Result);
		}
		else 
		{	
				System.out.println("リストある");
				request.setAttribute("resultList", sessResultList);
	            request.setAttribute("ID", ID);
	            RequestDispatcher rd = request.getRequestDispatcher("jspLogin.jsp");
				//jspにフォワード
				rd.forward(request, response);
		}
		
		//一致した件数に合わせて処理
		if(Result == 1) //ログイン成功
		{
			//ログイン成功時データをjspに渡してページ移動する
			strStatement = "SELECTALL";
			strSQL = "select * from tbproduct";
			ResultSet rs = dbconect.processString(strSQL,strStatement) ;
	
			// ResultSetからデータを取り出してArrayListに格納
	        try {
				while (rs.next()) {
				HashMap<String, Object> row = new HashMap<>();
				row.put("id", rs.getString("ID"));
				row.put("Image", rs.getString("Image"));
				row.put("name", rs.getString("Name"));
				row.put("Price", rs.getString("Price"));
				row.put("Seller", rs.getString("Seller"));
				row.put("Category", rs.getString("Category"));
				row.put("Freetext", rs.getString("Freetext"));
				 // 列の数に応じて追加
				resultList.add(row);
				}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
	            // リクエスト属性にArrayListを設定
	            session.setAttribute("resultList", resultList);
	            session.setAttribute("Result", Result);
	            session.setAttribute("ID", ID);
	            request.setAttribute("resultList", resultList);
	            request.setAttribute("ID", ID);
		        dbconect.ReleaseResources();
				//request.setAttribute("ID",ID);
				RequestDispatcher rd = request.getRequestDispatcher("jspLogin.jsp");
				//jspにフォワード
				rd.forward(request, response);
			
			}
			if (Result == 0) //ログイン失敗
			{
				//ログイン失敗時、IDPassが一致しないことを伝えるページを作成
				PrintWriter out = response.getWriter();
	            System.out.println(Result);
		     	 out.println("<html><body>");
		         out.println("<h2>ID,Passが一致しません。もしくはエラーが起きました。ログイン画面に戻ります</h2>");
		         out.println("<a href=\"Login.html\">Go back</a>");
		         out.println("</body></html>");
			}

		}
	}
