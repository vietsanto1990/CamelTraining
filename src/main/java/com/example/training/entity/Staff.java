package com.example.training.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@Entity
@Table(name = "staff")
@CsvRecord(separator = ",", skipFirstLine = true)
@NamedQuery(name = "fetchAllStaff", query = "select s from Staff s")
public class Staff {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@DataField(pos = 1)
	private int agencyid;
	@DataField(pos = 2)
	private int staffid;
	@DataField(pos = 3)
	private String staffagencyid;
	@DataField(pos = 4)
	private int status;
	@DataField(pos = 5)
	private String status2;
	@DataField(pos = 6)
	private String soe;
	@DataField(pos = 7)
	private String eoe;
	@DataField(pos = 8)
	private String rehiredate;
	@DataField(pos = 9)
	private String position;
	@DataField(pos = 10)
	private int officestaff;
	@DataField(pos = 11)
	private int fieldstaff;
	@DataField(pos = 12)
	private int person;
	@DataField(pos = 13)
	private int contractor;
	@DataField(pos = 14)
	private String title;
	@DataField(pos = 15)
	private String firstname;
	@DataField(pos = 16)
	private String middleinitial;
	@DataField(pos = 17)
	private String lastname;
	@DataField(pos = 18)
	private String suffix;
	@DataField(pos = 19)
	private String ethnicity;
	@DataField(pos = 20)
	private String income;
	@DataField(pos = 21)
	private String language;
	@DataField(pos = 22)
	private String marital;
	@DataField(pos = 23)
	private int sex;
	@DataField(pos = 24)
	private int ssn;
	@DataField(pos = 25)
	private String dob;
	@DataField(pos = 26)
	private String dod;
	@DataField(pos = 27)
	private String address;
	@DataField(pos = 28)
	private String address2;
	@DataField(pos = 29)
	private String aptno;
	@DataField(pos = 30)
	private String county;
	@DataField(pos = 31)
	private String city;
	@DataField(pos = 32)
	private String state;
	@DataField(pos = 33)
	private String zip;
	@DataField(pos = 34)
	private Long homephone;
	@DataField(pos = 35)
	private Long workphone;
	@DataField(pos = 36)
	private Long mobilephone;
	@DataField(pos = 37)
	private String fax;
	@DataField(pos = 38)
	private String email;
	@DataField(pos = 39)
	private String api;
	@DataField(pos = 40)
	private int npi;
	@DataField(pos = 41)
	private int otherid1;
	@DataField(pos = 42)
	private int otherid2;
	@DataField(pos = 43, name = "class")
	@Column(name = "class")
	private String classname;
	@DataField(pos = 44)
	private String militarystatus;
	@DataField(pos = 45)
	private String transportation;
	@DataField(pos = 46)
	private int compliant;
	@DataField(pos = 47)
	private String compliantthru;
	@DataField(pos = 48)
	private String homelocation;
	@DataField(pos = 49)
	private int ismanager;
	@DataField(pos = 50)
	private int iscoordinator;
	@DataField(pos = 51)
	private int coordinator;
	@DataField(pos = 52)
	private int manager;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAgencyid() {
		return agencyid;
	}

	public void setAgencyid(int agencyid) {
		this.agencyid = agencyid;
	}

	public int getStaffid() {
		return staffid;
	}

	public void setStaffid(int staffid) {
		this.staffid = staffid;
	}

	public String getStaffagencyid() {
		return staffagencyid;
	}

	public void setStaffagencyid(String staffagencyid) {
		this.staffagencyid = staffagencyid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStatus2() {
		return status2;
	}

	public void setStatus2(String status2) {
		this.status2 = status2;
	}

	public String getSoe() {
		return soe;
	}

	public void setSoe(String soe) {
		this.soe = soe;
	}

	public String getEoe() {
		return eoe;
	}

	public void setEoe(String eoe) {
		this.eoe = eoe;
	}

	public String getRehiredate() {
		return rehiredate;
	}

	public void setRehiredate(String rehiredate) {
		this.rehiredate = rehiredate;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getOfficestaff() {
		return officestaff;
	}

	public void setOfficestaff(int officestaff) {
		this.officestaff = officestaff;
	}

	public int getFieldstaff() {
		return fieldstaff;
	}

	public void setFieldstaff(int fieldstaff) {
		this.fieldstaff = fieldstaff;
	}

	public int getPerson() {
		return person;
	}

	public void setPerson(int person) {
		this.person = person;
	}

	public int getContractor() {
		return contractor;
	}

	public void setContractor(int contractor) {
		this.contractor = contractor;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddleinitial() {
		return middleinitial;
	}

	public void setMiddleinitial(String middleinitial) {
		this.middleinitial = middleinitial;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getEthnicity() {
		return ethnicity;
	}

	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}

	public String getIncome() {
		return income;
	}

	public void setIncome(String income) {
		this.income = income;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getMarital() {
		return marital;
	}

	public void setMarital(String marital) {
		this.marital = marital;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getSsn() {
		return ssn;
	}

	public void setSsn(int ssn) {
		this.ssn = ssn;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getDod() {
		return dod;
	}

	public void setDod(String dod) {
		this.dod = dod;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAptno() {
		return aptno;
	}

	public void setAptno(String aptno) {
		this.aptno = aptno;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getFax() {
		return fax;
	}

	public Long getHomephone() {
		return homephone;
	}

	public void setHomephone(Long homephone) {
		this.homephone = homephone;
	}

	public Long getWorkphone() {
		return workphone;
	}

	public void setWorkphone(Long workphone) {
		this.workphone = workphone;
	}

	public Long getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(Long mobilephone) {
		this.mobilephone = mobilephone;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public int getNpi() {
		return npi;
	}

	public void setNpi(int npi) {
		this.npi = npi;
	}

	public int getOtherid1() {
		return otherid1;
	}

	public void setOtherid1(int otherid1) {
		this.otherid1 = otherid1;
	}

	public int getOtherid2() {
		return otherid2;
	}

	public void setOtherid2(int otherid2) {
		this.otherid2 = otherid2;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getMilitarystatus() {
		return militarystatus;
	}

	public void setMilitarystatus(String militarystatus) {
		this.militarystatus = militarystatus;
	}

	public String getTransportation() {
		return transportation;
	}

	public void setTransportation(String transportation) {
		this.transportation = transportation;
	}

	public int getCompliant() {
		return compliant;
	}

	public void setCompliant(int compliant) {
		this.compliant = compliant;
	}

	public String getCompliantthru() {
		return compliantthru;
	}

	public void setCompliantthru(String compliantthru) {
		this.compliantthru = compliantthru;
	}

	public String getHomelocation() {
		return homelocation;
	}

	public void setHomelocation(String homelocation) {
		this.homelocation = homelocation;
	}

	public int getIsmanager() {
		return ismanager;
	}

	public void setIsmanager(int ismanager) {
		this.ismanager = ismanager;
	}

	public int getIscoordinator() {
		return iscoordinator;
	}

	public void setIscoordinator(int iscoordinator) {
		this.iscoordinator = iscoordinator;
	}

	public int getCoordinator() {
		return coordinator;
	}

	public void setCoordinator(int coordinator) {
		this.coordinator = coordinator;
	}

	public int getManager() {
		return manager;
	}

	public void setManager(int manager) {
		this.manager = manager;
	}
}
