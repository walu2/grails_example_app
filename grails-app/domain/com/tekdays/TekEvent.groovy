package com.tekdays

class TekEvent {
	
	String city
	String name
	TekUser organizer
	String venue
	Date startDate
	Date endDate
	String description
	
	static hasMany = [volunteers: TekUser, 
					  respondents : String, 
					  sponsorships : Sponsorship,
					  tasks : Task,
					  messags : TekMessage]
	
	
	String toString() { 
		"$name, $city"
	}

    static constraints = {
		name()
		city()
		description maxSize: 5000
		organizer()
		venue()
		startDate()
		endDate()
		volunteers nullable: true
		sponsorships nullable: true
		tasks nullable: true
		messages nullable: true
    }
}
