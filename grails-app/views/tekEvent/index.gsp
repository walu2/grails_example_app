<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main" />
	<g:set var="entityName" value="${message(code: 'tekEvent.label', default: 'TekEvent')}" />
	<title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
		
	<thead>
		<tr>
			<g:sortableColumn property="name" title="${message(code: 'tekEvent.name.label',
			default: 'Name')}" />
			<g:sortableColumn property="city"
			title="${message(code: 'tekEvent.city.label',
			default: 'City')}" />
			<g:sortableColumn property="description"
			title="${message(code: 'tekEvent.description.label',
			default: 'Description')}" />
			<g:sortableColumn property="venue"
			title="${message(code: 'tekEvent.venue.label',
			default: 'Venue')}" />
			<g:sortableColumn property="startDate"
			title="${message(code: 'tekEvent.startDate.label',
			default: 'Start Date')}" />
		</tr>
	</thead>
	<tbody>
		<g:each in="${tekEventInstanceList}" status="i"
		var="tekEventInstance">
		<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
			<td><g:link action="show"
				id="${tekEventInstance.id}">
				${fieldValue(bean: tekEventInstance,
					field: "name")}</g:link>
				</td>
				<td>${fieldValue(bean: tekEventInstance,
					field: "city")}</td>
					<td>${fieldValue(bean: tekEventInstance,
						field: "description")}
					</td>
					<td>${fieldValue(bean: tekEventInstance,
						field: "venue")}</td>
						<td><g:formatDate
							date="${tekEventInstance.startDate}" />
						</td>
					</tr>
				</g:each>
			</tbody>
		</body>
		</html>