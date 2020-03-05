<%@page contentType="text/html; charset=utf-8" %><%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div id="content">
    <div id="content-header">
        <div id="breadcrumb">
            <a href="<c:url value="/admin/index"/>" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> ${Lang['generic.home']}</a> 
            <a cl href="<c:url value="/admin/sys-account"/>" class="tip-bottom">${Lang['system.account.subMenu']}</a>
            <a cl href="<c:url value="/admin/sys-account/edit-account?id=${sysAcc.id}"/>" class="current">${Lang['system.account.edit']}</a>
        </div>
    </div>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span12" style="padding-top: 5px">
                <c:if test="${result!=null && result.code!=1}">
                    <div id="alert alert-error" class="alert"><button class="close" data-dismiss="alert">×</button><strong>Error!</strong> ${result.message}</div>
                </c:if>
                <div class="widget-box">
                    <div class="widget-content nopadding">
                        <form:form id="editForm" method="post" modelAttribute="sysAcc"  action="">
                            <input type="hidden" name="id" value="${sysAcc.id}"/>
                            <table class="table table-bordered table-striped">
                                <tbody>
                                    <tr>
                                        <td class="span3">${Lang['generic.userName']}</td>
                                        <td>
                                            <input readonly="true" value="${sysAcc.username}" type="text" class="span10" name="username"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>${Lang['generic.password']}</td>
                                        <td><input autocomplete="off" name="password" class="text-input span10" size="25" value="" type="password"></td>
                                    </tr>
                                    <tr>
                                        <td>${Lang['generic.fullName']}</td>
                                        <td>
                                            <input value="${sysAcc.full_name}" class="text-input span10" size="55" name="full_name" type="text">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>${Lang['generic.phone']}</td>
                                        <td><input value="${sysAcc.phone}" type="text" class="span10" name="phone"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>${Lang['generic.email']}</td>
                                        <td><input name="email" value="${sysAcc.email}" class="text-input span10" size="25" type="text"></td>
                                    </tr>
                                    <tr>
                                        <td>Telegram</td>
                                        <td>
                                            <input value="${sysAcc.telegram}" type="text" name="telegram" size="25" class="text-input span7" placeholder="Tài khoản telegram của chủ sỡ hữu tài khoản"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Voice</td>
                                        <td>
                                            <input value="${sysAcc.voice}" type="text" name="voice" size="25" class="text-input span7" placeholder="Tài khoản voice của chủ sỡ hữu tài khoản"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>${Lang['generic.address']}</td>
                                        <td>
                                            <textarea class="span10" name="address">${sysAcc.address}</textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>${Lang['generic.status']}</td>
                                        <td>
                                            <form:select path="status">
                                                <form:option value="${status.ACTIVE}">${Lang['generic.status.active']}</form:option>
                                                <form:option value="${status.UNACTIVE}">${Lang['generic.status.unactive']}</form:option>
                                                <form:option value="${status.LOCK}">${Lang['generic.status.lock']}</form:option>
                                            </form:select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Mô tả</td>
                                        <td><textarea class="span7" name="description">${sysAcc.description}</textarea></td>
                                    </tr>
                                    <tr>
                                        <td colspan="6" class="img-center">
                                            <input id="btnSubmit" class="btn btn-success" name="submit" value="${Lang['generic.update']}" type="submit">
                                            <input onclick="location.href = '<c:url value="/admin/sys-account/list" />'" class="btn btn-danger" name="submit" value="${Lang['generic.back']}" type="button">
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="<c:url value='/admin/js/jquery.min.js'/>"></script>
<script src="<c:url value='/admin/js/bootstrap.min.js'/>"></script>
<script src="<c:url value='/admin/js/jquery.alerts.js'/>"></script>