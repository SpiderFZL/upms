<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="<%=request.getContextPath()%>" var="ctxPath"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中宇万通产品中心管理系统>>用户管理</title>
<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport' />
    <script src='assets/javascripts/html5shiv.js' type='text/javascript'></script>
    <link href='css/css.css' media='all' rel='stylesheet' type='text/css' />
    <link href='assets/stylesheets/bootstrap/bootstrap.css' media='all' rel='stylesheet' type='text/css' />
    <link href='assets/stylesheets/bootstrap/bootstrap-responsive.css' media='all' rel='stylesheet' type='text/css' />
    <!-- / jquery ui -->
    <link href='assets/stylesheets/jquery_ui/jquery-ui-1.10.0.custom.css' media='all' rel='stylesheet' type='text/css' />
    <link href='assets/stylesheets/jquery_ui/jquery.ui.1.10.0.ie.css' media='all' rel='stylesheet' type='text/css' />
    <!-- / switch buttons -->
    <link href='assets/stylesheets/plugins/bootstrap_switch/bootstrap-switch.css' media='all' rel='stylesheet' type='text/css' />
    <!-- / xeditable -->
    <link href='assets/stylesheets/plugins/xeditable/bootstrap-editable.css' media='all' rel='stylesheet' type='text/css' />
    <link href='assets/stylesheets/plugins/common/bootstrap-wysihtml5.css' media='all' rel='stylesheet' type='text/css' />
    <!-- / wysihtml5 (wysywig) -->
    <link href='assets/stylesheets/plugins/common/bootstrap-wysihtml5.css' media='all' rel='stylesheet' type='text/css' />
    <!-- / jquery file upload -->
    <link href='assets/stylesheets/plugins/jquery_fileupload/jquery.fileupload-ui.css' media='all' rel='stylesheet' type='text/css' />
    <!-- / full calendar -->
    <link href='assets/stylesheets/plugins/fullcalendar/fullcalendar.css' media='all' rel='stylesheet' type='text/css' />
    <!-- / select2 -->
    <link href='assets/stylesheets/plugins/select2/select2.css' media='all' rel='stylesheet' type='text/css' />
    <!-- / mention -->
    <link href='assets/stylesheets/plugins/mention/mention.css' media='all' rel='stylesheet' type='text/css' />
    <!-- / tabdrop (responsive tabs) -->
    <link href='assets/stylesheets/plugins/tabdrop/tabdrop.css' media='all' rel='stylesheet' type='text/css' />
    <!-- / jgrowl notifications -->
    <link href='assets/stylesheets/plugins/jgrowl/jquery.jgrowl.min.css' media='all' rel='stylesheet' type='text/css' />
    <!-- / datatables -->
    <link href='assets/stylesheets/plugins/datatables/bootstrap-datatable.css' media='all' rel='stylesheet' type ='text/css' />
    <!-- / dynatrees (file trees) -->
    <link href='assets/stylesheets/plugins/dynatree/ui.dynatree.css' media='all' rel='stylesheet' type='text/css' />
    <!-- / color picker -->
    <link href='assets/stylesheets/plugins/bootstrap_colorpicker/bootstrap-colorpicker.css' media='all' rel='stylesheet' type='text/css' />
    <!-- / datetime picker -->
    <link href='assets/stylesheets/plugins/bootstrap_datetimepicker/bootstrap-datetimepicker.min.css' media='all' rel='stylesheet' type='text/css' />
    <!-- / daterange picker) -->
    <link href='assets/stylesheets/plugins/bootstrap_daterangepicker/bootstrap-daterangepicker.css' media='all' rel='stylesheet' type='text/css' />
    <!-- / flags (country flags) -->
    <link href='assets/stylesheets/plugins/flags/flags.css' media='all' rel='stylesheet' type='text/css' />
    <!-- / slider nav (address book) -->
    <link href='assets/stylesheets/plugins/slider_nav/slidernav.css' media='all' rel='stylesheet' type='text/css' />
    <!-- / fuelux (wizard) -->
    <link href='assets/stylesheets/plugins/fuelux/wizard.css' media='all' rel='stylesheet' type='text/css' />
    <!-- / flatty theme -->
    <link href='assets/stylesheets/light-theme.css' id='color-settings-body-color' media='all' rel='stylesheet' type='text/css' />
    <!-- / demo -->
    <link href='assets/stylesheets/demo.css' media='all' rel='stylesheet' type='text/css' />
</head>
<body class='contrast-red '>
<!-- 头部导航开始 -->
<header>
    <div class='navbar'>
        <div class='navbar-inner'>
            <div class='container-fluid'>
                <a class='brand' href='index.jsp'>
                    <i class='icon-leaf'></i>
                    <span class='hidden-phone'>中宇万通产品中心管理系统</span>
                </a>
                <a class='toggle-nav btn pull-left' href='#'>
                    <i class='icon-reorder'></i>
                </a>
                <ul class='nav pull-right'>
                 	<!-- 消息提示开始 -->
<!--                     <li class='dropdown medium only-icon widget'> -->
<!--                         <a class='dropdown-toggle' data-toggle='dropdown' href='#'> -->
<!--                             <i class='icon-bell-alt'></i> -->
<!--                             <div class='label'>3</div> -->
<!--                         </a> -->
<!--                         <ul class='dropdown-menu'> -->
<!--                             <li> -->
<!--                                 <a href='#'> -->
<!--                                     <div class='widget-body'> -->
<!--                                         <div class='pull-left icon'> -->
<!--                                             <i class='icon-comment text-success'></i> -->
<!--                                         </div> -->
<!--                                         <div class='pull-left text'> -->
<!--                                            		大师兄，  师傅被妖怪抓走了。。。 -->
<!--                                             <small class='muted'>1 hour ago</small> -->
<!--                                         </div> -->
<!--                                     </div> -->
<!--                                 </a> -->
<!--                             </li> -->
<!--                             <li class='divider'></li> -->
<!--                             <li> -->
<!--                                 <a href='#'> -->
<!--                                     <div class='widget-body'> -->
<!--                                         <div class='pull-left icon'> -->
<!--                                             <i class='icon-comment text-success'></i> -->
<!--                                         </div> -->
<!--                                         <div class='pull-left text'> -->
<!--                                             	二师兄， 师傅被妖怪抓走了。。。 -->
<!--                                             <small class='muted'>last week</small> -->
<!--                                         </div> -->
<!--                                     </div> -->
<!--                                 </a> -->
<!--                             </li> -->
<!--                             <li class='divider'></li> -->
<!--                             <li> -->
<!--                                 <a href='#'> -->
<!--                                     <div class='widget-body'> -->
<!--                                         <div class='pull-left icon'> -->
<!--                                             <i class='icon-comment text-success'></i> -->
<!--                                         </div> -->
<!--                                         <div class='pull-left text'> -->
<!--                                           		 大师兄，师傅抓了只妖怪回来了。。。 -->
<!--                                             <small class='muted'>1 year ago</small> -->
<!--                                         </div> -->
<!--                                     </div> -->
<!--                                 </a> -->
<!--                             </li> -->
<!--                             <li class='widget-footer'> -->
<!--                                 <a href='#'>所有消息</a> -->
<!--                             </li> -->
<!--                         </ul> -->
<!--                     </li> -->
                    <!-- 消息提示结束-->
                    <!-- 用户信息开始 -->
                    <li class='dropdown dark user-menu'>
                        <a class='dropdown-toggle' data-toggle='dropdown'>
                            <img alt='Mila Kunis' height='23' src='assets/images/Koala.jpg' width='23' />
                            <span class='user-name hidden-phone' id="userName_span">用户</span>
                            <b class='caret'></b>
                        </a>
                        <ul class='dropdown-menu'>
<!--                             <li> -->
<!--                                 <a href='user_profile.html'> -->
<!--                                     <i class='icon-user'></i> -->
<!--                                    		 个人信息 -->
<!--                                 </a> -->
<!--                             </li> -->
<!--                             <li> -->
<!--                                 <a href='user_profile.html'> -->
<!--                                     <i class='icon-cog'></i> -->
<!--                                  		   设置 -->
<!--                                 </a> -->
<!--                             </li> -->
<!--                             <li class='divider'></li> -->
                            <li>
                                <a href='index.jsp'>
                                    <i class='icon-cog'></i>
                                 		   升级包管理
                                </a>
                            </li>
                            <li>
                                <a id="logoutBtn" href="#logoutComfirmModal" data-toggle="modal">
                                    <i class='icon-signout'></i>
                                    	注销
                                </a>
                            </li>
                        </ul>
                    </li>
                     <!-- 用户信息结束 -->
                </ul>
            </div>
        </div>
    </div>
</header>
 <!-- 头部导航结束 -->
 <!-- 左部导航开始 -->
<div id='wrapper'>
<div id='main-nav-bg'></div>
<nav class='' id='main-nav'>
<div class='navigation'>
<ul class='nav nav-stacked'>
<li class='active'>
    <a href='user-index.jsp'>
        <i class='icon-sitemap'></i>
        <span>用户控制台</span>
    </a>
</li>
</ul>
</div>
</nav>
<!-- 左部导航结束 -->
<!-- 内容开始 -->
<section id='content'>
<div class='container-fluid'>
<div class='row-fluid' id='content-wrapper'>
<div class='span12'>
<div class='page-header'>
    <h1 class='pull-left'>
        <i class='icon-sitemap'></i>
        <span>用户控制台</span>
    </h1>
</div>
<div class='row-fluid'>
    <div class='span12 box'>
    	<div class="span12" style=" margin-bottom:15px">
	    	<a id="addNewUserBtn" class="btn btn-success float_r" role="button" href="#addUserModal" data-toggle="modal"><i class="icon-plus"></i>新增</a> 
	    </div>
    	<!-- 数据展示开始 -->
        <div class='row-fluid'>
			<div class='span12 box bordered-box orange-border'>
				<div class='box-header blue-background'>
				    <div class='title'>用户信息列表</div>
				    <div class='actions'>
				        <a href="#" class="btn box-remove btn-mini btn-link"><i class='icon-remove'></i>
				        </a>
				        <a href="#" class="btn box-collapse btn-mini btn-link"><i></i>
				        </a>
				    </div>
				</div>
				<!-- table开始 -->
				<div class='box-content box-no-padding'>
					<div class='responsive-table'>
						<div class='scrollable-area'>
							<!-- 表格数据开始 -->
							<table class='table table-bordered table-hover table-striped' >
		                        <thead>
			                        <tr>
			                            <th>序列号</th>
			                            <th>用户账号</th>
			                            <th>用户邮箱</th>
			                            <th>真实姓名</th>
			                            <th>用户权限</th>
			                            <th>项目权限</th>
			                            <th>操作</th>
			                        </tr>
		                        </thead>
		                        <tbody id="tbody" role="alert" aria-live="polite" aria-relevant="all">
			                        <!-- 数据 -->
		                        </tbody>
	                        </table>
	                        <!-- 表格数据结束 -->
						</div>
					</div>
				</div>
				<!-- table结束 -->
			</div>
		</div>
	<!-- 数据展示结束 -->
    </div>
</div>
<hr class='hr-drouble' />
<!-- footer开始 -->
<div class='row-fluid'>
    <div class='span6 offset3'>
        <div class='text-center'>
            <small class='muted'>版权所有(C) ZYWT Rights Reserved.Author Tangwe</small>
        </div>
    </div>
</div>
<!-- footer结束 -->
</div>
</div>
</div>
</section>
<!-- 内容结束 -->
</div>
<!-- / jquery -->
<script src='assets/javascripts/jquery/jquery.min.js' type='text/javascript'></script>
<!-- / jquery mobile events (for touch and slide) -->
<script src='assets/javascripts/plugins/mobile_events/jquery.mobile-events.min.js' type='text/javascript'></script>
<!-- / jquery migrate (for compatibility with new jquery) -->
<script src='assets/javascripts/jquery/jquery-migrate.min.js' type='text/javascript'></script>
<!-- / jquery ui -->
<script src='assets/javascripts/jquery_ui/jquery-ui.min.js' type='text/javascript'></script>
<!-- / bootstrap -->
<script src='assets/javascripts/bootstrap/bootstrap.min.js' type='text/javascript'></script>
<script src='assets/javascripts/plugins/flot/excanvas.js' type='text/javascript'></script>
<!-- / sparklines -->
<script src='assets/javascripts/plugins/sparklines/jquery.sparkline.min.js' type='text/javascript'></script>
<!-- / flot charts -->
<script src='assets/javascripts/plugins/flot/flot.min.js' type='text/javascript'></script>
<script src='assets/javascripts/plugins/flot/flot.resize.js' type='text/javascript'></script>
<script src='assets/javascripts/plugins/flot/flot.pie.js' type='text/javascript'></script>
<!-- / bootstrap switch -->
<script src='assets/javascripts/plugins/bootstrap_switch/bootstrapSwitch.min.js' type='text/javascript'></script>
<!-- / fullcalendar -->
<script src='assets/javascripts/plugins/fullcalendar/fullcalendar.min.js' type='text/javascript'></script>
<!-- / datatables -->
<script src='assets/javascripts/plugins/datatables/jquery.dataTables.min.js' type='text/javascript'></script>
<script src='assets/javascripts/plugins/datatables/jquery.dataTables.columnFilter.js' type='text/javascript'></script>
<!-- / wysihtml5 -->
<script src='assets/javascripts/plugins/common/wysihtml5.min.js' type='text/javascript'></script>
<script src='assets/javascripts/plugins/common/bootstrap-wysihtml5.js' type='text/javascript'></script>
<!-- / select2 -->
<script src='assets/javascripts/plugins/select2/select2.js' type='text/javascript'></script>
<!-- / color picker -->
<script src='assets/javascripts/plugins/bootstrap_colorpicker/bootstrap-colorpicker.min.js' type='text/javascript'></script>
<!-- / mention -->
<script src='assets/javascripts/plugins/mention/mention.min.js' type='text/javascript'></script>
<!-- / input mask -->
<script src='assets/javascripts/plugins/input_mask/bootstrap-inputmask.min.js' type='text/javascript'></script>
<!-- / fileinput -->
<script src='assets/javascripts/plugins/fileinput/bootstrap-fileinput.js' type='text/javascript'></script>
<!-- / modernizr -->
<script src='assets/javascripts/plugins/modernizr/modernizr.min.js' type='text/javascript'></script>
<!-- / retina -->
<script src='assets/javascripts/plugins/retina/retina.js' type='text/javascript'></script>
<!-- / fileupload -->
<script src='assets/javascripts/plugins/fileupload/tmpl.min.js' type='text/javascript'></script>
<script src='assets/javascripts/plugins/fileupload/load-image.min.js' type='text/javascript'></script>
<script src='assets/javascripts/plugins/fileupload/canvas-to-blob.min.js' type='text/javascript'></script>
<script src='assets/javascripts/plugins/fileupload/jquery.iframe-transport.min.js' type='text/javascript'></script>
<script src='assets/javascripts/plugins/fileupload/jquery.fileupload.min.js' type='text/javascript'></script>
<script src='assets/javascripts/plugins/fileupload/jquery.fileupload-fp.min.js' type='text/javascript'></script>
<script src='assets/javascripts/plugins/fileupload/jquery.fileupload-ui.min.js' type='text/javascript'></script>
<script src='assets/javascripts/plugins/fileupload/jquery.fileupload-init.js' type='text/javascript'></script>
<!-- / timeago -->
<script src='assets/javascripts/plugins/timeago/jquery.timeago.js' type='text/javascript'></script>
<!-- / slimscroll -->
<script src='assets/javascripts/plugins/slimscroll/jquery.slimscroll.min.js' type='text/javascript'></script>
<!-- / autosize (for textareas) -->
<script src='assets/javascripts/plugins/autosize/jquery.autosize-min.js' type='text/javascript'></script>
<!-- / charCount -->
<script src='assets/javascripts/plugins/charCount/charCount.js' type='text/javascript'></script>
<!-- / validate -->
<script src='assets/javascripts/plugins/validate/jquery.validate.min.js' type='text/javascript'></script>
<script src='assets/javascripts/plugins/validate/additional-methods.js' type='text/javascript'></script>
<!-- / naked password -->
<script src='assets/javascripts/plugins/naked_password/naked_password-0.2.4.min.js' type='text/javascript'></script>
<!-- / nestable -->
<script src='assets/javascripts/plugins/nestable/jquery.nestable.js' type='text/javascript'></script>
<!-- / tabdrop -->
<script src='assets/javascripts/plugins/tabdrop/bootstrap-tabdrop.js' type='text/javascript'></script>
<!-- / jgrowl -->
<script src='assets/javascripts/plugins/jgrowl/jquery.jgrowl.min.js' type='text/javascript'></script>
<!-- / bootbox -->
<script src='assets/javascripts/plugins/bootbox/bootbox.min.js' type='text/javascript'></script>
<!-- / inplace editing -->
<script src='assets/javascripts/plugins/xeditable/bootstrap-editable.min.js' type='text/javascript'></script>
<script src='assets/javascripts/plugins/xeditable/wysihtml5.js' type='text/javascript'></script>
<!-- / ckeditor -->
<script src='assets/javascripts/plugins/ckeditor/ckeditor.js' type='text/javascript'></script>
<!-- / filetrees -->
<script src='assets/javascripts/plugins/dynatree/jquery.dynatree.min.js' type='text/javascript'></script>
<!-- / datetime picker -->
<script src='assets/javascripts/plugins/bootstrap_datetimepicker/bootstrap-datetimepicker.js' type='text/javascript'></script>
<!-- / daterange picker -->
<script src='assets/javascripts/plugins/bootstrap_daterangepicker/moment.min.js' type='text/javascript'></script>
<script src='assets/javascripts/plugins/bootstrap_daterangepicker/bootstrap-daterangepicker.js' type='text/javascript'></script>
<!-- / max length -->
<script src='assets/javascripts/plugins/bootstrap_maxlength/bootstrap-maxlength.min.js' type='text/javascript'></script>
<!-- / dropdown hover -->
<script src='assets/javascripts/plugins/bootstrap_hover_dropdown/twitter-bootstrap-hover-dropdown.min.js' type='text/javascript'></script>
<!-- / slider nav (address book) -->
<script src='assets/javascripts/plugins/slider_nav/slidernav-min.js' type='text/javascript'></script>
<!-- / fuelux -->
<script src='assets/javascripts/plugins/fuelux/wizard.js' type='text/javascript'></script>
<!-- / flatty theme -->
<script src='assets/javascripts/nav.js' type='text/javascript'></script>
<script src='assets/javascripts/tables.js' type='text/javascript'></script>
<script src='assets/javascripts/theme.js' type='text/javascript'></script>
<!-- / demo -->
<script src='assets/javascripts/demo/jquery.mockjax.js' type='text/javascript'></script>
<script src='assets/javascripts/demo/inplace_editing.js' type='text/javascript'></script>
<script src='assets/javascripts/demo/charts.js' type='text/javascript'></script>
<script src='assets/javascripts/demo/demo.js' type='text/javascript'></script>

<!-- /用户js -->
<script src='js/upm-util.js' type='text/javascript'></script>
<script src='js/upm-template.js' type='text/javascript'></script>
<script src='js/upm-user.js' type='text/javascript'></script>
<script src='js/user.js' type='text/javascript'></script>
</body>
</html>