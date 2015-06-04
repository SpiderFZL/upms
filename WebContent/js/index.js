var Index = {};

/**
 * 获取登录用户信息
 */
Index.loadLoginUser = function(callbackToDo) {
	// 获取登录用户
	UpmUser.loadLoginUser(function(user) {
		$('#userName_span').html(user.realName);
		// 最高权限管理员拥有用户管理
		if (user.role == 10) {
			$('#user_drop').prepend(' <li><a href="user-index.jsp"><i class="icon-user"></i>			用户管理</a></li>');
		}
		callbackToDo(user.role);// 回调执行函数
	});
};

/**
 * 页面分页查询绑定事件
 */
Index.BindClickPageSearch = function() {
	// 用户项目事件绑定
	$('#myPros>li').each(function() {
		$(this).unbind('click').click(function() {
			$(this).find('input[name=proID]').attr('checked', true);
			var pro = $(this).find('span').text();
			$('#consoleName').text(pro);
			Index.loadPageData();// 加载数据
		});
	});

	// 版本信息事件绑定
	$('#tags>li').each(function() {
		$(this).unbind('click').click(function() {
			$(this).find('input[name=tagID]').attr('checked', true);
			$('#tags>li').find('a').removeClass('label label-success');
			$(this).find('a').addClass('label label-success');
			Index.loadPageData();// 加载数据
		});
	});

	// 类型信息事件绑定
	$('#ptypes>li').each(function() {
		$(this).unbind('click').click(function() {
			$(this).find('input[name=ptypeID]').attr('checked', true);
			$('#ptypes>li').find('a').removeClass('label label-success');
			$(this).find('a').addClass('label label-success');
			Index.loadPageData();// 加载数据
		});
	});
	// 分页大小
	$('#pageSizeSelect').unbind('change').bind('change', function() {
		Index.loadPageData();// 加载数据
	});
};

/**
 * 加载用户拥有权限的项目
 */
Index.loadMyAuthPros = function() {
	UpmIndex.loadMyPros(function(pros) {
		var myPros = [];
		$.each(pros, function(i, d) {
			var proLi = '<li><input type="radio" name="proID" value="' + d.id + '" style="display: none;" /><a href="javascript:;"><i class="icon-desktop"></i><span>' + d.projectName + '</span></a></li>';
			myPros.push(proLi);
		});
		$('#myPros').html(myPros.join(''));
		Index.BindClickPageSearch();// 分页查询事件绑定
	});
};

/**
 * 加载所有类型
 */
Index.loadAllTypes = function() {
	UpmIndex.loadAllTypes(function(ptypes) {
		var types = [ '<li style="line-height: 30px; float:left; margin-left: 15px; font-size:16px;"><input type="radio" name="ptypeID" value="" style="display: none;" /><a href="javascript:;" class="label label-success">全部</a></li>' ];
		$.each(ptypes, function(i, d) {
			var typeLi = '<li style="line-height: 30px; float:left; margin-left: 15px; font-size:16px;"><input type="radio" name="ptypeID" value="' + d.id + '" style="display: none;" /><a href="javascript:;">' + d.typeName + '</a></li>';
			types.push(typeLi);
		});
		$('#ptypes').html(types.join(''));
		Index.BindClickPageSearch();// 分页查询事件绑定
	});
};
/**
 * 分页组件(用于加载数据是分页组件计算显示)
 * 
 * @param totalPage
 * @param pageNow
 */
Index.paginationComponent = function(totalPage, pageNow) {
	$('#pagination li').remove();
	// 判断是否有分页
	if (totalPage > 0) {
		$('#pagination ul').append('<li><a href="javascript:;" onclick="Index.loadPageData(1)">«</a></li>');// 第一页
		$('#pagination ul').append('<li><a href="javascript:;" onclick="Index.loadPageData(' + totalPage + ')">»</a></li>');// 最后一页
		for (var i = 1; i <= totalPage; i++) {
			if (i == pageNow) {
				$('#pagination li:last').before('<li class="active"><a href="javascript:;" onclick="Index.loadPageData(' + i + ')">' + i + '</a></li>');
			} else {
				$('#pagination li:last').before('<li><a href="javascript:;" onclick="Index.loadPageData(' + i + ')">' + i + '</a></li>');
			}
		}
	}

};

/**
 * 分页当前页码
 */
Index.pageCurr = function() {
	var pCurr = '1';
	$('#pagination li').each(function(i) {
		if ($(this).hasClass('active')) {
			pCurr = $(this).find('a').text();
		}
	});
	return pCurr;
};

/**
 * 加载数据
 */
Index.loadPageData = function(pageNow) {
	UpmUtil.ajaxLoader();// 显示加载图片
	var projectID = $('#myPros input[name=proID]:checked').val();
	var tag = $('#tags input[name=tagID]:checked').val();
	var pType = $('#ptypes input[name=ptypeID]:checked').val();
	var pageSize = $('#pageSizeSelect').children('option:selected').val();
	var params = {
		pageNow : pageNow ? pageNow : '1',
		pageSize : pageSize ? pageSize : '10',
		projectID : projectID ? projectID : $('#myPros input[name=proID]:eq(0)').val(),
		tag : tag ? tag : '',
		pType : pType ? pType : ''
	};
	// 获取登录用户
	UpmUser.loadLoginUser(function(user) {
		load(params, user.role);// 加载
	});

	/**
	 * 加载
	 */
	function load(params, role) {
		// 初始化数据
		UpmIndex.loadPageData(params, function(page) {
			var content = [];
			var data = page.dataList;
			$.each(data, function(i, d) {
				content.push('<tr>');
				content.push('<input type="hidden" name= "id" value="' + d.id + '"/>');// 放入ID序列
				content.push('<td>' + (i + 1) + '</td>');
				// content.push('<td>' + d.projectName + '</td>');
				content.push('<td>' + d.typeName + '</td>');
				content.push('<td>' + d.tag + '</td>');
				content.push('<td>' + d.version + '</td>');
				content.push('<td>' + d.packgeSize + '</td>');
				content.push('<td>' + d.realName + '</td>');
				content.push('<td>' + d.createTime + '</td>');
				content.push('<td>');
				// 测试人员 废弃功能
				if (role == 3 && d.tag != 'discard') {
					content.push('<a class="btn btn-danger btn-inverse btn-mini" name="discardBtn" style="margin-bottom:5px" href="#discardComfirmModal" onclick="Index.BindClickDiscard(' + d.id + ');"  data-toggle="modal">废弃</a>'); // 废弃按钮
				}
				// 测试人员通过beta包
				if (role == 3 && d.tag == 'beta') {
					content.push('<a class="btn btn-primary btn-inverse btn-mini" name="passBtn" style="margin-bottom:5px" href="#passComfirmModal" onclick="Index.BindClickPass(' + d.id + ');"  data-toggle="modal"><i class="icon-wrench"></i>通过</a>'); // 测试通过按钮
				}
				// 是否可下载
				if (d.downloadStatus) {
					content.push('<a class="btn btn-success btn-mini" style="margin-bottom:5px" href="/'+d.downloadLink+'" onclick="Index.BindClickDownload(' + d.id + ');"><i class="icon-cloud-download"></i>下载<span class="downloadCountSpan-' + d.id + '">[' + d.downloadCount
							+ ']</span></a>');
				} else {
					// 状态为0不可用，为1失效
					if(d.status == 0){
						content.push('<a class="btn btn-danger btn-mini disabled" style="margin-bottom:5px"><i class="icon-cloud-download"></i>不可用</a>');
					}else if(d.status == 1){
						content.push('<a class="btn btn-danger btn-mini disabled" style="margin-bottom:5px"><i class="icon-cloud-download"></i>失效<span class="downloadCountSpan-' + d.id + '">[' + d.downloadCount
								+ ']</span></a>');
					}
				}
				content.push('<a class="btn btn-info btn-inverse btn-mini" style="margin-bottom:5px" href="#chatModal" data-toggle="modal" onclick="Index.BindClickChat(' + d.id + ');"><i class="icon-comment-alt"></i>评论 <span class="chatCountSpan-' + d.id + '">[' + d.chatCount + ']</span></a>'); // 交流按钮
				// 管理员修改删除功能
				if (role == 10) {
					content.push('<a class="btn btn-primary btn-inverse btn-mini" name="editBtn" style="margin-bottom:5px" href="#editModal" onclick="Index.BindClickEdit(' + d.id + ');"  data-toggle="modal"><i class="icon-edit"></i>修改</a>'); // 修改按钮
					content.push('<a class="btn btn-danger btn-inverse btn-mini" name="delBtn" style="margin-bottom:5px" href="#deleteComfirmModal" onclick="Index.BindClickDelete(' + d.id + ');" data-toggle="modal"><i class="icon-cut"></i>删除</a>'); // 删除按钮
				}
				// 打包日志功能，状态为0，不可下载，有svn版本
				if((d.version).lastIndexOf(']') != -1 && (d.version).lastIndexOf('[0000]') == -1){
					content.push('<a class="btn btn-mini" role="button" style="margin-bottom:5px" href="#tarLogModal" data-toggle="modal" onclick="Index.BindClickTarLog(' + d.id + ')">日志</a>'); // 查看日志按钮
				}
				content.push('</td>');
				content.push('</tr>');
				UpmUtil.ajaxLoaderRemove();// 移除加载图片
			});
			$('#tbody').html(content.join(''));
			$('#totalcount_span').text('	共' + page.totalCount + '条记录');
			Index.paginationComponent(page.totalPage, page.pageNow);// 分页组件
		});
	}
};

/**
 * 新增发布包
 */
Index.BindClickAdd = function(role) {
	$('#addNewBtn').unbind('click').click(function() {
		UpmUtil.newModal('addModal'); // 加载新增发布包对话框
		$('#versionExtenstion').text(UpmUtil.versionExtenstion());// 版本后缀
		// 加载用户拥有项目和项目对应的类型
		UpmIndex.loadMyPros(function(myPros) {
			var options = [];
			var $proSelect = $('#addNewPackage select[name=prosSelect]');
			$.each(myPros, function(i, d) {
				var option = '<option value=' + d.id + '>' + d.projectName + '</option>';
				options.push(option);
			});
			$proSelect.html(options.join(''));

			var proID = $proSelect.first().val();// 获取第一个项目id
			loadProTypes(proID);// 加载发布包类型
			// 联动绑定事件
			$proSelect.change(function() {
				loadProTypes($(this).children('option:selected').val());
			});
			// 研发权限限制
			if (role == 2) {
				var $tagSelect = $('#addNewPackage select[name=tagSelect]');
				$tagSelect.children('option[value=beta]').attr('selected', true);// 默认beta
				$tagSelect.attr('disabled', true);
			}

			var uploadURL = 'PackageController/uploadUpgradePackage.do?timestamp=' + UpmUtil.timestamp();
			saveInit(uploadURL);// 保存
		});
	});

	/**
	 * 加载发布包类型
	 */
	function loadProTypes(proID) {
		var $typeSelect = $('#addNewPackage select[name=typeSelect]');
		$typeSelect.html('');
		UpmIndex.loadProTypes(proID, function(proTypes) {
			var options = [];
			$.each(proTypes, function(i, d) {
				var option = '<option value=' + d.id + '>' + d.typeName + '</option>';
				options.push(option);
			});
			$typeSelect.html(options.join(''));
			var packageType = $typeSelect.first().val();// 获取第一个类型id
			loadVersion(proID, packageType);// 加载数据库最后一次版本
			$typeSelect.unbind('change').change(function() {
				var proID = $('#addNewPackage select[name=prosSelect]').children('option:selected').val();
				var packageType = $(this).children('option:selected').val();
				loadVersion(proID, packageType);
			});
		});
	}

	/**
	 * 加载数据库最后一次版本信息记录
	 */
	function loadVersion(proID, packageType) {
		UpmIndex.loadLastVersion({
			proID : proID,
			packageType : packageType
		}, function(data) {
			$('#addNewPackage input[name=version]').val($.trim(data));
		});
	}

	/**
	 * 保存
	 */
	function saveInit(uploadURL) {
		var uploader = new plupload.Uploader({
			runtimes : 'html5,flash,silverlight,html4',
			browse_button : 'browseFile', // browse文件按钮
			container : document.getElementById('container'),
			url : uploadURL,
			flash_swf_url : '../plupload//Moxie.swf',
			silverlight_xap_url : '../plupload/Moxie.xap',

			filters : {
				max_file_size : '1gb', // 限制文件大小
				mime_types : [ { // 限制文件类型
					title : "Zip files",
					extensions : "zip,7z,rar"
				} ]
			},
			init : {
				PostInit : function() {
					document.getElementById('filelist').innerHTML = '';
					document.getElementById('saveNewBtn').onclick = function() { // 保存按钮事件绑定
						var version = $('#addNewPackage input[name=version]').val();
						var remark = $('#addNewPackage textarea[name=remark]').val();
						if (!UpmUtil.isEmpty(remark) && UpmUtil.versionPattern(version)) {
							uploader.start();
						} else {
							UpmUtil.alertError("填写内容存在不匹配！");
						}
						return false;
					};
				},

				FilesAdded : function(up, files) {
					if (files.length == 1) {
						$('#browseFile').attr('disabled', 'disabled');// 加入一文件，disabled按钮，屏蔽多文件上传
					}
					var file = files[0];
					document.getElementById('filelist').innerHTML += '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ') <b></b></div>';
				},

				UploadProgress : function(up, file) {
					document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";
				},

				UploadComplete : function(up, files) {
					var projectID = $('#addNewPackage select[name=prosSelect]').children('option:selected').val();
					var tag = $('#addNewPackage select[name=tagSelect]').children('option:selected').val();
					var version = $('#addNewPackage input[name=version]').val() + $('#versionExtenstion').text();
					var remark = $('#addNewPackage textarea[name=remark]').val();
					var packageType = $('#addNewPackage select[name=typeSelect]').children('option:selected').val();
					var packageSize = plupload.formatSize(files[0].size);// 获取文件大小
					var params = {
						projectID : projectID,
						tag : tag,
						version : version,
						remark : remark,
						packageType : packageType,
						packgeSize : packageSize,
					};
					// 添加数据到数据库
					UpmIndex.addData(params, function() {
						$('#addCloseBtn').click();// 去除添加框
						Index.loadPageData();// 加载数据
					});
				},
				Error : function(up, err) {
					UpmUtil.alertError("文件上传出错啦！错误信息：" + err.message);
				}
			}
		});
		uploader.init();
	}
};

/**
 * 一键打包绑定事件
 */
Index.BindClickOneKeyPack = function() {
	$('#oneKeyPackBtn').unbind('click').click(function() {
		UpmUtil.newModal('packModel'); // 加载一键打包发布对话框
		loadAuthPros();// 加载拥有项目
		$('#packBtn').unbind('click').click(function() {
			var projectID = $('#pack select[name=prosSelect]').children('option:selected').val();
			var packageType = $('#pack select[name=typeSelect]').children('option:selected').val();
			var startRevision = $('#pack input[name=startRevision]').val();
			if (UpmUtil.isEmpty(startRevision)) {
				UpmUtil.alertError("版本信息不能为空！");
			} else {
				var params = {
					projectID : projectID,
					packageType : packageType,
					startRevision : startRevision
				};
				pack(params);// 一键打包入库
			}
		});
	});

	/**
	 * 加载拥有项目
	 */
	function loadAuthPros() {
		UpmIndex.loadMyPros(function(myPros) {
			var options = [];
			var $proSelect = $('#pack select[name=prosSelect]');
			$.each(myPros, function(i, d) {
				var option = '<option value=' + d.id + '>' + d.projectName + '</option>';
				options.push(option);
			});
			$proSelect.html(options.join(''));
			var proID = $proSelect.first().val();// 获取第一个项目id
			loadProTypes(proID);// 加载发布包类型
			// 联动绑定事件
			$proSelect.unbind('change').change(function() {
				var proID = $(this).children('option:selected').val();
				loadProTypes(proID);
			});
		});
	}

	/**
	 * 加载发布包类型
	 */
	function loadProTypes(proID) {
		var $typeSelect = $('#pack select[name=typeSelect]');
		$typeSelect.html('');
		UpmIndex.loadProTypes(proID, function(proTypes) {
			var options = [];
			$.each(proTypes, function(i, d) {
				var option = '<option value=' + d.id + '>' + d.typeName + '</option>';
				options.push(option);
			});
			$typeSelect.html(options.join(''));
			var packageType = $typeSelect.first().val();// 获取第一个类型id
			loadLastSVNVersion(proID, packageType);// 加载数据库最后一次svn版本
			$typeSelect.unbind('change').change(function() {
				var proID = $('#pack select[name=prosSelect]').children('option:selected').val();
				var packageType = $(this).children('option:selected').val();
				loadLastSVNVersion(proID, packageType);
			});
		});
	}

	/**
	 * 加载数据库最后一次版本信息记录
	 */
	function loadLastSVNVersion(proID, packageType) {
		UpmIndex.loadLastSVNVersion({
			proID : proID,
			packageType : packageType
		}, function(data) {
			if (data != '') {
				$('#pack input[name=startRevision]').val(data);
			} else {
				$('#pack input[name=startRevision]').val('');
			}

		});
	}
	/**
	 * 一键打包入库
	 */
	function pack(params) {
		UpmIndex.oneKeyPack(params, function(data) {
			if (data.success) {
				$('#packModel #packBody').html('<p>线程启动，打包正在处理中。。。</p>');
			} else {
				$('#packModel #packBody').html('<p>' + data.msg + '</p>');
			}
			$('#packModel #packBtn').attr('disabled', 'disabled');
			setTimeout(function() {
				$('#packModel #packCloseBtn').click();
			}, 3000);

		});
	}
}

/**
 * 页面注销绑定事件
 */
Index.BindClickLogout = function() {
	// 取消上次事件的叠加绑定 注销事件绑定
	$('#logoutSureBtn').unbind('click').click(function() {
		window.location.href = "authLogout.do";
	});
};

/**
 * 删除发布包
 */
Index.BindClickDelete = function(id) {
	// 取消上次事件的叠加绑定
	$('#deleteSureBtn').unbind('click').click(function() {
		UpmIndex.deleteData(id, function() {
			var pageCurr = Index.pageCurr(); // 获取当前页
			Index.loadPageData(pageCurr);// 加载数据
		});
	});
};

/**
 * 修改发布包
 */
Index.BindClickEdit = function(id) {
	UpmUtil.newModal('editModal'); // 加载修改发布包对话框
	// 加载发布包信息
	UpmIndex.loadData(id, function(obj) {
		$('#editPackage input[name=editID]').val(id);
		$('#editPackage select[name=tagSelect]').val(obj.tag);
		$('#editPackage input[name=version]').val(obj.version);
		$('#editPackage textarea[name=remark]').val(obj.remark);
		$('#editPackage select[name=downloadSelect]').val(obj.downloadStatus);
		$('#editPackage input[name=packageName]').val(obj.packageName);
		// 加载用户拥有项目和项目对应的类型
		UpmIndex.loadMyPros(function(myPros) {
			var options = [];
			var $proSelect = $('#editPackage select[name=prosSelect]');
			$.each(myPros, function(i, d) {
				var option = '<option value=' + d.id + '>' + d.projectName + '</option>';
				options.push(option);
			});
			$proSelect.html(options.join(''));
			$proSelect.children('option[value=' + obj.projectID + ']').attr('selected', true);

			// 加载发布包类型
			loadProTypes(obj.projectID, obj.packageType);
			// 联动绑定事件
			$proSelect.change(function() {
				loadProTypes($(this).children('option:selected').val(), '');
			});

			// 文件上传对象
			var uploader = new plupload.Uploader({
				runtimes : 'html5,flash,silverlight,html4',
				browse_button : 'reBrowseFile', // browse文件按钮
				container : document.getElementById('reContainer'),
				url : 'PackageController/uploadUpgradePackage.do?timestamp=' + UpmUtil.timestamp(),
				flash_swf_url : '../plupload//Moxie.swf',
				silverlight_xap_url : '../plupload/Moxie.xap',

				filters : {
					max_file_size : '1gb', // 限制文件大小
					mime_types : [ { // 限制文件类型
						title : "Zip files",
						extensions : "zip,7z,rar"
					} ]
				},
				init : {
					PostInit : function() {
						$('#editPackage #filePercent').text('');
					},

					FilesAdded : function(up, files) {
						if (files.length == 1) {
							var file = files[0];
							$('#editPackage input[name=packageName]').val(file.name + '(' + plupload.formatSize(file.size) + ')');
							$('#editPackage input[name=reupload]').val(1);// 设置修改附带文件标识
							$('#editPackage input[name=packageSize]').val(plupload.formatSize(file.size));// 设置文件大小
						}
					},

					UploadProgress : function(up, file) {
						$('#editPackage #filePercent').text(file.percent + '%');
					},

					UploadComplete : function(up, files) {
						var editID = $('#editPackage input[name=editID]').val() || '';
						var projectID = $('#editPackage select[name=prosSelect]').children('option:selected').val() || '';
						var tag = $('#editPackage select[name=tagSelect]').children('option:selected').val() || '';
						var version = $('#editPackage input[name=version]').val() || '';
						var remark = $('#editPackage textarea[name=remark]').val() || '';
						var packageType = $('#editPackage select[name=typeSelect]').children('option:selected').val() || '';
						var downloadStatus = $('#editPackage select[name=downloadSelect]').children('option:selected').val() || '';
						var packageSize = $('#editPackage input[name=packageSize]').val();// 设置文件大小
						var params = {
							id : editID,
							projectID : projectID,
							tag : tag,
							version : version,
							remark : remark,
							packageType : packageType,
							downloadStatus : downloadStatus,
							packgeSize : packageSize,
							ifWithFile : true
						};
						edit(params);// 修改
					},
					Error : function(up, err) {
						UpmUtil.alertError("文件上传出错啦！错误信息：" + err.message);
					}
				}
			});
			uploader.init(); // 上传文件对象初始化

			// 编辑click绑定
			$('#editSaveBtn').unbind('click').click(function() {
				var version = $('#editPackage input[name=version]').val() || '';
				var remark = $('#editPackage textarea[name=remark]').val() || '';
				var ifWithFile = $('#editPackage input[name=reupload]').val() || 0;// 设置修改附带文件标识
				if (UpmUtil.isEmpty(version) || UpmUtil.isEmpty(remark)) {
					UpmUtil.alertError('填写内容存在不匹配！');
				} else {
					if (ifWithFile == 1) {
						// 带文件修改
						uploader.start();
					} else {
						var editID = $('#editPackage input[name=editID]').val() || '';
						var projectID = $('#editPackage select[name=prosSelect]').children('option:selected').val() || '';
						var tag = $('#editPackage select[name=tagSelect]').children('option:selected').val() || '';
						var version = $('#editPackage input[name=version]').val() || '';
						var remark = $('#editPackage textarea[name=remark]').val() || '';
						var packageType = $('#editPackage select[name=typeSelect]').children('option:selected').val() || '';
						var downloadStatus = $('#editPackage select[name=downloadSelect]').children('option:selected').val() || '';
						// 不带文件修改
						var params = {
							id : editID,
							projectID : projectID,
							tag : tag,
							version : version,
							remark : remark,
							packageType : packageType,
							downloadStatus : downloadStatus,
							ifWithFile : false
						};
						edit(params);// 修改
					}
				}
			});
		});
	});

	/**
	 * 修改
	 */
	function edit(params) {
		// 修改数据
		UpmIndex.editData(params, function() {
			var pageCurr = Index.pageCurr();
			$('#editModal .close').click();// 弹窗隐藏
			Index.loadPageData(pageCurr);// 加载数据
		});
	}

	/**
	 * 加载发布包类型
	 */
	function loadProTypes(proID, type) {
		var $typeSelect = $('#editPackage select[name=typeSelect]');
		$typeSelect.html('');
		UpmIndex.loadProTypes(proID, function(proTypes) {
			var options = [];
			$.each(proTypes, function(i, d) {
				var option = '<option value=' + d.id + '>' + d.typeName + '</option>';
				options.push(option);
			});
			$typeSelect.html(options.join(''));
			if (type != '') {
				$typeSelect.children('option[value=' + type + ']').attr('selected', true);
			}
		});
	}

};

/**
 * 测试通过
 */
Index.BindClickPass = function(id) {
	// 取消上次事件的叠加绑定
	$('#passSureBtn').unbind('click').click(function() {
		var params = {
			id : id
		};
		UpmIndex.testPass(params, function() {
			var pageCurr = Index.pageCurr(); // 获取当前页
			Index.loadPageData(pageCurr);// 加载数据
		});
	});
};

/**
 * 废弃
 */
Index.BindClickDiscard = function(id) {
	// 取消上次事件的叠加绑定
	$('#discardSureBtn').unbind('click').click(function() {
		var params = {
			id : id
		};
		UpmIndex.discard(params, function() {
			var pageCurr = Index.pageCurr(); // 获取当前页
			Index.loadPageData(pageCurr);// 加载数据
		});
	});
};

/**
 * 删除评论
 */
Index.BindClickChatDel = function(id) {
	// 绑定删除事件
	$('#chatMsg li').each(function(i) {
		var dateHtmls = [];
		dateHtmls[i] = $(this).find('.date').html();
		$(this).mouseover(function() {
			if ($(this).find('.date').text().indexOf('删除') == -1) {
				$(this).find('.date').html('<a href="javascript:;">删除</a>');
				$(this).find('a').unbind('click').click(function() {
					if (confirm('确定要删除这条评论信息？')) {
						var $li = $('#chatMsg li:eq(' + i + ')');
						var chatID = $li.children('input[name=chatID]').val();
						UpmIndex.delChatData(chatID, function() {
							$li.hide();
							var $chatCountSpan = $('.chatCountSpan-' + id);
							var textValue = $chatCountSpan.text();
							var chatCount = parseInt(textValue.slice(textValue.indexOf('[') + 1, textValue.indexOf(']')));
							$chatCountSpan.text('[' + (chatCount - 1) + ']');
						});
					}
				});
			}
		}).mouseleave(function() {
			$(this).find('.date').html(dateHtmls[i]);
		});

	});
}

/**
 * 评论
 */
Index.BindClickChat = function(id) {
	UpmUtil.newModal('chatModal'); // 加载评论发布包对话框
	loadPackData(id);// 加载发布包数据
	toggleAndChat(id);// 切换显示绑定

	/**
	 * 加载发布包数据
	 */
	function loadPackData(id) {
		// 加载发布包数据
		UpmIndex.loadData(id, function(obj) {
			$('#chatModal h3').text(obj.projectName + '-' + obj.tag + '-' + obj.version); // 设置title
			$('#chatModal pre').html(obj.remark); // 设置备注
		});
	}

	/**
	 * 切换显示绑定
	 */
	function toggleAndChat(id) {
		// 评论
		$('#toChatsBtn').unbind('click').click(function() {
			var chatHtml = UpmTemp.chatHtmlTemp();// 评论html
			$('#chatModal>.modal-body').html(chatHtml);
			$('#toRemarkBtn').show();
			$('#toChatsBtn').hide();
			chats(id);// chat
		});
		// 详情
		$('#toRemarkBtn').unbind('click').click(function() {
			var remarkHtml = UpmTemp.remarkHtmlTemp();// 显示详情html
			$('#chatModal>.modal-body').html(remarkHtml);
			$('#toRemarkBtn').hide();
			$('#toChatsBtn').show();
			loadPackData(id);
		});
	}
	/**
	 * 加载评论内容
	 */
	function loadChats(id) {
		// 加载chat记录
		UpmIndex.loadChatData(id, function(data) {
			var chatLis = [];
			$.each(data, function(i, d) {
				var chatLi = '<li class="message"> <input type="hidden" name="chatID" value="' + d.id + '">' + '<div class="name-and-time"><div class="name pull-left"><small><a class="text-contrast" href="javascript:;">' + d.userName + '</a></small></div>' + '<div class="time pull-right">'
						+ '<small class="date pull-right muted">' + '<span title="May 30, 2013 - 21:03" data-placement="top" class="timeago fade has-tooltip in">' + d.createTime + '</span>' + '<i class="icon-time"></i>' + '</small>' + '</div>' + '</div>' + '<div class="body">' + d.chatMsg
						+ '</div></li>';
				chatLis.push(chatLi);
			});
			$('#chatMsg').html(chatLis.join(''));
			var li_len = $('#chatMsg>li').length;// 获取评论信息长度
			if (li_len > 5) {
				$('.slimScrollDiv').css('overflow', 'scroll');// 显示滚动条
			}
			Index.BindClickChatDel(id);// 绑定删除
		});
	}

	/**
	 * 评论
	 */
	function chats(id) {
		metionUser(); // 加载用户提示
		loadChats(id); // 加载chat记录
		// 提交评论绑定
		$('#saveChatBtn').unbind('click').click(function() {
			var chatMsg = $('#message_body').val();
			if (chatMsg == '') {
				UpmUtil.alertError("评论内容不能为空！");
			} else {
				var params = {
					chatMsg : chatMsg,
					packageID : id
				};
				// 添加评论数据
				UpmIndex.addChatData(params, function() {
					loadChats(params.packageID);// 重新加载评论记录
					var $chatCountSpan = $('.chatCountSpan-' + id);
					var textValue = $chatCountSpan.text();
					var chatCount = parseInt(textValue.slice(textValue.indexOf('[') + 1, textValue.indexOf(']')));
					$chatCountSpan.text('[' + (chatCount + 1) + ']');
					// 发送邮件
					UpmIndex.mail({
						chatMsg : params.chatMsg
					}, function() {
						UpmUtil.alertError("通知邮件已发送！");
					});
				});
			}
		});
	}

	/**
	 * 用户@提示
	 */
	function metionUser() {
		var $mention = $('.mention');
		if ($mention.length > 0) {
			UpmUser.loadAllUser(function(data) {
				var users = [];
				for ( var i in data) {
					users.push({
						name : data[i].realName + ":",
						username : "(" + data[i].mail + ")"
					});
				}
				$mention.mention({
					users : users
				});
			});
		}
	}

};

/**
 * 修改状态
 */
Index.BindClickStatus = function(id, oldStatus) {
	var newStatus = oldStatus == 1 ? 0 : 1;
	var params = {
		id : id,
		status : newStatus
	};
	// 修改状态
	UpmIndex.editDataStatus(params, function() {
		var pageCurr = Index.pageCurr();
		Index.loadPageData(pageCurr);// 加载数据
	});
};


/**
 * 添加下载记录
 */
Index.BindClickDownload = function(id) {
	var $downloadCountSpan = $('.downloadCountSpan-' + id);
	var textValue = $downloadCountSpan.text();// 获取值
	var downloadCount = parseInt(textValue.slice(textValue.indexOf('[') + 1, textValue.indexOf(']')));// 截取数字
	$downloadCountSpan.text('[' + (downloadCount + 1) + ']');// 重新赋值
	UpmIndex.addDownload(id);
};

/**
 * 查看打包日志
 */
Index.BindClickTarLog = function(id){
	UpmUtil.newModal('tarLogModal'); // 加载评论发布包对话框
	UpmIndex.loadTarLog(id,function(tarLog){
		$('#tarLogModal pre').html(tarLog); // 设置打包日志
	});
};

$(function() {
	// 加载登录用户信息,加载成功执行操作
	Index.loadLoginUser(function(role) {
		UpmUtil.appendComfirmModalHtml();// 加载判断框html
		Index.loadAllTypes();// 加载所有类型
		Index.loadMyAuthPros();// 加载拥有权限项目
		Index.loadPageData('1');// 初始化加载数据
		Index.BindClickLogout();// 页面注销事件绑定
		if (role != 1) {
			$('#oneKeyPackBtn').show();
			Index.BindClickOneKeyPack();// 一键打包绑定事件
			if (role != 3) {
				$('#addNewBtn').show();
				Index.BindClickAdd(role);// 添加升级包事件绑定
			}
		}
	});
});