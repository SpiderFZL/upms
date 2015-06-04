var UpmUser = {};

/**
 * 加载登录用户信息
 * 
 * @param callbackFunc
 */
UpmUser.loadLoginUser = function(callbackFunc) {
	var loadLoginUserURL = 'UserController/loadLoginedUser.do?timestamp=' + UpmUtil.timestamp();
	$.getJSON(loadLoginUserURL, function(data) {
		if (data.success) {
			var user = data.obj;
			callbackFunc(user);// 用户信息回调函数
		} else {
			window.location.href = "";
		}
	});
};

/**
 * 加载所有用户信息
 */
UpmUser.loadAllUser = function(callbackFunc) {
	var loadAllUserURL = 'UserController/loadAllUsers.do?timestamp=' + UpmUtil.timestamp();
	$.getJSON(loadAllUserURL, function(data) {
		if (data.length > 0) {
			callbackFunc(data);// 用户信息回调函数
		}
	});
};

/**
 * 加载所有的项目
 * 
 */
UpmUser.loadAllPros = function() {
	var pros = [];
	var loadAllProsURL = 'PackageController/loadAllPro.do?timestamp=' + UpmUtil.timestamp();
	$.ajax({
		url : loadAllProsURL,
		dataType : 'json',
		async : false,
		success : function(data) {
			var len = data.length;
			for (var i = 0; i < len; i++) {
				var key = data[i].id;
				var value = data[i].projectName;
				pros[key] = value;// 设置key为ID val为projectName
			}
		}
	});
	return pros;
};

/**
 * 加载权限项目
 */
UpmUser.loadAuthPros = function(callbackFunc) {
	var loadMyProsURL = 'PackageController/loadAuthedPro.do?timestamp=' + UpmUtil.timestamp();
	$.getJSON(loadMyProsURL, function(data) {
		if (data.length > 0) {
			callbackFunc(data); // 回调渲染页面函数
		}
	});
};

/**
 * 转换项目id->projectName
 */
UpmUser.converAuthLabel = function(auth, pros) {
	if (auth == '') {
		return '没有权限项目！';
	}
	var auths = auth.split(',');
	var auths_label = '';
	for (var i = 0; i < auths.length; i++) {
		auths_label += pros[auths[i]] + ' ';
	}
	return auths_label;

};

/**
 * 用户转换
 */
UpmUser.converUsers = function(data) {
	var users = [];
	var Len = data.length;
	var pros = UpmUser.loadAllPros();// 所有项目模板
	for (var i = 0; i < Len; i++) {
		var id = data[i].id;
		var userName = data[i].userName;
		var realName = data[i].realName;
		var mail = data[i].mail;
		var auth = data[i].auth;
		var role = data[i].role;
		var role_label = role == 10 ? "最高管理员" : role == 1 ? "技术" : role == 2 ? "研发" : role == 3 ? "测试" : "";
		var auth_label = UpmUser.converAuthLabel(auth, pros);// 权限项目文本转换
		users.push({
			id : id,
			userName : userName,
			realName : realName,
			mail : mail,
			role_label : role_label,
			auth : auth,
			auth_label : auth_label == undefined ? '没有授权项目' : auth_label
		});
	}
	return users;
};

/**
 * 加载用户
 */
UpmUser.loadNormalUser = function(callbackFunc) {
	var loadNormalUserURL = 'UserController/loadNormalUser.do?timestamp=' + UpmUtil.timestamp();
	$.getJSON(loadNormalUserURL, function(data) {
		if (data != null) {
			if (data.length > 0) {
				var users = UpmUser.converUsers(data);
				callbackFunc(users);// 用户信息回调函数
			} else {
				UpmUtil.ajaxLoaderRemove();
				callbackFunc([]);// 用户信息回调函数
			}
		} else {
			UpmUtil.alertError("你没有用户管理的任何权限，无法查看用户！");
			UpmUtil.ajaxLoaderRemove();
		}

	});
};

/**
 * 删除用户
 */
UpmUser.delNormalUser = function(id, callbackFunc) {
	var delNormalUserURL = 'UserController/delNormalUser.do?timestamp=' + UpmUtil.timestamp();
	$.getJSON(delNormalUserURL, {
		id : id
	}, function(data) {
		if (data.success) {
			callbackFunc();// 回调渲染页面函数
		} else {
			UpmUtil.alertError('删除数据出错啦！' + data.msg);
		}
	});
};

/**
 * 授权用户
 */
UpmUser.authNormalUser = function(params, callbackFunc) {
	var authNormalUserURL = 'UserController/authNormalUser.do?timestamp=' + UpmUtil.timestamp();
	$.post(authNormalUserURL, params, function(data) {
		data = $.parseJSON(data);
		if (data.success) {
			callbackFunc();// 回调渲染页面函数
		} else {
			UpmUtil.alertError('授权出错啦！' + data.msg);
		}
	});
};

/**
 * 添加用户
 */
UpmUser.addNormalUser = function(params, callbackFunc) {
	var addNormalUserURL = 'UserController/addNormalUser.do?timestamp=' + UpmUtil.timestamp();
	$.post(addNormalUserURL, params, function(data) {
		data = $.parseJSON(data);
		if (data.success) {
			callbackFunc();// 回调渲染页面函数
		} else {
			UpmUtil.alertError('添加用户出错啦！' + data.msg);
		}
	});
};
