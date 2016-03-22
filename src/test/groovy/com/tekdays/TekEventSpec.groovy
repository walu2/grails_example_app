package com.tekdays

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(TekEvent)
class TekEventSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
	
	void "test toString"() {
		when: "a tekEvent has a name and a city"
				def tekEvent = new TekEvent(name:'Groovy One', : 'San Francisco', organizer: 'John Doe')
		then: "the toString method will combine them."
				tekEvent.toString() == 'Groovy One, San Francisco'
	}
}
