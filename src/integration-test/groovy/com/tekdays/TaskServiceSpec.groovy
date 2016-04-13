package com.tekdays

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.*

@TestFor(TaskService)
@Mock([TekUser, TekEvent, Task])
class TaskServiceSpec extends Specification {

    def setupData() {
        new TekUser(fullName:'Tammy Tester', userName:'tester' ,
                password: 'letmein',
                email:'tester@test.com' , website:'http://www.test.com' ,
                bio:'A test person').save(flush: true)

    }

    def cleanup() {
    }

    void "test addDefaultTasks"() {
        when: 'we pass an event to taskService.addDefaultTasks'
        setupData()
        def e = new TekEvent(name:'Test Event',
                city:'TestCity, USA',
                description:'Test Description',
                organizer:TekUser.findByUserName('tester' ),
                venue:'TestCenter' ,
                startDate:new Date(),
                endDate:new Date() + 1)

        service.addDefaultTasks(e)

        then: 'the event will have 6 default tasks'
        e.tasks.size() == 6
    }
}
