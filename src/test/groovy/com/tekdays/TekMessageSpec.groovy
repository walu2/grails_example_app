package com.tekdays

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(TekMessage)
@Mock([TekEvent, TekUser])
class TekMessageSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "message is valid"() {
        when: "message has no properties"
        def m = new TekMessage()

        then: "validation should fail"
        !m.validate()
        m.hasErrors()
        m.errors.errorCount == 4
        m.errors['subject'].code == 'nullable'
        m.errors['content'].code == 'nullable'
        m.errors['event'].code == 'nullable'
        m.errors['author'].code == 'nullable'

        when: "subject, content, event and author are provide correctly"
        m = new TekMessage( subject: 'subject',
                            content: 'content',
                            event: new TekEvent(),
                            author: new TekUser())

        then: "validation should pass"
        m.validate()
    }

    void "test toString"() {

        given: "a tekMessage"
        def tekMessage = new TekMessage(
                subject: 'Great News!',
                content: 'Here is the reason for our great news!',
                event: new TekEvent(),
                author: new TekUser(fullName: 'Joe Bings')
                )

        when: "the tekMessage is valid"
        tekMessage.validate()

        then: "the toString method will display the subject"
        tekMessage.toString() == 'Great News!'
    }
}