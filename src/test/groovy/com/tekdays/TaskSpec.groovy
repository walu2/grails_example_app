package com.tekdays

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(Task)
@Mock(TekEvent)
class TaskSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "task is valid"(){
        when: "task has no properties"
        def t = new Task()

        then: "validation should fail"
        !t.validate()
        t.hasErrors()
        t.errors.errorCount == 2
        t.errors['title'].code == 'nullable'
        t.errors['event'].code == 'nullable'

        when: "title and event are provided correctly"
        t = new Task(   title: 'title',
                        event: new TekEvent())

        then: "validation should pass"
        t.validate()

    }
}
