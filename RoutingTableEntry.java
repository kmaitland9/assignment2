package assignment2;

/*****************************************************************************************
 * // class RoutingTableEntry
 * Linda Crane,   CST8130    
 * // Purpose: This class represents an entry in the  routing table 
 * // data members: destination - an IP address of the destination network 
 * // portCode - and description of port to go out 
 * // methods:
 * // constructor 
 * // toString - displays results of "show" command on device to a String 
 * // addEntry - adds entry into the table based on parameters passed in
 * // searchForPort - searches table for destination network and returns port to
 * send out 
 ***************************************************************************************/
class RoutingTableEntry {
	protected IPAddress destination = new IPAddress();
	protected String portCode = new String();

	public RoutingTableEntry() {
	}

	public void addEntry(IPAddress dest, String PC) {
		destination.initialize(dest);
		portCode = PC;
	}

	public String toString() {
		String out = new String();
		out += destination.toString() + " Port: " + portCode;
		return out;
	}

	public String searchForPort(IPAddress dest) {
		if (destination.isEqual(dest))
			return portCode;
		else
			return null;
	}
}