import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) {
		Connection con = null;

		try {
			Class.forName("org.sqlite.JDBC");

			String dbFile = "myfirst.db";
			con = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
			
			//데이터 조회 
			System.out.println("\n*** 데이터 조회 ***");
			Statement stat1 = con.createStatement();
			String sql1 = "select * from g_artists";
			ResultSet rs1 = stat1.executeQuery(sql1);
			
			while (rs1.next()){
				String id = rs1.getString("id");
				String name = rs1.getString("name");
				String regdate = rs1.getString("debut");
				System.out.println(id + " " + name + " " + regdate);
			}
			stat1.close();
			
			//데이터 추가 
			System.out.println("\n*** 새 데이터 추가 ***");
			Statement stat2 = con.createStatement();
				//추가된 데이터들의 갯수를 cnt에 반환하기 위해 추가구문들을 StringBuffer로 생성하였습니다.
			StringBuffer sql2 = new StringBuffer();
			sql2.append("insert into g_artists (name, a_type, a_year, debut, regdate)"
					+ " values ('예빛', '여성', '2010년대, 2020년대','2019년', datetime('now', 'localtime'));");
			sql2.append("insert into g_artists (name, a_type, a_year, debut, regdate)"
					+ " values ('멜로망스', '남성', '2010년대, 2020년대','2015년', datetime('now', 'localtime'));");
			sql2.append("insert into g_artists (name, a_type, a_year, debut, regdate)"
					+ " values ('10cm','남성', '2010년대, 2020년대','2010년', datetime('now', 'localtime'));");
			sql2.append("insert into g_artists (name, a_type, a_year, debut, regdate)"
					+ " values ('치즈', '여성', '2010년대, 2020년대','2011년', datetime('now', 'localtime'));");
			sql2.append("insert into g_artists (name, a_type, a_year, debut, regdate)"
					+ " values ('검정치마', '남성', '2020년대, 2010년대, 2020년대','2008년', datetime('now', 'localtime'));");
			
			int cnt = stat2.executeUpdate(sql2.toString());
			
			if(cnt > 0)
				System.out.printf("%d개의 새로운 데이터가 추가되었습니다!\n", cnt);
			else
				System.out.println("[Error] 데이터 추가 오류!");
			stat2.close();
			
			//데이터 수정
			System.out.println("\n*** 데이터 수정 ***");
			Statement stat3 = con.createStatement();
			
			StringBuffer sql3 = new StringBuffer();
			sql3.append("update g_artists set debut = '2012년/우주를 건너' "
					+ "where id='3' ;");
			sql3.append("update g_artists set debut = '2008년/미아' "
					+ "where id='4' ;");
			sql3.append("update g_artists set debut = '2014년/Vineyard' "
					+ "where id='5' ;");
			sql3.append("update g_artists set debut = '2014년/로켓트' "
					+ "where id='7' ;");
			sql3.append("update g_artists set debut = '2019년/바람' "
					+ "where name='예빛' ;");
			sql3.append("update g_artists set debut = '2015년/너에게' "
					+ "where name='멜로망스' ;");
			sql3.append("update g_artists set debut = '2010년/Good Night' "
					+ "where name='10cm' ;");
			sql3.append("update g_artists set debut = '2011년/나홀로 집에' "
					+ "where name='치즈' ;");
			sql3.append("update g_artists set debut = '2008년/좋아해줘' "
					+ "where name='검정치마' ;");
			sql3.append("update g_artists set debut = '2016년/Bus Stop' "
					+ "where name='카더가든' ;");
			
			int cnt3 = stat3.executeUpdate(sql3.toString());
			
			if(cnt3 > 0)
				System.out.printf("%d개의 데이터가 수정되었습니다!\n", cnt3);
			else
				System.out.println("[Error] 데이터 수정 오류!");
			stat3.close();
			
			//데이터 삭제
			System.out.println("\n*** 데이터 삭제 ***");
			Statement stat4 = con.createStatement();
			
			String sql4 = "delete from artists where artistid='9';";
	
			boolean result = stat4.execute(sql4);
			
			String sql5 = "select * from artists;";

			ResultSet rs2 = stat4.executeQuery(sql5);
			
			while (rs2.next()){
				String id = rs2.getString("artistid");
				String name = rs2.getString("name");
				System.out.println(id + " " + name );
			}

			stat4.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
