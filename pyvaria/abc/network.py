"""
Abstractions and default implementations of the
pyvaria neural network system in python. The final
superclass. All client and private networks will
inherit from the Network class.
"""

class NetworkMeta(type):
    def __new__(cls, name, bases, namespace, *, _root=False):
        if not _root:
            raise TypeError("Cannot subclass %s" %
                            (', '.join(map(repr, bases)) or '()'))
        return super().__new__(cls, name, bases, namespace)

    def __init__(self, *args, **kwds):
        pass

    def _eval_type(self, globalns, localns):
        return self

    def _get_type_vars(self, tvars):
        pass

    def __call__(self, *args, **kwds):
        raise TypeError("Cannot instantiate %r" % type(self))

class Network(metaclass=NetworkMeta, _root=True):
    __slots__ = 'neurons',

    def __eq__(self, n) -> bool:
        return isinstance(n, Network) and all(n1 == n2 for n1, n2 in zip(self.neurons, n.neurons))

