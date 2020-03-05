<%@page contentType="text/html; charset=utf-8" %><%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div id="content">
    <div id="content-header">
        <div id="breadcrumb">
            <a href="<c:url value="/admin/index"/>" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> ${Lang['generic.home']}</a> 
            <a cl href="<c:url value="/admin/sys-account/list"/>" class="tip-bottom">${Lang['system.account.subMenu']}</a>
            <a cl href="#" class="current">${Lang['system.account.subMenu.add']}</a>
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
                        <form:form  id="addAccfrm" action="" modelAttribute="sysAcc" method="post">
                            <table class="table table-bordered table-striped">
                                <tbody>
                                    <tr>
                                        <td class="span3">${Lang['generic.userName']}</td>
                                        <td>
                                            <input value="${sysAcc.username}" autocomplete="off" type="text" name="username" class="span7" placeholder="username"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>${Lang['generic.password']}</td>
                                        <td>
                                            <input value="kdie#Ak12dieE$" autocomplete="off"  type="password" name="password" cssClass="span7" placeholder="password"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>${Lang['generic.fullName']}</td>
                                        <td>
                                            <input value="${sysAcc.full_name}" type="text" name="full_name" size="55" class="text-input span7" placeholder="Nguyễn Văn A hoặc Jhon Smith"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>${Lang['generic.phone']}</td>
                                        <td>
                                            <input value="${sysAcc.phone}" type="text" name="phone" class="span7" placeholder="0988988988"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>${Lang['generic.email']}</td>
                                        <td>
                                            <input value="${sysAcc.email}" type="text" name="email" size="25" class="text-input span7" placeholder="abc@aef.xyz"/>
                                        </td>
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
                                        <td><textarea class="span7" name="address">${sysAcc.address}</textarea></td>
                                    </tr>
                                    <tr>
                                        <td>${Lang['generic.status']}</td>
                                        <td>
                                            <form:select path="status">
                                                <form:option value="${status.ACTIVE}">Kích hoạt </form:option>
                                                <form:option value="${status.UNACTIVE}">Chưa kích hoạt</form:option>
                                                <form:option value="${status.LOCK}">Khóa</form:option>
                                            </form:select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Mô tả</td>
                                        <td><textarea class="span7" name="description">${sysAcc.description}</textarea></td>
                                    </tr>
                                    <tr>
                                        <td class="img-center" colspan="2" align="center">
                                            <input value="${Lang['generic.addNew']}" type="submit" id="btnSubmit" class="btn btn-success" name="submit" />
                                            &nbsp;&nbsp;&nbsp;
                                            <input value="${Lang['generic.back']}" class="btn btn-danger" onclick="location.href = '<c:url value="/admin/sys-account/list" />'" type="button">
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
<script src="<c:url value='/admin/js/jquery.validate.js'/>"></script>
<script type="text/javascript">
var pasReg = /^(?=.*[0-9])(?=.*[!@#$%^&*()])(?=.*[A-Z])[a-zA-Z0-9!@#$%^&*]{8,30}$/;
$("#addAccfrm").validate({
    rules: {
        username: "required", fullName: "required", phone: "required",
        email: {
            required: true,
            email: true
        },
        password: {
            required: true,
            maxlength: 30,
//            regex: pasReg
        }
    },
    messages: {
        username: "Bạn chưa nhập UserName",
        password: {
            required: "Hãy cung cấp Mật khẩu",
            minlength: "Mật khẩu phải có it nhất 8 ký tự",
            regex: "Mật khẩu phải bao gồm chữ hoa chữ thường, ký tự đặc biệt và số Ex: kdie#Ak12dieE$"
        }
    }   // Ong này sẽ gây ra lỗi too much recursion
//            ,
//            submitHandler: function (form) {
//                $(form).submit();
//            }
});
$.validator.addMethod("regex", function (value, element, regexpr) {
    return regexpr.test(value);
});
</script>
