"""
Abstractions and default implementations of the
pyvaria neural network system in python. The final
superclass. All client and private networks will
inherit from the Network class.
"""

class Network():
    __slots__ = 'neurons',

    def __eq__(self, n) -> bool:
        return isinstance(n, Network) and all(n1 == n2 for n1, n2 in zip(self.neurons, n.neurons))
