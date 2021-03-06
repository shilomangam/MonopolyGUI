//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.04.27 at 04:07:17 PM IDT 
//


package com.monopoly.scheme;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for cityType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="cityType">
 *   &lt;complexContent>
 *     &lt;extension base="{}assetType">
 *       &lt;attribute name="houseCost" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *       &lt;attribute name="stayCost1" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *       &lt;attribute name="stayCost2" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *       &lt;attribute name="stayCost3" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cityType")
public class CityType
    extends AssetType
{

    @XmlAttribute(name = "houseCost", required = true)
    @XmlSchemaType(name = "unsignedInt")
    protected long houseCost;
    @XmlAttribute(name = "stayCost1", required = true)
    @XmlSchemaType(name = "unsignedInt")
    protected long stayCost1;
    @XmlAttribute(name = "stayCost2", required = true)
    @XmlSchemaType(name = "unsignedInt")
    protected long stayCost2;
    @XmlAttribute(name = "stayCost3", required = true)
    @XmlSchemaType(name = "unsignedInt")
    protected long stayCost3;

    /**
     * Gets the value of the houseCost property.
     * 
     */
    public long getHouseCost() {
        return houseCost;
    }

    /**
     * Sets the value of the houseCost property.
     * 
     */
    public void setHouseCost(long value) {
        this.houseCost = value;
    }

    /**
     * Gets the value of the stayCost1 property.
     * 
     */
    public long getStayCost1() {
        return stayCost1;
    }

    /**
     * Sets the value of the stayCost1 property.
     * 
     */
    public void setStayCost1(long value) {
        this.stayCost1 = value;
    }

    /**
     * Gets the value of the stayCost2 property.
     * 
     */
    public long getStayCost2() {
        return stayCost2;
    }

    /**
     * Sets the value of the stayCost2 property.
     * 
     */
    public void setStayCost2(long value) {
        this.stayCost2 = value;
    }

    /**
     * Gets the value of the stayCost3 property.
     * 
     */
    public long getStayCost3() {
        return stayCost3;
    }

    /**
     * Sets the value of the stayCost3 property.
     * 
     */
    public void setStayCost3(long value) {
        this.stayCost3 = value;
    }

}
