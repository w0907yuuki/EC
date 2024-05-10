

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kaitorisisystem.DBconect;


public class ServSerch extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServSerch() {
        super();

    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strSerch = request.getParameter("txtSerch");
		String strSQL = "SELECT* FROM tbproduct WHERE name LIKE '"+strSerch+"%'";
		String strStatement = "SERCH";
		DBconect dbconect =null;
		dbconect = new DBconect();
		ArrayList<HashMap<String, Object>> resultList = null;
        resultList = new ArrayList<>();
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
        request.setAttribute("resultList", resultList);
        dbconect.ReleaseResources();
		RequestDispatcher rd = request.getRequestDispatcher("jspLogin.jsp");
		//jspにフォワード
		rd.forward(request, response);
	}

}
