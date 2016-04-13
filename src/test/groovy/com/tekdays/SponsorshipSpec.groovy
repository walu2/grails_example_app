package com.tekdays

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

@TestFor(Sponsorship)
@Mock([TekEvent, Sponsor])
class SponsorshipSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "sponsorship is valid"(){
        when: "sponsorship has no properties"
        def s = new Sponsorship()

        then: "validation should fail"
        !s.validate()
        s.hasErrors()
        s.errors.errorCount == 3
        s.errors['sponsor'].code == 'nullable'
        s.errors['event'].code == 'nullable'
        s.errors['contributionType'].code == 'nullable'

        when: "an unsupported contributionType is provided"
        s = new Sponsorship(    sponsor: new Sponsor(),
                                event: new TekEvent(),
                                contributionType: 'unsupported')

        then: "validation should fail"
        !s.validate()
        s.errors.errorCount == 1
        s.errors['contributionType'].code == 'not.inList'

        when: "sponsor, event and contributionType are provided correctly"
        s = new Sponsorship(    sponsor: new Sponsor(),
                                event: new TekEvent(),
                                contributionType: 'Other')

        then: "validation should pass"
        s.validate()

    }

    @Unroll
    void "#sponsor, #event, #contributionType is #result" (Sponsor sponsor, TekEvent event,
                                                           String contributionType,
                                                           boolean valid, String result){
        expect:
        new Sponsorship(sponsor: sponsor, event: event,
                        contributionType: contributionType).validate() == valid

        where:
        [sponsor, event, contributionType] <<
                [[new Sponsor(name: 'sponsor'), null],
                 [new TekEvent(name: 'event', city: 'city'), null],
                 ["Other", "Venue", "A/V", "Promotion", "Cash", " "]].combinations()

        valid = sponsor != null && event != null && ["Other", "Venue", "A/V", "Promotion", "Cash"].contains(contributionType)
        result = valid ? 'valid' : 'NOT valid'

    }
}
