package puresport.mvc.sport_item;

import java.util.List;

import org.apache.log4j.Logger;

import com.jfinal.aop.Enhancer;

import com.platform.mvc.base.BaseService;

public class Sport_ItemService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(Sport_ItemService.class);
	
	public static final Sport_ItemService service = Enhancer.enhance(Sport_ItemService.class);
	
	public Sport_Item SelectById(Integer id){
		
		Sport_Item mdl = Sport_Item.dao.findFirst("select * from sport_item where id=?", id);
		return mdl;
	}
	public List<Sport_Item> SelectByItemId(Integer id){
		
		List<Sport_Item> mdl = Sport_Item.dao.find("select * from sport_item where parentid=?", id);
		return mdl;
	}
}
