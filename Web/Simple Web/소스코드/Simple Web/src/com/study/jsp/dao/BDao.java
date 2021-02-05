package com.study.jsp.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.study.jsp.command.BPageInfo;
import com.study.jsp.dto.BDto;
import com.study.jsp.dto.FDto;

public class BDao {
	private static BDao instance = new BDao();
	
	int listCount = 10;
	int pageCount = 10;
	public BDao() { }

	public static BDao getInstance() {
		return instance;
	}

	public void write(String bName, String bTitle, String bContent) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "insert into mvc_board" +
					   "(bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) " +
					   "values " +
					   "(mvc_board_seq.nextval, ?, ?, ?, 0, mvc_board_seq.currval, 0, 0 ) ";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void writeFile(String fName, String fTitle, String fContent, String f_name) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "insert into mvc_file" +
					   "(fId, fName, fTitle, fContent, fHit, f_name) " +
					   "values " +
					   "(file_board_seq.nextval, ?, ?, ?, 0, ?) ";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, fName);
			pstmt.setString(2, "image2");
			pstmt.setString(3, "green2");
			pstmt.setString(4, f_name);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void writeNotice(String bName, String bTitle, String bContent, int bNumber) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "insert into mvc_board" +
					   "(bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent, bNumber, bIsNotice) " +
					   "values " +
					   "(notice_board_seq.nextval, ?, ?, ?, 0, notice_board_seq.currval, 0, 0, ?, 1 ) ";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setInt(4, bNumber);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void modify(String bId, String bTitle, String bContent) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String query = "update mvc_board " +
					   "   set bTitle = ?, " +
					   "       bContent = ? " +
					   " where bId = ?";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bTitle);
			pstmt.setString(2, bContent);
			pstmt.setString(3, bId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void reply(String bId, String bName, String bTitle, String bContent, String bGroup, String bStep, String bIndent) {
		
		replyShape(bGroup, bStep);
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = getConnection();
			String query = "insert into mvc_board " +
						   " (bId, bName, bTitle, bContent,  bGroup, bStep, bIndent) " +
					       " values (mvc_board_seq.nextval, ?, ?, ?, ?, ?, ? )";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setInt(4, Integer.parseInt(bGroup));
			pstmt.setInt(5, Integer.parseInt(bStep) + 1);
			pstmt.setInt(6, Integer.parseInt(bIndent) + 1);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
	}
	public ArrayList<BDto> noticelist(int curPage, String option, String word) {
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "";
		int nStart = (curPage - 1) * listCount + 1;
		int nEnd = (curPage - 1) * listCount +listCount;
		if(option == null) option = "";
		if(word == null) word = "";
		try {
			con = getConnection();
			if(option.equals("ALL")) {
				query = "select * " +
							   "  from ( " +
							   "	select rownum num, A.* " +
							   "	  from ( " +
							   "		select * " +
							   "		  from mvc_board " +
							   "		  where bTitle LIKE ? or bContent LIKE ? or bName LIKE ? and bNumber = 1 " +
							   "		order by bGroup desc, bStep asc) A " +
							   "    where rownum <= ? ) B " +
							   " where B.num >= ?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, "%" + word + "%");
				pstmt.setString(2, "%" + word + "%");
				pstmt.setString(3, "%" + word + "%");
				pstmt.setInt(4, nEnd);
				pstmt.setInt(5, nStart);
				set =  pstmt.executeQuery();
				
				while(set.next()) {
					int bId = set.getInt("bId");
					String bName = set.getString("bName");
					String bTitle = set.getString("bTitle");
					String bContent = set.getString("bContent");
					Timestamp bDate = set.getTimestamp("bDate");
					long bDate_long = bDate.getTime();
					int bHit = set.getInt("bHit");
					int bGroup = set.getInt("bGroup");
					int bStep = set.getInt("bStep");
					int bIndent = set.getInt("bIndent");
					int bNumber = set.getInt("bNumber");
					int bIsNotice = set.getInt("bIsNotice");
					BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent, bNumber, bIsNotice, bDate_long);
					dtos.add(dto);
				}
			} else if(option.equals("Title")) {
				query = "select * " +
							   "  from ( " +
							   "	select rownum num, A.* " +
							   "	  from ( " +
							   "		select * " +
							   "		  from mvc_board " +
							   "		  where bTitle LIKE ? and bNumber = 1 " +
							   "		order by bGroup desc, bStep asc) A " +
							   "    where rownum <= ? ) B " +
							   " where B.num >= ?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, "%" + word + "%");
				pstmt.setInt(2, nEnd);
				pstmt.setInt(3, nStart);
				set =  pstmt.executeQuery();
				
				while(set.next()) {
					int bId = set.getInt("bId");
					String bName = set.getString("bName");
					String bTitle = set.getString("bTitle");
					String bContent = set.getString("bContent");
					Timestamp bDate = set.getTimestamp("bDate");
					long bDate_long = bDate.getTime();
					int bHit = set.getInt("bHit");
					int bGroup = set.getInt("bGroup");
					int bStep = set.getInt("bStep");
					int bIndent = set.getInt("bIndent");
					int bNumber = set.getInt("bNumber");
					int bIsNotice = set.getInt("bIsNotice");
					BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent, bNumber, bIsNotice, bDate_long);
					dtos.add(dto);
				}
			} else if(option.equals("Content")) {
				query = "select * " +
						   "  from ( " +
						   "	select rownum num, A.* " +
						   "	  from ( " +
						   "		select * " +
						   "		  from mvc_board " +
						   "		  where bContent LIKE ? and bNumber = 1 " +
						   "		order by bGroup desc, bStep asc) A " +
						   "    where rownum <= ? ) B " +
						   " where B.num >= ?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, "%" + word + "%");
				pstmt.setInt(2, nEnd);
				pstmt.setInt(3, nStart);
				set =  pstmt.executeQuery();
				
				while(set.next()) {
					int bId = set.getInt("bId");
					String bName = set.getString("bName");
					String bTitle = set.getString("bTitle");
					String bContent = set.getString("bContent");
					Timestamp bDate = set.getTimestamp("bDate");
					long bDate_long = bDate.getTime();
					int bHit = set.getInt("bHit");
					int bGroup = set.getInt("bGroup");
					int bStep = set.getInt("bStep");
					int bIndent = set.getInt("bIndent");
					int bNumber = set.getInt("bNumber");
					int bIsNotice = set.getInt("bIsNotice");
					BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent, bNumber, bIsNotice, bDate_long);
					dtos.add(dto);
				}
			} else if(option.equals("Name")) {
				query = "select * " +
						   "  from ( " +
						   "	select rownum num, A.* " +
						   "	  from ( " +
						   "		select * " +
						   "		  from mvc_board " +
						   "		  where bName LIKE ? and bNumber = 1 " +
						   "		order by bGroup desc, bStep asc) A " +
						   "    where rownum <= ? ) B " +
						   " where B.num >= ?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, "%" + word + "%");
				pstmt.setInt(2, nEnd);
				pstmt.setInt(3, nStart);
				set =  pstmt.executeQuery();
				
				while(set.next()) {
					int bId = set.getInt("bId");
					String bName = set.getString("bName");
					String bTitle = set.getString("bTitle");
					String bContent = set.getString("bContent");
					Timestamp bDate = set.getTimestamp("bDate");
					long bDate_long = bDate.getTime();
					int bHit = set.getInt("bHit");
					int bGroup = set.getInt("bGroup");
					int bStep = set.getInt("bStep");
					int bIndent = set.getInt("bIndent");
					int bNumber = set.getInt("bNumber");
					int bIsNotice = set.getInt("bIsNotice");
					BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent, bNumber, bIsNotice, bDate_long);
					dtos.add(dto);
				}
			} else {
				query = "select * " +
						   "  from ( " +
						   "	select rownum num, A.* " +
						   "	  from ( " +
						   "		select * " +
						   "		  from mvc_board " +
						   "		  where bNumber = 1 " +
						   "		order by bGroup desc, bStep asc) A " +
						   "    where rownum <= ? ) B " +
						   " where B.num >= ?";
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, nEnd);
				pstmt.setInt(2, nStart);
				set =  pstmt.executeQuery();
			
				while(set.next()) {
					int bId = set.getInt("bId");
					String bName = set.getString("bName");
					String bTitle = set.getString("bTitle");
					String bContent = set.getString("bContent");
					Timestamp bDate = set.getTimestamp("bDate");
					long bDate_long = bDate.getTime();
					int bHit = set.getInt("bHit");
					int bGroup = set.getInt("bGroup");
					int bStep = set.getInt("bStep");
					int bIndent = set.getInt("bIndent");
					int bNumber = set.getInt("bNumber");
					int bIsNotice = set.getInt("bIsNotice");
					BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent, bNumber, bIsNotice, bDate_long);
					dtos.add(dto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(set != null) set.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dtos;
	}
	
	public ArrayList<BDto> list(int curPage, String option, String word) {
		
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "";
		int nStart = (curPage - 1) * listCount + 1;
		int nEnd = (curPage - 1) * listCount +listCount;
		if(option == null) option = "";
		if(word == null) word = "";
		try {
			con = getConnection();
			if(option.equals("ALL")) {
				query = "select * " +
							   "  from ( " +
							   "	select rownum num, A.* " +
							   "	  from ( " +
							   "		select * " +
							   "		  from mvc_board " +
							   "		  where bTitle LIKE ? or bContent LIKE ? or bName LIKE ? and bNumber = 0 " +
							   "		order by bGroup desc, bStep asc) A " +
							   "    where rownum <= ? ) B " +
							   " where B.num >= ?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, "%" + word + "%");
				pstmt.setString(2, "%" + word + "%");
				pstmt.setString(3, "%" + word + "%");
				pstmt.setInt(4, nEnd);
				pstmt.setInt(5, nStart);
				set =  pstmt.executeQuery();
				
				while(set.next()) {
					int bId = set.getInt("bId");
					String bName = set.getString("bName");
					String bTitle = set.getString("bTitle");
					String bContent = set.getString("bContent");
					Timestamp bDate = set.getTimestamp("bDate");
					long bDate_long = bDate.getTime();
					int bHit = set.getInt("bHit");
					int bGroup = set.getInt("bGroup");
					int bStep = set.getInt("bStep");
					int bIndent = set.getInt("bIndent");
					int bNumber = set.getInt("bNumber");
					int bIsNotice = set.getInt("bIsNotice");
					BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent, bNumber, bIsNotice, bDate_long);
					dtos.add(dto);
				}
			} else if(option.equals("Title")) {
				query = "select * " +
							   "  from ( " +
							   "	select rownum num, A.* " +
							   "	  from ( " +
							   "		select * " +
							   "		  from mvc_board " +
							   "		  where bTitle LIKE ? and bNumber = 0  " +
							   "		order by bGroup desc, bStep asc) A " +
							   "    where rownum <= ? ) B " +
							   " where B.num >= ?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, "%" + word + "%");
				pstmt.setInt(2, nEnd);
				pstmt.setInt(3, nStart);
				set =  pstmt.executeQuery();
				
				while(set.next()) {
					int bId = set.getInt("bId");
					String bName = set.getString("bName");
					String bTitle = set.getString("bTitle");
					String bContent = set.getString("bContent");
					Timestamp bDate = set.getTimestamp("bDate");
					long bDate_long = bDate.getTime();
					int bHit = set.getInt("bHit");
					int bGroup = set.getInt("bGroup");
					int bStep = set.getInt("bStep");
					int bIndent = set.getInt("bIndent");
					int bNumber = set.getInt("bNumber");
					int bIsNotice = set.getInt("bIsNotice");
					BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent, bNumber, bIsNotice, bDate_long);
					dtos.add(dto);
				}
			} else if(option.equals("Content")) {
				query = "select * " +
						   "  from ( " +
						   "	select rownum num, A.* " +
						   "	  from ( " +
						   "		select * " +
						   "		  from mvc_board " +
						   "		  where bContent LIKE ? and bNumber = 0 " +
						   "		order by bGroup desc, bStep asc) A " +
						   "    where rownum <= ? ) B " +
						   " where B.num >= ?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, "%" + word + "%");
				pstmt.setInt(2, nEnd);
				pstmt.setInt(3, nStart);
				set =  pstmt.executeQuery();
				
				while(set.next()) {
					int bId = set.getInt("bId");
					String bName = set.getString("bName");
					String bTitle = set.getString("bTitle");
					String bContent = set.getString("bContent");
					Timestamp bDate = set.getTimestamp("bDate");
					long bDate_long = bDate.getTime();
					int bHit = set.getInt("bHit");
					int bGroup = set.getInt("bGroup");
					int bStep = set.getInt("bStep");
					int bIndent = set.getInt("bIndent");
					int bNumber = set.getInt("bNumber");
					int bIsNotice = set.getInt("bIsNotice");
					BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent, bNumber, bIsNotice, bDate_long);
					dtos.add(dto);
				}
			} else if(option.equals("Name")) {
				query = "select * " +
						   "  from ( " +
						   "	select rownum num, A.* " +
						   "	  from ( " +
						   "		select * " +
						   "		  from mvc_board " +
						   "		  where bName LIKE ? and bNumber = 0 " +
						   "		order by bGroup desc, bStep asc) A " +
						   "    where rownum <= ? ) B " +
						   " where B.num >= ?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, "%" + word + "%");
				pstmt.setInt(2, nEnd); 
				
				pstmt.setInt(3, nStart);
				set =  pstmt.executeQuery();
				
				while(set.next()) {
					int bId = set.getInt("bId");
					String bName = set.getString("bName");
					String bTitle = set.getString("bTitle");
					String bContent = set.getString("bContent");
					Timestamp bDate = set.getTimestamp("bDate");
					long bDate_long = bDate.getTime();
					int bHit = set.getInt("bHit");
					int bGroup = set.getInt("bGroup");
					int bStep = set.getInt("bStep");
					int bIndent = set.getInt("bIndent");
					int bNumber = set.getInt("bNumber");
					int bIsNotice = set.getInt("bIsNotice");
					BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent, bNumber, bIsNotice, bDate_long);
					dtos.add(dto);
				}
			} else {
				query = "select * " +
						   "  from ( " +
						   "	select rownum num, A.* " +
						   "	  from ( " +
						   "		select * " +
						   "		  from mvc_board " +
						   "		  where bNumber = 0 " +
						   "		order by bGroup desc, bStep asc) A " +
						   "    where rownum <= ? ) B " +
						   " where B.num >= ?";
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, nEnd);
				pstmt.setInt(2, nStart);
				set =  pstmt.executeQuery();
			
				while(set.next()) {
					int bId = set.getInt("bId");
					String bName = set.getString("bName");
					String bTitle = set.getString("bTitle");
					String bContent = set.getString("bContent");
					Timestamp bDate = set.getTimestamp("bDate");
					long bDate_long = bDate.getTime();
					int bHit = set.getInt("bHit");
					int bGroup = set.getInt("bGroup");
					int bStep = set.getInt("bStep");
					int bIndent = set.getInt("bIndent");
					int bNumber = set.getInt("bNumber");
					int bIsNotice = set.getInt("bIsNotice");
					BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent, bNumber, bIsNotice, bDate_long);
					dtos.add(dto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(set != null) set.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dtos;
	}
	
	public BDto contentView(String strID, String kind) {
		
		if(kind.equals("view")) {
			upHit(strID);
		}
		
		BDto dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		
		try {
			con = getConnection();			
			String query = "select * from mvc_board where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(strID));
			set = pstmt.executeQuery();
			
			if (set.next()) {
				int bId = set.getInt("bId");
				String bName = set.getString("bName");
				String bTitle = set.getString("bTitle");
				String bContent = set.getString("bContent");
				Timestamp bDate = set.getTimestamp("bDate");
				long bDate_long = bDate.getTime();
				int bHit = set.getInt("bHit");
				int bGroup = set.getInt("bGroup");
				int bStep = set.getInt("bStep");
				int bIndent = set.getInt("bIndent");
				int bNumber = set.getInt("bNumber");
				int bIsNotice = set.getInt("bIsNotice");
				dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent, bNumber, bIsNotice, bDate_long);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(set != null) set.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return dto;
	}
	
	public FDto fileContentView(String strID, String kind) {
		
		FDto dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		
		try {
			con = getConnection();			
			String query = "select * from mvc_file where fId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(strID));
			set = pstmt.executeQuery();
			
			if (set.next()) {
				int fId = set.getInt("fId");
				String fName = set.getString("fName");
				String fTitle = set.getString("fTitle");
				String fContent = set.getString("fContent");
				Timestamp fDate = set.getTimestamp("fDate");
				long fDate_long = fDate.getTime();
				int fHit = set.getInt("fHit");
				String f_name = set.getString("f_name");
				System.out.println("fFname : " + f_name);
				dto = new FDto(fId, fName, fTitle, fContent, fDate, fHit, f_name, fDate_long);
				System.out.println("dto.getf_name() : " + dto.getf_name());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(set != null) set.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
		return dto;
	}
	
	public void delete(String bId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = getConnection();
			String query = "delete from mvc_board where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(bId));
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public BDto reply_view(String str) {
		BDto dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		
		try {
			con = getConnection();			
			String query = "select * from mvc_board where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(str));
			set = pstmt.executeQuery();
			
			if (set.next()) {
				int bId = set.getInt("bId");
				String bName = set.getString("bName");
				String bTitle = set.getString("bTitle");
				String bContent = set.getString("bContent");
				Timestamp bDate = set.getTimestamp("bDate");
				long bDate_long = bDate.getTime();
				int bHit = set.getInt("bHit");
				int bGroup = set.getInt("bGroup");
				int bStep = set.getInt("bStep");
				int bIndent = set.getInt("bIndent");
				int bNumber = set.getInt("bNumber");
				int bIsNotice = set.getInt("bIsNotice");
				dto = new BDto(bId, bName, bTitle, bContent, bDate, 
							   bHit, bGroup, bStep, bIndent, bNumber, bIsNotice, bDate_long);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dto;
	}
	
	private void replyShape(String strGroup, String strStep) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			String query = "update mvc_board " +
						   "   set bStep = bStep + 1 " +
						   " where bGroup = ? and bStep > ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(strGroup));
			pstmt.setInt(2, Integer.parseInt(strStep));
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void upHit(String bId) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = getConnection();
			String query = "update mvc_board set bHit = bHit + 1 where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public Connection getConnection() {
		Context context = null;
		DataSource dataSource = null;
		Connection con = null;
		
		try {
			context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
			con = dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return con;
	}

	public BPageInfo articlePage(int curPage) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		
		int listCount = 10;
		int pageCount = 10;
		int totalCount = 0;
		
		try {
			con = getConnection();
			String query = "select count(*) as total from mvc_board";
			pstmt = con.prepareStatement(query);
			set = pstmt.executeQuery();
			
			if (set.next()) {
				totalCount = set.getInt("total");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(set != null) set.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		int totalPage = totalCount / listCount;
		if (totalCount % listCount > 0)
			totalPage++;
		
		int myCurPage = curPage;
		if (myCurPage > totalPage)
			myCurPage = totalPage;
		if (myCurPage < 1)
			myCurPage = 1;
		int startPage = ((myCurPage - 1) / pageCount) * pageCount + 1;
		int endPage = startPage + pageCount - 1;
		if (endPage > totalPage)
			endPage = totalPage;
		
		BPageInfo pinfo = new BPageInfo();
		pinfo.setTotalCount(totalCount);
		pinfo.setListCount(listCount);
		pinfo.setTotalPage(totalPage);
		pinfo.setCurPage(curPage);
		pinfo.setPageCount(pageCount);
		pinfo.setStartPage(startPage);
		pinfo.setEndPage(endPage);
		
		return pinfo;
	}
	
	public BPageInfo libraryPage(int curPage) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		
		int listCount = 10;
		int pageCount = 10;
		int totalCount = 0;
		
		try {
			con = getConnection();
			String query = "select count(*) as total from mvc_file";
			pstmt = con.prepareStatement(query);
			set = pstmt.executeQuery();
			
			if (set.next()) {
				totalCount = set.getInt("total");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(set != null) set.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		int totalPage = totalCount / listCount;
		if (totalCount % listCount > 0)
			totalPage++;
		
		int myCurPage = curPage;
		if (myCurPage > totalPage)
			myCurPage = totalPage;
		if (myCurPage < 1)
			myCurPage = 1;
		int startPage = ((myCurPage - 1) / pageCount) * pageCount + 1;
		int endPage = startPage + pageCount - 1;
		if (endPage > totalPage)
			endPage = totalPage;
		
		BPageInfo pinfo = new BPageInfo();
		pinfo.setTotalCount(totalCount);
		pinfo.setListCount(listCount);
		pinfo.setTotalPage(totalPage);
		pinfo.setCurPage(curPage);
		pinfo.setPageCount(pageCount);
		pinfo.setStartPage(startPage);
		pinfo.setEndPage(endPage);
		
		return pinfo;
	}

	public void writeBoradNotice(String bName, String bTitle, String bContent, int bNumber) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "insert into mvc_board" +
					   "(bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent, bNumber, bIsNotice) " +
					   "values " +
					   "(mvc_board_seq.nextval, ?, ?, ?, 0, mvc_board_seq.currval, 0, 0, ?, 1 ) ";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setInt(4, bNumber);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	public ArrayList<FDto> libraryList(int curPage, String option, String word) {
		ArrayList<FDto> dtos = new ArrayList<FDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "";
		int nStart = (curPage - 1) * listCount + 1;
		int nEnd = (curPage - 1) * listCount +listCount;
		if(option == null) option = "";
		if(word == null) word = "";
		try {
			con = getConnection();
			if(option.equals("ALL")) {
				query = "select * " +
							   "  from ( " +
							   "	select rownum num, A.* " +
							   "	  from ( " +
							   "		select * " +
							   "		  from mvc_file " +
							   "		  where fTitle LIKE ? or fContent LIKE ? or f_name LIKE ? " +
							   "		order by fId ) A " +
							   "    where rownum <= ? ) B " +
							   " where B.num >= ?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, "%" + word + "%");
				pstmt.setString(2, "%" + word + "%");
				pstmt.setString(3, "%" + word + "%");
				pstmt.setInt(4, nEnd);
				pstmt.setInt(5, nStart);
				set =  pstmt.executeQuery();
				
				while(set.next()) {
					int fId = set.getInt("fId");
					String fName = set.getString("fName");
					String fTitle = set.getString("fTitle");
					String fContent = set.getString("fContent");
					Timestamp fDate = set.getTimestamp("fDate");
					long fDate_long = fDate.getTime();
					int fHit = set.getInt("fHit");
					String fFname = set.getString("f_name");
	
					FDto dto = new FDto(fId, fName, fTitle, fContent, fDate, fHit, fFname, fDate_long);
					dtos.add(dto);
				}
			} else if(option.equals("Title")) {
				query = "select * " +
							   "  from ( " +
							   "	select rownum num, A.* " +
							   "	  from ( " +
							   "		select * " +
							   "		  from mvc_file " +
							   "		  where fTitle LIKE ? " +
							   "		order by fId ) A " +
							   "    where rownum <= ? ) B " +
							   " where B.num >= ?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, "%" + word + "%");
				pstmt.setInt(2, nEnd);
				pstmt.setInt(3, nStart);
				set =  pstmt.executeQuery();
				
				while(set.next()) {
					int fId = set.getInt("fId");
					String fName = set.getString("fName");
					String fTitle = set.getString("fTitle");
					String fContent = set.getString("fContent");
					Timestamp fDate = set.getTimestamp("fDate");
					long fDate_long = fDate.getTime();
					int fHit = set.getInt("fHit");
					String fFname = set.getString("f_name");
	
					FDto dto = new FDto(fId, fName, fTitle, fContent, fDate, fHit, fFname, fDate_long);
					dtos.add(dto);
				}
			} else if(option.equals("Content")) {
				query = "select * " +
						   "  from ( " +
						   "	select rownum num, A.* " +
						   "	  from ( " +
						   "		select * " +
						   "		  from mvc_file " +
						   "		  where fContent LIKE ? " +
						   "		order by fId ) A " +
						   "    where rownum <= ? ) B " +
						   " where B.num >= ?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, "%" + word + "%");
				pstmt.setInt(2, nEnd);
				pstmt.setInt(3, nStart);
				set =  pstmt.executeQuery();
				
				while(set.next()) {
					int fId = set.getInt("fId");
					String fName = set.getString("fName");
					String fTitle = set.getString("fTitle");
					String fContent = set.getString("fContent");
					Timestamp fDate = set.getTimestamp("fDate");
					long fDate_long = fDate.getTime();
					int fHit = set.getInt("fHit");
					String fFname = set.getString("f_name");
	
					FDto dto = new FDto(fId, fName, fTitle, fContent, fDate, fHit, fFname, fDate_long);
					dtos.add(dto);
				}
			} else if(option.equals("Name")) {
				query = "select * " +
						   "  from ( " +
						   "	select rownum num, A.* " +
						   "	  from ( " +
						   "		select * " +
						   "		  from mvc_file " +
						   "		  where fName LIKE ? " +
						   "		order by fId ) A " +
						   "    where rownum <= ? ) B " +
						   " where B.num >= ?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, "%" + word + "%");
				pstmt.setInt(2, nEnd); 
				
				pstmt.setInt(3, nStart);
				set =  pstmt.executeQuery();
				
				while(set.next()) {
					int fId = set.getInt("fId");
					String fName = set.getString("fName");
					String fTitle = set.getString("fTitle");
					String fContent = set.getString("fContent");
					Timestamp fDate = set.getTimestamp("fDate");
					long fDate_long = fDate.getTime();
					int fHit = set.getInt("fHit");
					String fFname = set.getString("f_name");
	
					FDto dto = new FDto(fId, fName, fTitle, fContent, fDate, fHit, fFname, fDate_long);
					dtos.add(dto);
				}
			} else {
				query = "select * " +
						   "  from ( " +
						   "	select rownum num, A.* " +
						   "	  from ( " +
						   "		select * " +
						   "		  from mvc_file " +
						   "		order by fId ) A " +
						   "    where rownum <= ? ) B " +
						   " where B.num >= ?";
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, nEnd);
				pstmt.setInt(2, nStart);
				set =  pstmt.executeQuery();
			
				while(set.next()) {
					int fId = set.getInt("fId");
					String fName = set.getString("fName");
					String fTitle = set.getString("fTitle");
					String fContent = set.getString("fContent");
					Timestamp fDate = set.getTimestamp("fDate");
					long fDate_long = fDate.getTime();
					int fHit = set.getInt("fHit");
					String fFname = set.getString("f_name");
	
					FDto dto = new FDto(fId, fName, fTitle, fContent, fDate, fHit, fFname, fDate_long);
					dtos.add(dto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(set != null) set.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dtos;		
	}
}
