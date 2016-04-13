package com.tekdays

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(TekEvent)
class TekEventSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "tekEvent is valid"(){
        when:"tekEvent has no properties"
        def t = new TekEvent()

        then: "validation should fail"
        !t.validate()
        t.hasErrors()
        t.errors.errorCount == 3
        t.errors['name'].code == 'nullable'
        t.errors['city'].code == 'nullable'
        t.errors['organizer'].code == 'nullable'

        when: "name, city and organizer are provided correctly"
        t = new TekEvent(   name: 'name',
                            city: 'city',
                            organizer: new TekUser())

        then: "validation should fail"
        t.validate();

    }

    void "test toString"() {

        given: "a TekEvent"
        def tekEvent = new TekEvent(name: 'Groovy One',
                                    city: 'San Francisco',
                                    organizer: [fullName: 'John Doe'] as TekUser
                                    )

        when: "the tekEvent is valid"
        tekEvent.validate()

        then: "the toString method will combine the event name and city."
        tekEvent.toString() == 'Groovy One, San Francisco'
    }
}
