package assignment2;

import java.util.*;
import java.io.*;

/*****************************************************************************************
 * // class Router 
 * // Purpose: This class represents a router 
 * Linda Crane,   CST8130
 * // data members:
 * //  routingTable - to hold up to MaxEntries routing table entries 
 * //  numEntries - number of entries currently in the table  
 * //  maxEntries - maximum number of entries  in table 
 * // methods: constructor 
 * // displayTable - displays results of "show ip route" command on device-ie entries in table 
 * // processPackets (Packet) - uses the parameter "packet" - processes it    
 ***************************************************************************************/
class Router {
	protected RoutingTableEntry[] routingTable;
	protected int numEntries;
	protected int maxEntries;

	public Router() {
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter number of entries maximum for array: ");
		maxEntries = keyboard.nextInt();

		routingTable = new RoutingTableEntry[maxEntries];
		// check this worked***********************************************
		numEntries = 0;

	}
	
	

	public void processPackets(Packet inPacket) {

		boolean found = false;
		String port = null;
		for (int i = 0; i < numEntries; i++) {
			port = routingTable[i].searchForPort(inPacket.getDestNetwork());
			if (port != null) {
				found = true;
				break;
			}
		}
		boolean addToTable = false;
		if (found)
			addToTable = inPacket.processFoundPacket(port);
		else
			addToTable = inPacket.processNotFoundPacket(port);

		if (addToTable) {
			if (numEntries >= maxEntries)
				System.out.println("Table is full.........cannot add " + inPacket.getDestNetwork());
			else {
				routingTable[numEntries] = new RoutingTableEntry();
				routingTable[numEntries++].addEntry(inPacket.getDestNetwork(),
						inPacket.getPacketData());
			}
		}
	}

	public void displayTable() {
		System.out.println("\nRouting table...\n");
		for (int i = 0; i < numEntries; i++)
			System.out.println(routingTable[i]);
	}
};
