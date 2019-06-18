package com.revature.assignforce.beans;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
@Table(name = "CERTS")
public class Cert {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cert")
	@SequenceGenerator(name = "cert", sequenceName = "cert_seq", allocationSize = 1)
	@Column(name = "CERT_ID")
	private int id;

	@Column(name = "CERT_NAME")
	private String name;

	public Cert() {

	}

	public Cert(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
