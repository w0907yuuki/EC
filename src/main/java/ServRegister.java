

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kaitorisisystem.DBconect;

/**
 * Servlet implementation class ServRegister
 */
public class ServRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
	    
		String ID =request.getParameter("txtRegisterID");
		String Password = request.getParameter("txtPassword");
		String strStatement = "SELECT";
		String strSQL = "select count(ID ="+ID+ " and Password = '"+Password+"'or null) as Count  from tbpass";
		//インスタンスを作成
		DBconect dbconect = new DBconect();
		//DB接続コードに値を渡す
		dbconect.processString(strSQL,strStatement) ;
		//一致したID、Passwordの件数を取得
		int Result = dbconect.getCount();
		
		//一致した件数に合わせて処理
		if(Result == 0) //登録成功
		{
			//ログイン成功時IDをjspに渡してページ移動する
			strSQL = "Insert into tbpass(ID,Password) Values("+ID+",'"+Password+"')";
			strStatement = "UPDATE";
			dbconect.processString(strSQL,strStatement) ;
			request.setAttribute("ID",ID);
			RequestDispatcher rd = request.getRequestDispatcher("jspLogin.jsp");
			//jspにフォワード
			rd.forward(request, response);
		}
		else if (Result == 1) //ID、パスワードが存在していることを表示
		{
			//ログイン失敗時、IDPassが一致しないことを伝えるページを作成
			PrintWriter out = response.getWriter();
			System.out.println(ID);
			System.out.println(Password);
			System.out.println(Result);
	     	 out.println("<html><body>");
	         out.println("<h2>同じIDが存在してます。別のIDに変更して下さい。</h2>");
	         out.println("<a href=\"Login.html\">Go back</a>");
	         out.println("</body></html>");
			
		}

	}

	}
