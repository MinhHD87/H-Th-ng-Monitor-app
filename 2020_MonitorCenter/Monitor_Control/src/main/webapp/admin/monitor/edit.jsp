<%@page contentType="text/html; charset=utf-8" %><%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div id="content">
    <div id="content-header">
        <div id="breadcrumb">
            <a href="<c:url value="/admin/index"/>" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> ${Lang['generic.home']}</a> 
            <a cl href="<c:url value="/admin/monitor"/>" class="tip-bottom">Quản lý monitor</a>
            <a cl href="<c:url value="/admin/monitor/edit-monitor?id=${mon.id}"/>" class="current">Cập nhật Monitor</a>
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
                        <form:form id="editForm" method="post" modelAttribute="mon"  action="">
                            <input type="hidden" name="id" value="${mon.id}"/>
                            <table class="table table-bordered table-striped">
                                <tbody>
                                    <tr>
                                        <td class="span3">Tên hệ thống</td>
                                        <td>
                                            <input value="${mon.name}" autocomplete="off" type="text" name="name" class="span7" placeholder="Nhập tên của hệ thống"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="span3">IP hệ thống</td>
                                        <td>
                                            <input value="${mon.ip}" autocomplete="off" type="text" name="ip" class="span7" placeholder="Nhập ip của hệ thống"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="span3">POS hệ thống</td>
                                        <td>
                                            <input value="${mon.pos}" autocomplete="off" type="text" name="pos" class="span7" placeholder="Nhập pos của hệ thống"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="span3">${Lang['generic.userName']}</td>
                                        <td>
                                            <input value="${mon.username}" autocomplete="off" type="text" name="username" class="span7" placeholder="username"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>${Lang['generic.password']}</td>
                                        <td>
                                            <input value="kdie#Ak12dieE$" autocomplete="off"  type="password" name="password" cssClass="span7" placeholder="password"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>ID của hệ thống cha</td>
                                        <td>
                                            <input value="${mon.parent}" type="text" name="parent" size="55" class="text-input span7" placeholder="Nhập id của tài khoản cha"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>${Lang['generic.phone']}</td>
                                        <td>
                                            <input value="${mon.phone}" type="text" name="phone" class="span7" placeholder="0988988988"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>${Lang['generic.email']}</td>
                                        <td>
                                            <input value="${mon.email}" type="text" name="email" size="25" class="text-input span7" placeholder="abc@aef.xyz"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Telegram</td>
                                        <td>
                                            <input value="${mon.telegram}" type="text" name="telegram" size="25" class="text-input span7" placeholder="Tài khoản telegram của chủ sỡ hữu tài khoản"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Voice</td>
                                        <td>
                                            <input value="${mon.voice}" type="text" name="voice" size="25" class="text-input span7" placeholder="Tài khoản voice của chủ sỡ hữu tài khoản"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Mô tả</td>
                                        <td><textarea class="span7" name="description">${mon.description}</textarea></td>
                                    </tr>
                                <td colspan="6" class="img-center">
                                    <input id="btnSubmit" class="btn btn-success" name="submit" value="${Lang['generic.update']}" type="submit">
                                    <input onclick="location.href = '<c:url value="/admin/monitor/list" />'" class="btn btn-danger" name="submit" value="${Lang['generic.back']}" type="button">
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