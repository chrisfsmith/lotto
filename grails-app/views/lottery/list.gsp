<%@ page import="lotto.Lottery" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'lottery.label', default: 'Lottery')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-lottery" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                              default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <sec:access controller='lottery' action='create'>
            <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                                  args="[entityName]"/></g:link></li>
        </sec:access>
        <sec:ifNotGranted roles="ROLE_ADMIN">
            <g:set var="regName" value="${message(code: 'registration.label', default: 'Registration')}"/>
            <li><g:link class="list" controller="registration" action="list"><g:message code="default.list.label"
                                                                                        args="[regName]"/></g:link></li>
        </sec:ifNotGranted>
    </ul>
</div>

<lotto:isAdmin>
    <g:render template="/lottery/adminList"/>
</lotto:isAdmin>
<lotto:isNotAdmin>
    <g:render template="/lottery/list"/>
</lotto:isNotAdmin>

</body>
</html>
