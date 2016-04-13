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
            <h1>${tekEvent?.name}</h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>  

            <ol class="property-list tekEvent">             
                <g:if test="${tekEvent?.city}">
                <li class="fieldcontain">
                    <span id="city-label" class="property-label">Location</span>
                    <span class="property-value" aria-labelledby="city-label">
                        <f:display bean="tekEvent" property="venue"/>,
                        <f:display bean="tekEvent" property="city"/>
                    </span>
                </li>
                </g:if>

                <li class="fieldcontain">
                    <span id="description-label" class="property-label">Description</span>
                    <div class="property-value" aria-labelledby="description-label">
                        <f:display bean="tekEvent" property="description"/>
                    </div>
                </li>

                <li class="fieldcontain">
                    <span id="startDate-label" class="property-label">Start Date</span>
                    <div class="property-value" aria-labelledby="startDate-label">
                        <f:display bean="tekEvent" property="startDate">
                            <g:formatDate format="dd MMM yyyy" date="${tekEvent?.startDate}"/>
                        </f:display>                
                    </div>
                </li>

                <li class="fieldcontain">
                    <span id="endDate-label" class="property-label">End Date</span>
                    <div class="property-value" aria-labelledby="endDate-label">
                        <f:display bean="tekEvent" property="endDate">
                            <g:formatDate format="dd MMM yyyy" date="${tekEvent?.endDate}"/>
                        </f:display>                                        
                    </div>
                </li>

                <li class="fieldcontain">
                    <span id="volunteers-label" class="property-label">Volunteers</span>

                    <g:each in="${tekEvent.volunteers}" var="v">
                    <div class="property-value" aria-labelledby="volunteers-label">
                        <g:link controller="volunteer" action="show" id="${v.id}">
                            ${v?.encodeAsHTML()}
                        </g:link>
                    </div>
                    </g:each>
                </li>

                <li class="fieldcontain">
                    <span id="sponsorships-label" class="property-label">Sponsorships</span>

                    <g:each in="${tekEvent.sponsorships}" var="s">
                    <div class="property-value" aria-labelledby="sponsorships-label">                    
                        <g:link controller="sponsorship" action="show" id="${s.id}">
                            ${s?.sponsor.encodeAsHTML()}
                        </g:link>
                    </div>    
                    </g:each>
                </li>

                <li class="fieldcontain">
                    <span id="tasks-label" class="property-label">Tasks</span>

                    <g:each in="${tekEvent.tasks}" var="t">
                    <div class="property-value" aria-labelledby="tasks-label">                    
                        <g:link controller="task" action="show" id="${t.id}">
                            ${t.title}
                        </g:link>
                    </div>    
                    </g:each>
                </li>

                <li class="fieldcontain">
                    <span id="messages-label" class="property-label">Messages</span>
                    <div class="property-value" aria-labelledby="messages-label">
                        <f:display bean="tekEvent"property="messages"/>
                    </div>
                </li>                

                <li class="fieldcontain">
                    <span id="respondents-label" class="property-label">Respondents</span>
                    <div class="property-value" aria-labelledby="respondents-label">
                        <f:display bean="tekEvent" property="respondents"/>
                    </div>
                </li>   
            </ol>            

            <g:form resource="${this.tekEvent}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.tekEvent}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
