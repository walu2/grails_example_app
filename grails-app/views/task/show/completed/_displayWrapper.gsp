<li class="fieldcontain">
    <span id="${label.toLowerCase()}-label" class="property-label">
    	<g:message code="${label}" default="${label}" />
    </span>
    <div class="property-value" aria-labelledby="${label.toLowerCase()}-label">
    	<g:formatBoolean bean="${task}" name="${property}" 
    		boolean="${value}" true="Yes" false="No"/>
    </div>
</li>