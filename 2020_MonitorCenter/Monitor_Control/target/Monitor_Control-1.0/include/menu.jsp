<%@ page contentType="text/html; charset=utf-8" %><%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--Header-part-->
<div id="header">
    <h1><a href="dashboard.html">Maruti Admin</a></h1>
</div>
<!--close-Header-part--> 

<!--top-Header-messaages-->
<div class="btn-group rightzero"> <a class="top_message tip-left" title="Manage Files"><i class="icon-file"></i></a> <a class="top_message tip-bottom" title="Manage Users"><i class="icon-user"></i></a> <a class="top_message tip-bottom" title="Manage Comments"><i class="icon-comment"></i><span class="label label-important">5</span></a> <a class="top_message tip-bottom" title="Manage Orders"><i class="icon-shopping-cart"></i></a> </div>
<!--close-top-Header-messaages--> 

<!--top-Header-menu-->
<div id="user-nav" class="navbar navbar-inverse">
    <ul class="nav">
        <li class="" ><a title="" href="<c:url value="/admin/sys-account/updateInfo"/>"><i class="icon icon-user"></i> <span class="text">${userlogin.username}</span></a></li>
        <li class=" dropdown" id="menu-messages"><a href="#" data-toggle="dropdown" data-target="#menu-messages" class="dropdown-toggle"><i class="icon icon-envelope"></i> <span class="text">Messages</span> <span class="label label-important">5</span> <b class="caret"></b></a>
            <ul class="dropdown-menu">
                <li><a class="sAdd" title="" href="#">new message</a></li>
                <li><a class="sInbox" title="" href="#">inbox</a></li>
                <li><a class="sOutbox" title="" href="#">outbox</a></li>
                <li><a class="sTrash" title="" href="#">trash</a></li>
            </ul>
        </li>
        <li class=""><a title="" href="#"><i class="icon icon-cog"></i> <span class="text">Settings</span></a></li>
        <li class=""><a title="" href="<c:url value="/admin/logout"/>"><i class="icon icon-share-alt"></i> <span class="text">Logout</span></a></li>
    </ul>
</div>
<!--<div id="search">
    <input type="text" placeholder="Search here..."/>
    <button type="submit" class="tip-left" title="Search"><i class="icon-search icon-white"></i></button>
</div>-->
<!--close-top-Header-menu-->

<div id="sidebar"> 
    <a href="#" class="visible-phone"><i class="icon icon-signal"></i> Charts &amp; graphs</a>
    <ul>
        <li class="submenu"> <a href="<c:url value="/admin/index"/>"><i class="icon icon-th-list"></i> <span>Trang chủ</span> </a> </li>
            <c:if test="${accService.accessModule('/admin/sys-account',pageContext.request)
                          ||accService.accessModule('/admin/manager-role',pageContext.request)
                          ||accService.accessModule('/admin/manager-action',pageContext.request) 
                          || accService.accessModule('/admin/module',pageContext.request)
                          ||accService.accessModule('/admin/screen-path',pageContext.request)
                          || accService.accessModule('/admin/config-category',pageContext.request)
                          ||accService.accessModule('/admin/user-action',pageContext.request)}">
            <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>${Lang['menu.system.main']}</span> <span class="label label-important">3</span></a>
                <ul>
                    <c:if test="${accService.accessModule('/admin/sys-account',pageContext.request)}"><li><a href="<c:url value="/admin/sys-account/list"/>">${Lang['menu.system.account']}</a></li></c:if>
                    <c:if test="${accService.accessModule('/admin/manager-role',pageContext.request)}"><li><a href="<c:url value="/admin/manager-role/list"/>">${Lang['menu.system.role']}</a></li></c:if>
                    <c:if test="${accService.accessModule('/admin/manager-action',pageContext.request)}"><li><a href="<c:url value="/admin/manager-action/list"/>">${Lang['menu.system.moduleaction']}</a></li></c:if>
                    <c:if test="${accService.accessModule('/admin/screen-path',pageContext.request)}"><li><a href="<c:url value="/admin/screen-path/list"/>">${Lang['menu.system.screenpath']}</a></li></c:if>
                    <c:if test="${accService.accessModule('/admin/module',pageContext.request)}"><li><a href="<c:url value="/admin/module/list"/>">${Lang['menu.system.module']}</a></li></c:if>
                    <c:if test="${accService.accessModule('/admin/config-category',pageContext.request)}"><li><a href="<c:url value="/admin/config-category/list"/>">${Lang['menu.system.configcategory']}</a></li></c:if>
                    <c:if test="${accService.accessModule('/admin/user-action',pageContext.request)}"><li><a href="<c:url value="/admin/user-action/list"/>">${Lang['menu.system.logaction']}</a></li></c:if>
                    </ul>
                </li>
        </c:if>
        <c:if test="${accService.accessModule('/admin/country',pageContext.request)
                      ||accService.accessModule('/admin/nationality',pageContext.request)
                      ||accService.accessModule('/admin/countrylanguage',pageContext.request)
                      ||accService.accessModule('/admin/city/list',pageContext.request)
                      ||accService.accessModule('/admin/district',pageContext.request)
                      ||accService.accessModule('/admin/town/list',pageContext.request)
                      ||accService.accessModule('/admin/timezones',pageContext.request)
                      ||accService.accessModule('/admin/timezone-config',pageContext.request)
                      ||accService.accessModule('/admin/timeformat-conf',pageContext.request)
                      ||accService.accessModule('/admin/dateformat-conf',pageContext.request)
                      ||accService.accessModule('/admin/user-type',pageContext.request)
              }">
            <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>${Lang['menu.config.main']}</span> <span class="label label-important">4</span></a>
                <ul>
                    <c:if test="${accService.accessModule('/admin/country',pageContext.request)}"><li><a href="<c:url value="/admin/country/list"/>">${Lang['menu.config.country']}</a></li></c:if>
                    <c:if test="${accService.accessModule('/admin/nationality',pageContext.request)}"><li><a href="<c:url value="/admin/nationality/list"/>">${Lang['menu.config.nationality']}</a></li></c:if>
                    <c:if test="${accService.accessModule('/admin/countrylanguage',pageContext.request)}"><li><a href="<c:url value="/admin/countrylanguage/list"/>">${Lang['menu.config.countrylanguage']}</a></li></c:if>
                    <c:if test="${accService.accessModule('/admin/city',pageContext.request)}"><li><a href="<c:url value="/admin/city/list"/>">${Lang['menu.config.city']}</a></li></c:if>
                    <c:if test="${accService.accessModule('/admin/district',pageContext.request)}"><li><a href="<c:url value="/admin/district/list"/>">${Lang['menu.config.district']}</a></li></c:if>
                    <c:if test="${accService.accessModule('/admin/town',pageContext.request)}"><li><a href="<c:url value="/admin/town/list"/>">${Lang['menu.config.town']}</a></li></c:if>
                    <c:if test="${accService.accessModule('/admin/timezones',pageContext.request)}"><li><a href="<c:url value="/admin/timezones/list"/>">${Lang['menu.config.timezones']}</a></li></c:if>
                    <c:if test="${accService.accessModule('/admin/timezone-config',pageContext.request)}"><li><a href="<c:url value="/admin/timezone-config/list"/>">${Lang['menu.config.timezoneConfig']}</a></li></c:if>
                    <c:if test="${accService.accessModule('/admin/timeformat-conf',pageContext.request)}"><li><a href="<c:url value="/admin/timeformat-conf/list"/>">${Lang['menu.config.timeformatConf']}</a></li></c:if>
                    <c:if test="${accService.accessModule('/admin/dateformat-conf',pageContext.request)}"><li><a href="<c:url value="/admin/dateformat-conf/list"/>">${Lang['menu.config.dateformatConf']}</a></li></c:if>
                    <c:if test="${accService.accessModule('/admin/user-type',pageContext.request)}"><li><a href="<c:url value="/admin/user-type/list"/>">${Lang['menu.config.userType']}</a></li></c:if>
                    </ul>
                </li>
        </c:if>
        <c:if test="${accService.accessModule('/admin/company',pageContext.request)
                      || accService.accessModule('/admin/branch',pageContext.request)
                      || accService.accessModule('/admin/bank-info',pageContext.request)
                      || accService.accessModule('/admin/department',pageContext.request)
              }">
            <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>${Lang['menu.company.main']}</span> <span class="label label-important">3</span></a>
                <ul>
                    <c:if test="${accService.accessModule('/admin/company',pageContext.request)}"><li><a href="<c:url value="/admin/company/list"/>">${Lang['menu.company.company']}</a></li></c:if>
                    <c:if test="${accService.accessModule('/admin/branch',pageContext.request)}"><li><a href="<c:url value="/admin/branch/list"/>">${Lang['menu.company.branch']}</a></li></c:if>
                    <c:if test="${accService.accessModule('/admin/bank-info',pageContext.request)}"><li><a href="<c:url value="/admin/bank-info/list"/>">${Lang['menu.company.bankInfo']}</a></li></c:if>
                    <c:if test="${accService.accessModule('/admin/department',pageContext.request)}"><li><a href="<c:url value="/admin/department/list"/>">${Lang['menu.company.department']}</a></li></c:if>
                    </ul>
                </li>
        </c:if>
        <c:if test="${accService.accessModule('/admin/employee',pageContext.request)
                      || accService.accessModule('/admin/emp-account',pageContext.request)
                      || accService.accessModule('/admin/emp-children',pageContext.request)
                      || accService.accessModule('/admin/emp-termination',pageContext.request)
              }">
            <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>${Lang['menu.employee.main']}</span> <span class="label label-important">3</span></a>
                <ul>
                    <c:if test="${accService.accessModule('/admin/employee',pageContext.request)}"><li><a href="<c:url value="/admin/employee/list"/>">${Lang['menu.employee.employee']}</a></li></c:if>
                    <c:if test="${accService.accessModule('/admin/emp-account',pageContext.request)}"><li><a href="<c:url value="/admin/emp-account/list"/>">${Lang['menu.employee.empAccount']}</a></li></c:if>
                    <c:if test="${accService.accessModule('/admin/emp-children',pageContext.request)}"><li><a href="<c:url value="/admin/emp-children/list"/>">${Lang['menu.employee.empChildren']}</a></li></c:if>
                    <c:if test="${accService.accessModule('/admin/emp-termination',pageContext.request)}"><li><a href="<c:url value="/admin/emp-termination/list"/>">${Lang['menu.employee.empTermination']}</a></li></c:if>
                    </ul>
                </li>
        </c:if>
        <c:if test="${accService.accessModule('/admin/emp-termination-reseason',pageContext.request)
                      || accService.accessModule('/admin/position',pageContext.request)
                      || accService.accessModule('/admin/job-title',pageContext.request)
                      || accService.accessModule('/admin/gender',pageContext.request)
                      || accService.accessModule('/admin/marital-status',pageContext.request)
              }">
            <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>Quản lý Cấu hình </span> <span class="label label-important">4</span></a>
                <ul>
                    <c:if test="${accService.accessModule('/admin/emp-termination-reseason',pageContext.request)}"><li><a href="<c:url value="/admin/emp-termination-reseason/list"/>">Lý do nghỉ việc</a></li></c:if>
                    <c:if test="${accService.accessModule('/admin/position',pageContext.request)}"><li><a href="<c:url value="/admin/position/list"/>">QL Chức vụ</a></li></c:if>
                    <c:if test="${accService.accessModule('/admin/job-title',pageContext.request)}"><li><a href="<c:url value="/admin/job-title/list"/>">QL Nghề nghiệp</a></li></c:if>
                    <c:if test="${accService.accessModule('/admin/gender',pageContext.request)}"><li><a href="<c:url value="/admin/gender/list"/>">QL Giới Tính</a></li></c:if>
                    <c:if test="${accService.accessModule('/admin/marital-status',pageContext.request)}"><li><a href="<c:url value="/admin/marital-status/list"/>">Tình trạng hôn nhân</a></li></c:if>
                    </ul>
                </li>
        </c:if>
    </ul>
</div>
