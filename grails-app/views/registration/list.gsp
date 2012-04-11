<%@ page import="lotto.Registration" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'registration.label', default: 'Registration')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-registration" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                   default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <sec:access controller='registration' action='create'>
            <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                                  args="[entityName]"/></g:link></li>
        </sec:access>
        <sec:ifNotGranted roles="ROLE_ADMIN">
            <g:set var="lotteryName" value="${message(code: 'lottery.label', default: 'Lottery')}"/>
            <li><g:link class="list" controller="lottery" action="list"><g:message code="default.list.label"
                                                                                   args="[lotteryName]"/></g:link></li>
        </sec:ifNotGranted>
    </ul>
</div>

<lotto:isAdmin>
    <g:render template="/registration/adminList"/>
</lotto:isAdmin>
<lotto:isNotAdmin>
    <g:render template="/registration/list"/>
</lotto:isNotAdmin>

</body>
</html>
