package com.shietion.activemq.test;

import com.shietion.activemq.model.UserInfoModel;
import com.shietion.activemq.user.UserMq;

public class UserInfoTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserInfoModel user = new UserInfoModel(1,"杜建桥","男","15623893269","41251794@qq.com");
		UserMq.getUserMq().sendUserInfo(user);
		UserMq.getUserMq().getUserInfo();
		UserMq.getUserMq().close();
	}

}
