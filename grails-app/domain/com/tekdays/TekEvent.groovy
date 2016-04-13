package com.tekdays

class TekEvent {

	String city
	String name
	TekUser organizer
	String venue
	Date startDate
	Date endDate
	String description

	static hasMany = [volunteers : TekUser,
					  respondents : String,
					  sponsorships : Sponsorship,
					  tasks : Task,
					  messages : TekMessage]

    static constraints = {
        name blank: false
        city()
        description nullable: true, widget: 'textarea', maxSize: 5000
        organizer()
        venue nullable: true
        startDate nullable: true
        endDate nullable: true
        volunteers nullable: true
        sponsorships nullable: true
        tasks nullable: true
        messages nullable: true
    }

	String toString(){
		"$name, $city"
	}
}
