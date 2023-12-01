package spold2;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlTransient;

@XmlTransient
public abstract class MasterDataList {

	@XmlAttribute
	public int majorRelease;

	@XmlAttribute
	public int minorRelease;

	@XmlAttribute
	public int majorRevision;

	@XmlAttribute
	public int minorRevision;

	@XmlAttribute
	public String contextId;

}
