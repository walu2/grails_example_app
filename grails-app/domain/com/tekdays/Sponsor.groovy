package com.tekdays

class Sponsor {
	String name
	String website
	String description
	byte[] logo
	
	static constraints = {
		name blank: false
		website blank: false, url: true
		description nullable: true, maxSize: 5000
		logo nullable: true, maxSize: 1000000
		sponsorships nullable: true
	}
	
	static hasMany = [sponsorships : Sponsorship]
	static belongsTo = TekEvent

	String toString(){
		name
	}
}
