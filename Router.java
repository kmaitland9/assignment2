package assignment2;

import java.util.*;
import java.io.*;

/*****************************************************************************************
 * // class Router // Purpose: This class represents a router Linda Crane,
 * CST8130 // data members: // routingTable - to hold up to MaxEntries routing
 * table entries // numEntries - number of entries currently in the table //
 * maxEntries - maximum number of entries in table // methods: constructor //
 * displayTable - displays results of "show ip route" command on device-ie
 * entries in table // processPackets (Packet) - uses the parameter "packet" -
 * processes it
 ***************************************************************************************/
class Router {
	protected ArrayList<RoutingTableEntry> routingTable;

	// protected RoutingTableEntry[] routingTable;
	// protected int numEntries;
	protected int maxEntries;

	public Router() {
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter number of entries maximum for array: ");
		maxEntries = keyboard.nextInt();

		// routingTable = new RoutingTableEntry[maxEntries];
		routingTable = new ArrayList<RoutingTableEntry>();
		// check this worked***********************************************
		// numEntries = 0;

	}

	public void processPackets(Packet inPacket) {

		boolean found = false;
		String port = null;
		int[] testNum;
		int[] compNum;
		for (int i = 0; i < routingTable.size(); i++) {
			if (routingTable.contains(inPacket.getDestNetwork())) {

			}
			port = String.valueOf(routingTable.indexOf(inPacket
					.getDestNetwork()));
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
			if (routingTable.size() >= maxEntries)
				System.out.println("Table is full.........cannot add "
						+ inPacket.getDestNetwork());
			else {
				if (routingTable.isEmpty()) {
					routingTable.add(new RoutingTableEntry());
					routingTable.get(0).addEntry(inPacket.getDestNetwork(),
							inPacket.getPacketData());
					// routingTable.add(inPacket.getDestNetwork(),
					// inPacket.getPacketData());
				} else {
					for (int q = 0; q < routingTable.size(); q++) {
						testNum = inPacket.getDestNetwork().address;
						compNum = routingTable.get(q).destination.address;
						if (testNum[0] < compNum[0]) {
							routingTable.add(q, new RoutingTableEntry());
							routingTable.get(q).addEntry(
									inPacket.getDestNetwork(),
									inPacket.getPacketData());
						} else if (testNum[1] < compNum[1]) {
							routingTable.add(q, new RoutingTableEntry());
							routingTable.get(q).addEntry(
									inPacket.getDestNetwork(),
									inPacket.getPacketData());
						} else if(testNum[2] < compNum[2]){
							routingTable.add(q, new RoutingTableEntry());
							routingTable.get(q).addEntry(
									inPacket.getDestNetwork(),
									inPacket.getPacketData());
						} else if(testNum[3] < compNum[3]){
							routingTable.add(q, new RoutingTableEntry());
							routingTable.get(q).addEntry(
									inPacket.getDestNetwork(),
									inPacket.getPacketData());
						}
					}

				}

			}
		}
	}

	public void displayTable() {
		System.out.println("\nRouting table...\n");
		for (int i = 0; i < routingTable.size(); i++)
			System.out.println(routingTable.get(i));
	}

};
