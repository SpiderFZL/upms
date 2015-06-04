var UpmUtil = {};
/**
 * 获取项目根路径
 */
UpmUtil.getRootPath = function() {
	var curWwwPath = window.document.location.href;
	var pathName = window.document.location.pathname;
	var pos = curWwwPath.indexOf(pathName);
	var localhostPaht = curWwwPath.substring(0, pos);
	var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
	return (localhostPaht + projectName + '/');
};

/**
 * 判断是否为空
 */
UpmUtil.isEmpty = function(val) {
	return !val.length > 0 || val == '';
};

/**
 * 判断不为空
 */
UpmUtil.isNotEmpty = function(val) {
	return val.length > 0 || val != '';
};

/**
 * 获取时间戳
 */
UpmUtil.timestamp = function() {
	return new Date().getTime();
};

/**
 * 版本后缀补充
 */
UpmUtil.versionExtenstion = function() {
	var d = new Date();
	var year = d.getFullYear();
	var month = d.getMonth() + 1;
	if (month < 10) {
		month = '0' + month;
	}
	var day = d.getDate();
	if (day < 10) {
		day = '0' + day;
	}
	return ' Build' + year + month + day;

};

/**
 * 版本号匹配
 */
UpmUtil.versionPattern = function(param) {
	// var re = /([0-9]{1}.){2,}[0-9]{1}$/; // 1.0.0
	// return re.test(param);
	return param.length > 0 || param != '';
};

/**
 * 邮箱匹配
 */
UpmUtil.mailPattern = function(param) {
	var re = /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/;
	return re.test(param);
};

/**
 * 加载图片
 */
UpmUtil.ajaxLoader = function() {
	var $ajaxLoader = $('#ajaxLoader');
	if ($ajaxLoader.html()) {
		$ajaxLoader.remove();
	} else {
		var ajaxLoaderTemp = UpmTemp.ajaxLoaderTemp();
		$('body').append(ajaxLoaderTemp);
	}
};

/**
 * 图片移除
 */
UpmUtil.ajaxLoaderRemove = function() {
	var $ajaxLoader = $('#ajaxLoader');
	$ajaxLoader.remove();
};

/**
 * alert弹出框
 */
UpmUtil.alertError = function(msg) {
	bootbox.dialog(msg, [ {
		label : "确定",
		"class" : "btn-danger"
	} ]);
};

/**
 * confirm弹出框
 */
UpmUtil.confirm = function(msg, toDo) {
	bootbox.confirm(msg, function(result) {
		if (result === true) {
			toDo();
		}
	});
};

/**
 * 判断对话框
 */
UpmUtil.appendComfirmModalHtml = function() {
	var logoutComfirmModalHtml = UpmTemp.logoutComfirmModalTemp();// 注销判断框模板
	var deleteComfirmModalHtml = UpmTemp.deleteComfirmModalTemp();// 删除判断框模板
	var passComfirmModalHtml = UpmTemp.passComfirmModalTemp();// 测试通过判断框模板
	var discardComfirmModalHtml = UpmTemp.discardComfirmModalTemp();// 测试通过判断框模板
	$('body').append(logoutComfirmModalHtml);
	$('body').append(deleteComfirmModalHtml);
	$('body').append(passComfirmModalHtml);
	$('body').append(discardComfirmModalHtml);
};

/**
 * 刷新对话框 logoutComfirmModal
 */
UpmUtil.newModal = function(modalElementID) {
	var $modal = $('#' + modalElementID);
	if ($modal.html()) {
		$modal.remove();// 移除
	}
	$('body').append(UpmTemp[modalElementID + 'Temp']());
};

/**
 * 项目版本号叠加
 */
UpmUtil.proVersionOverlap = function(version, offset) {
	var re = /([0-9]+.){3,}[0-9]+$/; // 1.0.0.1
	var ifPattern = re.test(version);
	if (!ifPattern) {
		return '';
	}
	var verArr = version.split('.');
	verArr[offset - 1] = parseInt(verArr[offset - 1]) + 1;
	return verArr.join('.');

};
