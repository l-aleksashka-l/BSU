import numpy as numpy


class Node(object):
    def __init__(self, x):
        self.data = x
        self.left = None
        self.right = None


class Tree(object):

    def __init__(self):
        self.root = None

    def insert(self, x):
        if self.root is None:
            self.root = Node(x)
            return
        if x < self.root.data:
            if self.root.left is None:
                self.root.left = Tree()
            self.root.left.insert(x)
        elif x > self.root.data:
            if self.root.right is None:
                self.root.right = Tree()
            self.root.right.insert(x)

    def print(self, f):
        if self.root is None:
            return
        f.write(str(self.root.data)+'\n')
        if self.root.left:
            self.root.left.print(f)
        if self.root.right:
            self.root.right.print(f)


T = Tree()
data = numpy.loadtxt("input.txt",  dtype=int)
for x in data:
    T.insert(x)

f = open('output.txt', 'w')

T.print(f)
