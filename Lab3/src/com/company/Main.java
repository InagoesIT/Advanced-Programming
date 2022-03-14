package com.company;

public class Main
{
	public static void main(String[] args)
	{
		// create the nodes from the network
		Node v1 = new Computer("v1", 512, "0035:96FF:FE12:3456");
		Node v2 = new Router("v2", "0025:96FF:FE12:3765");
		Node v3 = new Switch("v3");
		Node v4 = new Switch("v4");
		Node v5 = new Router("v5", "0025:96FF:FE12:AAAB");
		Node v6 = new Computer("v6", 256, "0CD5:96FF:FE12:256B");

		Network network = new Network();
		network.addNode(v1);
		network.addNode(v2);
		network.addNode(v3);
		network.addNode(v4);
		network.addNode(v5);
		network.addNode(v6);

		// creating the links between nodes and setting the costs
		v1.setNodeCost(v2, 10);
		v1.setNodeCost(v3, 50);
		v2.setNodeCost(v3, 20);
		v2.setNodeCost(v4, 20);
		v2.setNodeCost(v5, 10);
		v3.setNodeCost(v4, 20);
		v4.setNodeCost(v5, 30);
		v4.setNodeCost(v6, 10);
		v5.setNodeCost(v6, 20);

		v2.setNodeCost(v1, 10);
		v3.setNodeCost(v1, 50);
		v3.setNodeCost(v2, 20);
		v4.setNodeCost(v2, 20);
		v5.setNodeCost(v2, 10);
		v4.setNodeCost(v3, 20);
		v5.setNodeCost(v4, 30);
		v6.setNodeCost(v4, 10);
		v6.setNodeCost(v5, 20);

		network.printNodes();
		network.printIdentifiableNodes();
		network.displayMinCosts(network.computeMinCosts());
	}
}
