package puresport.mvc.sport_item;

import com.platform.constant.ConstantRender;
import com.platform.mvc.base.BaseController;
import com.platform.mvc.base.BaseModel;

import org.apache.log4j.Logger;
import com.jfinal.aop.Before;

import puresport.constant.ConstantInitMy;


/**
 * XXX 管理	
 * 描述：
 * 
 * /jf/puresport/sport_Item
 * /jf/puresport/sport_Item/save
 * /jf/puresport/sport_Item/edit
 * /jf/puresport/sport_Item/update
 * /jf/puresport/sport_Item/view
 * /jf/puresport/sport_Item/delete
 * /puresport/sport_Item/add.html
 * 
 */
//@Controller(controllerKey = "/jf/puresport/sport_Item")
public class Sport_ItemController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(Sport_ItemController.class);

	public static final String pthc = "/jf/puresport/sport_Item/";
	public static final String pthv = "/puresport/sport_Item/";

	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, Sport_Item.sqlId_splitPage_from);
		renderWithPath(pthv+"list.html");
	}
	
	/**
	 * 保存
	 */
	@Before(Sport_ItemValidator.class)
	public void save() {
		Sport_Item sport_Item = getModel(Sport_Item.class);
		//other set 
		
		//sport_Item.save();		//guiid
		sport_Item.saveGenIntId();	//serial int id
		renderWithPath(pthv+"add.html");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		//Sport_Item sport_Item = Sport_Item.dao.findById(getPara());	//guuid
		Sport_Item sport_Item = Sport_ItemService.service.SelectById(getParaToInt());		//serial int id
		setAttr("sport_Item", sport_Item);
		renderWithPath(pthv+"update.html");

	}
	
	/**
	 * 更新
	 */
	@Before(Sport_ItemValidator.class)
	public void update() {
		getModel(Sport_Item.class).update();
		redirect(pthc);
	}

	/**
	 * 查看
	 */
	public void view() {
		//Sport_Item sport_Item = Sport_Item.dao.findById(getPara());	//guuid
		Sport_Item sport_Item = Sport_ItemService.service.SelectById(getParaToInt());		//serial int id
		setAttr("sport_Item", sport_Item);
		renderWithPath(pthv+"view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		//Sport_ItemService.service.delete("sport_item", getPara() == null ? ids : getPara());	//guuid
		Sport_ItemService.service.deleteById("sport_item", getPara() == null ? ids : getPara());	//serial int id
		redirect(pthc);
	}
	
	public void setViewPath(){
		setAttr(ConstantRender.PATH_CTL_NAME, pthc);
		setAttr(ConstantRender.PATH_VIEW_NAME, pthv);
	}
	
}
