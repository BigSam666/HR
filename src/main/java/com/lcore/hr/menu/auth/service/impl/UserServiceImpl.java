package com.lcore.hr.menu.auth.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lcore.hr.core.entity.Root;
import com.lcore.hr.core.entity.User;
import com.lcore.hr.menu.auth.service.UserService;
import com.lcore.hr.menu.base.service.impl.BaseServiceImpl;

@Service("userService")
@Transactional
public class UserServiceImpl extends BaseServiceImpl implements UserService {

	@Override
	public List<Root> getUserList(int offset, int limit, String sort,
			String order, String key) {

		String condition = " 1=1 ";
		if (key != null && !"".equals(key)) {
			condition += " and obj.userName like '%" + key + "%'";
		}
		if (null != sort && !"".equals(sort))
			condition += " order by " + " obj." + sort + "" + "  " + order;
		return this.getPagedObjListWithCondition(User.class.getName(),
				condition, offset, limit);
	}

	@Override
	public void addUser(User user) throws Exception {
		this.save(user);

	}

	@Override
	public void deleteUser(List<String> ids) throws Exception {
		for (String id : ids) {
			if (id != null && !"".equals(id.trim()))
				this.delete(User.class.getName(), id);
		}
	}

	@Override
	public void updateUser(User user) throws Exception {
		this.update(user);
	}

}
