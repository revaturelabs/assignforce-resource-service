
package com.revature.assignforce.beans;

import com.revature.assignforce.validators.IntervalHolder;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "LocationUnavailability")
@IntervalHolder(startInterval="startDate", endInterval="endDate", inclusive=true)
public class LocationUnavailability {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Unavailability_ID")
	@SequenceGenerator(name = "Unavailability_ID", sequenceName = "Unavailability_ID_seq", allocationSize = 1)
	@Column(name = "UNAVAILABLE_ID")
	private int id;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "END_DATE")
	private LocalDate endDate;
	
	@Column(name = "START_DATE")
	private LocalDate startDate;
	

	//The angular side of the application does not accept a Room object so I need to use the @JsonIgnoreProperties for the roomObject field
	// instead what gets sent to the front-end is the room field which holds the primary key of the room that pertains to 
	// the LocationUnavailability
//	@JsonIgnoreProperties
//	@ManyToOne(targetEntity=Room.class,fetch=FetchType.LAZY)
//	@JoinColumn(name = "ROOMS_ID")
//	private Room roomObject;

	
	@Column(name="ROOMS_ID")
	private Integer room;


	public LocationUnavailability(int id, String description, LocalDate endDate, LocalDate startDate, Integer room) {
		super();
		this.id = id;
		this.description = description;
		this.endDate = endDate;
		this.startDate = startDate;
		//this.roomObject = notroom;
		this.room = room;
	}

	public LocationUnavailability() {
		super();
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}


	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}



//	public void setRoomObject(Room room) {
//		this.roomObject = room;
//	}
//
//	public Room getRoomObject(){return this.roomObject;}


	public Integer getRoom() {
		return room;
	}

	public void setRoom(Integer room) {
		this.room = room;
	}

	@Override
	public String toString() {
		return "LocationUnavailability{" +
				"id=" + id +
				", description='" + description + '\'' +
				", endDate=" + endDate +
				", startDate=" + startDate +
//				", roomObject=" + roomObject +
				", room=" + room +
				'}';
	}
}
