//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.07.02 at 10:16:47 AM IST 
//


package com.bcd.bdu.model.bookingdata.db;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Rail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Rail">
 *   &lt;complexContent>
 *     &lt;extension base="{}Segment">
 *       &lt;sequence>
 *         &lt;element name="bookingSiteConfNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bookingSiteURL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Departure" type="{}RailInfo" minOccurs="0"/>
 *         &lt;element name="Arrival" type="{}RailInfo" minOccurs="0"/>
 *         &lt;element name="CarrierInfo" type="{}Carrier" minOccurs="0"/>
 *         &lt;element name="Fare" type="{}Fare" minOccurs="0"/>
 *         &lt;element name="numberInParty" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="calculatedDuration" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="seats" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="classOfService" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Status" type="{}Status" minOccurs="0"/>
 *         &lt;element name="isPassive" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="segmentRemarks" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="notes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Rail", propOrder = {
    "bookingSiteConfNum",
    "bookingSiteURL",
    "departure",
    "arrival",
    "carrierInfo",
    "fare",
    "numberInParty",
    "calculatedDuration",
    "seats",
    "classOfService",
    "status",
    "isPassive",
    "segmentRemarks",
    "notes"
})
public class Rail
    extends Segment
{

    protected String bookingSiteConfNum;
    protected String bookingSiteURL;
    @XmlElement(name = "Departure")
    protected RailInfo departure;
    @XmlElement(name = "Arrival")
    protected RailInfo arrival;
    @XmlElement(name = "CarrierInfo")
    protected Carrier carrierInfo;
    @XmlElement(name = "Fare")
    protected Fare fare;
    protected String numberInParty;
    protected String calculatedDuration;
    protected String seats;
    protected String classOfService;
    @XmlElement(name = "Status")
    protected Status status;
    protected String isPassive;
    protected String segmentRemarks;
    protected String notes;

    /**
     * Gets the value of the bookingSiteConfNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBookingSiteConfNum() {
        return bookingSiteConfNum;
    }

    /**
     * Sets the value of the bookingSiteConfNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBookingSiteConfNum(String value) {
        this.bookingSiteConfNum = value;
    }

    /**
     * Gets the value of the bookingSiteURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBookingSiteURL() {
        return bookingSiteURL;
    }

    /**
     * Sets the value of the bookingSiteURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBookingSiteURL(String value) {
        this.bookingSiteURL = value;
    }

    /**
     * Gets the value of the departure property.
     * 
     * @return
     *     possible object is
     *     {@link RailInfo }
     *     
     */
    public RailInfo getDeparture() {
        return departure;
    }

    /**
     * Sets the value of the departure property.
     * 
     * @param value
     *     allowed object is
     *     {@link RailInfo }
     *     
     */
    public void setDeparture(RailInfo value) {
        this.departure = value;
    }

    /**
     * Gets the value of the arrival property.
     * 
     * @return
     *     possible object is
     *     {@link RailInfo }
     *     
     */
    public RailInfo getArrival() {
        return arrival;
    }

    /**
     * Sets the value of the arrival property.
     * 
     * @param value
     *     allowed object is
     *     {@link RailInfo }
     *     
     */
    public void setArrival(RailInfo value) {
        this.arrival = value;
    }

    /**
     * Gets the value of the carrierInfo property.
     * 
     * @return
     *     possible object is
     *     {@link Carrier }
     *     
     */
    public Carrier getCarrierInfo() {
        return carrierInfo;
    }

    /**
     * Sets the value of the carrierInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Carrier }
     *     
     */
    public void setCarrierInfo(Carrier value) {
        this.carrierInfo = value;
    }

    /**
     * Gets the value of the fare property.
     * 
     * @return
     *     possible object is
     *     {@link Fare }
     *     
     */
    public Fare getFare() {
        return fare;
    }

    /**
     * Sets the value of the fare property.
     * 
     * @param value
     *     allowed object is
     *     {@link Fare }
     *     
     */
    public void setFare(Fare value) {
        this.fare = value;
    }

    /**
     * Gets the value of the numberInParty property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumberInParty() {
        return numberInParty;
    }

    /**
     * Sets the value of the numberInParty property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumberInParty(String value) {
        this.numberInParty = value;
    }

    /**
     * Gets the value of the calculatedDuration property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCalculatedDuration() {
        return calculatedDuration;
    }

    /**
     * Sets the value of the calculatedDuration property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCalculatedDuration(String value) {
        this.calculatedDuration = value;
    }

    /**
     * Gets the value of the seats property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeats() {
        return seats;
    }

    /**
     * Sets the value of the seats property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeats(String value) {
        this.seats = value;
    }

    /**
     * Gets the value of the classOfService property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClassOfService() {
        return classOfService;
    }

    /**
     * Sets the value of the classOfService property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClassOfService(String value) {
        this.classOfService = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link Status }
     *     
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link Status }
     *     
     */
    public void setStatus(Status value) {
        this.status = value;
    }

    /**
     * Gets the value of the isPassive property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsPassive() {
        return isPassive;
    }

    /**
     * Sets the value of the isPassive property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsPassive(String value) {
        this.isPassive = value;
    }

    /**
     * Gets the value of the segmentRemarks property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSegmentRemarks() {
        return segmentRemarks;
    }

    /**
     * Sets the value of the segmentRemarks property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSegmentRemarks(String value) {
        this.segmentRemarks = value;
    }

    /**
     * Gets the value of the notes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Sets the value of the notes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotes(String value) {
        this.notes = value;
    }

}
