package puresport.mvc.comm;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import puresport.mvc.t1usrbsc.T1usrBsc;

public class ExportData2Excel {
	public HSSFCellStyle cellstyle;
	
//	public static final String aboutsporter = "files/querydata/aboutsporter/";
	/**
	 * 
	 * @param response:响应对象，类型是HttpServletResponse
	 * @param map:要封装的信息的map容器，其中key为Student，value为String类型的，在这里代表分数
	 * @throws Exception:代表异常对象
	 */
	public static void downPoi(HttpServletResponse response,
			List<T1usrBsc> list) throws Exception {
		String fname = "detial" + getTimeStamp();// Excel文件名
		OutputStream os = response.getOutputStream();// 取得输出流
		response.reset();// 清空输出流
		response.setHeader("Content-disposition", "attachment; filename="
				+ fname + ".xls"); // 设定输出文件头,该方法有两个参数，分别表示应答头的名字和值。
		response.setContentType("application/msexcel");
		try {
			new ExportData2Excel().new POIS().createSporterSheet(os, list);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static boolean downLoadSporterExcel(List<T1usrBsc> list,String aboutsporterDir,String usrid,String aboutsporterFileName) throws Exception {

		String fileParentPath = getWebContentPath()+aboutsporterDir+usrid+"/";
		File file = new File(fileParentPath);  
		if(!file.exists())
		{
			file.mkdirs();
		}
		String fileAbsolutePathAndName = fileParentPath+aboutsporterFileName;
		OutputStream os = new FileOutputStream(fileAbsolutePathAndName);// 取得输出流
//		response.reset();// 清空输出流
//		response.setHeader("Content-disposition", "attachment; filename="
//				+ fname + ".xls"); // 设定输出文件头,该方法有两个参数，分别表示应答头的名字和值。
//		response.setContentType("application/msexcel");
		try {
			new ExportData2Excel().new POIS().createSporterSheet(os, list);
			return true;
//			return "/"+aboutsporterDir+usrid+"/"+aboutsporterFileName;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
//			return "文件下载失败";
		}
		
	}
	public static String getWebContentPath()
	   {
		   try {
			   	String t=Thread.currentThread().getContextClassLoader().getResource("").getPath(); 
				int num=t.indexOf("WEB-INF");
				String path=t.substring(1,num);
				return path;
		   }catch(Exception e)
		   {
			   e.printStackTrace();
		   }
		   return null;
	   }
	/**
	 * 该方法用来产生一个时间字符串（即：时间戳）
	 * @return
	 */
	public static String getTimeStamp() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd hh:MM:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

class POIS {
 
		public void createSporterSheet(OutputStream os,
				List<T1usrBsc> list) throws Exception {
			// 创建工作薄
			HSSFWorkbook wb = new HSSFWorkbook();
			// 在工作薄上建一张工作表
			HSSFSheet sheet = wb.createSheet();
			HSSFRow row = sheet.createRow((int) 0);
			sheet.createFreezePane(0, 1);
			cteateCell(wb, row, (short) 0, "人员类型");
			cteateCell(wb, row, (short) 1, "姓名");
			cteateCell(wb, row, (short) 2, "证件类型");
			cteateCell(wb, row, (short) 3, "证件号");
			cteateCell(wb, row, (short) 4, "工作单位");
			cteateCell(wb, row, (short) 5, "职务");
			cteateCell(wb, row, (short) 6, "性别");
			cteateCell(wb, row, (short) 7, "出生日期");
			cteateCell(wb, row, (short) 8, "运动项目");
			cteateCell(wb, row, (short) 9, "级别");
			cteateCell(wb, row, (short) 10, "所属省（自治区）");
			cteateCell(wb, row, (short) 11, "所属市");
			cteateCell(wb, row, (short) 12, "手机号");
			cteateCell(wb, row, (short) 13, "邮箱");
			
			cellstyle = wb.createCellStyle();
			cellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
//			int i = 0;
//			Set<Student> keySet = student.keySet();
//			Iterator<Student> iterator = keySet.iterator();
//			while (iterator.hasNext()) {
//				HSSFRow rowi = sheet.createRow((short) (++i));
//				Student student2 = iterator.next();
			for(int i =0;i<list.size();i++) {
				T1usrBsc usr = list.get(i);
				HSSFRow rowi = sheet.createRow((int) (i+1));
				
				for (int j = 0; j < 4; j++) {
					cteateCell(wb, rowi, (short) 0, usr.getUsr_tp());
					cteateCell(wb, rowi, (short) 1, usr.getNm());
					cteateCell(wb, rowi, (short) 2, usr.getCrdt_tp());
					cteateCell(wb, rowi, (short) 3, usr.getCrdt_no());
					cteateCell(wb, rowi, (short) 4, usr.getDepartment());
					cteateCell(wb, rowi, (short) 5, usr.getPost());
					cteateCell(wb, rowi, (short) 6, usr.getGnd());
					cteateCell(wb, rowi, (short) 7, usr.getBrth_dt());
					cteateCell(wb, rowi, (short) 8, usr.getSpt_prj());
					cteateCell(wb, rowi, (short) 9, usr.getTypelevel());
					cteateCell(wb, rowi, (short) 10, usr.getProvince());
					cteateCell(wb, rowi, (short) 11, usr.getCity());
					cteateCell(wb, rowi, (short) 12, usr.getMblph_no());
					cteateCell(wb, rowi, (short) 13, usr.getEmail());
				}
			}
			wb.write(os);
			os.flush();
			os.close();
			System.out.println("文件生成");
 
		}
 
		@SuppressWarnings("deprecation")
		private void cteateCell(HSSFWorkbook wb, HSSFRow row, short col,
				String val) {
			HSSFCell cell = row.createCell(col);
			cell.setCellValue(val);
//			HSSFCellStyle cellstyle = wb.createCellStyle();
//			cellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
			cell.setCellStyle(cellstyle);
		}

}
}
