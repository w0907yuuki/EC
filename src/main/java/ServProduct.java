

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.UUID;

import javax.imageio.ImageIO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import kaitorisisystem.DBconect;

@MultipartConfig
public class ServProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServProduct() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    //初期値設定
		String outFilename = null;
		//フォームから値を取得
		String strName =request.getParameter("txtName");
		String strPrice  = request.getParameter("intPrice");
		String strSeller  = request.getParameter("txtSeller");
		String strCategory  = request.getParameter("txtCategory");
		String strFreetext  = request.getParameter("txtFreetext");
		//DB接続状態を設定
		String strStatement = "UPDATE";
		//ファイルのユニーク名設定
		String strUnique = UUID.randomUUID().toString();
		//
		String strPath = "C:/pleiades/2023-12/workspace/kaitorisisystem/src/main/webapp/image/"+strName+strSeller+strUnique;
		//フォームから画像を取得
		Part part=request.getPart("imageFile");
		if (request.getPart("imageFile") ==null || part.getSize()==0) {
			// ファイルがアップロードされていない場合はエラーを返す
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "画像をアップロードしてください");
            return;
        }
		else{
			InputStream inputStream = part.getInputStream();
		//ファイル名を取得
		String saveFilename=Paths.get(part.getSubmittedFileName()).getFileName().toString();
		//アップロードするフォルダ
		String path=getServletContext().getRealPath("image");

		//書き込み
        try {
            // 画像を読み込む
            BufferedImage image = ImageIO.read(inputStream); // 画像のファイルパスを指定
            System.out.println("読み込み成功");

            // 画像を保存する
            //output最後のパスはファイル名を追加ID＋日時
            File output = new File(strPath+".jpg"); // 保存先のファイルパスを指定
            ImageIO.write(image, "jpg", output); // "jpg" は保存する画像の形式を指定

            System.out.println("画像が保存されました。");
        } catch (IOException e) {
            System.out.println("画像の読み込みまたは保存中にエラーが発生しました。");
            e.printStackTrace();
        }
        System.out.println(strPath);
       outFilename = strName+strSeller+strUnique+".jpg";
		}
		String strSQL = "insert into tbproduct(Name,Image,Price,Seller,Category,Freetext) values('"+strName+"','"+outFilename+"',"+strPrice+",'"+strSeller+"','"+strCategory+"','"+strFreetext+"')";
		//インスタンスを作成
		DBconect dbconect = new DBconect();
		//DB接続コードに値を渡して登録処理を行う
		dbconect.processString(strSQL,strStatement);
		//リソース開放
	    dbconect.ReleaseResources();
	   //jsp
		RequestDispatcher rd = request.getRequestDispatcher("jspProduct.jsp");
		//jspにフォワード
		rd.forward(request, response);

	}

}
