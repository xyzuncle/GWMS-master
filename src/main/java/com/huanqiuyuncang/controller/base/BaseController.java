package com.huanqiuyuncang.controller.base;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.huanqiuyuncang.dao.checktable.CheckTableDAO;
import com.huanqiuyuncang.entity.system.User;
import com.huanqiuyuncang.service.system.checktable.CheckTableInterface;
import com.huanqiuyuncang.service.system.checktable.impl.CheckTableService;
import com.huanqiuyuncang.service.system.role.RoleManager;
import com.huanqiuyuncang.service.system.user.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.util.Logger;
import com.huanqiuyuncang.util.PageData;
import com.huanqiuyuncang.util.UuidUtil;

/**
 * @author
 * 修改时间：2015、12、11
 */
public class BaseController {
	
	protected Logger logger = Logger.getLogger(this.getClass());

	private static final long serialVersionUID = 6357869213649815390L;

    @Autowired
    protected  CheckTableInterface checkTableService;


    @Autowired
    protected UserManager userService;
    @Autowired
    protected RoleManager roleService;
	
	/** new PageData对象
	 * @return
	 */
	public PageData getPageData(){
		return new PageData(this.getRequest());
	}
	
	/**得到ModelAndView
	 * @return
	 */
	public ModelAndView getModelAndView(){
		return new ModelAndView();
	}
	
	/**得到request对象
	 * @return
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}

	/**得到32位的uuid
	 * @return
	 */
	public String get32UUID(){
		return UuidUtil.get32UUID();
	}
	
	/**得到分页列表的信息
	 * @return
	 */
	public Page getPage(){
		return new Page();
	}
	
	public static void logBefore(Logger logger, String interfaceName){
		logger.info("");
		logger.info("start");
		logger.info(interfaceName);
	}
	
	public static void logAfter(Logger logger){
		logger.info("end");
		logger.info("");
	}

    protected Integer checkTable(String tableName,String idField,String value) {
        PageData pd = new PageData();
        pd.put("idfield",idField);
        pd.put("id",value);
        return checkTableService.sumCheckTable(tableName,pd);
    }

    protected Integer checkTable(String tableName,String idField,String[] values) {
        PageData pd = new PageData();
        pd.put("idfield",idField);
        Integer sum = 0;
        for (String value:values) {
            pd.put("id",value);
            Integer count = checkTableService.sumCheckTable(tableName, pd);
            sum += count;
        }
        return sum;

    }


    protected String gerRolename(String USERNAME) throws Exception {
        PageData argspd = new PageData();
        argspd.put("USERNAME",USERNAME);
        PageData userinfo = userService.findByUsername(argspd);
        String USER_ID = userinfo.getString("USER_ID");
        User user = userService.getUserAndRoleById(USER_ID);
        return user.getRole().getROLE_NAME();
    }

}
