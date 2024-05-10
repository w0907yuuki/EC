package kaitorisisystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBconect {
			private int Count;
			private Connection con;
			private Statement stmt;
			public  void setCount(int Count) {
				this.Count = Count;
			}

			public int getCount() {
				return Count;
			}
			public void ReleaseResources() {
		        //⑤リソースを解放
		    try{
			      if(stmt != null) stmt.close();
			      if(con != null) con.close();
			      System.out.println("リソース開放");
		    }
		    catch(SQLException e){
			      e.printStackTrace();
			    }
			
		}
		public ResultSet processString(String strSQL,String strStatement) {
			//MySQLに接続するための設定
			final String URL = "jdbc:mysql://localhost/torikai?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false\"";
		    final String USER = "root";
		    final String PASS = "root";
		    Connection con = null;
		    Statement stmt = null;
		    ResultSet rs = null;


		    try{
			      Class.forName("com.mysql.cj.jdbc.Driver");
			      //①DBに接続
			      con = DriverManager.getConnection(URL, USER, PASS);
			      //②ステートメントを生成
			      stmt = con.createStatement();
			      //③SQLを実行
		    	  
			      //SELECT文を実行
			      //SQL実行するコードが違うためこのように処理を書く
			      if("SELECT".equals(strStatement)) 
			      {
			    	  //④検索結果の処理
			    	  rs = stmt.executeQuery(strSQL);
				      rs.next();
				      Count = rs.getInt("Count");
			  	      setCount(Count); 
			    	  return rs;
			      }
			      //SELECTリスト取得
			      else if("SELECTALL".equals(strStatement)) 
			      {
			    	  rs = stmt.executeQuery(strSQL);
			    	  System.out.println("SELECTALL");
			    	  return rs;
			      }
			      //UPDATA,INSERT,CREATE TABLE文の実行
			      else if("UPDATE".equals(strStatement)) 
			      {
			    	 
			    	  stmt.executeUpdate(strSQL);
			    	  System.out.println("UPDATE");
			    	  return rs;
			      }
			      else if("SERCH".equals(strStatement)) 
			      {
			    	  rs = stmt.executeQuery(strSQL);
			    	  System.out.println("SELECTALL");
			    	  return rs;
			      }
			    }
			    catch(SQLException | ClassNotFoundException e){
			      e.printStackTrace();
			      return null;
			    }
			return rs;
			    
		}
		
}
		  


