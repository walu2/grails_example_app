<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main" />
	<g:set var="entityName" value="${message(code: 'tekEvent.label', default: 'TekEvent')}" />
	<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
	<a href="#show-tekEvent" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
	<div class="nav" role="navigation">
		<ul>
			<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
		</ul>
	</div>
	<div id="show-tekEvent" class="content scaffold-show" role="main">
		<h1><g:message code="default.show.label" args="[entityName]" /></h1>
		<g:if test="${flash.message}">
		<div class="message" role="status">${flash.message}</div>
	</g:if>
	<f:display bean="tekEvent" />
	<g:form resource="${this.tekEvent}" method="DELETE">
	<fieldset class="buttons">
		<g:link class="edit" action="edit" resource="${this.tekEvent}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
		<input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
	</fieldset>
</g:form>
</div>
<g:if test="${tekEventInstance?.city}">
<li class="fieldcontain">
	<span id="city-label" class="property-label">
</span>
<span class="property-value" aria-labelledby="city-label">
	<g:fieldValue bean="${tekEventInstance}" field="venue"/>,
	<g:fieldValue bean="${tekEventInstance}" field="city"/>
</span>
</li>
</g:if>
</body>
</html>
