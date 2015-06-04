var UpmIndex = {};

/**
 * 加载拥有权限的项目
 * 
 * @param callbackFunc
 */
UpmIndex.loadMyPros = function(callbackFunc) {
	var loadMyProsURL = 'PackageController/loadAuthedPro.do?timestamp=' + UpmUtil.timestamp();
	$.getJSON(loadMyProsURL, function(data) {
		if (data.length > 0) {
			callbackFunc(data); // 回调渲染页面函数
		} else {
			UpmUtil.alertError('你暂时没有被授权的项目！');
		}
	});
};

/**
 * 加载项目所有发布包类型
 * 
 * @param id
 * @param callbackFunc
 */
UpmIndex.loadProTypes = function(id, callbackFunc) {
	var loadProTypesURL = 'PackageController/loadProedPackageType.do?timestamp=' + UpmUtil.timestamp();
	var params = {
		projectID : id
	}
	$.getJSON(loadProTypesURL, params, function(data) {
		if (data.length > 0) {
			callbackFunc(data);
		} else {
			UpmUtil.alertError('此项目没有升级包类型，请和数据库管理员联系！');
		}
	});
};

/**
 * 加载所有发布包类型
 * 
 * @param callbackFunc
 */
UpmIndex.loadAllTypes = function(cb) {
	var loadAllTypesURL = 'PackageController/loadAllPackages.do?timestamp=' + UpmUtil.timestamp();
	$.getJSON(loadAllTypesURL, function(data) {
		if (data.length > 0) {
			cb(data);
		} else {
			UpmUtil.alertError('数据库类型数据丢失，请和数据库管理员联系！');
		}
	});
};

/**
 * 封装一页数据(用于数据加载)
 * 
 * @param data
 */
UpmIndex.packPage = function(data) {
	var page = {};
	page.pageNow = data.pageNow;// 当前页
	page.totalCount = data.totalCount;// 总记录数
	page.totalPage = data.totalPage;// 总页数
	page.dataList = [];// 升级包数据
	var len = data.dataList.length;
	for (var i = 0; i < len; i++) {
		page.dataList.push({
			auth : data.dataList[i].auth,// 用户权限
			createTime : data.dataList[i].createTimeLabel,// 上传时间
			downloadLink : data.dataList[i].downloadLink,// 下载路径
			id : data.dataList[i].id,// 序列号
			mail : data.dataList[i].mail,// 用户邮件
			packageType : data.dataList[i].packageType,// 升级包类型
			typeName : data.dataList[i].typeName,// 升级包类型名称
			packgeSize : data.dataList[i].packgeSize,// 升级包大小
			projectID : data.dataList[i].projectID,// 项目ID
			packageName : data.dataList[i].packageName,// 升级包名称
			projectName : data.dataList[i].projectName,// 项目名称
			realName : data.dataList[i].realName,// 用户真实姓名
			downloadStatus : data.dataList[i].downloadStatus,// 下载状态
			status : data.dataList[i].status,// 升级包状态是否close
			tag : data.dataList[i].tag,// 版本标签
			userID : data.dataList[i].userID,// 用户ID
			userName : data.dataList[i].userName,// 用户名
			version : data.dataList[i].version,// 版本号
			chatCount : data.dataList[i].chatCount,// 评论记录统计
			downloadCount : data.dataList[i].downloadCount,// 下载统计
		});
	}
	return page;
};

/**
 * 加载数据
 * 
 * @param params
 *            {pageNow : pageNow, pageSize : pageSize, projectID : projectID,
 *            tag:tag, }
 * @param callbackFunc
 */
UpmIndex.loadPageData = function(params, callbackFunc) {
	var loadPageDataURL = 'PackageController/loadUpgradePackages.do?timestamp=' + UpmUtil.timestamp();
	$.getJSON(loadPageDataURL, params, function(data) {
		if (data.success) {
			if (data.dataList.length > 0) {// 判断是否有数据
				var page = UpmIndex.packPage(data);// 封装一页数据
				callbackFunc(page);// 回调渲染页面函数
			} else {
				UpmUtil.alertError('数据库中没有相关数据。');
				UpmUtil.ajaxLoaderRemove();
			}
		} else {
			UpmUtil.alertError(data.msg);
			UpmUtil.ajaxLoaderRemove();
		}
	});
};

/**
 * 新增
 * 
 * @param params
 * @param callbackFunc
 */
UpmIndex.addData = function(params, callbackFunc) {
	var addDataURL = 'PackageController/addUpradePackage.do?timestamp=' + UpmUtil.timestamp();
	$.post(addDataURL, params, function(data) {
		data = $.parseJSON(data);
		if (data.success) {
			callbackFunc();// 回调渲染函数
		} else {
			UpmUtil.alertError(data.msg == '' ? "发布包添加出错啦！" : data.msg);
		}
	});
};

/**
 * 删除
 * 
 * @param id
 * @param callbackFunc
 */
UpmIndex.deleteData = function(id, callbackFunc) {
	var param = {
		id : id
	};
	var deleteDataURL = 'PackageController/delUpradePackage.do?timestamp=' + UpmUtil.timestamp();
	$.getJSON(deleteDataURL, param, function(data) {
		if (data.success) {
			callbackFunc();// 回调渲染页面函数
		} else {
			UpmUtil.alertError('删除数据出错啦！');
		}
	});
};

/**
 * 加载一条数据
 * 
 * @param id
 * @param callbackFunc
 */
UpmIndex.loadData = function(id, callbackFunc) {
	var loadDataURL = 'PackageController/loadUpgradePackageByID.do?timestamp=' + UpmUtil.timestamp();
	var params = {
		id : id
	};
	$.getJSON(loadDataURL, params, function(data) {
		if (data.success) {
			var obj = data.obj;
			callbackFunc(obj);// 回调渲染页面函数
		} else {
			UpmUtil.alertError('加载数据出错啦！')
		}
	});
};

/**
 * 编辑
 * 
 * @param params
 * @param callbackFunc
 */
UpmIndex.editData = function(params, callbackFunc) {
	var editDataURL = 'PackageController/updateUpradePackage.do?timestamp=' + UpmUtil.timestamp();
	$.post(editDataURL, params, function(data) {
		data = $.parseJSON(data);
		if (data.success) {
			callbackFunc();// 回调渲染页面函数
		} else {
			UpmUtil.alertError(data.msg == '' ? "修改数据出错啦！" : data.msg);
		}
	});
};

/**
 * 加载chat记录
 * 
 * @param params
 * @param callbackFunc
 */
UpmIndex.loadChatData = function(pcID, callbackFunc) {
	var params = {
		packageID : pcID
	};
	var loadChatDataURL = 'ChatController/loadPackagedChats.do?timestamp=' + UpmUtil.timestamp();
	$.getJSON(loadChatDataURL, params, function(data) {
		if (data.length > 0) {
			callbackFunc(data);// 回调渲染页面函数
		}
	});
};

/**
 * 添加chat记录
 * 
 * @param params
 * @param callbackFunc
 */
UpmIndex.addChatData = function(params, callbackFunc) {
	var addChatDataURL = 'ChatController/addChat.do?timestamp=' + UpmUtil.timestamp();
	$.post(addChatDataURL, params, function(data) {
		data = $.parseJSON(data);
		if (data.success) {
			callbackFunc();// 回调渲染页面函数
		} else {
			UpmUtil.alertError(data.msg == '' ? "添加评论数据出错啦！" : data.msg);
		}
	});
};

/**
 * 删除chat记录
 * 
 * @param params
 * @param callbackFunc
 */
UpmIndex.delChatData = function(id, callbackFunc) {
	var delChatDataURL = 'ChatController/delChat.do?timestamp=' + UpmUtil.timestamp();
	$.getJSON(delChatDataURL, {
		chatID : id
	}, function(data) {
		if (data.success) {
			callbackFunc();// 回调渲染页面函数
		} else {
			UpmUtil.alertError("删除评论数据出错啦！");
		}
	});
};

/**
 * 发送邮件
 * 
 * @param params
 * @param callbackFunc
 */
UpmIndex.mail = function(params, callbackFunc) {
	var mailURL = 'ChatController/mailToUser.do?timestamp=' + UpmUtil.timestamp();
	$.post(mailURL, params, function(data) {
		data = $.parseJSON(data);
		if (data.success) {
			if (data.status == 200) {
				callbackFunc(); // 回调渲染页面函数
			}
		} else {
			UpmUtil.alertError(data.msg == '' ? "发送邮件出错啦！" : data.msg);
		}
	});
};

/**
 * 修改发布包状态
 */
UpmIndex.editDataStatus = function(params, callbackFunc) {
	var editDataStatusURL = 'PackageController/updatePackageStatus.do?timestamp=' + UpmUtil.timestamp();
	$.post(editDataStatusURL, params, function(data) {
		data = $.parseJSON(data);
		if (data.success) {
			callbackFunc();// 回调渲染页面函数
		} else {
			UpmUtil.alertError(data.msg == '' ? "修改发布包状态出错啦！" : data.msg);
		}
	});
};

/**
 * 获取数据库最后一次提交SVN版本记录
 */
UpmIndex.loadLastSVNVersion = function(params, callbackFunc) {
	var loadLastSVNVersionURL = 'PackageController/loadVersion.do?timestamp=' + UpmUtil.timestamp();
	$.post(loadLastSVNVersionURL, params, function(data) {
		data = $.parseJSON(data);
		if (data.length > 0) {
			var str = data[0];
			var beginIndex = str.indexOf('[');
			var endIndex = str.indexOf(']');
			var latestRevision;
			if (beginIndex == -1 || endIndex == -1) {
				latestRevision = '';
			} else {
				latestRevision = str.slice(beginIndex + 1, endIndex);// 截取svn版本号
			}
			callbackFunc(latestRevision);// 回调渲染页面函数
		}
	});
};

/**
 * 获取数据库最后一次提交项目版本记录
 */
UpmIndex.loadLastVersion = function(params, callbackFunc) {
	var loadLastVersionURL = 'PackageController/loadVersion.do?timestamp=' + UpmUtil.timestamp();
	$.post(loadLastVersionURL, params, function(data) {
		data = $.parseJSON(data);
		if (data.length > 0) {
			var str = data[0];
			var version = str.indexOf('-') == -1 ? '' : str;
			if (str != '') {
				var versionPre = version.slice(0, version.indexOf('-'));// 前缀
				var versionAft = version.slice(version.indexOf('-'), version.length);// 后缀
				versionPre = UpmUtil.proVersionOverlap(versionPre, 4);
				version = versionPre + versionAft;// 拼接 1.0.0.1-[8671]
			}
			callbackFunc(version);// 回调渲染页面函数
		}
	});
};

/**
 * 一键打包
 */
UpmIndex.oneKeyPack = function(params, callbackFunc) {
	var oneKeyPackURL = 'PackageController/oneKeyPack.do?timestamp=' + UpmUtil.timestamp();
	$.post(oneKeyPackURL, params, function(data) {
		data = $.parseJSON(data);
		callbackFunc(data);// 回调渲染页面函数
	});
};

/**
 * 测试通过
 */
UpmIndex.testPass = function(params, callbackFunc) {
	var testPassURL = 'PackageController/testPass.do?timestamp=' + UpmUtil.timestamp();
	$.getJSON(testPassURL, params, function(data) {
		if (data.success) {
			callbackFunc();// 回调渲染页面函数
		} else {
			UpmUtil.alertError(data.msg == '' ? "废弃功能出错啦！" : data.msg);
		}
	});
};

/**
 * 废弃
 */
UpmIndex.discard = function(params, callbackFunc) {
	var discardURL = 'PackageController/discard.do?timestamp=' + UpmUtil.timestamp();
	$.getJSON(discardURL, params, function(data) {
		if (data.success) {
			callbackFunc();// 回调渲染页面函数
		} else {
			UpmUtil.alertError(data.msg == '' ? "测试通过功能出错啦！" : data.msg);
		}
	});
};

/**
 * 添加下载记录
 */
UpmIndex.addDownload = function(packageID) {
	var addDownloadURL = 'DownloadController/addDownload.do?timestamp=' + UpmUtil.timestamp();
	var params = {
		packageID : packageID
	}
	$.post(addDownloadURL, params, function(data) {
	});
};

/**
 * 查看打包日志
 */
UpmIndex.loadTarLog = function(packageID,callbackFunc) {
	var loadTarLogURL = 'PackageController/loadTarLog.do?timestamp=' + UpmUtil.timestamp();
	var params = {
		id : packageID
	}
	$.getJSON(loadTarLogURL, params, function(data) {
		if(data.tarLog){
			callbackFunc(data.tarLog);
		}else{
			callbackFunc('没有打包日志！');
		}
	});
};
