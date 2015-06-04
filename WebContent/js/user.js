var UserIndex = {};
/**
 * 获取登录用户信息
 */
UserIndex.loadLoginUser = function(toDo) {
	// 获取登录用户
	UpmUser.loadLoginUser(function(user) {
		$('#userName_span').html(user.realName);
		toDo(user.role);// 回调执行函数
	});
};

/**
 * 页面注销绑定事件
 */
UserIndex.BindClickLogout = function() {
	// 取消上次事件的叠加绑定 注销事件绑定
	$('#logoutSureBtn').unbind('click').click(function() {
		UpmUser.logout(); // 注销
	});
};

UserIndex.BindClickChangePwd = function() {
	$('#updatePwdBtn').unbind('click').click(function() {
		UpmUtil.newModal('changePwdModel'); // 加载新增发布包对话框
		$('#changePwd input[name=newPassword]').val('');
		$('#changePwd input[name=reNewPassword]').val('');
		$('#saveNewPwd').unbind('click').click(function() {
			var newPwd = $('#changePwd input[name=newPassword]').val();
			var reNewPwd = $('#changePwd input[name=reNewPassword]').val();
			if (vali(newPwd, reNewPwd)) {
				var parmas = {
					newPwd : reNewPwd || ''
				};
				// 修改密码
				UpmUser.changePwd(parmas, function() {
					// 修改成功
				});
			}
		});
	});

	/**
	 * 密码文本校验
	 */
	function vali(newPwd, reNewPwd) {
		if (newPwd == '' || reNewPwd == '') {
			UpmUtil.alertError("所填项不能为空!");
			return false;
		} else if (newPwd != reNewPwd) {
			UpmUtil.alertError("两次密码填写不一致!");
			return false;
		} else {
			return true;
		}
	}

};

/**
 * 用户加载
 */
UserIndex.loadNormalUser = function() {
	UpmUtil.ajaxLoader();// 显示加载图片
	// 普通用户数据
	UpmUser.loadNormalUser(function(data) {
		var content = [];
		$.each(data, function(i, d) {
			content.push('<tr>');
			content.push('<td>' + (i + 1) + '</td>');
			content.push('<td>' + d.userName + '</td>');
			content.push('<td>' + d.mail + '</td>');
			content.push('<td>' + d.realName + '</td>');
			content.push('<td style="color:green;">' + d.role_label + '</td>');
			content.push('<td style="color:green;">' + d.auth_label + '</td>');
			content.push('<td>');
			content.push('<a class="btn btn-success btn-inverse btn-mini" style="margin-bottom:5px" href="#authModal" onclick="UserIndex.BindClickAuthCheck([' + d.auth + '],' + d.id + ');" data-toggle="modal"><i class="icon-user-md"></i>授权</a>'); // 授权按钮
			content.push('<a class="btn btn-danger btn-inverse btn-mini" style="margin-bottom:5px" href="#deleteComfirmModal" onclick="UserIndex.BindClickDelete(' + d.id + ');"  data-toggle="modal"><i class="icon-cut"></i>删除</a>'); // 删除按钮
			content.push('</td>');
			content.push('</tr>');
		});
		$('#tbody').html(content.join(''));
		UpmUtil.ajaxLoaderRemove();// 移除加载图片
	});
};

/**
 * 删除用户
 */
UserIndex.BindClickDelete = function(id) {
	// 取消上次事件的叠加绑定
	$('#deleteSureBtn').unbind('click').click(function() {
		UpmUser.delNormalUser(id, function() {
			UserIndex.loadNormalUser();// 加载用户
		});
	});
};

/**
 * 用户授权项目
 */
UserIndex.BindClickAuthCheck = function(auth, id) {
	UpmUtil.newModal('authModal');// 项目权限选择框
	loadAuthedPros(auth);// 加载权限项目
	$('#saveAuthBtn').unbind('click').bind('click', function() {
		authed(id);// 授权
	});

	/**
	 * 加载权限项目,并选中
	 */
	function loadAuthedPros(auth) {
		UpmUser.loadAuthPros(function(data) {
			var labels = [];
			$.each(data, function(i, d) {
				var label = '<label class="checkbox inline"><input type="checkbox" value="' + d.id + '">' + d.projectName + '</label>';
				labels.push(label);
			});
			$('#authModal .controls').html(labels.join(''));
			// 选中项目
			for (var i = 0; i < auth.length; i++) {
				$('#authModal input[value=' + auth[i] + ']').attr('checked', true);
			}
		});
	}

	/**
	 * 授权
	 */
	function authed(id) {
		var auth = '';
		// 选中已被授权项目，拼接auth
		$('#authModal input[type=checkbox]:checked').each(function(i, d) {
			auth += $(this).val() + ',';
		});
		auth = auth.slice(0, -1);// 去除最后一个,
		var params = {
			id : id,
			auth : auth
		};
		// 授权用户
		UpmUser.authNormalUser(params, function() {
			UserIndex.loadNormalUser();// 重新加载用户
		});
	}
};

/**
 * 添加用户绑定
 */
UserIndex.BingClickAddUser = function() {
	$('#addNewUserBtn').unbind('click').click(function() {
		UpmUtil.newModal('addUserModal'); // 加载新增用户对话框
		loadAllPros();
		$('#addUserModal #saveNewBtn').unbind('click').click(function() {
			var userName = $('#addNewUser input[name=userName]').val();
			var realName = $('#addNewUser input[name=realName]').val();
			var mail = $('#addNewUser input[name=mail]').val();
			var role = $('#addNewUser select[name=role]').children('option:selected').val();
			if (UpmUtil.isEmpty(userName) || UpmUtil.isEmpty(realName) || UpmUtil.mailPattern(mail) == false) {
				UpmUtil.alertError('填写内容存在不匹配项！');
			} else {
				var auth = '';
				// 选中已被授权项目，拼接auth
				$('#addNewUser input[type=checkbox]:checked').each(function(i, d) {
					auth += $(this).val() + ',';
				});
				if (auth != '') {
					auth = auth.slice(0, -1);// 去除最后一个,
				}
				var params = {
					userName : userName,
					realName : realName,
					mail : mail,
					auth : auth,
					role : role
				};
				saveUser(params);// 保存用户
			}
		});
	});

	/**
	 * 加载所有项目
	 */
	function loadAllPros() {
		var proHtml = [];
		var pros = UpmUser.loadAllPros();// 所有项目模板
		for ( var key in pros) {
			proHtml.push('<label class="checkbox inline"><input type="checkbox" value="' + key + '">' + pros[key] + '</label>');
		}
		$('#allPro').html(proHtml.join(''));
	}

	/**
	 * 添加用户
	 */
	function saveUser(params) {
		UpmUser.addNormalUser(params, function() {
			UserIndex.loadNormalUser();// 加载用户
		});
	}
};

$(function() {
	// 加载登录用户信息,加载成功执行操作
	UserIndex.loadLoginUser(function(role) {
		UpmUtil.appendComfirmModalHtml();// 加载判断框html
		UserIndex.BindClickLogout();// 注销
		UserIndex.loadNormalUser();// 普通用户信息加载
		UserIndex.BindClickChangePwd();// 密码修改
		if (role == 10) {
			UserIndex.BingClickAddUser();
		}
	});
});