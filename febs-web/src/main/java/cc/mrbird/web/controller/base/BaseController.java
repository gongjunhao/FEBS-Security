package cc.mrbird.web.controller.base;

import cc.mrbird.security.domain.FebsSocialUserDetails;
import cc.mrbird.security.domain.FebsUserDetails;
import cc.mrbird.system.domain.MyUser;
import com.github.pagehelper.PageInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashMap;
import java.util.Map;

public class BaseController {

	protected Map<String, Object> getDataTable(PageInfo<?> pageInfo) {
		Map<String, Object> rspData = new HashMap<>();
		rspData.put("rows", pageInfo.getList());
		rspData.put("total", pageInfo.getTotal());
		return rspData;
	}

	protected MyUser getCurrentUser(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		MyUser user = new MyUser();
		if(principal instanceof FebsUserDetails){
			FebsUserDetails userDetails = (FebsUserDetails) principal;
			user.setUserId(userDetails.getUserId());
			user.setPassword(userDetails.getPassword());
			user.setUsername(userDetails.getUsername());
		}else{
			FebsSocialUserDetails userDetails = (FebsSocialUserDetails) principal;
			user.setUserId(userDetails.getUsersId());
			user.setPassword(userDetails.getPassword());
			user.setUsername(userDetails.getUsername());
		}
		return user;
	}
}
