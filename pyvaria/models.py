from .abc import network

class ConnectedNetwork(network.Network):
    def __init__(self, NeuronClass, nodes):
        self.neurons = []
        for node1, node2 in zip(nodes, nodes):
            if node1 != node2:
                self.neurons.append(NeuronClass(node1, node2))