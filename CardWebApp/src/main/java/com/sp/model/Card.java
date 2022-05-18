package com.sp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "card")
public class Card {
	@Id
	@GeneratedValue
	private Integer c_id;
	private String name;
	private String description;
	private String family;
	private String affinity;
	private String imgUrl;
	private String smallImgUrl;
	private Integer energy;
	private Integer hp;
	private Integer defence;
	private Integer attack;
	private Integer price;
	
	private Integer idUser;
	
	
	public Card() {
		
	}
	
	public Card(Integer c_id, String name, String description, String family, String affinity, String imgUrl,
			String smallImgUrl, Integer energy, Integer hp, Integer defence, Integer attack, Integer price,
			Integer iduser) {
		super();
		this.c_id = c_id;
		this.name = name;
		this.description = description;
		this.family = family;
		this.affinity = affinity;
		this.imgUrl = imgUrl;
		this.smallImgUrl = smallImgUrl;
		this.energy = energy;
		this.hp = hp;
		this.defence = defence;
		this.attack = attack;
		this.price = price;
		this.idUser = idUser;
	}

	public Integer getId() {
		return c_id;
	}

	public void setId(Integer id) {
		this.c_id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getAffinity() {
		return affinity;
	}

	public void setAffinity(String affinity) {
		this.affinity = affinity;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getSmallImgUrl() {
		return smallImgUrl;
	}

	public void setSmallImgUrl(String smallImgUrl) {
		this.smallImgUrl = smallImgUrl;
	}

	public Integer getEnergy() {
		return energy;
	}

	public void setEnergy(Integer energy) {
		this.energy = energy;
	}

	public Integer getHp() {
		return hp;
	}

	public void setHp(Integer hp) {
		this.hp = hp;
	}

	public Integer getDefence() {
		return defence;
	}

	public void setDefence(Integer defence) {
		this.defence = defence;
	}

	public Integer getAttack() {
		return attack;
	}

	public void setAttack(Integer attack) {
		this.attack = attack;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setUser(Integer idUser) {
		this.idUser = idUser;
	}

	@Override
	public String toString() {
		return "Card [id=" + c_id + ", name=" + name + ", description=" + description + ", family=" + family
				+ ", affinity=" + affinity + ", imgUrl=" + imgUrl + ", smallImgUrl=" + smallImgUrl + ", energy="
				+ energy + ", hp=" + hp + ", defence=" + defence + ", attack=" + attack + ", price=" + price
				+ ", user=" + idUser + "]";
	}
	
}