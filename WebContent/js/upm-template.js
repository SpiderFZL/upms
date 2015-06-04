var UpmTemp = {};

/**
 * 加载图片模板
 */
UpmTemp.ajaxLoaderTemp = function(){
	var ajaxLoader = '<div id="ajaxLoader" style="display:block; position: fixed; top: 50%; left: 50%; width:32px;height:32px; margin-top:-32px"><img src="assets/images/ajax-loaders/11.gif"></div>';
	return ajaxLoader;
};

/**
 * 注销提示对话框
 */
UpmTemp.logoutComfirmModalTemp = function() {
	var logoutComfirmModal = '<div tabindex="-1" role="dialog" id="logoutComfirmModal" class="modal hide fade" style="display: none;" aria-hidden="true">' + '<div class="modal-header">'
			+ '<button type="button" data-dismiss="modal" class="close">×</button>' + '<h3>用户注销提示</h3>' + '</div>' + '<div class="modal-body">' + '<h4>确定要注销登录？</h4>'
			+ '</div><div class="modal-footer"><button data-dismiss="modal" class="btn">取消</button><button data-dismiss="modal" id="logoutSureBtn" class="btn btn-primary">确定</button>'
			+ '</div></div>';
	return logoutComfirmModal;
};

/**
 * 删除提示对话框
 */
UpmTemp.deleteComfirmModalTemp = function() {
	var deleteComfirmModal = '<div tabindex="-1" role="dialog" id="deleteComfirmModal" class="modal hide fade" style="display: none;" aria-hidden="true">' + '<div class="modal-header">'
			+ '<button type="button" data-dismiss="modal" class="close">×</button>' + '<h3>删除数据提示</h3>' + '</div>' + '<div class="modal-body">' + '<h4>确定要删除这条数据？</h4>'
			+ '</div><div class="modal-footer"><button data-dismiss="modal" class="btn">取消</button><button data-dismiss="modal" id="deleteSureBtn" class="btn btn-primary">确定</button>'
			+ '</div></div>';
	return deleteComfirmModal;
};

/**
 * 通过提示对话框
 */
UpmTemp.passComfirmModalTemp = function() {
	var passComfirmModal = '<div tabindex="-1" role="dialog" id="passComfirmModal" class="modal hide fade" style="display: none;" aria-hidden="true">' + '<div class="modal-header">'
			+ '<button type="button" data-dismiss="modal" class="close">×</button>' + '<h3>升级包通过提示</h3>' + '</div>' + '<div class="modal-body">' + '<h4>确定已通过测试？通过将发布成release包。</h4>'
			+ '</div><div class="modal-footer"><button data-dismiss="modal" class="btn">取消</button><button data-dismiss="modal" id="passSureBtn" class="btn btn-primary">确定</button>'
			+ '</div></div>';
	return passComfirmModal;
};

/**
 * 废弃提示对话框
 */
UpmTemp.discardComfirmModalTemp = function() {
	var discardComfirmModal = '<div tabindex="-1" role="dialog" id="discardComfirmModal" class="modal hide fade" style="display: none;" aria-hidden="true">' + '<div class="modal-header">'
			+ '<button type="button" data-dismiss="modal" class="close">×</button>' + '<h3>升级包废弃提示</h3>' + '</div>' + '<div class="modal-body">' + '<h4>确定将废弃？</h4>'
			+ '</div><div class="modal-footer"><button data-dismiss="modal" class="btn">取消</button><button data-dismiss="modal" id="discardSureBtn" class="btn btn-primary">确定</button>'
			+ '</div></div>';
	return discardComfirmModal;
};

/**
 * 添加对话框
 */
UpmTemp.addModalTemp = function() {
var addModal = '<div tabindex="-1" role="dialog" id="addModal" class="modal hide fade" style="display: none;" aria-hidden="true">' +
    '<div class="modal-header">' +
'<button type="button" data-dismiss="modal" class="close" id="addCloseBtn">×</button>' +
'<h3>新增发布包</h3>' +
'</div>' +
'<div class="modal-body">' +
'<form id="addNewPackage" class="form form-horizontal" style="margin-bottom: 0;" method="post" action="">' + 
'<div class="control-group">' + 
	'<label class="control-label" for="inputSelect">项目：</label>' + 
	'<div class="controls">' + 
		'<select name="prosSelect">' + 
        '</select>' + 
    '<span style="color: red"> *</span>' + 
    '</div>' + 
'</div>' + 
'<div class="control-group">' + 
	'<label class="control-label">类别：</label>' +
	'<div class="controls">' +
		'<select name="typeSelect">' + 
        '</select>' + 
    '<span style="color: red"> *</span>' + 
    '</div>' + 
'</div>' + 
'<div class="control-group">' + 
	'<label class="control-label" for="inputSelect">版本：</label>' + 
	'<div class="controls">' + 
		'<select name="tagSelect">' + 
           	'<option value="alpha">alpha</option>' + 
			'<option value="beta">beta</option>' + 
			'<option value="rc">rc</option>' + 
			'<option value="release">release</option>' + 
        '</select>' + 
    '<span style="color: red"> *</span>' + 
    '</div>' + 
'</div>' + 
'<div class="control-group">' + 
	'<label class="control-label">版本号：</label>' + 
	'<div class="controls">' + 
		'<div class="input-append">' +
			'<input type="text" name="version" data-rule-required style="width:120px">' + 
			'<span id="versionExtenstion" class="add-on"></span>' + 
		'</div>' +
		'<span style="color: red"> *</span>' + 
	'</div>' + 
'</div>' + 
'<div class="control-group">' + 
	'<label class="control-label">备注：</label>' + 
	'<div class="controls">' + 
		'<textarea name="remark" rows="2" class="autosize input-block-level" style="overflow: hidden; word-wrap: break-word; resize: vertical; height: 80px;width: 250px"></textarea>' + 
		'<span style="color: red"> *</span>' + 
	'</div>' + 
'</div>' + 
'<div class="control-group">' + 
	'<label class="control-label">文件：</label>' + 
	'<div class="controls">' + 
		'<div id="container">' + 
			'<button id="browseFile" class="btn btn-info"><i class="icon-cloud-upload"></i>选择文件</button>' + 
			'<span style="color: red"> *(zip,7z,rar)</span>' + 
		'</div>' + 
		'<div id="filelist"></div>' + 
	'</div>' + 
'</div>' + 
'</form>' + 
'</div>' +
'<div class="modal-footer">' +
'<button data-dismiss="modal" class="btn">取消</button>' +
'<button id="saveNewBtn" class="btn btn-primary">保存</button>' +
'</div>' +
'</div>';
return addModal;
};


/**
 * 添加对话框
 */
UpmTemp.editModalTemp = function() {
var editModal = '<div tabindex="-1" role="dialog" id="editModal" class="modal hide fade" style="display: none;" aria-hidden="true">' +
    '<div class="modal-header">' +
'<button type="button" data-dismiss="modal" class="close">×</button>' +
'<h3>编辑发布包</h3>' +
'</div>' +
'<div class="modal-body">' +
'<form id="editPackage" class="form form-horizontal" style="margin-bottom: 0;" method="post" action="">' +
'<input type="hidden" name="editID" value=""/>'+
'<input type="hidden" name="reupload" value="0"/>'+
'<input type="hidden" name="packageSize" value=""/>'+
'<div class="control-group">' + 
	'<label class="control-label" for="inputSelect">项目：</label>' + 
	'<div class="controls">' + 
		'<select name="prosSelect">' + 
        '</select>' + 
    '<span style="color: red"> *</span>' + 
    '</div>' + 
'</div>' + 
'<div class="control-group">' + 
	'<label class="control-label">类别：</label>' +
	'<div class="controls">' +
		'<select name="typeSelect">' + 
        '</select>' + 
    '<span style="color: red"> *</span>' + 
    '</div>' + 
'</div>' + 
'<div class="control-group">' + 
	'<label class="control-label" for="inputSelect">版本：</label>' + 
	'<div class="controls">' + 
		'<select name="tagSelect">' + 
           	'<option value="alpha">alpha</option>' + 
			'<option value="beta">beta</option>' + 
			'<option value="rc">rc</option>' + 
			'<option value="release">release</option>' + 
        '</select>' + 
    '<span style="color: red"> *</span>' + 
    '</div>' + 
'</div>' + 
'<div class="control-group">' + 
	'<label class="control-label">版本号：</label>' + 
	'<div class="controls">' + 
		'<input type="text" name="version" data-rule-required>' + 
		'<span style="color: red"> *</span>' + 
	'</div>' + 
'</div>' + 
'<div class="control-group">' + 
	'<label class="control-label">备注：</label>' + 
	'<div class="controls">' + 
		'<textarea name="remark" rows="2" class="autosize input-block-level" style="overflow: hidden; word-wrap: break-word; resize: vertical; height: 80px;width: 250px"></textarea>' + 
		'<span style="color: red"> *</span>' + 
	'</div>' + 
'</div>' +
'<div class="control-group">' + 
	'<label class="control-label" for="inputSelect">下载：</label>' + 
	'<div class="controls">' + 
		'<select name="downloadSelect">' + 
	       	'<option value="1">可下载</option>' + 
			'<option value="0">失效</option>' + 
	    '</select>' + 
	    '<span style="color: red">*</span>' + 
	'</div>' + 
'</div>' +
'<div class="control-group">' + 
	'<label class="control-label">文件：</label>' + 
	'<div class="controls">' + 
		'<input type="text" name="packageName" disabled="">' +
		'<button id="reBrowseFile" class="btn btn-info"><i class="icon-cloud-upload"></i>选择文件</button>' +
		'<button id="uploadFile" class="btn btn-info" style="display:none;"><i class="icon-cloud-upload"></i>重新上传</button>'+
		'<div id="reContainer" style="display:none;"></div>' + 
		'<span id="filePercent"></span>' +
	'</div>' + 
'</div>' +
'</form>' + 
'</div>' +
'<div class="modal-footer">' +
'<button data-dismiss="modal" class="btn">取消</button>' +
'<button id="editSaveBtn" data-dismiss="modal" class="btn btn-primary">修改</button>' +
'</div>' +
'</div>';
return editModal;
};

/**
 * chat评论对话框
 */
UpmTemp.chatModalTemp3 = function() {
	var chatModal = '<div id = "chatModal3" style="position: absolute; top: 0; left: 19%; right: auto; margin: 0 auto;max-width: 100%; width:60%; height:50%; display:none;" tabindex="-1" class="modal hide fade" role="dialog" aria-hidden="true">' +
		'<div class="modal-header">' +
		    '<button type="button" data-dismiss="modal" class="close" aria-hidden="true">×</button>' +
		    '<h3></h3>' +
		 '</div>' +
		'<div class="modal-body" style="width: 95%; max-height:100%; height: 70%">' +
			'<div class="box-content">' +
		        '<h4>备注：</h4>' +
		        '<pre></pre>' +
		    '</div>' +
		'</div>' +
		'<div class="chat row-fluid">' +
			'<div class="box box-nomargin span12">' +
				'<div class="box-content box-no-padding">' +
				'<form class="new-message" accept-charset="UTF-8" onsubmit="return false;"><div style="margin:0;padding:0;display:inline"></div>' +
				    '<input class="input-block-level mention" type="text" placeholder="说点什么（按回车键提交）..." name="message[body]" id="message_body" class="span12">' +
				    '<button id="saveChatBtn" class="btn btn-success">' +
				        '<i class="icon-plus"></i>' +
				    '</button>' +
			    '</form>' +
					'<div class="slimScrollDiv" style="position: relative; overflow: hidden; width: auto; height: 300px;"><div data-scrollable-start="bottom" data-scrollable-height="300" class="scrollable" style="overflow: hidden; width: auto; height: 300px;">' +
						'<ul id="chatMsg" class="unstyled list-hover list-striped">' +
							'<!-- 消息 -->' +
						'</ul>' +
					'</div>' +
					'<div class="slimScrollBar ui-draggable" style="background: none repeat scroll 0% 0% rgb(0, 0, 0); width: 7px; position: absolute; top: 219px; opacity: 0.4; display: none; border-radius: 7px; z-index: 99; right: 1px; height: 80.7175px;"></div>' +
					'<div class="slimScrollRail" style="width: 7px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; background: none repeat scroll 0% 0% rgb(51, 51, 51); opacity: 0.2; z-index: 90; right: 1px;"></div>' +
				'</div>' +
			'</div>' +
		'</div>' +
	'</div>';
return chatModal;
}

/**
 * chat评论对话框
 */
UpmTemp.chatModalTemp = function(){
	var chatModal = '<div tabindex="-1" role="dialog" id="chatModal" class="modal hide fade" style="display: none;" aria-hidden="true">' +
    					'<div class="modal-header">' +
    						'<button type="button" data-dismiss="modal" class="close">×</button>' +
    							'<h3></h3>' +
    					'</div>' +
						'<div class="modal-body">' +
							'<div class="box-content">' +
								'<pre></pre>' +
							'</div>' +
						'</div>' +
						'<div class="modal-footer">' +
							'<button id="toChatsBtn" class="btn btn-primary"><i class="icon-arrow-right">评论</i></button>' +
							'<button id="toRemarkBtn" style="display:none;" class="btn btn-primary"><i class="icon-arrow-left">详情</i></button>' +
						'</div>' +
					'</div>';
	return chatModal;
};

/**
 * 详情HTML
 */
UpmTemp.remarkHtmlTemp = function(){
	var remarkHtml = '<div class="box-content"><pre></pre></div>';
	return remarkHtml;
};
/**
 * 评论html
 */
UpmTemp.chatHtmlTemp = function(){
	var chatHtml = '<div class="chat row-fluid">' +
		'<div class="box box-nomargin span12">' +
	'<div class="box-content box-no-padding">' +
	'<form method="post" class="new-message" accept-charset="UTF-8"><div style="margin:0;padding:0;display:inline"></div>' +
	    '<input type="text" class="input-block-level mention" placeholder="说点什么（按回车键提交）..." name="message[body]" id="message_body" class="span12">' +
	    '<button  id="saveChatBtn" class="btn btn-success">' +
	        '<i class="icon-reply"></i>' +
	    '</button>' +
    '</form>' +
	'<div class="slimScrollDiv" style="position: relative; overflow: hidden; width: auto; height: 300px;">' +
		'<ul id="chatMsg" class="unstyled list-hover list-striped">' +
		'<!-- 消息 -->' +
		'</ul>' +
	'</div>'+
	'</div>' +
	'</div>' +
	'</div>';
	return chatHtml;
};

/**
 * 提示框
 */
UpmTemp.tipModalTemp = function(html){
	var tipModal = '<div id="tipModal" style="position: relative; top: auto; left: auto; right: auto; margin: 0 auto 20px; z-index: 1; max-width: 100%;" class="modal">' +
        '<div class="modal-header">' +
		    '<button type="button" class="close" aria-hidden="true">×</button>' +
		    '<h3>提示</h3>' +
		'</div>' +
		'<div class="modal-body">' +
		    html +
		'</div>' +
	'</div>';
	return tipModal;
};

/**
 * 权限项目chekbox选择框
 */
UpmTemp.authModalTemp = function() {
	var authModal = '<div tabindex="-1" role="dialog" id="authModal" class="modal hide fade" style="display: none;" aria-hidden="true">' +
	'<div class="modal-header">' +
		'<button type="button" data-dismiss="modal" class="close">×</button>' +
			'<h3>项目权限选择</h3>' +
	'</div>' +
	'<div class="modal-body">' +
		'<div class="box-content">' +
			'<div class="controls">' +
	        '</div>' +
		'</div>' +
	'</div>' +
	'<div class="modal-footer">' +
		'<button data-dismiss="modal" class="btn">取消</button>' +
		'<button id="saveAuthBtn" data-dismiss="modal" class="btn btn-primary">授权</button>' +
	'</div>' +
'</div>';
	return authModal;
};

/**
 * 添加对话框
 */
UpmTemp.addUserModalTemp = function() {
var addUserModal = '<div tabindex="-1" role="dialog" id="addUserModal" class="modal hide fade" style="display: none;" aria-hidden="true">' +
    '<div class="modal-header">' +
'<button type="button" data-dismiss="modal" class="close" id="addUserCloseBtn">×</button>' +
'<h3>新增用户</h3>' +
'</div>' +
'<div class="modal-body">' +
'<form id="addNewUser" class="form form-horizontal" style="margin-bottom: 0;" method="post" action="">' + 
	'<div class="control-group">' + 
		'<label class="control-label">用户账号：</label>' + 
		'<div class="controls">' + 
			'<input type="text" name="userName" data-rule-required>' + 
			'<span style="color: red"> *</span>' + 
		'</div>' + 
	'</div>' +
	'<div class="control-group">' + 
		'<label class="control-label">真实姓名：</label>' + 
		'<div class="controls">' + 
			'<input type="text" name="realName" data-rule-required>' + 
			'<span style="color: red"> *</span>' + 
		'</div>' + 
	'</div>' +
	'<div class="control-group">' + 
	'<label class="control-label">邮箱：</label>' + 
	'<div class="controls">' + 
		'<input type="text" name="mail" data-rule-required>' + 
		'<span style="color: red"> *</span>' + 
	'</div> <br/>' + 
	'<div class="control-group">' + 
		'<label class="control-label">用户权限：</label>' +
		'<div class="controls">' +
			'<select name="role">' + 
				'<option value="2">研发</option>'+
				'<option value="3">测试</option>'+
				'<option value="1">技术</option>'+
	        '</select>' + 
	    '<span style="color: red"> *</span>' + 
	    '</div>' + 
    '</div>' + 
	'<div class="control-group">' + 
	    '<label class="control-label">项目权限：</label>' + 
	    '<div class="controls" id="allPro">' + 
	        '<label class="checkbox inline">' + 
	            '<input type="checkbox" value="">' + 
	            'Magni' + 
	        '</label>' + 
	        '<label class="checkbox inline">' + 
	            '<input type="checkbox" value="">' + 
	            'Explicabo' + 
	        '</label>' + 
	        '<label class="checkbox inline">' + 
	            '<input type="checkbox" value="">' + 
	            'Odit' + 
	        '</label>' + 
	    '</div>' + 
   '</div>' +
'</form>' + 
'</div>' +
'<div class="modal-footer">' +
'<button data-dismiss="modal" class="btn">取消</button>' +
'<button id="saveNewBtn" data-dismiss="modal" class="btn btn-primary">保存</button>' +
'</div>' +
'</div>';
return addUserModal;
};

/**
 * 打包框
 */
UpmTemp.packModelTemp = function(){
	var packModel = '<div tabindex="-1" role="dialog" id="packModel" class="modal hide fade" style="display: none;" aria-hidden="true">' +
    '<div class="modal-header">' +
'<button type="button" data-dismiss="modal" class="close" id="packCloseBtn">×</button>' +
'<h3>一键打包发布</h3>' +
'</div>' +
'<div class="modal-body" id="packBody">' +
'<form id="pack" class="form form-horizontal" style="margin-bottom: 0;" method="post" action="">' + 
	'<div class="control-group">' + 
		'<label class="control-label" for="inputSelect">打包项目：</label>' + 
		'<div class="controls">' + 
			'<select name="prosSelect">' + 
		    '</select>' + 
		'<span style="color: red"> *</span>' + 
		'</div>' + 
	'</div>' + 
	'<div class="control-group">' + 
		'<label class="control-label">项目类型：</label>' +
		'<div class="controls">' +
			'<select name="typeSelect">' + 
	        '</select>' + 
	    '<span style="color: red"> *</span>' + 
	    '</div>' + 
    '</div>' + 
	'<div class="control-group">' + 
		'<label class="control-label">SVN版本号：</label>' + 
		'<div class="controls">' + 
			'<input type="text" name="startRevision" data-rule-required style="width:100px">	' + 
			'-	<input type="text" data-rule-required value="最新版本" disabled = "disabled" style="width:100px">' +
		'</div>' + 
	'</div>' +
'</form>' + 
'</div>' +
'<div class="modal-footer">' +
'<button id="packBtn" class="btn btn-primary">执行</button>' +
'</div>' +
'</div>';
return packModel;
};

/**
 * 修改密码框
 */
UpmTemp.changePwdModelTemp = function(){
	var changePwdModel = '<div tabindex="-1" role="dialog" id="changePwdModel" class="modal hide fade" style="display: none;" aria-hidden="true">' +
    '<div class="modal-header">' +
'<button type="button" data-dismiss="modal" class="close" id="changePwdCloseBtn">×</button>' +
'<h3>修改密码</h3>' +
'</div>' +
'<div class="modal-body" id="changePwdBody">' +
'<form id="changePwd" class="form form-horizontal" style="margin-bottom: 0;" method="post" action="">' + 
'<div class="control-group">' + 
	'<label class="control-label">输入修改密码：</label>' + 
	'<div class="controls">' + 
		'<input type="password" name="newPassword" data-rule-required>' + 
	'</div>' + 
	'<label class="control-label">重复输入密码：</label>' + 
	'<div class="controls">' + 
		'<input type="password" name="reNewPassword" data-rule-required>' + 
	'</div>' + 
'</div>' +
'</form>' + 
'</div>' +
'<div class="modal-footer">' +
'<button data-dismiss="modal" class="btn">取消</button>' +
'<button id="saveNewPwd" data-dismiss="modal" class="btn btn-primary">修改</button>' +
'</div>' +
'</div>';
return changePwdModel;
};

/**
 * tarLog日志对话框
 */
UpmTemp.tarLogModalTemp = function(){
	var tarLogModal = '<div tabindex="-1" role="dialog" id="tarLogModal" class="modal hide fade" style="display: none;" aria-hidden="true">' +
    					'<div class="modal-header">' +
    						'<button type="button" data-dismiss="modal" class="close">×</button>' +
    							'<h3>打包日志</h3>' +
    					'</div>' +
						'<div class="modal-body">' +
							'<div class="box-content">' +
								'<pre></pre>' +
							'</div>' +
						'</div>' +
					'</div>';
	return tarLogModal;
};