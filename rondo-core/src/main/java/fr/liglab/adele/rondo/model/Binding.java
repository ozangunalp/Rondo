//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.04.07 at 03:02:37 PM CEST 
//


package fr.liglab.adele.rondo.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Binding complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Binding">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="dependencyInstance" type="{fr.liglab.adele.rondo.core}Identifier" />
 *       &lt;attribute name="serviceInstance" type="{fr.liglab.adele.rondo.core}Identifier" />
 *       &lt;attribute name="dependency" type="{fr.liglab.adele.rondo.core}Identifier" />
 *       &lt;attribute name="service" type="{fr.liglab.adele.rondo.core}Identifier" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Binding")
public class Binding {

    @XmlAttribute
    protected String dependencyInstance;
    @XmlAttribute
    protected String serviceInstance;
    @XmlAttribute
    protected String dependency;
    @XmlAttribute
    protected String service;

    /**
     * Gets the value of the dependencyInstance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDependencyInstance() {
        return dependencyInstance;
    }

    /**
     * Sets the value of the dependencyInstance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDependencyInstance(String value) {
        this.dependencyInstance = value;
    }

    /**
     * Gets the value of the serviceInstance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceInstance() {
        return serviceInstance;
    }

    /**
     * Sets the value of the serviceInstance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceInstance(String value) {
        this.serviceInstance = value;
    }

    /**
     * Gets the value of the dependency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDependency() {
        return dependency;
    }

    /**
     * Sets the value of the dependency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDependency(String value) {
        this.dependency = value;
    }

    /**
     * Gets the value of the service property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getService() {
        return service;
    }

    /**
     * Sets the value of the service property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setService(String value) {
        this.service = value;
    }

}
